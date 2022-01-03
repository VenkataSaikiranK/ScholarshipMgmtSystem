package com.dal.group7.persistent.model;

import org.json.JSONObject;

public class StudentFeedbackHandle extends StudentFeedback {

    public StudentFeedbackHandle() {
        super();
    }

    public StudentFeedback from(JSONObject jsonObject) {
        this.instituteId = jsonObject.getInt("institute_id");
        this.answerOne = jsonObject.getDouble("Answer_1");
        this.answerTwo = jsonObject.getDouble("Answer_2");
        this.answerThree = jsonObject.getDouble("Answer_3");
        this.answerFour = jsonObject.getDouble("Answer_4");
        this.answerFive = jsonObject.getDouble("Answer_5");
        return this;
    }
}
