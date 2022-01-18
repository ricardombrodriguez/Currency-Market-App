package yes.finance.services;

import yes.finance.model.Extension;
import yes.finance.model.User;
import yes.finance.repository.ExtensionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Extension> getExtensions() {
        return repository.findAll();
    }

    public List<Extension> getExtensionsByUser(User user) {
        return repository.findByUser(user);
    }

    public Extension getExtensionById(int id) {
        return repository.findById((int) id).orElse(null);
    }

    // IMPLEMENTAR TODOS OS FIND's QUE TIVERMOS NO ExtensionRepository.java

    public String deleteExtension(int id) {
        repository.deleteById(id);
        return "Extension (id=" + id + ") removed!";
    }

    public Extension updateExtension(Extension extension) {
        Extension existingExtension = repository.findById((int) extension.getId()).orElse(null);

        // FAZER SETS

        return saveExtension(existingExtension);
    }

    public Extension getExtensionByPath(String path) {
        return repository.findByPath(path);
    }
}