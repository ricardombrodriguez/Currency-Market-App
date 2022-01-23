package yes.finance.repository;

import yes.finance.model.Market;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MarketRepository extends JpaRepository<Market, Integer> {
    Market findByid(int id);

    Market findBySymbol(String symbol);
    
    @Query(
        value="SELECT * FROM market m WHERE m.origin_currency_id = :currency_id OR m.destiny_currency_id = :currency_id", 
        nativeQuery=true)
    List<Market> findByCurrencyId(int currency_id);

    long count();
}
