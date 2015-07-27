package com.epam.testers.checknowledgesystem;

import com.epam.testers.checknowledgesystem.dao.UserDao;
import com.epam.testers.checknowledgesystem.dao.UserRequestDao;
import com.epam.testers.checknowledgesystem.model.User;
import com.epam.testers.checknowledgesystem.model.UserRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import static com.epam.testers.checknowledgesystem.model.Constants.Status;
import static org.junit.Assert.assertTrue;

/**
 * Created by xalf on 24/07/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/testDaoContext.xml"})
public class UserRequestDaoImplTest {

    private final static Logger LOGGER = LogManager.getLogger();

    @Autowired
    public UserDao userDao;

    @Autowired
    public UserRequestDao userRequestDao;

    @Before
    public void initialize() throws Exception {
    }

    @Test
    public void testGetUserActions() throws Exception {
        Set<User> managers = userDao.getUsers(0);
        User user = managers.iterator().next();

        Set<UserRequest> userRequests = userRequestDao.getUserRequests(user.getUserId());
        Integer beforeSize = userRequests.size();

        Timestamp now = new Timestamp((new Date()).getTime());
        UserRequest userRequest = new UserRequest(null, "TestRequest", 100, user, user, Status.OPEN, now, now, "file content");

        Integer requestId = userRequestDao.addUserRequest(userRequest);
        assertTrue(requestId > 0);

        userRequests = userRequestDao.getUserRequests(user.getUserId());
        assertTrue(userRequests.size() == beforeSize + 1);
    }

}
