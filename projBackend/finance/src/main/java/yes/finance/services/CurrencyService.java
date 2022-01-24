package yes.finance.services;

import yes.finance.model.Currency;
import yes.finance.repository.CurrencyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Service
public class CurrencyService {
    @Autowired
    private CurrencyRepository repository;

    public Currency saveCurrency(Currency currency) {
        return repository.save(currency);
    }

    public List<Currency> saveCurrencies(List<Currency> currencies) {
        return repository.saveAll(currencies);
    }

    public Page<Currency> getCurrencies(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Currency getCurrencyById(int id) {
        return repository.findById((int)id).orElse(null);
    }

    public List<Currency> getCurrenciesByName(String name) {
        return repository.findCurrenciesByName(name);
    }

    public String deleteCurrency(int id) {
        repository.deleteById(id);
        return "Currency (id="+ id +") removed!";
    }

    public Currency updateCurrency(Currency currency) {
        Currency existingCurrency = repository.findById((int)currency.getId()).orElse(null);

        // FAZER SETS

        return saveCurrency(existingCurrency);
    }
}