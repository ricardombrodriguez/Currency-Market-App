package yes.finance.repository;

import yes.finance.model.Ticker;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TickerRepository extends JpaRepository<Ticker,Integer> {
    Ticker findByid(int id);
}
