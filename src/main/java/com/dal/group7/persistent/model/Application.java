package com.dal.group7.persistent.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Application {
    String applicationId;
    int schemeId;
    String studentId;
    int instituteId;
    String appliedDate;
    String lastUpdate;
    String applicationStatus;
    String instituteStatus;
    String ministryStatus;
    double academicScore;
    double nonAcademicScore;
    double profileScore;
    Scheme scheme;
    double tuitionAmount;
    double travelAmount;
    double insuranceAmount;
    double livingExpensesAmount;

    public Application() {
    }

    public Application(String applicationId, int schemeId,
                       String studentId, int instituteId,
                       String appliedDate, String lastUpdate,
                       String applicationStatus, String instituteStatus,
                       String ministryStatus, double academicScore,
                       double nonAcademicScore, double profileScore,
                       Scheme scheme) {
        this.applicationId = applicationId;
        this.schemeId = schemeId;
        this.studentId = studentId;
        this.instituteId = instituteId;
        this.appliedDate = appliedDate;
        this.lastUpdate = lastUpdate;
        this.applicationStatus = applicationStatus;
        this.instituteStatus = instituteStatus;
        this.ministryStatus = ministryStatus;
        this.academicScore = academicScore;
        this.nonAcademicScore = nonAcademicScore;
        this.profileScore = profileScore;
        this.scheme = scheme;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public int getSchemeId() {
        return schemeId;
    }

    public String getStudentId() {
        return studentId;
    }

    public int getInstituteId() {
        return instituteId;
    }

    public String getAppliedDate() {
        return appliedDate;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public String getInstituteStatus() {
        return instituteStatus;
    }

    public String getMinistryStatus() {
        return ministryStatus;
    }

    public double getAcademicScore() {
        return academicScore;
    }

    public void setAcademicScore(double academicScore) {
        this.academicScore = academicScore;
    }

    public double getNonAcademicScore() {
        return nonAcademicScore;
    }

    public void setNonAcademicScore(double nonAcademicScore) {
        this.nonAcademicScore = nonAcademicScore;
    }

    public double getProfileScore() {
        return profileScore;
    }

    public void setProfileScore(double profileScore) {
        this.profileScore = profileScore;
    }

    public Scheme getScheme() {
        return scheme;
    }

    public void setScheme(Scheme scheme) {
        this.scheme = scheme;
    }

    public double getTuitionAmount() {
        return tuitionAmount;
    }

    public void setTuitionAmount(double tuitionAmount) {
        this.tuitionAmount = tuitionAmount;
    }

    public double getTravelAmount() {
        return travelAmount;
    }

    public void setTravelAmount(double travelAmount) {
        this.travelAmount = travelAmount;
    }

    public double getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(double insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public double getLivingExpensesAmount() {
        return livingExpensesAmount;
    }

    public void setLivingExpensesAmount(double livingExpensesAmount) {
        this.livingExpensesAmount = livingExpensesAmount;
    }

    public Application from(ResultSet resultSet) throws SQLException {
        int counter = 1;
        this.applicationId = resultSet.getString(counter++);
        this.schemeId = resultSet.getInt(counter++);
        this.studentId = resultSet.getString(counter++);
        this.instituteId = resultSet.getInt(counter++);
        this.appliedDate = resultSet.getString(counter++);
        this.lastUpdate = resultSet.getString(counter++);
        this.applicationStatus = resultSet.getString(counter++);
        this.instituteStatus = resultSet.getString(counter++);
        this.ministryStatus = resultSet.getString(counter++);
        this.academicScore = resultSet.getDouble(counter++);
        this.nonAcademicScore = resultSet.getDouble(counter++);
        this.profileScore = resultSet.getDouble(counter);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Application)) return false;
        Application that = (Application) o;
        return getSchemeId() == that.getSchemeId() &&
                getInstituteId() == that.getInstituteId() && Double
                .compare(that.getAcademicScore(), getAcademicScore()) == 0 &&
                Double.compare(that.getNonAcademicScore(),
                        getNonAcademicScore()) == 0 && Double
                .compare(that.getProfileScore(), getProfileScore()) == 0 &&
                Objects
                        .equals(getApplicationId(), that.getApplicationId()) &&
                Objects.equals(getStudentId(), that.getStudentId()) &&
                Objects.equals(getAppliedDate(), that.getAppliedDate()) &&
                Objects.equals(getLastUpdate(), that.getLastUpdate()) &&
                Objects.equals(getApplicationStatus(),
                        that.getApplicationStatus()) && Objects
                .equals(getInstituteStatus(), that.getInstituteStatus()) &&
                Objects
                        .equals(getMinistryStatus(),
                                that.getMinistryStatus()) &&
                Objects.equals(getScheme(), that.getScheme());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getApplicationId(), getSchemeId(), getStudentId(),
                getInstituteId(), getAppliedDate(), getLastUpdate(),
                getApplicationStatus(), getInstituteStatus(),
                getMinistryStatus(),
                getAcademicScore(), getNonAcademicScore(), getProfileScore(),
                getScheme());
    }
}
