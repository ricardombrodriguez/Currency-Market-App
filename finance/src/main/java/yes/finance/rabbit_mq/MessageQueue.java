package yes.finance.rabbit_mq;

import org.hibernate.internal.util.MarkerObject;
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
                // vão dar informação a cada x minutos sobre um mercado
                break;

            case 2:
                //orders
                Float quantity = data.getFloat("quantity");
                Float value = data.getFloat("value");
                Order order = new Order(quantity,value);

                break;

            case 3:

                String name = data.getString("name");
                String symbol = data.getString("symbol");
                String logoUrl = data.getString("logoUrl");
                Byte online = (Byte) data.get("online");
                Currency currency = new Currency(name,symbol,logoUrl,online);

                break;

            case 4:
                
                // infomração base sobre um mercado
                Market market = new Market();
                break;

            default:
                break;

        }
    }
}
    