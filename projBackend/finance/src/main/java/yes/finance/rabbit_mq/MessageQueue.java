package yes.finance.rabbit_mq;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Delivery;

public class MessageQueue {


    static private MessageQueue instance;
    
    private MessageQueue() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(System.getenv("FINANCE_RABBITMQ_HOST"));

        try {

            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            
            channel.queueDeclare("Tickers", false, false, false, null);
            channel.queueDeclare("Currencies", false, false, false, null);
            channel.queueDeclare("Markets", false, false, false, null);

            channel.basicConsume("Tickers", true, this::tickersReceiver, consumerTag -> {});
            channel.basicConsume("Currencies", true, this::currenciesReceiver, consumerTag -> {});
            channel.basicConsume("Markets", true, this::marketReceiver, consumerTag -> {});
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private ArrayList<Notificable> subscribers = new ArrayList<Notificable>();

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
            String data = new String(delivery.getBody(), "UTF-8");
            for (Notificable subscriber : subscribers)
                subscriber.notification(data, channel);
        } catch (UnsupportedEncodingException e) {}
    }

    public void subscribe(Notificable notificable) {
        subscribers.add(notificable);
    }

    static public MessageQueue getInstance() {
        try {
            if (instance == null) instance = new MessageQueue();
        } 
        catch (Exception e) {
            System.out.println("erro no singleton");
            System.err.println(e.getStackTrace());
        }
        return instance;
    }

}
    