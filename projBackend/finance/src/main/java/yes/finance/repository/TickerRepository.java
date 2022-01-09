package yes.finance.repository;

import yes.finance.model.Market;
import yes.finance.model.Ticker;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TickerRepository extends JpaRepository<Ticker,Integer> {
    Ticker findByid(int id);
    List<Ticker> findFirst100ByMarketOrderByCreatedAtDesc(Market market);
}
