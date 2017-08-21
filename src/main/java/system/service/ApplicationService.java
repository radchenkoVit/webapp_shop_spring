package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.exceptions.ApplicationExistsException;
import system.entity.Application;
import system.entity.Category;
import system.repository.ApplicationRepository;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository appRepository;

    public List<Application> getAll(){
        return appRepository.findAll();
    }

    public List<Application> getApplication(Category category){
        return appRepository.findByCategories(category);
    }

    public void saveApplication(Application application) throws ApplicationExistsException {
        // validation part
        String applicationName = application.getName();
        if (appRepository.findByName(applicationName) != null){
            throw new ApplicationExistsException(String.format("Application with name: %s already exists", applicationName));
        }

        appRepository.save(application);
    }
}
