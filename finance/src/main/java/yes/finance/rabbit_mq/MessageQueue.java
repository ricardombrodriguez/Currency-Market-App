package yes.finance.rabbit_mq;

import java.util.ArrayList;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import yes.finance.exception.ResourceNotFoundException;

public class MessageQueue {

    private ArrayList<Notificable> subscribers = new ArrayList<Notificable>();
    
    private DatabasePersistance databasePersistance = new DatabasePersistance();

    // Queues:

    @RabbitListener(queues = "Tickers")
    public void tickersReceiver(String input) throws InterruptedException, ResourceNotFoundException {
        databasePersistance.notification(input, MQChannels.Tickers);
    }

    @RabbitListener(queues = "Orders")
    public void ordersReceiver(String input) throws InterruptedException, ResourceNotFoundException {
        databasePersistance.notification(input, MQChannels.Orders);
    }

    @RabbitListener(queues = "currencies")
    public void currenciesReceiver(String input) throws InterruptedException, ResourceNotFoundException {
        databasePersistance.notification(input, MQChannels.currencies);
    }

    @RabbitListener(queues = "markets")
    public void marketReceiver(String input) throws InterruptedException, ResourceNotFoundException {
        databasePersistance.notification(input, MQChannels.markets);
    }

    public void subscribe(Notificable notificable) {

    }

}
    