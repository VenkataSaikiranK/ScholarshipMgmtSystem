package com.dal.group7.service.implementation;

import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.StudentFeedback;
import com.dal.group7.persistent.model.StudentFeedbackHandle;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;

import static com.dal.group7.constants.ViewConstants.INVALID_PARAMS;

public class StudentFeedbackService {
    private final Dao<Integer, StudentFeedback> studentFeedbackDao;
    private final JsonFileReader jsonFileReader;

    public StudentFeedbackService(
            Dao<Integer, StudentFeedback> studentFeedbackDao,
            JsonFileReader jsonFileReader) {
        this.studentFeedbackDao = studentFeedbackDao;
        this.jsonFileReader = jsonFileReader;
    }

    public void saveFeedback(String filepath) throws IOException, SQLException {
        final JSONObject jsonObject = jsonFileReader.readJson(filepath);

        StudentFeedback feedback = new StudentFeedbackHandle().from(jsonObject);
        if (Boolean.TRUE.equals(isValid(feedback))) {
            studentFeedbackDao.insertOne(feedback);
        } else {
            throw new IllegalArgumentException(
                    INVALID_PARAMS);
        }
    }

    private Boolean isValid(StudentFeedback feedback) {
        boolean one = feedback.getAnswerOne() != null;
        boolean two = feedback.getAnswerTwo() != null;
        boolean three = feedback.getAnswerThree() != null;
        boolean four = feedback.getAnswerFour() != null;
        boolean five = feedback.getAnswerFive() != null;

        return one && two && three && four && five;
    }
}
