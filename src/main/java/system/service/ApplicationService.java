package system.service;

import net.coobird.thumbnailator.Thumbnails;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.filefilter.WildcardFileFilter;
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

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
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

    public List<Application> getAll(){
        return appRepository.findAll();
    }

    public Application getBy(Long id){
        return appRepository.findOne(id);
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


        File tempConvFile = null;
        String appFilesPath;
        try {
            //convert files MultipartFile(Spring) to Java File
            tempConvFile = new File(file.getOriginalFilename());
            file.transferTo(tempConvFile); // transfer files from UI to project location

            //extract file to correct directory
            appFilesPath = ZipSaver.extract(tempConvFile);
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

    public void downloadApp(HttpServletResponse response, Long appId){
        Application application = appRepository.findOne(appId);

        File appDir = new File(application.getFilesPath());
        FileFilter filter = new WildcardFileFilter("*.zip");
        File files[] = appDir.listFiles(filter);

        if (files != null && files.length == 0) throw new RuntimeException("No zip files found");
        Path file = Paths.get(files[0].getAbsolutePath()); //TODO using PATH under question?

        response.setContentType("application/zip");
        response.setHeader( "Content-Disposition", "attachment; filename=application.zip");
        try
        {
            Files.copy(file, response.getOutputStream());
            response.getOutputStream().flush();
            application.setDownloadedTimes(application.getDownloadedTimes() + 1);//TODO: increase downloading time
            appRepository.saveAndFlush(application);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //TODO: make it better - getting images
    public byte[] getPicture(Long appId) throws IOException {
        Application application = appRepository.findOne(appId);

        File appDir = new File(application.getFilesPath());
        File picture = getMainPicture(appDir);
        return Files.readAllBytes(Paths.get(picture.getAbsolutePath()));
    }

    private File getMainPicture(File appDir) throws IOException {
        File[] files = appDir.listFiles();

        if (files != null && files.length == 0) throw new RuntimeException("No files");

        File picture = null;
        for (int i = 0; i < files.length; i++){
            File file = files[i];
            if (file.isDirectory() && file.getName().equalsIgnoreCase("pictures")){
                if (file.list().length != 0) {
                    picture = file.listFiles()[0];
                    return picture;
                } else {
                    throw new RuntimeException("No Pictures found");
                }
            }
        }

        throw new RuntimeException("No Pictures is found");
    }
}
