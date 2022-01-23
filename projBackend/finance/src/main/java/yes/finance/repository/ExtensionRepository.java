package yes.finance.repository;

import yes.finance.model.Extension;
import yes.finance.model.User;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExtensionRepository extends JpaRepository<Extension, Integer> {
    Extension findByid(int id);

    List<Extension> findByUser(User user);

    Extension findByPath(String path);

    @Query("SELECT e FROM Extension e LEFT JOIN User u ON e.user = u WHERE u.id =:u_id")
    Page<Extension> getExtensionsByUser(@Param("u_id") int u_id, Pageable pageable);
}
