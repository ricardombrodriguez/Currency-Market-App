package yes.finance.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import yes.finance.model.*;
import yes.finance.model.Currency;
import yes.finance.services.*;

@CrossOrigin
@RestController
public class FinanceController {
    
    @Autowired
    private UserService service;
    @Autowired
    private CurrencyService currencyservice;
    @Autowired
    private ExtensionService extensionservice;
    @Autowired
    private MarketService marketservice;
    @Autowired
    private OrderService orderservice;
    @Autowired
    private TransactionService transactionservice;
    @Autowired
    private PortfolioService portfolioservice;
    @Autowired
    private TickerService tickerservice;


    ////////////////////////////////////////////  USER  ////////////////////////////////////////////

    @GetMapping("/user")
    public Page<User> getAllUsers(Pageable pageable) {
        return service.getUsers(pageable);
    }

    @PostMapping("/user")
    public User createUsers(@RequestBody User user){
        return service.saveUser(user);
    }

    @DeleteMapping("/user/{id}")
    public String deleteUsers(@PathVariable int id) {
        return service.deleteUser(id);
    }


    ////////////////////////////////////////////  CURRENCY  ////////////////////////////////////////////

    @GetMapping("/currency")
    public Page<Currency> getAllCurrencies(Pageable pageable) {
        return currencyservice.getCurrencies(pageable);
    }

    @PostMapping("/currency")
    public Currency createCurrencies(@RequestBody Currency currency){
        return currencyservice.saveCurrency(currency);
    }

    @DeleteMapping("/currency/{id}")
    public String deleteCurrency(@PathVariable int id) {
        return currencyservice.deleteCurrency(id);
    }

    
    /* @GetMapping("/currency/{id}")
    public List<Market> getCurrenciesById(@PathVariable(value = "id") int currencyId) {

        List<Market> markets_by_currency = new ArrayList<>();
        List<Market>  markets = marketservice.getMarkets();

        for (Market market : markets) {
            if (market.getOrigin_currency().getId() == currencyId) 
                markets_by_currency.add( market );
        }

        return markets_by_currency;
    }     */


    ////////////////////////////////////////////  EXTENSION  ////////////////////////////////////////////

    @GetMapping("/extension")
    public Page<Extension> getAllExtensions(Pageable pageable) {
        return extensionservice.getExtensions(pageable);
    }

    @PostMapping("/extension")
    public Extension createExtensions(@RequestBody Extension extension){
        return extensionservice.saveExtension(extension);
    }

    @DeleteMapping("/extension/{id}")
    public String deleteExtensions(@PathVariable int id) {
        return extensionservice.deleteExtension(id);
    }


    ////////////////////////////////////////////  PORTFOLIO  ////////////////////////////////////////////

    @GetMapping("/portfolio")
    public Page<Portfolio> getAllPortfolios(Pageable pageable) {
        return portfolioservice.getPortfolios(pageable);
    }

    @PostMapping("/portfolio")
    public Portfolio createPortfolios(@RequestBody Portfolio portfolio){
        return portfolioservice.savePortfolio(portfolio);
    }
    
    @DeleteMapping("/portfolio/{id}")
    public String deletePortfolios(@PathVariable int id) {
        return portfolioservice.deletePortfolio(id);
    }

    /* 
    @PostMapping("/portfolios")
    public Portfolio createPortfolio(@RequestBody Portfolio portfolio){
        Portfolio p = new Portfolio();
        String name = "nome"; // ir buscar o input do user 
        p.setName(name);
        p.setPublic_key(); // o que é suposto ser a public_key? um numero random grande?
        return p;
    } 
    */

    ////////////////////////////////////////////  MARKET  ////////////////////////////////////////////

    @PostMapping("/market")
    public Market createMarkets(@RequestBody Market market){
        return marketservice.saveMarket(market);
    }

    @GetMapping("/market")
    public Page<Market> getAllMarkets(Pageable pageable) {
        return marketservice.getMarkets(pageable);
    }

    
/*     /* @GetMapping("/markets2")
    public List<Float> getPrice() {
        /* List<Market>  markets = marketservice.getMarkets();
        List<Float> prices = new ArrayList<>();

        for (Market market : markets) {
            Ticker last_ticker = market.getTickers().get(0);
            prices.add( last_ticker.getPrev_value() );
        }

        return prices;
        List<Float> prices = new ArrayList<>();

        List<Market>  markets = marketservice.getMarkets();

        for (Market market : markets) {
            List<Ticker> tickers = tickerservice.getTickersByMarketId();
            Collections.sort(tickers, new CustomComparator());
            Ticker last_ticker = tickers.get(0);
            prices.add( last_ticker.getPrev_value() );
        }

        return prices;

    }   */

    public class CustomComparator implements Comparator<Ticker> {
        @Override
        public int compare(Ticker o1, Ticker o2) {
            return o1.getCreated_at().compareTo(o2.getCreated_at());
        }
    }


    ////////////////////////////////////////////  ORDER  ////////////////////////////////////////////

    @GetMapping("/order")
    public Page<Order> getAllOrders(Pageable pageable) {
        return orderservice.getOrders(pageable);
    }

    @PostMapping("/order")
    public Order createOrders(@RequestBody Order order) {
        return orderservice.saveOrder(order);
    }

    @DeleteMapping("/order/{id}")
    public String deleteOrder(@PathVariable int id) {
        return orderservice.deleteOrder(id);
    }


    ////////////////////////////////////////////  TRANSACTION  ////////////////////////////////////////////

    @GetMapping("/transaction")
    public Page<Transaction> getAllTransactions(Pageable pageable) {
        return transactionservice.getTransactions(pageable);
    }

    @PostMapping("/transaction")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionservice.saveTransaction(transaction);
    }

    @DeleteMapping("/transaction/{id}")
    public String deleteTransaction(@PathVariable int id) {
        return transactionservice.deleteTransaction(id);
    }


    ////////////////////////////////////////////  TICKER  ////////////////////////////////////////////

    @GetMapping("/ticker")
    public Page<Ticker> getAllTickers(Pageable pageable) {
        return tickerservice.getTickers(pageable);
    }

    @PostMapping("/ticker")
    public Ticker createTicker(@RequestBody Ticker ticker) {
        return tickerservice.saveTicker(ticker);
    }

    @DeleteMapping("/ticker/{id}")
    public String deleteTicker(@PathVariable int id) {
        return tickerservice.deleteTicker(id);
    }

    // EndPoint para os gráficos
    @GetMapping("/currency/{id1}/market/{id2}")
    public List<Ticker> getTickersByMarketId(@PathVariable(value = "id1") int currencyId, @PathVariable(value = "id2") int marketId) {
        /* Market market = marketservice.getMarketById(marketId);        
        return market.getTickers(); */
        List<Ticker> tickersByMarket = new ArrayList<>();
        List<Ticker> tickers = tickerservice.getTickers();
        for (Ticker t : tickers) {
            if (t.getMarketId() == marketId) {
                tickersByMarket.add( t );
            }
        }
        return tickersByMarket;
    }   

}