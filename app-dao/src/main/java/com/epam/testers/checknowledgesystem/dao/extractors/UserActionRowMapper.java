package com.epam.testers.checknowledgesystem.dao.extractors;

import com.epam.testers.checknowledgesystem.model.UserAction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by xalf on 24/07/15.
 */
public class UserActionRowMapper implements RowMapper<UserAction> {

    @Override
    public UserAction mapRow(ResultSet resultSet, int i) throws SQLException {
        UserActionExtractor userActionExtractor = new UserActionExtractor();
        return userActionExtractor.extractData(resultSet);
    }
}
