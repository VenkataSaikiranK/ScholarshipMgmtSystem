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

import static com.dal.group7.constants.ViewConstants.NO_USER_FOUND;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class StudentLoginServiceTest {

  private static final String PASSWORD = "pwd";
  private static final String USERNAME = "user";
  private static final String TEST_DATE = "2021-07-21 00:00:01";
  private static final String TEST_DATE1 = "2022-07-21 08:00:01";
  private static final String hashedPwd ="xtrsfd";
  private static final UserCredential CREDENTIALS = new UserCredential(USERNAME, PASSWORD, "NO", "NO",
          "2020-01-10", "1", "NONE", "NONE",
          "NONE", "0", "student", "NO");
  @Mock
  private Dao<String, UserCredential> userCredentialDao;

  @Mock
  private PwdEncrypt pwdEncrypt;

  @InjectMocks
  private StudentLoginService studentLoginService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldReturnCredentialsIfUsernameAndPasswordAreValid() throws SQLException {
    Mockito.when(pwdEncrypt.getEncryptedPwd(any())).thenReturn(PASSWORD);
    Mockito.when(userCredentialDao.doesExist(any())).thenReturn(true);
    Mockito.when(userCredentialDao.get(any())).thenReturn(Optional.of(CREDENTIALS));

    final UserCredential userCredential = studentLoginService.userLogin(USERNAME, PASSWORD);

    assertEquals(CREDENTIALS, userCredential);
  }

  @Test
  void shouldThrowExceptionIfUsernameDoesntExist() throws SQLException {
    Mockito.when(pwdEncrypt.getEncryptedPwd(any())).thenReturn(PASSWORD);
    Mockito.when(userCredentialDao.doesExist(any())).thenReturn(false);

    final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> studentLoginService.userLogin(USERNAME, PASSWORD));

    assertEquals(NO_USER_FOUND, exception.getMessage());
  }

  @Test
  void shouldThrowExceptionIfNoCredentialsReturned() throws SQLException {
    Mockito.when(pwdEncrypt.getEncryptedPwd(any())).thenReturn(PASSWORD);
    Mockito.when(userCredentialDao.doesExist(any())).thenReturn(true);
    Mockito.when(userCredentialDao.get(any())).thenReturn(Optional.empty());

    final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> studentLoginService.userLogin(USERNAME, PASSWORD));

    assertEquals("You have entered invalid Credentials", exception.getMessage());
  }

  @Test
  void calculateHrsSinceLoginTest() {
    Long testDateHours = studentLoginService.calculateHrsSinceLogin(TEST_DATE);
    boolean isTestDateHoursPositive = testDateHours>0;
    assertEquals(isTestDateHoursPositive, true);
  }

  @Test
  void calculateHrsSinceLoginTestFalse() {
    Long testDateHours = studentLoginService.calculateHrsSinceLogin(TEST_DATE1);
    boolean isTestDateHoursPositive = testDateHours>0;
    assertEquals(isTestDateHoursPositive, false);
  }

  @Test
  void getLoginCountTest() {
    String loginCount ="1";
    int loginCountVal = studentLoginService.getLoginCount(loginCount);
    assertEquals(1,loginCountVal);
  }

  @Test
  void getLoginCountTestNotEquals() {
    String loginCount ="2";
    int loginCountVal = studentLoginService.getLoginCount(loginCount);
    assertNotEquals(1,loginCountVal);
  }

  @Test
  void getEncryptedPasswordTest() {
    Mockito.when(studentLoginService.getEncryptedPassword(any())).thenReturn("xtrsfd");
    assertEquals(studentLoginService.getEncryptedPassword("abc"),hashedPwd);
  }

  @Test
  void getEncryptedPasswordTestNotEquals() {
    Mockito.when(studentLoginService.getEncryptedPassword(any())).thenReturn("xtrfd");
    assertNotEquals(studentLoginService.getEncryptedPassword("abc"),hashedPwd);
  }


}