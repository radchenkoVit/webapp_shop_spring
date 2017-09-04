package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import system.entity.Application;
import system.entity.Category;
import system.service.ApplicationService;
import system.service.CategoryService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
        List<Category> categories = categoryService.getAll();
        List<Application> appCategories = applicationService.getApplication(categories.get(0)); // all applications by this category

        base.addObject("applications", applications);
        base.addObject("categories", categories);
        base.addObject("applications_by_category", appCategories);
        base.setViewName("index");

        return base;
    }

    @GetMapping(path = "/category/{categotyId}")
    public ModelAndView initCat(@PathVariable("categotyId") Integer categoryId){
        ModelAndView base = new ModelAndView();
        List<Application> applications = applicationService.getAll();
        List<Category> categories = categoryService.getAll();

        Category category = categoryService.getBy(categoryId);
        List<Application> appCategories = applicationService.getApplication(category); // all applications by this category

        base.addObject("applications", applications);
        base.addObject("categories", categories);
        base.addObject("applications_by_category", appCategories);
        base.setViewName("index");

        return base;
    }
}
