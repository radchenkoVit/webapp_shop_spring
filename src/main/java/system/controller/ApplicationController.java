package system.controller;

import net.lingala.zip4j.exception.ZipException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import system.entity.Application;
import system.entity.Category;
import system.exceptions.ApplicationExistsException;
import system.repository.CategoryRepository;
import system.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(path = "/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(path = "/all")
    public @ResponseBody
    List<Application> getAll() {
        return applicationService.getAll();
    }


    //TODO: very simplified
    //TODO: NOTE @RequestParam(value = "appName") value corresponds to 'name' attribute in html tag, example: name="appName"
    @PostMapping(path = "/add")//, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ModelAndView addApplication(@RequestParam(value = "appName") String applicationName,
                                @RequestParam(value = "appDesc") String appDescription,
                                @RequestParam(value = "categories") List<String> categories,
                                @RequestParam(value = "appUpload") MultipartFile file){

        Application application = new Application();
        application.setName(applicationName);
        application.setDescription(appDescription);

        List<Category> categoriesTest = categoryRepository.findAllByName(categories);
        application.setCategories(categoriesTest);

        try {
            applicationService.saveApplication(application, file);
        } catch (ApplicationExistsException | IOException | ZipException e) {
            return new ModelAndView("error", "message", String.format("Failed to add: %s", application.getName()));
        }

        ModelAndView addAppicationView = new ModelAndView("addApplication", "message", String.format("Application \"%s\" was added", application.getName()));
        return addAppicationView;
    }

    @PostMapping(path = "/download")
    public void download(HttpServletResponse response,
                         @RequestParam("appId") String appId){
        applicationService.downloadApp(response, Long.valueOf(appId));

    }
//
//    @PostMapping(path = "/download/{appId}")
//    public void download2(HttpServletResponse response,
//                         @PathVariable("appId") String appId){
//        applicationService.downloadApp(response, Long.valueOf(appId));
//
//
//    }
}
