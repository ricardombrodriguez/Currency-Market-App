package yes.finance.repository;

import yes.finance.model.Market;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MarketRepository extends JpaRepository<Market, Integer> {
    Market findByid(int id);

    Market findBySymbol(String symbol);
    
    @Query(value = "SELECT * FROM market m WHERE m.origin_currency_id = :currency_id OR m.destiny_currency_id = :currency_id", 
        countQuery ="SELECT COUNT(*) FROM market m WHERE m.origin_currency_id = :currency_id OR m.destiny_currency_id = :currency_id", 
        nativeQuery=true)
    Page<Market> findByCurrencyId(int currency_id, Pageable pageable);

    long count();
}
