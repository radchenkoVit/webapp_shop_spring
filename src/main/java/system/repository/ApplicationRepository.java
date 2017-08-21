package system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import system.entity.Application;
import system.entity.Category;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Application findByName(String appName);
    List<Application> findByCategories(Category category);
}
