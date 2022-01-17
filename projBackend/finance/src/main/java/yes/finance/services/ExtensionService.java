package yes.finance.services;

import yes.finance.model.Extension;
import yes.finance.model.User;
import yes.finance.repository.ExtensionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Service
public class ExtensionService {
    @Autowired
    private ExtensionRepository repository;

    public Extension saveExtension(Extension extension) {
        return repository.save(extension);
    }

    public List<Extension> saveCurrencies(List<Extension> extensions) {
        return repository.saveAll(extensions);
    }

    public Page<Extension> getExtensions(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Extension> getExtensionsByUser(User user, Pageable pageable) {
        return repository.findByUser(user, pageable);
    }

    public Extension getExtensionById(int id) {
        return repository.findById((int)id).orElse(null);
    }

    // IMPLEMENTAR TODOS OS FIND's QUE TIVERMOS NO ExtensionRepository.java

    public String deleteExtension(int id) {
        repository.deleteById(id);
        return "Extension (id="+ id +") removed!";
    }

    public Extension updateExtension(Extension extension) {
        Extension existingExtension = repository.findById((int)extension.getId()).orElse(null);

        // FAZER SETS

        return saveExtension(existingExtension);
    }
}