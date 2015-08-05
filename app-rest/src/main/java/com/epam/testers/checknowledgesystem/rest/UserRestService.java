package com.epam.testers.checknowledgesystem.rest;

import com.epam.testers.checknowledgesystem.model.Constants;
import com.epam.testers.checknowledgesystem.model.User;

import java.util.List;

/**
 * Created by xalf on 31/07/15.
 */

public interface UserRestService {

    List<User> getUsers(Integer managerId);

    User addUser(String login, String userName, String password, String roleName, String managerLogin);

}
