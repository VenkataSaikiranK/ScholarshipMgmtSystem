/*
 * author    : Sai Rahul Kodumuru
 * file      : StudentDAO.java
 * purpose   : This DAO has the implementations that interact with
 *             the database for student services.
 */

package com.dal.group7.persistent.implementations;

import com.dal.group7.constants.SQLConstants;
import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.Student;
import com.dal.group7.service.implementation.PwdEncrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.dal.group7.constants.FieldConstants.ONE;
import static com.dal.group7.constants.SQLConstants.USER_CREDENTIAL;
import static com.dal.group7.constants.SQLConstants.getSelectByUserIdQuery;

public class StudentDao extends Dao<Integer, Student> {

    private final ConnectionManager connectionManager;

    public StudentDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Boolean doesExist(String id) throws SQLException {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(
                     getSelectByUserIdQuery(USER_CREDENTIAL))) {
            preparedStatement.setString(ONE, id);
            final var resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }

    }

    @Override
    public void insertOne(Student student) throws SQLException {
        PreparedStatement statement = null;

        try (Connection connection = connectionManager.getConnection()) {
            PwdEncrypt pwdEncrypt =
                    new PwdEncrypt(new PwdEncryptDao(new ConnectionManager()));

            connection.setAutoCommit(false);

            int counter = 1;
            statement = connection.
                    prepareStatement(SQLConstants.getInsertNewUser());

            statement.setString(counter++, student.getEmailId());
            statement.setString(counter++,
                    pwdEncrypt.getEncryptedPwd(student.getPassword()));
            statement.setString(counter++, SQLConstants.ONE);
            statement.setString(counter++, student.getSecurityAnswerOne());
            statement.setString(counter++, student.getSecurityAnswerTwo());
            statement.setString(counter++,
                    student.getSecurityAnswerThree());
            statement.setString(counter,
                    student.getClass().getSimpleName().toLowerCase());

            statement.execute();

            statement = connection.
                    prepareStatement(SQLConstants.getInsertNewStudent());
            counter = 1;

            // student_basic table object
            statement.setString(counter++, student.getFirstName());
            statement.setString(counter++, student.getLastName());
            statement.setString(counter++, student.getEmailId());
            statement.setString(counter++, student.getPhoneNumber());
            statement.setString(counter++, student.getPassportNumber());
            statement.setString(counter++, student.getDob());
            statement.setString(counter++, student.getGender());
            statement.setString(counter++, student.getState());
            statement.setString(counter++, student.getCity());
            statement.setString(counter++, student.getPincode());
            statement.setString(counter, student.getCountry());
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

}
