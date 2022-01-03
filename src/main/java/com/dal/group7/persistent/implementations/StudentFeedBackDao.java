package com.dal.group7.persistent.implementations;

import com.dal.group7.constants.SQLConstants;
import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.StudentFeedback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentFeedBackDao extends Dao<Integer, StudentFeedback> {

    private final ConnectionManager connectionManager;

    public StudentFeedBackDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void insertOne(StudentFeedback feedback) throws SQLException {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.
                     prepareStatement(SQLConstants.getInsertNewFeedback());) {

            double totalRating =
                    (feedback.getAnswerOne() + feedback.getAnswerTwo() +
                            feedback.getAnswerThree() +
                            feedback.getAnswerFour() +
                            feedback.getAnswerFive()) / 5;

            statement.setInt(1, feedback.getInstituteId());
            statement.setDouble(2, feedback.getAnswerOne());
            statement.setDouble(3, feedback.getAnswerTwo());
            statement.setDouble(4, feedback.getAnswerThree());
            statement.setDouble(5, feedback.getAnswerFour());
            statement.setDouble(6, feedback.getAnswerFive());
            statement.setDouble(7, totalRating);
            statement.execute();

        }
    }

    @Override
    public ArrayList<Integer> fetchValues() throws SQLException {
        ArrayList<Integer> institutes = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.
                     prepareStatement(SQLConstants.getAwardInstitutes())) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                institutes.add(resultSet.getInt(1));
            }
        }
        return institutes;
    }
}
