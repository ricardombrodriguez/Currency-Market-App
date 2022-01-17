package yes.finance.repository;

import yes.finance.model.Extension;
import yes.finance.model.User;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtensionRepository extends JpaRepository<Extension, Integer> {
    Extension findByid(int id);

    List<Extension> findByUser(User user);

    Extension findByPath(String path);
}
