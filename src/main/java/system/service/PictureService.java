package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.entity.Application;
import system.exceptions.NoPictureFoundException;
import system.repository.ApplicationRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class PictureService {

    @Autowired
    private ApplicationRepository appRepository;


    public byte[] getPreviewPicture(Long appId) throws NoPictureFoundException, IOException {
        Application application = appRepository.findOne(appId);
        File appDir = new File(application.getFilesPath());

        File previewPic = findPictureBy(appDir, "128");
        return Files.readAllBytes(Paths.get(previewPic.getAbsolutePath()));
    }

    public byte[] getMainPicture(Long appId) throws NoPictureFoundException, IOException {
        Application application = appRepository.findOne(appId);

        File appDir = new File(application.getFilesPath());
        File mainPic = findPictureBy(appDir, "512");
        return Files.readAllBytes(Paths.get(mainPic.getAbsolutePath()));
    }

    private File getPictureDir(File appDir){
        File[] matchingFiles = appDir.listFiles((dir, name) -> dir.isDirectory() && name.equalsIgnoreCase("pictures"));
        return matchingFiles[0];
    }

    private File findPictureBy(File appDir, String name) throws NoPictureFoundException {
        File pictureDir = getPictureDir(appDir);

        File[] pictures = pictureDir.listFiles();

        if (pictures != null && pictures.length == 0)
            throw new NoPictureFoundException("No pictures in application directory");

        File searchedPicture = null;

        for (int i = 0; i < pictures.length; i++){
            File currentPic = pictures[i];
            if (currentPic.getName().contains(name)){
                searchedPicture = currentPic;
                break;
            }
        }

        if (searchedPicture == null)
            throw new NoPictureFoundException(String.format("Picture with name pattern: [%s] is not found", name));

        return searchedPicture;
    }
}
