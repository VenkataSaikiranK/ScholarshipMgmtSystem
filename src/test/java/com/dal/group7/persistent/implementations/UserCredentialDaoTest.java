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

import static org.mockito.ArgumentMatchers.any;

class UserCredentialDaoTest {

  public static  final String EMAIL_ID = "test@dal.ca";

  @Mock
  private ConnectionManager connectionManager;

  @Mock
  private Connection connection;

  @Mock
  private PreparedStatement preparedStatement;

  @Mock
  private ResultSet resultSet;

  @InjectMocks
  private UserCredentialDao userCredentialDao;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }


  @Test
  void updateLastLoginTimeTest() throws SQLException{
    setUpMock();
    userCredentialDao.updateLastLoginTime(EMAIL_ID);
    Mockito.verify(preparedStatement).executeUpdate();
  }

  private void setUpMock() throws SQLException {
    Mockito.when(connectionManager.getConnection()).thenReturn(connection);
    Mockito.when(connection.prepareStatement(any())).thenReturn(preparedStatement);
    Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
  }
}