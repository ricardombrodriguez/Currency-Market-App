package yes.finance.services;

import yes.finance.model.Market;
import yes.finance.repository.MarketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class MarketService {
    @Autowired
    private MarketRepository repository;

    public Market saveMarket(Market market) {
        return repository.save(market);
    }

    public List<Market> saveMarkets(List<Market> markets) {
        return repository.saveAll(markets);
    }

    public Page<Market> getMarkets(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Market getMarketById(int id) {
        return repository.findById((int)id).orElse(null);
    }

    public Page<Market> getPrice(Pageable pageable){
        return // obter o preço da coin mais recente
    }

    // IMPLEMENTAR TODOS OS FIND's QUE TIVERMOS NO MarketRepository.java

    public String deleteMarket(int id) {
        repository.deleteById(id);
        return "Market (id="+ id +") removed!";
    }

    public Market updateMarket(Market market) {
        Market existingMarket = repository.findById((int)market.getId()).orElse(null);

        // FAZER SETS

        return saveMarket(existingMarket);
    }
}