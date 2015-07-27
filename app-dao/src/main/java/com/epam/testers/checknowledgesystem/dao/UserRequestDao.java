package com.epam.testers.checknowledgesystem.dao;

import com.epam.testers.checknowledgesystem.model.UserRequest;

import java.util.Set;

/**
 * Created by xalf on 24/07/15.
 */
public interface UserRequestDao {

    Integer addUserRequest(UserRequest userRequest);

    UserRequest getUserRequest(Integer requestId);

    Set<UserRequest> getUserRequests(Integer userId);

}
