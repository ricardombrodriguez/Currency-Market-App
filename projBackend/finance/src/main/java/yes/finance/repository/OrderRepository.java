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
        "SELECT o.id, o.created_at, o.order_value, IFNULL(o.quantity + SUM(o2.quantity), o.quantity) quantity, o.market_id, o.portfolio_id FROM orders o " +
        "LEFT JOIN `transaction` t ON t.origin_order_id = o.id " +
        "LEFT JOIN orders o2 ON t.destiny_order_id = o2.id " +
        "WHERE o.quantity < 0 " +
        "GROUP BY o.id, o.created_at, o.order_value, o.market_id, o.portfolio_id" +
        ") d WHERE d.quantity < 0 AND d.market_id = ?1", nativeQuery = true)
    Page<Order> findSellOrdersByMarket(int marketId, Pageable pageable);

    @Query(value = "SELECT * FROM (" +
        "SELECT o.id, o.created_at, o.order_value, IFNULL(o.quantity + SUM(o2.quantity), o.quantity) quantity, o.market_id, o.portfolio_id FROM orders o " +
        "LEFT JOIN `transaction` t ON t.destiny_order_id = o.id " +
        "LEFT JOIN orders o2 ON t.origin_order_id = o2.id " +
        "WHERE o.quantity > 0 " +
        "GROUP BY o.id, o.created_at, o.order_value, o.market_id, o.portfolio_id" +
        ") d WHERE d.quantity > 0 AND d.market_id = ?1", nativeQuery = true)
    Page<Order> findBuyOrdersByMarket(int marketId, Pageable pageable);

    @Query(value = 
        "SELECT id, created_at, order_value, market_id, portfolio_id, IFNULL(quantity_left, quantity) quantity FROM (" + 
	        "SELECT o.id,o.created_at,o.order_value,o.market_id,o.portfolio_id,o.quantity,o.quantity+SUM(o2.quantity) quantity_left from orders o " + 
	        "LEFT JOIN `transaction` t ON o.id = t.origin_order_id " + 
	        "LEFT JOIN orders o2 ON o2.id = t.destiny_order_id " + 
	        "GROUP BY o.id, o.created_at, o.order_value, o.market_id, o.portfolio_id" + 
        ") o " + 
        "WHERE order_value <= ?3 AND quantity < 0 AND (quantity_left IS NULL OR quantity_left < 0) AND market_id = ?2 AND portfolio_id != ?1 " + 
        "ORDER BY order_value", nativeQuery = true)
    public List<Order> findBuyOrderComplements(int portfolioId, int marketId, Float max);

    @Query(value = 
        "SELECT id, created_at, order_value, market_id, portfolio_id, IFNULL(quantity_left, quantity) quantity FROM (" + 
	        "SELECT o.id, o.created_at, o.order_value, o.market_id, o.portfolio_id, o.quantity, o.quantity + SUM(o2.quantity) quantity_left from orders o " + 
	        "LEFT JOIN `transaction` t ON o.id = t.destiny_order_id " + 
	        "LEFT JOIN orders o2 ON o2.id = t.origin_order_id " + 
	        "GROUP BY o.id, o.created_at, o.order_value, o.market_id, o.portfolio_id" + 
        ") o " + 
        "WHERE order_value >= ?3 AND quantity > 0 AND (quantity_left IS NULL OR quantity_left > 0) AND market_id = ?2 AND portfolio_id != ?1 " + 
        "ORDER BY order_value", nativeQuery = true)
    public List<Order> findSellOrderComplements(int portfolioId, int marketId, Float min);
}
