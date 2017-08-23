package system.utils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;

public class ZipSaver {
    private static Logger logger = LoggerFactory.getLogger(ZipSaver.class);
    private static final String appsFolderPath = Paths.get("applicationData", "apps").toAbsolutePath().toString();

    public static void filter(String path){
        File appDirectory = new File(path);

        FileFilter descriptionFilter = new WildcardFileFilter("*.txt");
        File[] files = appDirectory.listFiles(descriptionFilter);
        if (files != null && files.length == 0) throw new RuntimeException("No description file inside app folder");
        filterFiles(files, "description");


        FileFilter pictureFilter = new WildcardFileFilter("*.jpg");
        File[] pictureFiles = appDirectory.listFiles(pictureFilter);
        if (pictureFiles != null && pictureFiles.length == 0) throw new RuntimeException("No pictures file inside app folder");
        filterFiles(pictureFiles, "pictures");

    }

    private static void filterFiles(File[] files, String filterDirName){
        Arrays.stream(files).forEach(fileSource -> {
            String path = Paths.get(fileSource.getParentFile().getAbsolutePath(), filterDirName, fileSource.getName()).toAbsolutePath().toString();
            File descFileCopy = new File(path);
            try {
                FileUtils.copyFile(fileSource, descFileCopy);
                if (!fileSource.delete()){
                    logger.debug(String.format("Failed to delete file: %s", fileSource.getName()));
                }
            } catch (IOException e) {
                logger.debug(String.format("IOException during deleting, error: %s", e.getMessage()));
            }
        });
    }

    public static String extract(File sourceFile) throws ZipException, IOException {
        ZipFile zipFile = new ZipFile(sourceFile);
        if (!zipFile.isValidZipFile()){
            throw new ZipException("Not valid zip file");
        }
        String appPath = Paths.get(appsFolderPath, UUID.randomUUID().toString()).toAbsolutePath().toString();

        zipFile.extractAll(appPath);
        File dir = new File(appPath);
        FileUtils.copyFileToDirectory(sourceFile, dir); //TODO: split it

        return appPath;
    }

}
