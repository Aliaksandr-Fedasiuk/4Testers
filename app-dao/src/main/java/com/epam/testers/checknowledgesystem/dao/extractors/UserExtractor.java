package com.epam.testers.checknowledgesystem.dao.extractors;

import com.epam.testers.checknowledgesystem.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.testers.checknowledgesystem.model.Constants.Role;

/**
 * Created by xalf on 22/07/15.
 */
public class UserExtractor implements ResultSetExtractor<User> {
    @Override
    public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        User user = new User();
        user.setUserId(resultSet.getInt(1));
        user.setRole(Role.getById(resultSet.getInt(2)));
        user.setManagerId(resultSet.getInt(3));
        user.setUserName(resultSet.getString(4));
        user.setLogin(resultSet.getString(5));
        user.setPassword(resultSet.getString(6));
        user.setDeleted(resultSet.getBoolean(7));
        user.setBonus(resultSet.getInt(8));
        return user;
    }
}
