package yes.finance.repository;

import yes.finance.model.Market;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepository extends JpaRepository<Market,Integer> {
    Market findByid(int id);
    Market findBySymbol(String symbol);
    // Market getMarketByOriginCurrencyId(int currencyId);

    List<Market> findByOriginCurrencyId(int currency_id);
}
