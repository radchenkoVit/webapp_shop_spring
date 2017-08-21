package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import system.entity.Application;
import system.service.ApplicationService;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    ApplicationService service;

    @GetMapping(path = "/")
    public ModelAndView init(){
        ModelAndView base = new ModelAndView();
        List<Application> applications = service.getAll();

        base.addObject("applications", applications);
        base.setViewName("index");

        return base;
    }
}
