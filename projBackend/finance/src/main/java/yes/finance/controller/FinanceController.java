package yes.finance.controller;

import java.security.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.tomcat.util.digester.SystemPropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;

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

    // obter info sobre a moeda (nome, symbol e isso)
    @GetMapping("/currency/info/{id}")
    public Currency getCurrencyInfo(@PathVariable int id) {
        return currencyservice.getCurrencyById(id);
    }

    @GetMapping("/currency")
    public Page<Currency> getAllCurrencies(Pageable pageable) {
        return currencyservice.getCurrencies(pageable);
    }

    @PostMapping("/currency")
    public Currency createCurrencies(@RequestBody Currency currency){
        return currencyservice.saveCurrency(currency);
    }

    @DeleteMapping("/currency/del/{id}")
    public String deleteCurrency(@PathVariable int id) {
        return currencyservice.deleteCurrency(id);
    }

    
    @GetMapping("/currency/{id}")
    public Page<Market> getMarketsByCurrencyId(@PathVariable(value = "id") int currencyId) {

        List<Market> markets_by_currency = new ArrayList<>();
        List<Market>  markets = marketservice.getMarkets();

        for (Market market : markets) {
            if (market.getOriginCurrency().getId() == currencyId){

                List<Ticker> tickers = tickerservice.getTickersbyMarketID( market.getId() );
            
                if (!tickers.isEmpty()) {

                    Ticker last_ticker = tickers.get(0);
                    System.out.println("last ticker");
                    System.out.println(last_ticker);
                    market.setPrice( last_ticker.getPrev_value() );

                    java.sql.Timestamp last_minute = last_ticker.getCreatedAt();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(last_minute);
                    cal.add(Calendar.MINUTE, -1);
                    last_minute.setTime(cal.getTime().getTime());
                    System.out.println(last_minute);

                    java.sql.Timestamp last_hour = last_ticker.getCreatedAt();
                    Calendar cal2 = Calendar.getInstance();
                    cal2.setTime(last_minute);
                    cal2.add(Calendar.HOUR, -1);
                    last_hour.setTime(cal2.getTime().getTime());
                    System.out.println(last_hour);

                    Float value_last_minute = 0f;
                    Float value_last_hour = 0f;

                    System.out.println("tickers");

                    System.out.println(tickers);

                    for (Ticker t : tickers) {

                        if (t.getCreatedAt().after(last_minute)) {
                            System.out.println("last_min mudou");
                            System.out.println(t.getCreatedAt());
                            value_last_minute = t.getPrev_value();
                            break;
                        } else {
                            System.out.println(t.getCreatedAt());
                        }
                    }

                    for (Ticker t : tickers) {

                        if (t.getCreatedAt().after(last_minute)) {
                            System.out.println("last hour mudou");
                            System.out.println(t.getCreatedAt());
                            value_last_hour = t.getPrev_value();
                            break;
                        } else {
                            System.out.println(t.getCreatedAt());
                            
                        }
                    }

                    Float value_now = last_ticker.getPrev_value();

                    System.out.println("valores ultimo minuto e hora:");
                    System.out.println(value_last_minute);
                    System.out.println(value_last_hour);

                    Float last_minute_change = ( (value_last_minute - value_now) / value_last_minute) * 100;
                    Float last_hour_change = ( (value_last_hour - value_now) / value_last_hour) * 100;

                    System.out.println("mudança em %");
                    System.out.println(last_minute_change);
                    System.out.println(last_hour_change);
                    System.out.println();

                    market.setMinuteChange(last_minute_change);
                    market.setHourChange(last_hour_change);

                }

                markets_by_currency.add( market );
            }  
        }

        Page<Market> page_markets_by_currency = new PageImpl<>(markets_by_currency);

        return page_markets_by_currency;
    }    


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

    // receber todos os portfolios de um user ID (tem de ter um parametro id!)
    @GetMapping("/portfolio")
    public List<Portfolio> getAllPortfolios() {
        return portfolioservice.getPortfolios();
    }

    @DeleteMapping("/portfolio/{id}")
    public String deletePortfolios(@PathVariable int id) {
        return portfolioservice.deletePortfolio(id);
    }

    // recebe um post do angular com os parametros name e user (maybe) 
    @PostMapping("/portfolio")
    public Portfolio createPortfolio(@RequestBody String name) {

        System.out.println(">> A criar Portfolio '"+ name +"'...");

        return portfolioservice.savePortfolio( new Portfolio(name) );
    }
   
   

    ////////////////////////////////////////////  MARKET  ////////////////////////////////////////////

    @PostMapping("/market")
    public Market createMarkets(@RequestBody Market market){
        return marketservice.saveMarket(market);
    }

    @GetMapping("/market")
    public Page<Market> getAllMarkets(Pageable pageable) {

        List<Market> markets = marketservice.getMarkets();
        System.out.println("all markets:");
        System.out.println(markets.size());

        // atualizar o seu preço e a mudança em % do mercado para o ultimo ticker (ou para os tickers de há 24 horas atrás)
        for (Market market : markets) {

            List<Ticker> tickers = tickerservice.getTickersbyMarketID(market.getId());
            
            if (!tickers.isEmpty()) {
                // System.out.println("Not empty");
                // Collections.sort(tickers, new CustomComparator());
                Ticker last_ticker = tickers.get( 0 );
                System.out.println("last ticker");
                System.out.println(last_ticker);
                market.setPrice( last_ticker.getPrev_value() );

                java.sql.Timestamp last_minute = last_ticker.getCreatedAt();
                Calendar cal = Calendar.getInstance();
                cal.setTime(last_minute);
                cal.add(Calendar.MINUTE, -1);
                last_minute.setTime(cal.getTime().getTime());

                java.sql.Timestamp last_hour = last_ticker.getCreatedAt();
                Calendar cal2 = Calendar.getInstance();
                cal2.setTime(last_minute);
                cal2.add(Calendar.HOUR, -1);
                last_hour.setTime(cal2.getTime().getTime());

                Float value_last_minute = 0f;
                Float value_last_hour = 0f;

                for (Ticker t : tickers) {

                    if (t.getCreatedAt().after(last_minute)) {
                        value_last_minute = t.getPrev_value();
                        break;
                    } else {
                    }
                }

                for (Ticker t : tickers) {

                    if (t.getCreatedAt().after(last_minute)) {
                        value_last_hour = t.getPrev_value();
                        break;
                    } else {
                            
                    }
                }

                Float value_now = last_ticker.getPrev_value();

                System.out.println("valores ultimo minuto e hora:");
                System.out.println(value_last_minute);
                System.out.println(value_last_hour);

                Float last_minute_change = ( (value_last_minute - value_now) / value_last_minute) * 100;
                Float last_hour_change = ( (value_last_hour - value_now) / value_last_hour) * 100;

                market.setMinuteChange(last_minute_change);
                market.setHourChange(last_hour_change);

                System.out.println("mudança em %");
                System.out.println(market.getMinuteChange());
                System.out.println(market.getHourChange());
                System.out.println();

            }
            
        }

        return marketservice.getMarkets(pageable);
    }

    /* @GetMapping("/market")
    public List<Market> getAllMarkets() {

        List<Market>  markets = marketservice.getMarkets();

        // atualizar o seu preço e a mudança em % do mercado para o ultimo ticker (ou para os tickers de há 24 horas atrás)
        for (Market market : markets) {

            List<Ticker> tickers = tickerservice.getTickers();

            for (Ticker t : tickers) {
                if (t.getMarket().getId().equals( market.getId() ) ) {
                    tickers.add(t);
                }
            }
            
            if (tickers.isEmpty()) {
                market.setPrice( 0f );

            } else {
                
                Ticker last_ticker = tickers.get(0); // ou -1
                market.setPrice( last_ticker.getPrev_value() );
            }
            
        }

        return marketservice.getMarkets();
    } */

    /* public class CustomComparator implements Comparator<Ticker> {
        @Override
        public int compare(Ticker t1, Ticker t2) {
            return t1.getCreatedAt().compareTo(t2.getCreatedAt());
        }
    } */

    // EndPoint para os gráficos
    @GetMapping("/market/{id}")
    public List<Ticker> getTickersByMarketId(@PathVariable(value = "id") int marketId) {
        List<Ticker> tickersByMarket = new ArrayList<>();
        
        List<Ticker> tickers = tickerservice.getTickers();

        for (Ticker t : tickers) {
            if (t.getMarket().getId() == marketId ) {
                tickersByMarket.add( t );
            }
        }
        return tickersByMarket;
    }   

/*     // retorna uma lista dos preços atuais de cada mercado
    @GetMapping("/market2")
    public List<Float> getPrice() {
        // List<Market>  markets = marketservice.getMarkets();
        // List<Float> prices = new ArrayList<>();

        // for (Market market : markets) {
        //     Ticker last_ticker = market.getTickers().get(0);
        //     prices.add( last_ticker.getPrev_value() );
        // }

        // return prices;

        List<Float> prices = new ArrayList<>();

        Map<String, Object> marketSerialized = new HashMap<>();
        marketSerialized.put("id", market.getId());
        marketSerialized.put("originCurrency", market.getOriginCurrency());
        marketSerialized.put("destinyCurrency", market.getDestinyCurrency());
        marketSerialized.put("tickers", tickers);

        return marketSerialized;
    } */

    @GetMapping("/market/{id}/orders/sell")
    public Page<Order> getMarketSellOrders(@PathVariable int id, Pageable pageable) {
        return orderservice.getSellOrdersByMarket(id, pageable);
    }

    @GetMapping("/market/{id}/orders/buy")
    public Page<Order> getMarketBuyOrders(@PathVariable int id, Pageable pageable) {
        return orderservice.getBuyOrdersByMarket(id, pageable);
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

}