package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import system.exceptions.ApplicationExistsException;
import system.model.Application;
import system.model.Category;
import system.repository.CategoryRepository;
import system.service.ApplicationService;

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
    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody
    ModelAndView addApplication(@RequestParam(value = "appName") String applicationName,
                                @RequestParam(value = "appDesc") String appDescription,
                                @RequestParam(value = "categories") List<String> categories){

        Application application = new Application();
        application.setName(applicationName);

        List<Category> categoriesTest = categoryRepository.findAllByName(categories);
        application.setCategories(categoriesTest);

        try {
            applicationService.saveApplication(application);
        } catch (ApplicationExistsException e) {
            return new ModelAndView("error", "message", String.format("Failed to add: %s", application.getName()));
        }

        ModelAndView errorView = new ModelAndView("addApplication", "message", String.format("Application \"%s\" was added", application.getName()));
        return errorView;
    }
}
