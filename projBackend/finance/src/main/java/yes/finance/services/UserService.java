package yes.finance.services;

import yes.finance.model.User;
import yes.finance.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User saveUser(User user) {
        return repository.save(user);
    }

    public List<User> saveUsers(List<User> users) {
        return repository.saveAll(users);
    }

    public Page<User> getUsers(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public User getUserById(int id) {
        return repository.findById((int)id);
    }

    public boolean isEmailInUse(String email) {
        System.out.println(repository.findByEmail(email));
        return repository.findByEmail(email) != null;
    }

    public User getUserLogin(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }

    // IMPLEMENTAR TODOS OS FIND's QUE TIVERMOS NO UserRepository.java

    public String deleteUser(int id) {
        repository.deleteById(id);
        return "user (id="+ id +") removed!";
    }

    public User updateUser(User user) {
        User existingUser = repository.findById((int)user.getId());

        // FAZER SETS

        return saveUser(existingUser);
    }
}