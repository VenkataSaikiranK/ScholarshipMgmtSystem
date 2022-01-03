package com.dal.group7.persistent.model;

import org.json.JSONObject;

import java.sql.Date;
import java.util.Random;

public class Scheme {
    int applicationID;
    String userId;
    String gender;
    int schemeId;
    int instituteId;
    double gpaX;
    double gpaXII;
    double gpaBachelors;
    String boardX;
    String boardXII;
    int backlogX;
    int backlogXII;
    int backlogBachelors;
    Date joiningMonthBachelors;
    Date graduationMonthBachelors;
    int nationalSportsAwards;
    int stateSportsAwards;
    int districtSportsAwards;
    int nationalArtsAwards;
    int stateArtsAwards;
    int districtArtsAwards;
    String bankAccNumber;
    String bankIFSC;
    double annualIncome;
    String bankName;
    String bankHolderName;

    public Scheme() {
    }

    public Scheme(int applicationID, String userId, String gender, int schemeId,
                  int instituteId, double gpaX, double gpaXII,
                  double gpaBachelors,
                  String boardX, String boardXII, int backlogX, int backlogXII,
                  int backlogBachelors, Date joiningMonthBachelors,
                  Date graduationMonthBachelors, int nationalSportsAwards,
                  int stateSportsAwards, int districtSportsAwards,
                  int nationalArtsAwards, int stateArtsAwards,
                  int districtArtsAwards, String bankAccNumber,
                  String bankIFSC, double annualIncome, String bankName,
                  String bankHolderName) {
        this.applicationID = applicationID;
        this.userId = userId;
        this.gender = gender;
        this.schemeId = schemeId;
        this.instituteId = instituteId;
        this.gpaX = gpaX;
        this.gpaXII = gpaXII;
        this.gpaBachelors = gpaBachelors;
        this.boardX = boardX;
        this.boardXII = boardXII;
        this.backlogX = backlogX;
        this.backlogXII = backlogXII;
        this.backlogBachelors = backlogBachelors;
        this.joiningMonthBachelors = joiningMonthBachelors;
        this.graduationMonthBachelors = graduationMonthBachelors;
        this.nationalSportsAwards = nationalSportsAwards;
        this.stateSportsAwards = stateSportsAwards;
        this.districtSportsAwards = districtSportsAwards;
        this.nationalArtsAwards = nationalArtsAwards;
        this.stateArtsAwards = stateArtsAwards;
        this.districtArtsAwards = districtArtsAwards;
        this.bankAccNumber = bankAccNumber;
        this.bankIFSC = bankIFSC;
        this.annualIncome = annualIncome;
        this.bankName = bankName;
        this.bankHolderName = bankHolderName;
    }


    public int getApplicationID() {
        return applicationID;
    }

    public String getUserId() {
        return userId;
    }

    public String getGender() {
        return gender;
    }

    public int getSchemeId() {
        return schemeId;
    }

    public int getInstituteId() {
        return instituteId;
    }

    public double getGpaX() {
        return gpaX;
    }

    public double getGpaXII() {
        return gpaXII;
    }

    public double getGpaBachelors() {
        return gpaBachelors;
    }

    public String getBoardX() {
        return boardX;
    }

    public String getBoardXII() {
        return boardXII;
    }

    public int getBacklogX() {
        return backlogX;
    }

    public int getBacklogXII() {
        return backlogXII;
    }

    public int getBacklogBachelors() {
        return backlogBachelors;
    }

    public Date getJoiningMonthBachelors() {
        return joiningMonthBachelors;
    }

    public Date getGraduationMonthBachelors() {
        return graduationMonthBachelors;
    }

    public int getNationalSportsAwards() {
        return nationalSportsAwards;
    }

    public int getStateSportsAwards() {
        return stateSportsAwards;
    }

    public int getDistrictSportsAwards() {
        return districtSportsAwards;
    }

    public int getNationalArtsAwards() {
        return nationalArtsAwards;
    }

    public int getStateArtsAwards() {
        return stateArtsAwards;
    }

    public int getDistrictArtsAwards() {
        return districtArtsAwards;
    }

    public String getBankAccNumber() {
        return bankAccNumber;
    }

    public String getBankIFSC() {
        return bankIFSC;
    }

    public double getAnnualIncome() {
        return annualIncome;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankHolderName() {
        return bankHolderName;
    }

    public Scheme from(JSONObject jsonObject) {
        this.applicationID = new Random().nextInt() & Integer.MAX_VALUE;
        this.userId = jsonObject.getString("email_id");
        this.gender = jsonObject.getString("gender");
        this.schemeId = jsonObject.getInt("scheme_code");
        this.instituteId = jsonObject.getInt("institute_code");
        this.gpaX = jsonObject.getDouble("GPA-X");
        this.gpaXII = jsonObject.getDouble("GPA-XII");
        this.gpaBachelors = jsonObject.getDouble("GPA_Bachelors");
        this.boardX = jsonObject.getString("BOARD_X");
        this.boardXII = jsonObject.getString("BOARD_XII");
        this.backlogX = jsonObject.getInt("backlog_count_X");
        this.backlogXII = jsonObject.getInt("backlog_count_XII");
        this.backlogBachelors = jsonObject.getInt("backlog_count_bachelors");
        this.joiningMonthBachelors =
                Date.valueOf(jsonObject.getString("joining_month_bachelors"));
        this.graduationMonthBachelors =
                Date.valueOf(
                        jsonObject.getString("graduation_month_bachelors"));
        this.nationalSportsAwards =
                jsonObject.getInt("national_sports_awards_count");
        this.stateSportsAwards = jsonObject.getInt("state_sports_awards_count");
        this.districtSportsAwards =
                jsonObject.getInt("district_sports_awards_count");
        this.nationalArtsAwards =
                jsonObject.getInt("national_arts_awards_count");
        this.stateArtsAwards = jsonObject.getInt("state_arts_awards_count");
        this.districtArtsAwards =
                jsonObject.getInt("district_arts_awards_count");
        this.bankAccNumber = jsonObject.getString("bank_acc_num");
        this.bankIFSC = jsonObject.getString("bank_IFSC");
        this.annualIncome = jsonObject.getDouble("annual_income");
        this.bankName = jsonObject.getString("bank_name");
        this.bankHolderName = jsonObject.getString("bank_acc_holder_name");
        return this;
    }

}
