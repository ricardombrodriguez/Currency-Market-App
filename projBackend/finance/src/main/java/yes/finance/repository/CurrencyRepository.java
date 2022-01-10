package yes.finance.repository;

import yes.finance.model.Currency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
    Currency findByid(int id);
    Currency findByName(String name);
    Currency findBySymbol(String symbol);

    @Query("SELECT c FROM Currency c WHERE c.online = True")
    Page<Currency> findByOnline(Pageable pageable);
}
