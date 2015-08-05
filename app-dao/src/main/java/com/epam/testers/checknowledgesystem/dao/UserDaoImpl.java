package com.epam.testers.checknowledgesystem.dao;

import com.epam.testers.checknowledgesystem.dao.extractors.UserRowMapper;
import com.epam.testers.checknowledgesystem.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
 * Created by xalf on 21/07/15.
 */
@Repository
public class UserDaoImpl implements UserDao {

    private final static Logger LOGGER = LogManager.getLogger();

    private JdbcTemplate jdbcTemplate;

    private static final String sqlUserById = "SELECT * FROM user WHERE userId = ?";
    private static final String sqlUserLoginAndPassword = "SELECT * FROM user WHERE login = ? and password = ? and deleted = false";
    private static final String sqlUserLogin = "SELECT * FROM user WHERE login = ? and deleted = false";
    private static final String sqlCheckLoginUnique = "SELECT count(userId) FROM user WHERE login = ? and deleted = false";
    private static final String sqlAllUsers = "SELECT * FROM user WHERE userId > 0 AND deleted = false ORDER BY login, username";
    private static final String sqlUsersByManagerId = "SELECT * FROM user WHERE managerId = ? AND userId > 0 AND deleted = FALSE ORDER BY login, username";
    private static final String insertUser = "INSERT INTO user(userId, roleId, managerId, userName, login, password, deleted, bonus) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String setUserBonus = "update user set bonus = ? where userId = ?";
    private static final String deleteUser = "update user set deleted = true where userId = ? and userId > 0";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User getAdmin() {
        LOGGER.debug("getAdmin()");
        User admin = jdbcTemplate.queryForObject(sqlUserById, new Object[]{0}, new UserRowMapper());
        return admin;
    }

    @Override
    public User getUser(Integer userId) {
        LOGGER.debug("getUser(" + userId + ")");
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sqlUserById, new Object[]{userId}, new UserRowMapper());
        } catch (EmptyResultDataAccessException ex) {

        }
        return user;
    }

    @Override
    public User getUser(String login) {
        LOGGER.debug("getUser(login = " + login + ")");
        User user = jdbcTemplate.queryForObject(sqlUserLogin, new Object[]{login}, new UserRowMapper());
        return user;
    }

    @Override
    public User getUser(String login, String password) {
        LOGGER.debug("getUser(login = " + login + ", password)");
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sqlUserLoginAndPassword, new Object[]{login, password}, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("Skip 'EmptyResultDataAccessException': getUser(login = " + login + ", password)");
        }
        return user;
    }

    @Override
    public Set<User> getUsers() {
        LOGGER.debug("getUsers()");
        List<User> users = jdbcTemplate.query(sqlAllUsers, new UserRowMapper());
        return new LinkedHashSet<>(users);
    }

    @Override
    public Set<User> getUsers(Integer managerId) {
        LOGGER.debug("getUsers(" + managerId + ")");
        List<User> users = jdbcTemplate.query(sqlUsersByManagerId, new Object[]{managerId}, new UserRowMapper());
        return new LinkedHashSet<>(users);
    }

    @Override
    public boolean isLoginUnique(String login) {
        LOGGER.debug("isLoginUnique(" + login + ")");
        Integer count = jdbcTemplate.queryForObject(sqlCheckLoginUnique, new Object[]{login}, Integer.class);
        return count.intValue() == 0;

    }

    @Override
    public Integer addUser(final User user) {
        LOGGER.debug("addUser(" + user + ")");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(insertUser, new int[]{1});
                if (user.getUserId() != null) {
                    ps.setInt(1, user.getUserId());
                } else {
                    ps.setNull(1, Types.INTEGER);
                }
                ps.setInt(2, user.getRole().ordinal());
                ps.setInt(3, user.getManagerId());
                ps.setString(4, user.getUserName());
                ps.setString(5, user.getLogin());
                ps.setString(6, user.getPassword());
                ps.setBoolean(7, user.getDeleted());
                ps.setInt(8, user.getBonus());
                return ps;
            }
        };
        jdbcTemplate.update(psc, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void deleteUser(Integer userId) {
        jdbcTemplate.update(deleteUser, new Object[]{userId});
    }

    @Override
    public void setUserBonus(Integer bonus, Integer userId) {
        jdbcTemplate.update(setUserBonus, new Object[]{bonus, userId});
    }
}
