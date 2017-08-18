package system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import system.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
