package com.epam.testers.checknowledgesystem.dao.extractors;

import com.epam.testers.checknowledgesystem.model.UserRequest;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by xalf on 24/07/15.
 */
public class UserRequestRowMapper implements RowMapper<UserRequest> {

    @Override
    public UserRequest mapRow(ResultSet resultSet, int i) throws SQLException {
        UserRequestExtractor userRequestExtractor = new UserRequestExtractor();
        return userRequestExtractor.extractData(resultSet);
    }
}
