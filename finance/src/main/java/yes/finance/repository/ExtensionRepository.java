package yes.finance.repository;

import yes.finance.model.Extension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtensionRepository extends JpaRepository<Extension,Integer> {
    Extension findById(int id);
}
