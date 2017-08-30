package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import system.entity.Application;
import system.entity.Category;
import system.service.ApplicationService;
import system.service.CategoryService;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    ApplicationService applicationService;

    @Autowired
    CategoryService categoryService;

    @GetMapping(path = "/")
    public ModelAndView init(){
        ModelAndView base = new ModelAndView();
        List<Application> applications = applicationService.getAll();
        List<String> categories = categoryService.getCategoriesName();

        base.addObject("applications", applications);
        base.addObject("categories", categories);
        base.setViewName("index");

        return base;
    }
}
