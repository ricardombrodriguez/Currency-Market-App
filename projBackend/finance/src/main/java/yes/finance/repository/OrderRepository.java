package yes.finance.repository;

import yes.finance.model.Order;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    Order findByid(int id);

    @Query(value = "SELECT o.id, o.created_at, o.order_value, d.quantity, o.market_id, o.portfolio_id FROM orders o " +
            "LEFT JOIN (" +
            "SELECT d.id, d.quantity - SUM(IFNULL(tquantity, 0)) quantity FROM (" +
            "SELECT o2.id, o2.quantity, -o4.quantity tquantity FROM orders o2 LEFT JOIN `transaction` t ON t.origin_order_id = o2.id LEFT JOIN orders o4 ON t.destiny_order_id = o4.id "
            +
            "UNION " +
            "SELECT o3.id, o3.quantity, o5.quantity tquantity FROM orders o3 LEFT JOIN `transaction` t2 ON t2.destiny_order_id = o3.id LEFT JOIN orders o5 ON t2.origin_order_id = o5.id"
            +
            ") d GROUP BY d.id, d.quantity" +
            ") d on d.id = o.id " +
            "WHERE d.quantity < 0 AND o.market_id = ?1", nativeQuery = true)
    Page<Order> findSellOrdersByMarket(int marketId, Pageable pageable);

    @Query(value = "SELECT o.id, o.created_at, o.order_value, d.quantity, o.market_id, o.portfolio_id FROM orders o " +
            "LEFT JOIN (" +
            "SELECT d.id, d.quantity - SUM(IFNULL(tquantity, 0)) quantity FROM (" +
            "SELECT o2.id, o2.quantity, -o4.quantity tquantity FROM orders o2 LEFT JOIN `transaction` t ON t.origin_order_id = o2.id LEFT JOIN orders o4 ON t.destiny_order_id = o4.id "
            +
            "UNION " +
            "SELECT o3.id, o3.quantity, o5.quantity tquantity FROM orders o3 LEFT JOIN `transaction` t2 ON t2.destiny_order_id = o3.id LEFT JOIN orders o5 ON t2.origin_order_id = o5.id"
            +
            ") d GROUP BY d.id, d.quantity" +
            ") d on d.id = o.id " +
            "WHERE d.quantity > 0 AND o.market_id = ?1", nativeQuery = true)
    Page<Order> findBuyOrdersByMarket(int marketId, Pageable pageable);

    @Query(value = "SELECT * from orders WHERE portfolio_id != ?1 AND order_value < ?2 AND quantity < 0 ORDER BY order_value", nativeQuery = true)
    public List<Order> findBuyOrderComplements(int portfolioId, Float max);

    @Query(value = "SELECT * from orders WHERE portfolio_id != ?1 AND order_value > ?2 AND quantity > 0 ORDER BY order_value", nativeQuery = true)
    public List<Order> findSellOrderComplements(int portfolioId, Float min);
}
