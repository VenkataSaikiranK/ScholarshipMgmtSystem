package com.dal.group7.persistent.implementations;

import com.dal.group7.persistent.interfaces.Dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import static com.dal.group7.constants.SQLConstants.getSelectAllQuery;

public class PwdEncryptDao extends Dao {
    private final ConnectionManager connectionManager;
    private static final String ENCRYPTION_TABLE = "encryption_logic";
    private static final String CHAR_COLUMN = "character";
    private static final String HASH_COLUMN = "hash";

    public PwdEncryptDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public HashMap<String, String> getValue() throws SQLException {
        try(var connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getSelectAllQuery(ENCRYPTION_TABLE))) {
            final var resultSet = preparedStatement.executeQuery();
            HashMap<String, String> map = new HashMap<String, String>();
            while (resultSet.next()) {
                map.put(resultSet.getString(CHAR_COLUMN),resultSet.getString(HASH_COLUMN));
            }
            return map;
        }
    }

}
