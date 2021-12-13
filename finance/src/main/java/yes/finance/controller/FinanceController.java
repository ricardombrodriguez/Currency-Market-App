package yes.finance.controller;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import yes.finance.model.*;
import yes.finance.repository.*;

@RestController
public class FinanceController {
    @Autowired
    private CurrencyRepository repository;
    private ExtensionRepository extensionRepository;
    private MarketRepository marketRepository;
    private OrderRepository orderRepository;
    private PortfolioRepository portfolioRepository;
    private TransactionRepository transactionRepository;
    private UserRepository userRepository;


    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
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