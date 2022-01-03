package com.dal.group7.persistent.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class PwdEncryptDaoTest {
    private static final String INPUT_STRING = "a";
    private static final String INPUT_STRING_1 = "b";
    private static final String HASH_STRING = "xt";

    @Mock
    private ConnectionManager connectionManager;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private PwdEncryptDao pwdEncryptDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getValue_testEncryptionMap() throws SQLException {
        setUpMock();
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getString("character")).thenReturn(INPUT_STRING);
        Mockito.when(resultSet.getString("hash")).thenReturn(HASH_STRING);
        final HashMap<String, String> encMap= pwdEncryptDao.getValue();
        final HashMap<String,String> expectedMap = new HashMap<String, String>() {
            {
                put(INPUT_STRING,HASH_STRING);
            }
        };
        assertFalse(encMap.isEmpty());
        assertEquals(expectedMap.get(INPUT_STRING),encMap.get(INPUT_STRING));
    }

    @Test
    void getValue_testEncryptionEmptyMap() throws SQLException {
        setUpMock();
        Mockito.when(resultSet.next()).thenReturn(false);

        final HashMap<String, String> encMap= pwdEncryptDao.getValue();
        assertTrue(encMap.isEmpty());
    }

    @Test
    void getValue_testEncryptionMapFalse() throws SQLException {
        setUpMock();
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getString("character")).thenReturn(INPUT_STRING_1);
        Mockito.when(resultSet.getString("hash")).thenReturn(HASH_STRING);
        final HashMap<String, String> encMap= pwdEncryptDao.getValue();
        final HashMap<String,String> expectedMap = new HashMap<String, String>() {
            {
                put(INPUT_STRING,HASH_STRING);
            }
        };
        assertFalse(encMap.isEmpty());
        assertFalse(expectedMap.get(INPUT_STRING).equals(encMap.get(HASH_STRING)));
    }

    private void setUpMock() throws SQLException {
        Mockito.when(connectionManager.getConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }
}