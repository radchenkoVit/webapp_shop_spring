package system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import system.model.User;
import system.repository.ApplicationRepository;
import system.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    ApplicationRepository applicationRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    List<User> getAllUsers() {
        return service.getAll();
    }
}
