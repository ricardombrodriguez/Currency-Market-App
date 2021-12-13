package yes.finance.repository;

import yes.finance.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio,Integer> {
    Portfolio findByID(int id);
}
