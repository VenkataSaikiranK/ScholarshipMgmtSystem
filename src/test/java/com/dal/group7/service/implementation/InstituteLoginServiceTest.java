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

public class InstituteLoginServiceTest {

    private static final String USERNAME = "userName123";
    private static final String PASSWORD = "password@123";
    private static final UserCredential CREDENTIALS = new UserCredential(USERNAME, PASSWORD, "NO", "NO",
            "2020-01-10", "1", "NONE", "NONE",
            "NONE", "0", "institute", "NO");
    @Mock
    private Dao<String, UserCredential> userCredentialDao;

    @Mock
    private PwdEncrypt pwdEncrypt;

    @InjectMocks
    private InstituteLoginService instituteLoginService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnCredentialsIfUsernameAndPasswordAreValid() throws SQLException {
        Mockito.when(pwdEncrypt.getEncryptedPwd(any())).thenReturn(PASSWORD);
        Mockito.when(userCredentialDao.doesExist(any())).thenReturn(true);
        Mockito.when(userCredentialDao.get(any())).thenReturn(Optional.of(CREDENTIALS));

        final UserCredential userCredential = instituteLoginService.instituteLogin(USERNAME, PASSWORD);

        assertEquals(CREDENTIALS, userCredential);
    }

    @Test
    void shouldThrowExceptionIfUsernameDoesntExist() throws SQLException {
        Mockito.when(pwdEncrypt.getEncryptedPwd(any())).thenReturn(PASSWORD);
        Mockito.when(userCredentialDao.doesExist(any())).thenReturn(false);

        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> instituteLoginService.instituteLogin(USERNAME, PASSWORD));

        assertEquals("You have entered invalid Credentials", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfNoCredentialsReturned() throws SQLException {
        Mockito.when(pwdEncrypt.getEncryptedPwd(any())).thenReturn(PASSWORD);
        Mockito.when(userCredentialDao.doesExist(any())).thenReturn(true);
        Mockito.when(userCredentialDao.get(any())).thenReturn(Optional.empty());

        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> instituteLoginService.instituteLogin(USERNAME, PASSWORD));

        assertEquals("You have entered invalid Credentials", exception.getMessage());
    }

}
