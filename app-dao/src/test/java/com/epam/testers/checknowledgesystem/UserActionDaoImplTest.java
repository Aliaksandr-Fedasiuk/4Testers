package com.epam.testers.checknowledgesystem;

import com.epam.testers.checknowledgesystem.dao.UserActionDao;
import com.epam.testers.checknowledgesystem.dao.UserDao;
import com.epam.testers.checknowledgesystem.model.Constants;
import com.epam.testers.checknowledgesystem.model.User;
import com.epam.testers.checknowledgesystem.model.UserAction;
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

import static org.junit.Assert.assertTrue;

/**
 * Created by xalf on 24/07/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/testDaoContext.xml"})
public class UserActionDaoImplTest {

    private final static Logger LOGGER = LogManager.getLogger();

    @Autowired
    public UserDao userDao;

    @Autowired
    public UserActionDao userActionDao;

    @Before
    public void initialize() throws Exception {
    }

    @Test
    public void testGetUserActions() throws Exception {
        Set<User> managers = userDao.getUsers(0);
        User user = managers.iterator().next();
        Set<UserAction> actions = userActionDao.getUserActions(user.getUserId());
        Integer beforeSize = actions.size();

        Timestamp now = new Timestamp((new Date()).getTime());
        UserAction userAction = new UserAction(null, user.getUserId(), now, Constants.Action.IMPORT);
        userActionDao.addUserAction(userAction);

        actions = userActionDao.getUserActions(user.getUserId());
        assertTrue(actions.size() == beforeSize + 1);
    }

}
