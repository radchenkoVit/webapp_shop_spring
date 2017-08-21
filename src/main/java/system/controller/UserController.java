package system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import system.entity.User;
import system.repository.ApplicationRepository;
import system.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService service;

    @Autowired
    ApplicationRepository applicationRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    List<User> getAllUsers() {
        LOG.debug("End point /all is reached");
        return service.getAll();
    }
}
