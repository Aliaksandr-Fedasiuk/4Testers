package com.epam.testers.checknowledgesystem.dao.extractors;

import com.epam.testers.checknowledgesystem.model.Constants;
import com.epam.testers.checknowledgesystem.model.UserAction;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by xalf on 24/07/15.
 */
public class UserActionExtractor implements ResultSetExtractor<UserAction> {
    @Override
    public UserAction extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        UserAction userAction = new UserAction();
        userAction.setActionId(resultSet.getInt(1));
        userAction.setUserId(resultSet.getInt(2));
        userAction.setActionDate(resultSet.getTimestamp(3));
        userAction.setAction(Constants.Action.values()[resultSet.getInt(4)]);
        return userAction;
    }
}
