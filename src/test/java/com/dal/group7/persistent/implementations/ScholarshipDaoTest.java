package com.dal.group7.persistent.implementations;

import com.dal.group7.persistent.model.Scholarship;
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

import static java.sql.Date.valueOf;
import static java.time.LocalDate.now;
import static org.mockito.ArgumentMatchers.any;

class ScholarshipDaoTest {

    private static final Scholarship scholarship = new Scholarship(1, "name", valueOf(now()),
            500D,500D, 500D, 500D, true, true, true);


    @Mock
    private ConnectionManager connectionManager;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private ScholarshipDao scholarshipDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldInsertScholarship() throws SQLException {
        setUpMock();
        scholarshipDao.insertOne(scholarship);

        Mockito.verify(preparedStatement).executeUpdate();
    }

    @Test
    void shouldGetAllScholarships() throws SQLException {
        setUpMock();
        scholarshipDao.getAll();
        Mockito.verify(preparedStatement, Mockito.atLeastOnce()).executeQuery();
    }

    private void setUpMock() throws SQLException {
        Mockito.when(connectionManager.getConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(any()))
                .thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

}