package yes.finance.services;

import yes.finance.model.Market;
import yes.finance.model.Ticker;
import yes.finance.repository.TickerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Service
public class TickerService {
    @Autowired
    private TickerRepository repository;

    public Ticker saveTicker(Ticker Ticker) {
        return repository.save(Ticker);
    }

    public List<Ticker> saveCurrencies(List<Ticker> currencies) {
        return repository.saveAll(currencies);
    }

    public Page<Ticker> getTickers(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Ticker> getTickers() {
        return repository.findAll();
    }

    public List<Ticker> getTickersbyMarketID(int market_id) {
        return repository.findBymarket(market_id);
    }

    // IMPLEMENTAR TODOS OS FIND's QUE TIVERMOS NO TickerRepository.java

    public String deleteTicker(int id) {
        repository.deleteById(id);
        return "Ticker (id="+ id +") removed!";
    }

    /* public Ticker updateTicker(Ticker Ticker) {
        Ticker existingTicker = repository.findById((int)Ticker.getId()).orElse(null);

        // FAZER SETS

        return saveTicker(existingTicker);
    } */
}