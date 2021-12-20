package yes.finance.services;

import yes.finance.model.Order;
import yes.finance.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    public Order saveOrder(Order Order) {
        return repository.save(Order);
    }

    public List<Order> saveOrders(List<Order> Orders) {
        return repository.saveAll(Orders);
    }

    public Page<Order> getOrders(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Order getOrderById(int id) {
        return repository.findById((int)id).orElse(null);
    }

    // IMPLEMENTAR TODOS OS FIND's QUE TIVERMOS NO OrderRepository.java

    public String deleteOrder(int id) {
        repository.deleteById(id);
        return "Order (id="+ id +") removed!";
    }

    public Order updateOrder(Order Order) {
        Order existingOrder = repository.findById((int)Order.getId()).orElse(null);

        // FAZER SETS

        return saveOrder(existingOrder);
    }
}