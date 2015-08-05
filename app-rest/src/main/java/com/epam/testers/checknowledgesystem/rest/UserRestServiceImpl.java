package com.epam.testers.checknowledgesystem.rest;

import com.epam.testers.checknowledgesystem.model.User;
import com.epam.testers.checknowledgesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by xalf on 31/07/15.
 */
@RestController
public class UserRestServiceImpl implements UserRestService {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users")
    public @ResponseBody List<User> getUsers() {
        return userService.getUsers(0);
    }

    @Override
    @RequestMapping(value = "/users/{managerId}", method = RequestMethod.GET)
    public @ResponseBody List<User> getUsers(@PathVariable(value = "managerId") Integer managerId) {
        return userService.getUsers(managerId);
    }

    @Override
    @RequestMapping(value = "/user/{login}/{userName}/{password}/{roleName}/{managerLogin}", method = RequestMethod.POST)
    public @ResponseBody User addUser(@PathVariable(value = "login") String login,
                                      @PathVariable(value = "userName") String userName,
                                      @PathVariable(value = "password") String password,
                                      @PathVariable(value = "roleName") String roleName,
                                      @PathVariable(value = "managerLogin") String managerLogin) {
        return userService.addUser(login, userName, password, roleName, managerLogin);
    }
}
