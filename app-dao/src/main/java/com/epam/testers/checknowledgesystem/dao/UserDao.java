package com.epam.testers.checknowledgesystem.dao;

import com.epam.testers.checknowledgesystem.model.User;

import java.util.Set;

/**
 * Created by xalf on 21/07/15.
 */
public interface UserDao {

    User getAdmin();

    User getUser(Integer userId);

    User getUser(String login);

    User getUser(String login, String password);

    Set<User> getUsers();

    Set<User> getUsers(Integer managerId);

    Integer addUser(User user);

    boolean isLoginUnique(String login);

    void deleteUser(Integer userId);

    void setUserBonus(Integer bonus, Integer userId);

}
