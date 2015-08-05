package com.epam.testers.checknowledgesystem.util;

import com.epam.testers.checknowledgesystem.dao.UserDao;
import com.epam.testers.checknowledgesystem.model.Constants;
import com.epam.testers.checknowledgesystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.ServletException;

/**
 * Created by xalf on 05/08/15.
 */
public class AppInitializer {

    @Autowired
    private UserDao userDao;

    public void onStartup() throws ServletException {
        if (userDao.getUser(0) == null) {
            User user = new User(0, Constants.Role.ADMIN, null, "admin", "admin", "admin", false, 0);
            Integer userId = userDao.addUser(user);
            if (userDao.getUser(userId) == null) {
                throw new IllegalStateException("Database initialization exception during init application!");
            }
        }
    }
}
