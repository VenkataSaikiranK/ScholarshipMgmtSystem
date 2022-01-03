package com.dal.group7.persistent.implementations;

import com.dal.group7.constants.FieldConstants;
import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.Scholarship;
import com.dal.group7.persistent.model.ScholarshipHandle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.dal.group7.constants.SQLConstants.*;

public class ScholarshipDao extends Dao<Integer, Scholarship> {

    private final ConnectionManager connectionManager;

    public ScholarshipDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void insertOne(Scholarship scholarship) throws SQLException {
        try(var connection = connectionManager.getConnection();
            var preparedStatement = connection.prepareStatement(getInsertNewScholarship())) {
            setValuesToPreparedStatement(scholarship, preparedStatement);
            preparedStatement.executeUpdate();
        }
    }

    private void setValuesToPreparedStatement(Scholarship scholarship, java.sql.PreparedStatement preparedStatement) {
        final Map<Integer, Object> fieldValues = scholarship.getFieldValues();
        fieldValues.remove(0);
        fieldValues.forEach((index, field) -> {
            try {
                preparedStatement.setObject(index, field);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        });
    }

    @Override
    public List<Scholarship> getAll() throws SQLException {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection
                     .prepareStatement(getSelectAllQuery(SCHOLARSHIP))) {
            final var resultSet = preparedStatement.executeQuery();
            List<Scholarship> scholarships = new ArrayList<>();
            while (resultSet.next()) {
                scholarships
                        .add(new ScholarshipHandle().formResultSet(resultSet));
            }
            return scholarships;
        }
    }

    @Override
    public List<Scholarship> getAllScholarshipsByCriteria(String genderCriteria, String academicCriteria, String sportsCriteria) throws SQLException {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection
                     .prepareStatement(getSelectScholarshipByCriteria())) {
            preparedStatement.setString(FieldConstants.ONE, genderCriteria);
            preparedStatement.setString(FieldConstants.TWO, academicCriteria);
            preparedStatement.setString(FieldConstants.THREE, sportsCriteria);
            final var resultSet = preparedStatement.executeQuery();
            List<Scholarship> scholarships = new ArrayList<>();
            while (resultSet.next()) {
                scholarships.add(new ScholarshipHandle().formResultSet(resultSet));
            }
            return scholarships;
        }
    }

    @Override
    public Optional<Scholarship> findById(Integer id) throws SQLException {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(
                     getSelectScholarshipByIdQuery())) {

            preparedStatement.setInt(FieldConstants.ONE, id);
            final var resultSet = preparedStatement.executeQuery();
            return resultSet.next() ?
                    Optional.of(new ScholarshipHandle().formResultSet(resultSet)) :
                    Optional.empty();
        }
    }

}
