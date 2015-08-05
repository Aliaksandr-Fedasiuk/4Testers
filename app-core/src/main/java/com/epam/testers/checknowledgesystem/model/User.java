package com.epam.testers.checknowledgesystem.model;

import static com.epam.testers.checknowledgesystem.model.Constants.*;

/**
 * Created by xalf on 21/07/15.
 */
public class User {

    private Integer userId;

    private Role role = Role.SUBORDINATE;

    private Integer managerId = 0;

    private String userName;

    private String login;

    private transient String password;

    private Boolean deleted = Boolean.FALSE;

    private Integer bonus = 0;

    public User() {
    }

    public User(Integer userId, Role role, Integer managerId, String userName, String login, String password, Boolean deleted, Integer bonus) {
        this.userId = userId;
        this.role = role;
        if ((role.equals(Role.ADMIN)) || (role.equals(Role.MANAGER))) {
            this.managerId = 0;
        } else {
            this.managerId = managerId;
        }
        this.userName = userName;
        this.login = login;
        this.password = password;
        this.deleted = deleted;
        this.bonus = bonus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
        if ((role.equals(Role.ADMIN)) || (role.equals(Role.MANAGER))) {
            this.managerId = 0;
        }
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        if ((role.equals(Role.ADMIN)) || (role.equals(Role.MANAGER))) {
            this.managerId = 0;
        } else {
            this.managerId = managerId;
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return "User {" +
                "userId=" + userId +
                ", role=" + role +
                ", managerId=" + managerId +
                ", userName='" + userName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", deleted=" + deleted +
                ", bonus=" + bonus +
                '}';
    }
}
