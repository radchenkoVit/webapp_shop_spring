package system.service;

import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import system.entity.Application;
import system.entity.Category;
import system.exceptions.ApplicationExistsException;
import system.repository.ApplicationRepository;
import system.utils.ZipSaver;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ApplicationService {

    private Logger logger = LoggerFactory.getLogger(ApplicationService.class);

    @Autowired
    private ApplicationRepository appRepository;

    @Cacheable("all_applications")
    public List<Application> getAll(){
        return appRepository.findAll();
    }

    public Application getBy(Long id){
        return appRepository.findOne(id);
    }

    @Cacheable("category_applications")
    public List<Application> getApplication(Category category){
        return appRepository.findByCategories(category);
    }

    @Cacheable("top_applications")
    public List<Application> getTopApplications(){
        List<Application> applications = appRepository.findAll();
        applications.sort((app1, app2) -> app2.getDownloadedTimes() - app1.getDownloadedTimes());

        return applications;
    }

    @CacheEvict(value = {"all_applications", "category_applications", "top_applications"}, allEntries = true)
    public Application saveApplication(Application application, MultipartFile file) throws ApplicationExistsException, ZipException, IOException {
        // validation part
        String applicationName = application.getName();
        if (appRepository.findByName(applicationName) != null){
            throw new ApplicationExistsException(String.format("Application with name: %s already exists", applicationName));
        }


        File tempConvFile = null;
        String appFilesPath;
        try {
            //convert files MultipartFile(Spring) to Java File
            tempConvFile = new File(file.getOriginalFilename());
            file.transferTo(tempConvFile); // transfer files from UI to project location

            //extract file to correct directory
            appFilesPath = ZipSaver.extract(tempConvFile);
            //copy zip file to extracted application directory
            FileUtils.copyFileToDirectory(tempConvFile, new File(appFilesPath));
            ZipSaver.filter(appFilesPath);
        } finally {
            // delete temp file
            if (tempConvFile != null && tempConvFile.delete()){ // delete file here
                logger.debug(String.format("File %s is not deleted", tempConvFile.getName()));
            }
        }


        application.setFilesPath(appFilesPath);
        return appRepository.save(application);
    }

    @CacheEvict(value = {"all_applications", "category_applications", "top_applications"}, allEntries = true)
    public void downloadApplication(HttpServletResponse response, Long appId){
        Application application = appRepository.findOne(appId);

        File appDir = new File(application.getFilesPath());
        FileFilter filter = new WildcardFileFilter("*.zip");
        File files[] = appDir.listFiles(filter);

        if (files != null && files.length == 0) throw new RuntimeException("No zip files found");
        Path file = Paths.get(files[0].getAbsolutePath());

        response.setContentType("application/zip");
        response.setHeader( "Content-Disposition", String.format("attachment; filename=%s.zip", file.toFile().getName()));
        try
        {
            Files.copy(file, response.getOutputStream());
            response.getOutputStream().flush(); // send data throw http protocol
            application.setDownloadedTimes(application.getDownloadedTimes() + 1);
            appRepository.saveAndFlush(application);//save increased downloaded time to DB
        }
        catch (IOException e) {
            logger.debug(String.format("Failed to send application, error: %s", e.getMessage()));
        }
    }
}
