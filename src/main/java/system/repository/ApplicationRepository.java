package system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import system.model.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
