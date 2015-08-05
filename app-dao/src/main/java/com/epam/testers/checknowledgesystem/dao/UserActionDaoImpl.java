package com.epam.testers.checknowledgesystem.dao;

import com.epam.testers.checknowledgesystem.dao.extractors.UserActionRowMapper;
import com.epam.testers.checknowledgesystem.model.UserAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by xalf on 23/07/15.
 */
@Repository
public class UserActionDaoImpl implements UserActionDao {

    private final static Logger LOGGER = LogManager.getLogger();

    private JdbcTemplate jdbcTemplate;

    private static final String sqlUserActionsByUserId = "SELECT * FROM UserAction WHERE userId = ?";
    private static final String insertUserAction = "INSERT INTO UserAction (actionId, userId, actionDate, actionType, actionName) VALUES (?, ?, ?, ?, ?)";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addUserAction(UserAction userAction) {
        LOGGER.debug("addUserAction(" + userAction + ")");
        jdbcTemplate.update(insertUserAction, userAction.getActionId(), userAction.getUserId(),
                userAction.getActionDate(), userAction.getAction().ordinal(), userAction.getAction().getActionText());
    }

    @Override
    public Set<UserAction> getUserActions(Integer userId) {
        LOGGER.debug("getUserActions(" + userId + ")");
        List<UserAction> userActions = jdbcTemplate.query(sqlUserActionsByUserId, new Object[]{userId}, new UserActionRowMapper());
        return new LinkedHashSet<>(userActions);
    }
}
