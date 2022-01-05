package yes.finance.repository;

import yes.finance.model.Ticker;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;


public interface TickerRepository extends JpaRepository<Ticker,Integer> {
    Ticker findByid(int id);

    @Query("SELECT t FROM Ticker t LEFT JOIN Market m ON t.market = m.id WHERE t.market = :id")
    List<Ticker> findBymarket(int id);
}
