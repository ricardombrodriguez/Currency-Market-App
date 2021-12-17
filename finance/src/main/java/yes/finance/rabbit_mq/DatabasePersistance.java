package yes.finance.rabbit_mq;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import yes.finance.repository.CurrencyRepository;
import yes.finance.repository.ExtensionRepository;
import yes.finance.repository.MarketRepository;
import yes.finance.repository.OrderRepository;
import yes.finance.repository.PortfolioRepository;
import yes.finance.repository.TransactionRepository;
import yes.finance.repository.UserRepository;

public class DatabasePersistance implements Notificable {

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

    @Autowired
    public TickerRepository tickerRepository;


    @Override
    public void notification(String input, MQChannels channel) {
        // TODO Auto-generated method stub
        
        JSONObject data = new JSONObject(input);

        switch (channel) {
            case Tickers:
                // mudar parametros, estão mal
                Float prevValue = data.getFloat("prevValue");
                Float maxBuyerValue = data.getFloat("maxBuyerValue");
                Float minSellerValue = data.getFloat("minSellerValue");
                Ticker ticker = new Ticker(prevValue, maxBuyerValue, minSellerValue);
                tickerRepository.save(ticker);
                break;

            case Orders:
                Float quantity = data.getFloat("quantity");
                Float value = data.getFloat("value");
                Order order = new Order(quantity,value);    
                orderRepository.save(order);
                break;

            case currencies:
                String name = data.getString("name");
                String symbol = data.getString("symbol");
                String logoUrl = data.getString("logoUrl");
                Byte online = (Byte) data.get("online");
                Currency currency = new Currency(name,symbol,logoUrl,online);
                currencyRepository.save(currency);
                break;

            case markets:
                Market market = new Market();
                marketRepository.save(market);
                break;

            default:
                break;

        }

    }
    
}
