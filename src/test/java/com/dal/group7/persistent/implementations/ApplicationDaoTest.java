package com.dal.group7.persistent.implementations;

import com.dal.group7.persistent.model.Application;
import com.dal.group7.persistent.model.Scheme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.*;

import static org.mockito.ArgumentMatchers.any;

class ApplicationDaoTest {

    private static final String ID = "test@dal.ca";
    private static final Scheme scheme =
            new Scheme(1, "bruce@dal.ca", "male", 4, 1, 8.7, 7.9, 7.5, "CBSE",
                    "BEAP", 0, 1, 3,
                    Date.valueOf("2016-09-11"), Date.valueOf("2020-05-05"), 0,
                    2, 5, 0, 0, 0, "ABC123FGH", "ICICI0003", 680000, "ICICI",
                    "Bruce Wayne");
    private static final Application application =
            new Application("ABC123", 4, "bruce@dal.ca", 1, "2021-05-05",
                    "2021-07-08", "SUBMITTED", "APPROVED", "REJECTED", 2.0, 3.0,
                    2.5, scheme);

    @Mock
    private ConnectionManager connectionManager;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private ApplicationDao applicationDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetSingleRecord() throws SQLException {
        setUpMock();
        applicationDao.get(ID);
        Mockito.verify(preparedStatement, Mockito.atLeastOnce()).executeQuery();
    }

    @Test
    void getAllApplicationsByUser() throws SQLException {
        setUpMock();
        applicationDao.getAllByUser(ID);
        Mockito.verify(preparedStatement, Mockito.atLeastOnce()).executeQuery();
    }

    @Test
    void shouldCheckIfApplicationExists() throws SQLException {
        setUpMock();
        applicationDao.doesExist(ID);
        Mockito.verify(preparedStatement, Mockito.atLeastOnce())
                .executeQuery();
    }

    @Test
    void shouldAddAnApplication() throws SQLException {
        setUpMock();
        applicationDao.insertOne(application);
        Mockito.verify(preparedStatement, Mockito.times(4)).execute();
    }

    @Test
    void shouldUpdateValue() throws SQLException {
        setUpMock();
        applicationDao
                .updateValue(application.getApplicationId(), "ministry_status",
                        "submitted");
        Mockito.verify(preparedStatement, Mockito.atLeastOnce())
                .executeUpdate();
    }


    private void setUpMock() throws SQLException {
        Mockito.when(connectionManager.getConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(any()))
                .thenReturn(preparedStatement);
        Mockito.when(preparedStatement.execute()).thenReturn(true);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }
}