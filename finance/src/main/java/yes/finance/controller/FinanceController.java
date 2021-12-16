package yes.finance.controller;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import yes.finance.model.*;
import yes.finance.model.Currency;
import yes.finance.services.*;

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

    @GetMapping("/users")
    public Page<User> getAllUsers(Pageable pageable) {
        return service.getUsers(pageable);
    }

    @PostMapping("/users")
    public User createUsers(@RequestBody User user){
        return service.saveUser(user);
    }
    @DeleteMapping("/users/{id}")
    public String deleteUsers(@PathVariable int id) {
        return service.deleteUser(id);
    }


    ////////////////////////////////////////////  CURRENCY  ////////////////////////////////////////////

    @GetMapping("/currencies")
    public Page<Currency> getAllCurrencies(Pageable pageable) {
        return currencyservice.getCurrencies(pageable);
    }

    @PostMapping("/currencies")
    public Currency createCurrencies(@RequestBody Currency currency){
        return currencyservice.saveCurrency(currency);
    }
    
    /* @GetMapping("/currencies/{id}/market")
    public Page<Currency> getMarketsByCurrency(Pageable pageable, @PathVariable int id) {
        marketservice.getPrice();
        return currencyservice.getCurrencies(pageable);
    }  */
   


    ////////////////////////////////////////////  EXTENSION  ////////////////////////////////////////////

    @GetMapping("/extensions")
    public Page<Extension> getAllExtensions(Pageable pageable) {
        return extensionservice.getExtensions(pageable);
    }

    @PostMapping("/extensions")
    public Extension createExtensions(@RequestBody Extension extension){
        return extensionservice.saveExtension(extension);
    }
    @DeleteMapping("/extensions/{id}")
    public String deleteExtensions(@PathVariable int id) {
        return extensionservice.deleteExtension(id);
    }


    ////////////////////////////////////////////  PORTFOLIO  ////////////////////////////////////////////

    @GetMapping("/portfolios")
    public Page<Portfolio> getAllPortfolios(Pageable pageable) {
        return portfolioservice.getPortfolios(pageable);
    }

    @PostMapping("/portfolios")
    public Portfolio createPortfolios(@RequestBody Portfolio portfolio){
        return portfolioservice.savePortfolio(portfolio);
    }

    /* @PostMapping("/portfolios")
    public Portfolio createPortfolio(@RequestBody Portfolio portfolio){
        Portfolio p = new Portfolio();
        String name = "nome"; // ir buscar o input do user 
        p.setName(name);
        p.setPublic_key(); // o que é suposto ser a public_key? um numero random grande?
        return p;
    } */


    ////////////////////////////////////////////  MARKET  ////////////////////////////////////////////

    @PostMapping("/markets")
    public Market createMarkets(@RequestBody Market market){
        return marketservice.saveMarket(market);
    }

    @GetMapping("/markets")
    public Page<Market> getAllMarkets(Pageable pageable) {
        return marketservice.getMarkets(pageable);
    }

    // obter o preço da última moeda de um determinado mercado
    /* @GetMapping("/markets")
    public Page<Market> getPrice(Pageable pageable) {
        return marketservice.getPrice(pageable);
    } */


    ////////////////////////////////////////////  ORDER  ////////////////////////////////////////////

    @GetMapping("/orders")
    public Page<Order> getAllOrders(Pageable pageable) {
        return orderservice.getOrders(pageable);
    }

    @PostMapping("/orders")
    public Order createOrders(@RequestBody Order order) {
        return orderservice.saveOrder(order);
    }


    ////////////////////////////////////////////  TRANSACTION  ////////////////////////////////////////////

    @GetMapping("/transactions")
    public Page<Transaction> getAllTransactions(Pageable pageable) {
        return transactionservice.getTransactions(pageable);
    }

    @PostMapping("/transactions")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionservice.saveTransaction(transaction);
    }


    ////////////////////////////////////////////  TICKER  ////////////////////////////////////////////

    @GetMapping("/tickers")
    public Page<Ticker> getAllTickers(Pageable pageable) {
        return tickerservice.getTickers(pageable);
    }

    @PostMapping("/tickers")
    public Ticker createTicker(@RequestBody Ticker ticker) {
        return tickerservice.saveTicker(ticker);
    }
}