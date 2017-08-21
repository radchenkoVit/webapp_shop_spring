package system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import system.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
