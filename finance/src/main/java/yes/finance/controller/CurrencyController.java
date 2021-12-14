package yes.finance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import yes.finance.model.Currency;
import yes.finance.services.CurrencyService;

@RestController
@RequestMapping("/api/data") 
public class CurrencyController {
    
    @Autowired
    private CurrencyService service;

    @GetMapping("/currency")
    public List<Currency> getAllUsers() {
        return service.getCurrencies();
    }
}
