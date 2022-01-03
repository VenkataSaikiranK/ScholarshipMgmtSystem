package com.dal.group7.persistent.implementations;

import com.dal.group7.constants.SQLConstants;
import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.Institute;
import com.dal.group7.service.implementation.PwdEncrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.dal.group7.constants.FieldConstants.ONE;
import static com.dal.group7.constants.SQLConstants.*;

public class InstituteDao extends Dao<Integer, Institute> {

    private static final String INSTITUTE = "institute_basic";
    private final ConnectionManager connectionManager;

    public InstituteDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Boolean doesEmailExist(String emailId) throws SQLException {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.
                     prepareStatement(SQLConstants.getSelectByUserIdQuery(
                             SQLConstants.USER_CREDENTIAL))) {

            preparedStatement.setString(1, emailId);
            ResultSet rs = preparedStatement.executeQuery();

            return rs.next();
        }
    }

    @Override
    public void insertOne(Institute institute) throws SQLException {
        PreparedStatement statement = null;

        try (Connection connection = connectionManager.getConnection()) {
            PwdEncrypt pwdEncrypt =
                    new PwdEncrypt(new PwdEncryptDao(connectionManager));
            connection.setAutoCommit(false);

            int counter = 1;
            statement = connection
                    .prepareStatement(SQLConstants.getInsertNewUser());
            // user_cred table object
            statement.setString(counter++, institute.getEmailId());
            statement.setString(counter++,
                    pwdEncrypt.getEncryptedPwd(institute.getPassword()));
            statement.setString(counter++, SQLConstants.TWO);
            statement.setString(counter++, institute.getSecurityAnswerOne());
            statement.setString(counter++, institute.getSecurityAnswerTwo());
            statement.setString(counter++, institute.getSecurityAnswerThree());
            statement.setString(counter,
                    institute.getClass().getSimpleName().toLowerCase());

            statement.execute();
            statement = connection
                    .prepareStatement(SQLConstants.getInsertNewInstitute());
            counter = 1;

            // institute_basic table object
            statement.setInt(counter++, institute.getId());
            statement.setString(counter++, institute.getName());
            statement.setString(counter++, institute.getEmailId());
            statement.setString(counter++, institute.getRegistrationCode());
            statement.setString(counter++, institute.getAddress());
            statement.setString(counter++, institute.getCity());
            statement.setString(counter++, institute.getState());
            statement.setString(counter++, institute.getPhoneNumber());
            statement.setString(counter++, institute.getCountry());
            statement.setString(counter, institute.getPinCode());

            statement.execute();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    @Override
    public Optional<Institute> get(Integer id) throws SQLException {
        try(var connection = connectionManager.getConnection();
            var preparedStatement = connection.prepareStatement(getSelectByIdQuery(INSTITUTE))) {
            preparedStatement.setInt(ONE, id);
            final var resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? Optional.of(new Institute().from(resultSet)) : Optional.empty();
        }
    }


    @Override
    public List<Institute> getAll() throws SQLException {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection
                     .prepareStatement(getSelectAllQuery(INSTITUTE))) {
            final var resultSet = preparedStatement.executeQuery();
            List<Institute> institutes = new ArrayList<>();
            while (resultSet.next()) {
                institutes.add(new Institute().from(resultSet));
            }
            return institutes;
        }
    }

    @Override
    public void updateValue(Integer id, String field, Object value)
            throws SQLException {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(
                     setGrantAmountValue(field))
        ) {
            preparedStatement.setObject(1, value);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void incrementValue(Integer id, String field, double value)
            throws SQLException {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(
                     setAwardValues(field))
        ) {
            preparedStatement.setObject(1, value);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        }
    }
}
