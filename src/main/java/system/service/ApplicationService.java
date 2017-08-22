package system.service;

import net.lingala.zip4j.exception.ZipException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import system.entity.Application;
import system.entity.Category;
import system.exceptions.ApplicationExistsException;
import system.repository.ApplicationRepository;
import system.utils.ZipSaver;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ApplicationService {

    private Logger logger = LoggerFactory.getLogger(ApplicationService.class);

    @Autowired
    private ApplicationRepository appRepository;

    public List<Application> getAll(){
        return appRepository.findAll();
    }

    public List<Application> getApplication(Category category){
        return appRepository.findByCategories(category);
    }

    public Application saveApplication(Application application, MultipartFile file) throws ApplicationExistsException, ZipException, IOException {
        // validation part
        String applicationName = application.getName();
        if (appRepository.findByName(applicationName) != null){
            throw new ApplicationExistsException(String.format("Application with name: %s already exists", applicationName));
        }

        //convert files MultipartFile(Spring) to Java File
        File tempConvFile = new File(file.getOriginalFilename());
        file.transferTo(tempConvFile); // transfer files from UI to project location

        //extract file to correct directory
        String appFilesPath = ZipSaver.extract(tempConvFile);
        ZipSaver.filter(appFilesPath);

        // delete temp file
        if (tempConvFile.delete()){ // delete file here
            logger.debug(String.format("File %s is not deleted", tempConvFile.getName()));
        }

        application.setFilesPath(appFilesPath);
        return appRepository.save(application);
    }
}
