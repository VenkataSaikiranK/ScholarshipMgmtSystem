package com.dal.group7.persistent.implementations;

import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.StudentFinance;

import java.sql.SQLException;
import java.util.Optional;

import static com.dal.group7.constants.FieldConstants.ONE;
import static com.dal.group7.constants.SQLConstants.getSelectStudentFinanceByIdQuery;

public class StudentFinanceDao extends Dao<String, StudentFinance> {

    private final ConnectionManager connectionManager;

    public StudentFinanceDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Optional<StudentFinance> findById(String id) throws SQLException {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(
                     getSelectStudentFinanceByIdQuery())) {

            preparedStatement.setString(ONE, id);
            final var resultSet = preparedStatement.executeQuery();
            return resultSet.next() ?
                    Optional.of(new StudentFinance().from(resultSet)) :
                    Optional.empty();
        }
    }
}
