package com.epam.testers.checknowledgesystem.dao;

import com.epam.testers.checknowledgesystem.dao.extractors.UserRequestRowMapper;
import com.epam.testers.checknowledgesystem.model.UserRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by xalf on 24/07/15.
 */
@Repository
public class UserRequestDaoImpl implements UserRequestDao {

    private final static Logger LOGGER = LogManager.getLogger();

    private JdbcTemplate jdbcTemplate;

    private static final String sqlUserRequestsByUserId = "SELECT * FROM UserRequest WHERE userId = ?";
    private static final String insertUserRequest = "INSERT INTO UserRequest (requestId, requestName, requestSum, managerId, managerName, userId, statusId, statusName, startDate, updateDate, textRequest) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Integer addUserRequest(final UserRequest userRequest) {
        LOGGER.debug("addUserRequest(" + userRequest + ")");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(insertUserRequest, new int[]{1});
                ps.setNull(1, Types.INTEGER);
                ps.setString(2, userRequest.getRequestName());
                ps.setInt(3, userRequest.getRequestSum());
                ps.setInt(4, userRequest.getManagerId());
                ps.setString(5, userRequest.getManagerName());
                ps.setInt(6, userRequest.getUserId());
                ps.setInt(7, userRequest.getStatus().ordinal());
                ps.setString(8, userRequest.getStatus().getName());
                ps.setTimestamp(9, userRequest.getStartDate());
                ps.setTimestamp(10, userRequest.getUpdateDate());
                ps.setNull(11, Types.CLOB);
                return ps;
            }
        };
        jdbcTemplate.update(psc, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public UserRequest getUserRequest(Integer requestId) {
        return null;
    }

    @Override
    public Set<UserRequest> getUserRequests(Integer userId) {
        LOGGER.debug("getUserRequests(userId = " + userId + ")");
        List<UserRequest> userActions = jdbcTemplate.query(sqlUserRequestsByUserId, new Object[]{userId}, new UserRequestRowMapper());
        return new LinkedHashSet<>(userActions);
    }
}
