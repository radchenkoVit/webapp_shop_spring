package system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import system.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
