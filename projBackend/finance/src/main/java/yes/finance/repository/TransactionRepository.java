package yes.finance.repository;

import yes.finance.model.Transaction;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    Transaction findByid(int id);

    @Query(value=
    "SELECT t.created_at, LEAST(-oo.quantity, do.quantity) qt, do.order_value val, oo.portfolio_id = ?1 is_seller, co.id sell_curr_id, co.logo_url sell_curr_logo, co.name sell_curr_name, cd.id buy_curr_id, cd.logo_url buy_curr_logo, cd.name buy_curr_name FROM `transaction` t " +
    "INNER JOIN orders oo ON oo.id = t.origin_order_id " +
    "INNER JOIN orders do ON do.id = t.destiny_order_id " +
    "INNER JOIN market m ON m.id = oo.market_id " +
    "INNER JOIN currency co ON m.origin_currency_id = co.id " +
    "INNER JOIN currency cd ON m.destiny_currency_id = cd.id " +
    "WHERE oo.portfolio_id = ?1 OR do.portfolio_id = ?1", nativeQuery=true)
    public Page<Map<String, Object>> getTransactionDetails(int portfolio_id, Pageable pageable);
}
