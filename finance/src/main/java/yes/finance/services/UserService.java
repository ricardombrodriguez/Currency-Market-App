package yes.finance.services;

import yes.finance.model.User;
import yes.finance.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User getUserById(int id) {
        return repository.findById((int)id);
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