package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
        ModelAndView mainPage = new ModelAndView();
        List<Application> applications = applicationService.getTopApplications();
        List<Category> categories = categoryService.getAll();
        List<Application> appCategories = applicationService.getApplication(categories.get(0)); // all applications by this category

        mainPage.addObject("applications", applications);
        mainPage.addObject("categories", categories);
        mainPage.addObject("applications_by_category", appCategories);
        mainPage.setViewName("index");

        return mainPage;
    }

    @GetMapping(path = "/category")
    public ModelAndView mainByCategory(HttpServletRequest request){
        Map<String, String[]> map = request.getParameterMap();
        String categoryId = map.get("categoryId")[0];

        ModelAndView mainPage = new ModelAndView();
        List<Application> applications = applicationService.getTopApplications();
        List<Category> categories = categoryService.getAll();

        Category category = categoryService.getBy(Integer.parseInt(categoryId));
        List<Application> appCategories = applicationService.getApplication(category); // all applications by this category

        mainPage.addObject("applications", applications);
        mainPage.addObject("categories", categories);
        mainPage.addObject("applications_by_category", appCategories);
        mainPage.setViewName("index");

        return mainPage;
    }

    @GetMapping(path = "/application")
    public ModelAndView applicationPage(HttpServletRequest request){
        Map<String, String[]> map = request.getParameterMap();
        String appId = map.get("appId")[0];

        Application application = applicationService.getBy(Long.valueOf(appId));
        ModelAndView applicationDownloadPage = new ModelAndView();
        applicationDownloadPage.setViewName("application");
        applicationDownloadPage.addObject("app", application);

        List<Application> applications = applicationService.getAll();//TODO: should be most downloaded
        applicationDownloadPage.addObject("applications", applications);

        return applicationDownloadPage;
    }
}
