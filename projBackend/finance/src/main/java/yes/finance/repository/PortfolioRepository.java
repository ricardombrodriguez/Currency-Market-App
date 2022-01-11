package yes.finance.repository;

import yes.finance.model.Portfolio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {
    Portfolio findByid(int id);

    @Query("SELECT u.portfolios FROM User u WHERE u.id=:id")
    List<Portfolio> findPortfoliobyUserID(@Param("id") int id);
}
