package yes.finance.repository;

import yes.finance.model.Market;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepository extends JpaRepository<Market, Integer> {
    Market findByid(int id);

    Market findBySymbol(String symbol);

    List<Market> findByOriginCurrencyId(int currency_id);
}
