package yes.finance.services;

import yes.finance.model.Transaction;
import yes.finance.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import java.util.List;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repository;

    @Autowired
    private SimpMessageSendingOperations sendingOperations;

    public Transaction saveTransaction(Transaction Transaction) {
        sendingOperations.convertAndSend("/portfolio/" + Transaction.getDestiny_order().getPortfolioId(), 0);
        sendingOperations.convertAndSend("/portfolio/" + Transaction.getOrigin_order().getPortfolioId(), 0);
        return repository.save(Transaction);
    }

    public List<Transaction> saveCurrencies(List<Transaction> currencies) {
        return repository.saveAll(currencies);
    }

    public Page<Transaction> getTransactions(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Transaction getTransactionById(int id) {
        return repository.findById((int)id).orElse(null);
    }

    // IMPLEMENTAR TODOS OS FIND's QUE TIVERMOS NO TransactionRepository.java

    public String deleteTransaction(int id) {
        repository.deleteById(id);
        return "Transaction (id="+ id +") removed!";
    }

    public Page<Map<String, Object>> getTransactionDetails(int portfolio_id, Pageable pageable) {
        return repository.getTransactionDetails(portfolio_id, pageable);
    }

    /* public Transaction updateTransaction(Transaction Transaction) {
        Transaction existingTransaction = repository.findById((int)Transaction.getId()).orElse(null);

        // FAZER SETS

        return saveTransaction(existingTransaction);
    } */
}