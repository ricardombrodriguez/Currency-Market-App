package yes.finance.repository;

import yes.finance.model.Market;
import yes.finance.model.Ticker;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface TickerRepository extends JpaRepository<Ticker, Integer> {
    Ticker findByid(int id);

    @Query("SELECT t FROM Ticker t LEFT JOIN Market m ON t.market = m WHERE m.id = :id ORDER BY t.createdAt DESC")
    List<Ticker> findBymarket(@Param("id") int id);

    List<Ticker> findFirst100ByMarketOrderByCreatedAtDesc(Market market);
}
