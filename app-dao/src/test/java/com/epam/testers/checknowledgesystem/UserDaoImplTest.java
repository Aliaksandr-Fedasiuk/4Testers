package com.epam.testers.checknowledgesystem;

import com.epam.testers.checknowledgesystem.dao.UserDao;
import com.epam.testers.checknowledgesystem.model.Constants;
import com.epam.testers.checknowledgesystem.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test-dao-context.xml"})
public class UserDaoImplTest {

    private final static Logger LOGGER = LogManager.getLogger();

    @Autowired
    public UserDao userDao;

    private User user = new User(null, Constants.Role.MANAGER, 0, "FIO manager3", "manager3", "1234", false, 0);

    @Before
    public void initialize() throws Exception {
        User user = userDao.getUser("manager3", "1234");
        if (user != null) {
            userDao.deleteUser(user.getUserId());
        }
    }

    @Test
    public void testGetUsers() throws Exception {
        Set<User> users = userDao.getUsers();
        Integer beforeSize = users.size();
        assertTrue(beforeSize > 0);

        Integer result = userDao.addUser(user);
        assertTrue(result != null);

        userDao.deleteUser(result);
        users = userDao.getUsers();
        assertTrue(beforeSize == users.size());
    }

    @Test
    public void testGetUserById() throws Exception {
        Integer result = userDao.addUser(user);
        assertTrue(result != null);

        user = userDao.getUser(result);
        assertTrue(user != null);
        assertTrue(result == user.getUserId());
    }

    @Test
    public void testGetUserByLogin() throws Exception {
        Integer result = userDao.addUser(user);
        assertTrue(result != null);

        user = userDao.getUser("manager3", "1234");
        assertTrue(user != null);
        assertTrue(result == user.getUserId());
    }

    @Test
    public void testGetUsersByManagerId() throws Exception {
        Set<User> users = userDao.getUsers(0);
        assertTrue(users.size() > 0);
    }

    @Test
    public void testGetAdmin() throws Exception {
        User admin = userDao.getAdmin();
        assertTrue(admin.getUserId().equals(0));
    }

    @Test
    public void testAddUser() {
        Set<User> users = userDao.getUsers(0);
        Integer beforeSize = users.size();

        Integer result = userDao.addUser(user);
        assertTrue(result != null);

        users = userDao.getUsers(0);
        assertTrue(users.size() == beforeSize + 1);
    }

    @Test
    public void testIsLoginUnique() {
        userDao.addUser(user);
        assertTrue(!userDao.isLoginUnique(user.getLogin()));
    }

    @Test
    public void testSetUserBonus() {
        Integer result = userDao.addUser(user);
        assertTrue(result != null);

        Integer startUserBonus = user.getBonus();
        userDao.setUserBonus(user.getBonus() * 2, result);

        user = userDao.getUser(result);
        assertTrue(user != null);
        assertTrue(user.getBonus() == startUserBonus * 2);
    }

}