package com.dal.group7.service.implementation;

import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.Application;
import com.dal.group7.persistent.model.UserCredential;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.dal.group7.constants.FieldConstants.ONE;
import static com.dal.group7.constants.FieldConstants.ZERO;
import static com.dal.group7.constants.SQLConstants.NO;
import static com.dal.group7.constants.SQLConstants.*;
import static com.dal.group7.constants.ViewConstants.*;
import static java.lang.Integer.parseInt;

public class StudentLoginService {

  private final Dao<String, UserCredential> userCredentialDao;
  private final Dao<String, Application> applicationDao;
  private final PwdEncrypt pwdEncrypt;
  private UserCredential userCredential;
  private String userId;
  private String password;
  private int failLoginCount;
  private static final String pattern = "yyyy-MM-dd HH:mm:ss";


  public StudentLoginService(Dao<String, UserCredential> userCredentialDao,
                             PwdEncrypt pwdEncrypt,
                             Dao<String, Application> applicationDao) {
    this.userCredentialDao = userCredentialDao;
    this.pwdEncrypt = pwdEncrypt;
    this.applicationDao = applicationDao;
  }

  public UserCredential userLogin(String userId, String password)
          throws SQLException {
    this.userId = userId;
    this.password = getEncryptedPassword(password);

    if (getStoredCredential() && areCredentialsValid() &&
            !isStudentSoftBlocked() && !isStudentHardBlocked()
            && !isStudentBlackListed()) {
      userCredentialDao.updateLastLoginTime(userId);
      updateFailLoginCountOnSuccess();
      return userCredential;
    }
    if (!getStoredCredential()) {
      throw new IllegalArgumentException(NO_USER_FOUND);
    } else if (isStudentBlackListed()) {
      throw new IllegalArgumentException(STUDENT_BANNED_MSG);
    } else if (isStudentHardBlocked()) {
      throw new IllegalArgumentException(STUDENT_HARD_BLOCK_MSG);
    } else if (isStudentSoftBlocked()) {
      String lastLogin = userCredential.getLastLogin();
      checkSoftHardBlockCases(lastLogin);
      return userCredential;
    } else {
      updateFailLoginCount();
      if (parseInt(userCredential.getFailedLoginCount()) >= 2) {
        updateUserToSoftBlock();
        throw new IllegalArgumentException(MAX_ATTEMPT_MSG + "" + STUDENT_SOFT_BLOCK_MSG);
      } else {
        throw new IllegalArgumentException(INVALID_CREDENTIALS);
      }
    }
  }

  private void updateUserToSoftBlock() throws SQLException {
    userCredentialDao.updateValue(userId, SOFT_BLOCK_COL, YES);
  }

  public void updateUserToSoftBlockToNO() throws SQLException {
    userCredentialDao.updateValue(userId, SOFT_BLOCK_COL, NO);
  }

  private void checkSoftHardBlockCases(String lastLogin) throws SQLException {
    Long hrsSinceLogin = calculateHrsSinceLogin(lastLogin);
    if (hrsSinceLogin > 72) {
      userCredentialDao.updateValue(userId, HARD_BLOCK_COL, YES);
      applicationDao.updateUserStatus(userId, APP_STATUS_COL, HOLD);
      throw new IllegalArgumentException(STUDENT_HARD_BLOCK_MSG);
    } else if (hrsSinceLogin < 24) {
      throw new IllegalArgumentException(STUDENT_SOFT_BLOCK_MSG);
    }
  }

  public Long calculateHrsSinceLogin(String lastLogin) {
    DateTimeFormatter lastLoginDateFormat = DateTimeFormatter.ofPattern(pattern);
    LocalDateTime lastLoginDate = LocalDateTime.parse(lastLogin, lastLoginDateFormat);
    LocalDateTime currentDate = LocalDateTime.now();
    Duration duration = Duration.between(lastLoginDate, currentDate);
    return duration.toHours();
  }

  private void updateFailLoginCount() throws SQLException {
    this.failLoginCount = getLoginCount(userCredential.getFailedLoginCount());
    userCredentialDao.updateValue(userId, FAIL_LOGIN_COUNT_COL, failLoginCount + ONE);
  }

  private void updateFailLoginCountOnSuccess() throws SQLException {
    this.failLoginCount = getLoginCount(userCredential.getFailedLoginCount());
    userCredentialDao.updateValue(userId, FAIL_LOGIN_COUNT_COL, ZERO);
  }

  public String getEncryptedPassword(String password) {
    return pwdEncrypt.getEncryptedPwd(password);
  }

  private boolean getStoredCredential() throws SQLException {
    if (userCredentialDao.doesExist(userId)) {
      userCredential = userCredentialDao.get(userId)
              .orElseThrow(() -> new IllegalArgumentException(INVALID_CREDENTIALS));
      return true;
    }
    return false;

  }

  private boolean areCredentialsValid() {
    return password.equals(userCredential.getPassword());
  }

  private boolean isStudentSoftBlocked() {
    return userCredential.getIsSoftBlock().equals(YES);
  }

  private boolean isStudentHardBlocked() {
    return userCredential.getIsHardBlock().equals(YES);
  }

  private boolean isStudentBlackListed() {
    return userCredential.getIsBlackListed().equals(YES);
  }

  public Integer getLoginCount(String loginCount) {
    return parseInt(loginCount);
  }

  public void evaluateSecurityAnswer(String secAnswer) {
    String savedSecAnswer = userCredential.getSecurityAnswerOne();
    if (savedSecAnswer.equals(secAnswer)) {
      return;
    } else {
      throw new IllegalArgumentException(STUDENT_SOFT_BLOCK_MSG);
    }
  }
}
