package yes.finance.repository;

import yes.finance.model.Portfolio;
import yes.finance.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {
    Portfolio findByid(int id);

    @Query("SELECT u.portfolios FROM User u WHERE u.id=:id")
    List<Portfolio> findPortfoliobyUserID(@Param("id") int id);

    Portfolio findByPublicKey(String publicKey);

    // query
    @Modifying
    @Query(value = "SELECT c.*, SUM(quantity) quantity, SUM(volume) volume FROM ( " +
            "SELECT o.portfolio_id, o.market_id, -o.quantity quantity, -o.quantity*o.order_value volume FROM `transaction` t "
            +
            "INNER JOIN orders o ON t.origin_order_id = o.id " +
            "UNION " +
            "SELECT o.portfolio_id, o.market_id, o.quantity, o.quantity*o.order_value volume FROM `transaction` t " +
            "INNER JOIN orders o ON t.destiny_order_id = o.id ) d " +
            "INNER JOIN market m ON m.id = d.market_id " +
            "INNER JOIN currency c ON m.destiny_currency_id = c.id " +
            "WHERE d.portfolio_id = :id " +
            "GROUP BY c.id", nativeQuery = true)
    List<Object> getPortfolioDetailsById(@Param("id") int id);

    @Query("SELECT p.users FROM Portfolio p WHERE p.publicKey=:publicKey")
    List<User> getPortfolioByUsers(@Param("publicKey") String publicKey);

}

/*
 * @Query("SELECT c.*, SUM(quantity) quantity, SUM(volume) volume FROM ( " +
 * "SELECT o.portfolio_id, o.market_id, -o.quantity quantity,
 * -o.quantity*o.order_value volume FROM `transaction` t "
 * +
 * "INNER JOIN orders o ON t.origin_order_id = o.id " +
 * "UNION " +
 * "SELECT o.portfolio_id, o.market_id, o.quantity, o.quantity*o.order_value
 * volume FROM `transaction` t "
 * +
 * "INNER JOIN orders o ON t.destiny_order_id = o.id ) d "+
 * "INNER JOIN market m ON m.id = d.market_id "+
 * "INNER JOIN currency c ON m.destiny_currency_id = c.id "+
 * "WHERE d.portfolio_id = :id "+
 * "GROUP BY c.id")
 */

// @Query("SELECT c, SUM(quantity) AS quantity, SUM(volume) AS volume FROM ( " +
// "SELECT o.portfolio_id, o.market_id, -o.quantity, -o.quantity*o.order_value
// volume FROM Order o " +
// "OUTER JOIN Transaction t ON t.origin_order_id = o.id " +
// "UNION SELECT o.portfolio_id, o.market_id, o.quantity,
// o.quantity*o.order_value volume FROM Transaction t) d " +
// "INNER JOIN Market m ON m.id = d.market_id" +
// "INNER JOIN Currency c ON m.destiny_currency_id = c.id " +
// "WHERE d.portfolio_id = :id " +
// "GROUP BY c.id")