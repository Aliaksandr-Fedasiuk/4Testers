package com.epam.testers.checknowledgesystem.service;

import com.epam.testers.checknowledgesystem.model.User;

import java.util.List;

/**
 * Created by xalf on 04/08/15.
 */
public interface UserService {

    List<User> getUsers(Integer managerId);

    User addUser(String login, String userName, String password, String roleName, String managerLogin);

}
