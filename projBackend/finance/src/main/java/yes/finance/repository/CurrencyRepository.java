package yes.finance.repository;

import java.util.List;

import yes.finance.model.Currency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
    Currency findByid(int id);
    Currency findByName(String name);
    Currency findBySymbol(String symbol);

    @Query("SELECT c FROM Currency c WHERE c.online = True")
    Page<Currency> findByOnline(Pageable pageable);

    @Query("SELECT c FROM Currency c WHERE c.name LIKE CONCAT('%',:name,'%')")
    List<Currency> findCurrenciesByName(@Param("name") String name);
}
