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

    Portfolio findByPublicKey(String publicKey);

    @Modifying 
    @Query("SELECT c.*, SUM(quantity) quantity, SUM(volume) volume FROM ("+
        "SELECT o.portfolio_id, o.market_id, -o.quantity quantity, -o.quantity*o.order_value volume FROM `transaction` t "+
        "INNER JOIN orders o ON t.origin_order_id = o.id "+
        "UNION "+
        "SELECT o.portfolio_id, o.market_id, o.quantity, o.quantity*o.order_value volume FROM `transaction` t) d "+
        "INNER JOIN market m ON m.id = d.market_id"+
        "INNER JOIN currency c ON m.destiny_currency_id = c.id "+
        "WHERE d.portfolio_id = :id "+
        "GROUP BY c.id")
    List<Object> getPortfolioDetailsById(@Param("id") int id);
}
