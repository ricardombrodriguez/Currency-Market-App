package yes.finance.repository;

import yes.finance.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
    Currency findByID(int id);
    Currency findByName(String name);
    Currency findBySymbol(String symbol);
}
