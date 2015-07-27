package com.epam.testers.checknowledgesystem.dao.extractors;

import com.epam.testers.checknowledgesystem.model.UserRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.io.*;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.testers.checknowledgesystem.model.Constants.Status;

/**
 * Created by xalf on 24/07/15.
 */
public class UserRequestExtractor implements ResultSetExtractor<UserRequest> {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public UserRequest extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        UserRequest userRequest = new UserRequest();
        userRequest.setRequestId(resultSet.getInt(1));
        userRequest.setRequestName(resultSet.getString(2));
        userRequest.setRequestSum(resultSet.getInt(3));
        userRequest.setManagerId(resultSet.getInt(4));
        userRequest.setManagerName(resultSet.getString(5));
        userRequest.setUserId(resultSet.getInt(6));
        userRequest.setStatus(Status.values()[resultSet.getInt(7)]);
        userRequest.setStartDate(resultSet.getTimestamp(9));
        userRequest.setUpdateDate(resultSet.getTimestamp(10));
        if (resultSet.getBlob(11) != null) {
            userRequest.setTextRequest(blobToString(resultSet.getBlob(11)));
        }
        return userRequest;
    }

    private String blobToString(Blob blob) throws SQLException {
        String aux;
        StringBuffer strOut = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(blob.getBinaryStream()));
        try {
            while ((aux = br.readLine()) != null) {
                strOut.append(aux);
            }
        } catch (IOException e) {
            LOGGER.error("UserRequestExtractor.blobToString() :: " + e.fillInStackTrace());
        }
        return strOut.toString();
    }

}
