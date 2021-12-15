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


    @GetMapping("/currencies")
    public Page<Currency> getAllCurrencies(Pageable pageable) {
        return currencyservice.getCurrencies(pageable);
    }

    @PostMapping("/currencies")
    public Currency createCurrencies(@RequestBody Currency currency){
        return currencyservice.saveCurrency(currency);
    }


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


    @GetMapping("/portfolios")
    public Page<Portfolio> getAllPortfolios(Pageable pageable) {
        return portfolioservice.getPortfolios(pageable);
    }

    @PostMapping("/portfolios")
    public Portfolio createPortfolios(@RequestBody Portfolio portfolio){
        return portfolioservice.savePortfolio(portfolio);
    }


    @PostMapping("/markets")
    public Market createMarkets(@RequestBody Market market){
        return marketservice.saveMarket(market);
    }

    @GetMapping("/markets")
    public Page<Market> getAllMarkets(Pageable pageable) {
        return marketservice.getMarkets(pageable);
    }


    @GetMapping("/orders")
    public Page<Order> getAllOrders(Pageable pageable) {
        return orderservice.getOrders(pageable);
    }

    @PostMapping("/orders")
    public Order createOrders(@RequestBody Order order) {
        return orderservice.saveOrder(order);
    }



    @GetMapping("/transactions")
    public Page<Transaction> getAllTransactions(Pageable pageable) {
        return transactionservice.getTransactions(pageable);
    }

    @PostMapping("/transactions")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionservice.saveTransaction(transaction);
    }
}

/*


@RestController
public class QuotesController {
    @Autowired
    private QuotesRepository quotesRepository;
    @Autowired
    private MovieRepository moviesRepository;

    // @GetMapping("/quotes")
    // @ResponseBody
    // public List<Quotes> getAllQuotes(@RequestParam(defaultValue = "all") Long id) {
    //     if (id.equals("all")){
    //         return quotesRepository.findAll();
    //     }else{
    //         return quotesRepository.findById(id);
    //     }
        
    // }

    @GetMapping("/quotes")
    public List<Quotes> getAllQuotes() {
        return quotesRepository.findAll();
    }


    @GetMapping("/quotes/{id}")
    public ResponseEntity<Quotes> getQuotesById(@PathVariable(value = "id") Long quotesId)
        throws ResourceNotFoundException {
        Quotes quotes = quotesRepository.findById(quotesId)
          .orElseThrow(() -> new ResourceNotFoundException("Quotes not found for this id :: " + quotesId));
        return ResponseEntity.ok().body(quotes);
    }
    
    @PostMapping("/quotes")
    public Quotes createQuotes(@Valid @RequestBody Quotes quote) {
        return quotesRepository.save(quote);
    }


    @PutMapping("/quotes/{id}")
    public ResponseEntity<Quotes> updateEmployee(@PathVariable(value = "id") Long employeeId,
         @Valid @RequestBody Quotes employeeDetails) throws ResourceNotFoundException {
        Quotes employee = quotesRepository.findById(employeeId)
        .orElseThrow(() -> new ResourceNotFoundException("Quote not found for this id :: " + employeeId));

        employee.setTexto(employeeDetails.getTexto());
        employee.setYear(employeeDetails.getYear());
        final Quotes updatedEmployee = quotesRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/quotes/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
         throws ResourceNotFoundException {
        Quotes employee = quotesRepository.findById(employeeId)
       .orElseThrow(() -> new ResourceNotFoundException("Quote not found for this id :: " + employeeId));

        quotesRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


    // @GetMapping("/movies")
    // @ResponseBody
    // public List<Movie> getAllMovies() {
    //     return movieRepository.findAll();
    // }


    @GetMapping("/movies")
    public List<Movie> findAllMovies() {
        return moviesRepository.findAll();
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable(value = "id") Long movieId)
        throws ResourceNotFoundException {
        Movie movie = moviesRepository.findById(movieId)
          .orElseThrow(() -> new ResourceNotFoundException("Movie not found for this id :: " + movieId));
        return ResponseEntity.ok().body(movie);
    }
    
    @PostMapping("/movies")
    public Movie createMovie(@Valid @RequestBody Movie movie) {
        return moviesRepository.save(movie);
    }




}
*/