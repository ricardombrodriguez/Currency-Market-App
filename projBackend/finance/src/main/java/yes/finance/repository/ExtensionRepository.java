package yes.finance.repository;

import yes.finance.model.Extension;
import yes.finance.model.Portfolio;
import yes.finance.model.User;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExtensionRepository extends JpaRepository<Extension, Integer> {
    Extension findByid(int id);

    List<Extension> findByUser(User user);

    Extension findByPath(String path);

    @Query("SELECT e.portfolios FROM Extension e WHERE e = :extension")
    List<Portfolio> findExtensionPortfolios(@Param("extension") Extension extension);

}
