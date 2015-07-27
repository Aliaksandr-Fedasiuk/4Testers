package com.epam.testers.checknowledgesystem.model;

import java.sql.Timestamp;

import static com.epam.testers.checknowledgesystem.model.Constants.*;

/**
 * Created by xalf on 21/07/15.
 */
public class UserRequest {

    private Integer requestId;

    private String requestName;

    private Integer requestSum;

    private Integer managerId;

    private Integer userId;

    private String managerName;

    private Status status;

    private Timestamp startDate;

    private Timestamp updateDate;

    private String textRequest;

    public UserRequest() {
    }

    public UserRequest(Integer requestId, String requestName, Integer requestSum, User manager, User user, Status status,
                       Timestamp startDate, Timestamp updateDate, String textRequest) {
        this.requestId = requestId;
        this.requestName = requestName;
        this.requestSum = requestSum;
        this.managerId = manager.getManagerId();
        this.managerName = manager.getLogin();
        this.userId = user.getUserId();
        this.status = status;
        this.startDate = startDate;
        this.updateDate = updateDate;
        this.textRequest = textRequest;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public Integer getRequestSum() {
        return requestSum;
    }

    public void setRequestSum(Integer requestSum) {
        this.requestSum = requestSum;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public String getTextRequest() {
        return textRequest;
    }

    public void setTextRequest(String textRequest) {
        this.textRequest = textRequest;
    }

    @Override
    public String toString() {
        return "UserRequest {" +
                "requestId=" + requestId +
                ", requestName='" + requestName + '\'' +
                ", requestSum=" + requestSum +
                ", managerId=" + managerId +
                ", userId=" + userId +
                ", managerName='" + managerName + '\'' +
                ", status=" + status +
                ", startDate=" + startDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
