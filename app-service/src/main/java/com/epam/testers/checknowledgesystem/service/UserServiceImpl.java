package com.epam.testers.checknowledgesystem.service;

import com.epam.testers.checknowledgesystem.dao.UserDao;
import com.epam.testers.checknowledgesystem.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.epam.testers.checknowledgesystem.model.Constants.*;

/**
 * Created by xalf on 04/08/15.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final static Logger LOGGER = LogManager.getLogger();

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers(Integer managerId) {
        LOGGER.debug("UserServiceImpl.getUsers (managerId = " + managerId + ")");
        Set<User> users = userDao.getUsers(managerId);
        return new ArrayList<>(users);
    }

    @Override
    public User addUser(String login, String userName, String password, String roleName, String managerLogin) {
        LOGGER.debug("UserServiceImpl.addUser (login = " + login + ", userName = " + userName
                + ", roleName = " + roleName + ", managerLogin = " + managerLogin + ")");

        Role role = Role.valueOf(roleName.toUpperCase());
        if (role == null) {
            throw new InvalidParameterException("Role Name is incorrect!");
        }

        User manager = userDao.getUser(managerLogin);
        if (manager == null) {
            throw new InvalidParameterException("Manager Login is incorrect!");
        }

        try {
            if (userDao.getUser(login) != null) {
                throw new InvalidParameterException("User Login should be unique!");
            }
        } catch (IncorrectResultSizeDataAccessException ex) {
            throw new InvalidParameterException("User Login should be unique!");
        }

        User user = new User(null, role, manager.getManagerId(), userName, login, password, false, 0);
        Integer userId = userDao.addUser(user);
        user.setUserId(userId);

        return user;
    }
}
