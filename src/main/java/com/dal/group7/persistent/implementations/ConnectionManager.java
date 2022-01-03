package com.dal.group7.persistent.implementations;

import com.dal.group7.config.ApplicationConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration();

    public Connection getConnection() throws SQLException {
            return DriverManager.getConnection(applicationConfiguration.getDbUrl());
    }
}
