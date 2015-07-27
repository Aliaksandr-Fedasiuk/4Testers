package com.epam.testers.checknowledgesystem.model;

/**
 * Created by xalf on 22/07/15.
 */
public class Constants {

    public static enum Role {
        ADMIN,MANAGER,SUBORDINATE;
        public static Role getById(int roleId) {
            return values()[roleId];
        }
    }

    public static enum Status {
        OPEN("OPEN"),
        IN_PROGRESS("IN PROGRESS"),
        CLOSED("CLOSED"),
        CANCEL("CANCEL");

        private String name;
        Status(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }


    public static enum Action {
        IMPORT("IMPORT REQUEST"),
        IMPORT_IN_PROGRESS("IMPORT IN PROGRESS REQUEST"),
        IMPORT_CLOSED("IMPORT CLOSED REQUEST"),
        CHANGE_STATUS_OPEN_IN_PROGRESS("CHANGE STATUS 'OPEN' TO 'IN PROGRESS'"),
        CHANGE_STATUS_OPEN_CLOSED("CHANGE STATUS 'OPEN' TO 'CLOSED'"),
        CHANGE_STATUS_IN_PROGRESS_CLOSED("CHANGE STATUS 'IN PROGRESS' TO 'CLOSED'"),
        CHANGE_STATUS_TO_CANCELED("CHANGE STATUS TO CANCELED"),
        DELETE_REQUEST("DELETE REQUEST");

        private String actionText;
        Action(String actionText) {
            this.actionText = actionText;
        }

        public String getActionText() {
            return actionText;
        }
    }

}
