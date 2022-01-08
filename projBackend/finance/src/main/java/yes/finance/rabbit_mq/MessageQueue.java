package yes.finance.rabbit_mq;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.annotation.PostConstruct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class MessageQueue {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    private Channel channel;
    
    @PostConstruct
    public void init() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(System.getenv("FINANCE_RABBITMQ_HOST"));

        try {
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
            
            channel.queueDeclare("Tickers", true, false, false, null);
            channel.queueDeclare("Currencies", true, false, false, null);
            channel.queueDeclare("Markets", true, false, false, null);

            channel.basicConsume("Tickers", false, this::tickersReceiver, consumerTag -> {});
            channel.basicConsume("Currencies", false, this::currenciesReceiver, consumerTag -> {});
            channel.basicConsume("Markets", false, this::marketReceiver, consumerTag -> {});
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void tickersReceiver(String tag, Delivery delivery) {
        notify(delivery, MQChannels.Tickers);
    }

    public void currenciesReceiver(String tag, Delivery delivery) {
        notify(delivery, MQChannels.Currencies);
    }

    public void marketReceiver(String tag, Delivery delivery) {
        notify(delivery, MQChannels.Markets);
    }

    public void notify(Delivery delivery, MQChannels channel) {
        try {
            try {
                String data = new String(delivery.getBody(), "UTF-8");
                
                applicationEventPublisher.publishEvent(new MessageEvent(this, channel, data));
                this.channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            } catch (UnsupportedEncodingException e) {
                this.channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
    