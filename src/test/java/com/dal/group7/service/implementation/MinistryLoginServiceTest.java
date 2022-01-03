package com.dal.group7.service.implementation;

import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.UserCredential;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

class MinistryLoginServiceTest {

  private static final String PASSWORD = "password";
  private static final String USERNAME = "username";
  private static final String YES = "yes";
  private static final UserCredential CREDENTIALS = new UserCredential(USERNAME, PASSWORD, YES, YES,
          "2020-01-10", "1", "NONE", "NONE",
          "NONE", "0", "ministry", "NO");
  @Mock
  private Dao<String, UserCredential> userCredentialDao;

  @Mock
  private PwdEncrypt pwdEncrypt;

  @InjectMocks
  private MinistryLoginService ministryLoginService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldReturnCredentialsIfUsernameAndPasswordAreValid() throws SQLException {
    Mockito.when(pwdEncrypt.getEncryptedPwd(any())).thenReturn(PASSWORD);
    Mockito.when(userCredentialDao.doesExist(any())).thenReturn(true);
    Mockito.when(userCredentialDao.get(any())).thenReturn(Optional.of(CREDENTIALS));

    final UserCredential userCredential = ministryLoginService.userLogin(USERNAME, PASSWORD);

    assertEquals(CREDENTIALS, userCredential);
  }

  @Test
  void shouldThrowExceptionIfUsernameDoesntExist() throws SQLException {
    Mockito.when(pwdEncrypt.getEncryptedPwd(any())).thenReturn(PASSWORD);
    Mockito.when(userCredentialDao.doesExist(any())).thenReturn(false);

    final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> ministryLoginService.userLogin(USERNAME, PASSWORD));

    assertEquals("Invalid Credentials", exception.getMessage());
  }

  @Test
  void shouldThrowExceptionIfNoCredentialsReturned() throws SQLException {
    Mockito.when(pwdEncrypt.getEncryptedPwd(any())).thenReturn(PASSWORD);
    Mockito.when(userCredentialDao.doesExist(any())).thenReturn(true);
    Mockito.when(userCredentialDao.get(any())).thenReturn(Optional.empty());

    final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> ministryLoginService.userLogin(USERNAME, PASSWORD));

    assertEquals("Invalid Credentials", exception.getMessage());
  }
}