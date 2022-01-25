package yes.finance.repository;

import yes.finance.model.Extension;
import yes.finance.model.PCurrency;
import yes.finance.model.Portfolio;
import yes.finance.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {

        Portfolio findById(int id);

        @Query("SELECT u.portfolios FROM User u WHERE u.id=:id")
        List<Portfolio> findPortfoliobyUserID(@Param("id") int id);

        Portfolio findByPublicKey(String publicKey);

        // query

        @Query(value = "SELECT c.id, c.name, c.logo_url, c.online, c.symbol, SUM(IF(o.destiny_currency_id = c.id OR EXISTS(SELECT t1.id FROM `transaction` t1 INNER JOIN orders od ON t1.destiny_order_id = od.id WHERE od.portfolio_id = :id AND t1.id < o.tid), IF((p.id = sell_pt AND c.id = o.destiny_currency_id) OR (p.id = buy_pt AND c.id = o.origin_currency_id), -qt, qt), 0)) quantity  FROM portfolio p "
                        +
                        "LEFT JOIN (" +
                        "SELECT t.id tid, m.id, m.origin_currency_id, m.destiny_currency_id, oo.portfolio_id sell_pt, od.portfolio_id buy_pt, LEAST(-oo.quantity, od.quantity) qt FROM `transaction` t "
                        +
                        "INNER JOIN orders oo ON t.origin_order_id = oo.id " +
                        "INNER JOIN orders od ON t.destiny_order_id = od.id " +
                        "INNER JOIN market m ON m.id = oo.market_id " +
                        ") o ON p.id = o.sell_pt OR  p.id = o.buy_pt " +
                        "LEFT JOIN currency c ON c.id = o.origin_currency_id OR c.id = o.destiny_currency_id "
                        +
                        "WHERE p.id = :id " +
                        "GROUP BY p.id, c.id", nativeQuery = true)
        Page<PCurrency> getPortfolioDetailsById(@Param("id") int id, Pageable pageable);

        @Query(value = "SELECT c.id, c.name, c.logo_url, c.online, c.symbol, SUM(IF(o.destiny_currency_id = c.id OR EXISTS(SELECT t1.id FROM `transaction` t1 INNER JOIN orders od ON t1.destiny_order_id = od.id WHERE od.portfolio_id = :portfolioId AND t1.id < o.tid), IF((p.id = sell_pt AND c.id = o.destiny_currency_id) OR (p.id = buy_pt AND c.id = o.origin_currency_id), -qt, qt), 0)) quantity  FROM portfolio p "
                        +
                        "LEFT JOIN (" +
                        "SELECT t.id tid, m.id, m.origin_currency_id, m.destiny_currency_id, oo.portfolio_id sell_pt, od.portfolio_id buy_pt, LEAST(-oo.quantity, od.quantity) qt FROM `transaction` t "
                        +
                        "INNER JOIN orders oo ON t.origin_order_id = oo.id " +
                        "INNER JOIN orders od ON t.destiny_order_id = od.id " +
                        "INNER JOIN market m ON m.id = oo.market_id " +
                        ") o ON p.id = o.sell_pt OR  p.id = o.buy_pt " +
                        "LEFT JOIN currency c ON c.id = o.origin_currency_id OR c.id = o.destiny_currency_id "
                        +
                        "WHERE p.id = :portfolioId AND c.id = :currencyId " +
                        "GROUP BY p.id, c.id", nativeQuery = true)
        PCurrency getCurrencyDetailsInPortfolio(@Param("portfolioId") int portfolioId,
                        @Param("currencyId") int currencyId);

        @Query("SELECT p.users FROM Portfolio p WHERE p.publicKey=:publicKey")
        List<User> getPortfolioByUsers(@Param("publicKey") String publicKey);

        @Query("SELECT p.extensions FROM Portfolio p WHERE p.id = :portfolio_id")
        List<Extension> findByExtensions(@Param("portfolio_id") int portfolio_id);

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