package yes.finance.rabbit_mq;

import org.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import yes.finance.repository.*;
import yes.finance.exception.ResourceNotFoundException;
import yes.finance.model.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Tickers
 * Orders
 * usadas uma vez para registo:
 * currencies
 * markets 
 */


public class MessageQueue {
    
    @Autowired
    public CurrencyRepository currencyRepository;

    @Autowired
    public ExtensionRepository extensionRepository;

    @Autowired
    public MarketRepository marketRepository;

    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public PortfolioRepository portfolioRepository;

    @Autowired
    public TransactionRepository transactionRepository;

    @Autowired
    public UserRepository userRepository;

    // Queues:

    @RabbitListener(queues = "Tickers")
    public void tickersReceiver(String input) throws InterruptedException, ResourceNotFoundException {
        handler(input, 1);
    }

    @RabbitListener(queues = "Orders")
    public void ordersReceiver(String input) throws InterruptedException, ResourceNotFoundException {
        handler(input, 2);
    }

    @RabbitListener(queues = "currencies")
    public void currenciesReceiver(String input) throws InterruptedException, ResourceNotFoundException {
        handler(input, 3);
    }

    @RabbitListener(queues = "markets")
    public void marketReceiver(String input) throws InterruptedException, ResourceNotFoundException {
        handler(input, 4);
    }

    public void handler(String input, int operation) {
        
        JSONObject data = new JSONObject(input);

        switch (operation) {
            case 1:
                //tickers
                break;
            case 2:
                //orders
                break;
            case 3:
                //currencies
                break;

            case 4:
                //markets
                break;
            default:
                break;

        }

    }

}
    