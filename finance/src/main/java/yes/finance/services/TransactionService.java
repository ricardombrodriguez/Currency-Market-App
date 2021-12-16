package yes.finance.services;

import yes.finance.model.Transaction;
import yes.finance.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repository;

    public Transaction saveTransaction(Transaction Transaction) {
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

    /* public Transaction updateTransaction(Transaction Transaction) {
        Transaction existingTransaction = repository.findById((int)Transaction.getId()).orElse(null);

        // FAZER SETS

        return saveTransaction(existingTransaction);
    } */
}