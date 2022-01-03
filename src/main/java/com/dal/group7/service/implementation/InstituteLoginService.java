package com.dal.group7.service.implementation;

import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.UserCredential;

import java.sql.SQLException;

import static com.dal.group7.constants.ViewConstants.INVALID_CREDENTIALS;

public class InstituteLoginService {

    private Dao<String, UserCredential> userCredentialDao;
    private String userId;
    private String password;
    private PwdEncrypt passwordClass;
    private UserCredential userCredential;
    private static final String YES = "yes";

    public InstituteLoginService(Dao<String, UserCredential> userCredentialDao, PwdEncrypt pwdEncrypt) {
        this.userCredentialDao = userCredentialDao;
        this.passwordClass = pwdEncrypt;
    }

    private String getEncryptedPassword(String password) {
        return passwordClass.getEncryptedPwd(password);
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

    public UserCredential instituteLogin(String userId, String password) throws SQLException {
        this.userId = userId;
        this.password = getEncryptedPassword(password);

        if (getStoredCredential() && areCredentialsValid()) {
                return userCredential;
        }
        throw new IllegalArgumentException(INVALID_CREDENTIALS);
    }

}
