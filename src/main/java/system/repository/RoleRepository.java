package system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import system.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
