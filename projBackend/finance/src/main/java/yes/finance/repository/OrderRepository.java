package yes.finance.repository;

import yes.finance.model.Order;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    Order findByid(int id);

    @Query(value = "SELECT * FROM (" +
    "SELECT o.* FROM orders o " +
    "LEFT JOIN `transaction` t ON t.destiny_order_id = o.id OR t.origin_order_id = o.id " +
    "WHERE t.id IS NULL " +
    "UNION ALL " +
    "SELECT o.id, o.created_at, o.order_value, d.quantity, o.market_id, o.portfolio_id FROM (" +
        "SELECT MAX(t.id) transaction_id, origin_order_id order_id, oo.quantity + SUM(do.quantity) quantity FROM `transaction` t " +
        "INNER JOIN orders oo ON oo.id = t.origin_order_id " +
        "INNER JOIN orders do ON do.id = t.destiny_order_id " +
        "GROUP BY origin_order_id, oo.quantity " +
        "HAVING oo.quantity + SUM(do.quantity) < 0 " +
        "UNION ALL " +
        "SELECT MAX(t.id) transaction_id, destiny_order_id order_id, do.quantity + SUM(oo.quantity) quantity FROM `transaction` t " +
        "INNER JOIN orders oo ON oo.id = t.origin_order_id " +
        "INNER JOIN orders do ON do.id = t.destiny_order_id " +
        "GROUP BY destiny_order_id, do.quantity " +
        "HAVING do.quantity + SUM(oo.quantity) > 0 " +
    ") d INNER JOIN orders o ON d.order_id = o.id) d " +
    "WHERE quantity < 0 AND market_id = ?1", nativeQuery = true)
    Page<Order> findSellOrdersByMarket(int marketId, Pageable pageable);

    @Query(value = "SELECT * FROM (" +
    "SELECT o.* FROM orders o " +
    "LEFT JOIN `transaction` t ON t.destiny_order_id = o.id OR t.origin_order_id = o.id " +
    "WHERE t.id IS NULL " +
    "UNION ALL " +
    "SELECT o.id, o.created_at, o.order_value, d.quantity, o.market_id, o.portfolio_id FROM (" +
        "SELECT MAX(t.id) transaction_id, origin_order_id order_id, oo.quantity + SUM(do.quantity) quantity FROM `transaction` t " +
        "INNER JOIN orders oo ON oo.id = t.origin_order_id " +
        "INNER JOIN orders do ON do.id = t.destiny_order_id " +
        "GROUP BY origin_order_id, oo.quantity " +
        "HAVING oo.quantity + SUM(do.quantity) < 0 " +
        "UNION ALL " +
        "SELECT MAX(t.id) transaction_id, destiny_order_id order_id, do.quantity + SUM(oo.quantity) quantity FROM `transaction` t " +
        "INNER JOIN orders oo ON oo.id = t.origin_order_id " +
        "INNER JOIN orders do ON do.id = t.destiny_order_id " +
        "GROUP BY destiny_order_id, do.quantity " +
        "HAVING do.quantity + SUM(oo.quantity) > 0 " +
    ") d INNER JOIN orders o ON d.order_id = o.id) d " +
    "WHERE quantity > 0 AND market_id = ?1", nativeQuery = true)
    Page<Order> findBuyOrdersByMarket(int marketId, Pageable pageable);

    @Query(value = "SELECT * FROM (" +
        "SELECT o.* FROM orders o " +
        "LEFT JOIN `transaction` t ON t.destiny_order_id = o.id OR t.origin_order_id = o.id " +
        "WHERE t.id IS NULL " +
        "UNION ALL " +
        "SELECT o.id, o.created_at, o.order_value, d.quantity, o.market_id, o.portfolio_id FROM (" +
            "SELECT MAX(t.id) transaction_id, origin_order_id order_id, oo.quantity + SUM(do.quantity) quantity FROM `transaction` t " +
            "INNER JOIN orders oo ON oo.id = t.origin_order_id " +
            "INNER JOIN orders do ON do.id = t.destiny_order_id " +
            "GROUP BY origin_order_id, oo.quantity " +
            "HAVING oo.quantity + SUM(do.quantity) < 0 " +
            "UNION ALL " +
            "SELECT MAX(t.id) transaction_id, destiny_order_id order_id, do.quantity + SUM(oo.quantity) quantity FROM `transaction` t " +
            "INNER JOIN orders oo ON oo.id = t.origin_order_id " +
            "INNER JOIN orders do ON do.id = t.destiny_order_id " +
            "GROUP BY destiny_order_id, do.quantity " +
            "HAVING do.quantity + SUM(oo.quantity) > 0 " +
        ") d INNER JOIN orders o ON d.order_id = o.id) d " +
        "WHERE quantity < 0 AND market_id = ?2 AND order_value <= ?3 AND portfolio_id != ?1 " + 
        "ORDER BY order_value", nativeQuery = true)
    public List<Order> findBuyOrderComplements(int portfolioId, int marketId, Float max);

    @Query(value = "SELECT * FROM (" +
        "SELECT o.* FROM orders o " +
        "LEFT JOIN `transaction` t ON t.destiny_order_id = o.id OR t.origin_order_id = o.id " +
        "WHERE t.id IS NULL " +
        "UNION ALL " +
        "SELECT o.id, o.created_at, o.order_value, d.quantity, o.market_id, o.portfolio_id FROM (" +
            "SELECT MAX(t.id) transaction_id, origin_order_id order_id, oo.quantity + SUM(do.quantity) quantity FROM `transaction` t " +
            "INNER JOIN orders oo ON oo.id = t.origin_order_id " +
            "INNER JOIN orders do ON do.id = t.destiny_order_id " +
            "GROUP BY origin_order_id, oo.quantity " +
            "HAVING oo.quantity + SUM(do.quantity) < 0 " +
            "UNION ALL " +
            "SELECT MAX(t.id) transaction_id, destiny_order_id order_id, do.quantity + SUM(oo.quantity) quantity FROM `transaction` t " +
            "INNER JOIN orders oo ON oo.id = t.origin_order_id " +
            "INNER JOIN orders do ON do.id = t.destiny_order_id " +
            "GROUP BY destiny_order_id, do.quantity " +
            "HAVING do.quantity + SUM(oo.quantity) > 0 " +
        ") d INNER JOIN orders o ON d.order_id = o.id) d " +
        "WHERE quantity > 0 AND market_id = ?2 AND order_value >= ?3 AND portfolio_id != ?1 " + 
        "ORDER BY order_value", nativeQuery = true)
    public List<Order> findSellOrderComplements(int portfolioId, int marketId, Float min);
}
