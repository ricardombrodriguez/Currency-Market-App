package yes.finance.services;

import yes.finance.model.Extension;
import yes.finance.model.Portfolio;
import yes.finance.model.User;
import yes.finance.repository.PortfolioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {
    @Autowired
    private PortfolioRepository repository;

    public Portfolio savePortfolio(Portfolio Portfolio) {
        return repository.save(Portfolio);
    }

    public List<Portfolio> savePortfolios(List<Portfolio> Portfolios) {
        return repository.saveAll(Portfolios);
    }

    public List<Portfolio> getPortfoliosbyUserID(int id) {
        return repository.findPortfoliobyUserID(id);
    }

    public Portfolio getPortfolioById(int id) {
        return repository.findById((int) id).orElse(null);
    }

    // IMPLEMENTAR TODOS OS FIND's QUE TIVERMOS NO PortfolioRepository.java

    public String deletePortfolio(int id) {
        repository.deleteById(id);
        return "Portfolio (id=" + id + ") removed!";
    }

    public Portfolio updatePortfolio(Portfolio Portfolio) {
        Portfolio existingPortfolio = repository.findById((int) Portfolio.getId()).orElse(null);

        // FAZER SETS

        return savePortfolio(existingPortfolio);
    }

    public Portfolio getPortfoliobyPublicKey(String publicKey) {
        return repository.findByPublicKey(publicKey);
    }

    public Page<Object> getPortfolioDetailsById(int id, Pageable pageable) {
        return repository.getPortfolioDetailsById(id, pageable);
    }

    public List<User> getPortfolioByUsers(String publicKey) {
        return repository.getPortfolioByUsers(publicKey);
    }

    public List<Extension> getPortfolioExtensions(int id) {
        return repository.findByExtensions(id);
    }
}