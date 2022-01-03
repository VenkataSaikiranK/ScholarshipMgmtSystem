package com.dal.group7.persistent.implementations;

import com.dal.group7.persistent.model.Student;
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

class StudentDaoTest {
    private static final Student STUDENT =
            new Student("Bruce", "Wayne", "bruce.wayne@dal.ca",
                    "878-98287", "R198A2J", "somePass", "somePass",
                    "2020-09-10 08:22:31", "male", "Metropolis", "Gotham",
                    "3AT7A5",
                    "USA", "Liverpool", "Cheese B", "Bale");

    @Mock
    private ConnectionManager connectionManager;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private StudentDao studentDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldAddStudent() throws SQLException {
        setUpMock();
        studentDao.insertOne(STUDENT);
        Mockito.verify(preparedStatement, Mockito.times(2)).execute();
    }

    @Test
    void shouldCheckEmailExist() throws SQLException {
        setUpMock();
        studentDao.doesExist(STUDENT.getEmailId());
        Mockito.verify(preparedStatement, Mockito.atLeastOnce())
                .executeQuery();
    }

    private void setUpMock() throws SQLException {
        Mockito.when(connectionManager.getConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(any()))
                .thenReturn(preparedStatement);
        Mockito.when(preparedStatement.execute()).thenReturn(true);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }
}