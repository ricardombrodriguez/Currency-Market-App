package yes.finance.repository;

import yes.finance.model.Extension;
import yes.finance.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtensionRepository extends JpaRepository<Extension, Integer> {
    Extension findByid(int id);

    Page<Extension> findByUser(User user, Pageable pageable);

    Extension findByPath(String path);
}
