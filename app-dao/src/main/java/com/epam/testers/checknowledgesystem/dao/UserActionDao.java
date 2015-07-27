package com.epam.testers.checknowledgesystem.dao;

import com.epam.testers.checknowledgesystem.model.UserAction;

import java.util.Set;

/**
 * Created by xalf on 23/07/15.
 */
public interface UserActionDao {

    void addUserAction(UserAction userAction);

    Set<UserAction> getUserActions(Integer userId);

}
