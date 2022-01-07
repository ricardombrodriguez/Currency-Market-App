package yes.finance.repository;

import yes.finance.model.Market;
import yes.finance.model.Ticker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;


public interface TickerRepository extends JpaRepository<Ticker,Integer> {
    Ticker findByid(int id);

    @Query("SELECT t FROM Ticker t LEFT JOIN Market m ON t.market = m.id WHERE t.market = :market")
    List<Ticker> findByMarket(Market market);


}
