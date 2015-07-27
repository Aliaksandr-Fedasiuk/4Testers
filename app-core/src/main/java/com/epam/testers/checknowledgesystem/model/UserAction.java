package com.epam.testers.checknowledgesystem.model;

import com.epam.testers.checknowledgesystem.model.Constants.Action;

import java.sql.Timestamp;

/**
 * Created by xalf on 21/07/15.
 */
public class UserAction {

    private Integer actionId;

    private Integer userId;

    private Timestamp actionDate;

    private Action action;

    public UserAction() {
    }

    public UserAction(Integer actionId, Integer userId, Timestamp actionDate, Action action) {
        this.actionId = actionId;
        this.userId = userId;
        this.actionDate = actionDate;
        this.action = action;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getActionDate() {
        return actionDate;
    }

    public void setActionDate(Timestamp actionDate) {
        this.actionDate = actionDate;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "UserAction {" +
                "actionId=" + actionId +
                ", userId=" + userId +
                ", actionDate=" + actionDate +
                ", action=" + action +
                '}';
    }
}
