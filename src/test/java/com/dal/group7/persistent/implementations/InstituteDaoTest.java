package com.dal.group7.persistent.implementations;

import com.dal.group7.persistent.model.Institute;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

class InstituteDaoTest {

    private static final Institute INSTITUTE =
            new Institute(1, "name", "dal.ca", "5000",
                    "abc", "abc", "1111A", "LakeLouise", "NovaScotia",
                    "Halifax", "Canada", "1234A", "Rock",
                    "Alchemist", "Wall Street");

    @Mock
    private ConnectionManager connectionManager;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private InstituteDao instituteDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldAddInstitute() throws SQLException {
        setUpMock();
        instituteDao.insertOne(INSTITUTE);
        Mockito.verify(preparedStatement, Mockito.times(2)).execute();
    }

    @Test
    void shouldCheckInstEmailExist() throws SQLException {
        setUpMock();
        instituteDao.doesEmailExist(INSTITUTE.getEmailId());
        Mockito.verify(preparedStatement, Mockito.atLeastOnce())
                .executeQuery();
    }

    @Test
    void get_returnInstitute_whenIdIsPresent() throws SQLException {
        setUpMock();
        instituteDao.insertOne(INSTITUTE);
        Mockito.verify(preparedStatement,Mockito.times(2)).execute();
        Mockito.when(resultSet.next()).thenReturn(true);

    }

    @Test
    void getAll_returnEmptyList_whenInstitutesAreNotPresent() throws SQLException {
        setUpMock();
        Mockito.when(resultSet.next()).thenReturn(false);

        final List<Institute> institutes = instituteDao.getAll();

        assertTrue(institutes.isEmpty());
    }

    private void setUpMock() throws SQLException {
        Mockito.when(connectionManager.getConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.execute()).thenReturn(true);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }
}