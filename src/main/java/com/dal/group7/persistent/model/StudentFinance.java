package com.dal.group7.persistent.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentFinance {
    private String emailId;
    private String accountNumber;
    private String bankIFSC;
    private Double annualIncome;
    private String bankName;
    private String accountHolderName;

    public StudentFinance() {
    }

    public String getEmailId() {
        return emailId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getBankIFSC() {
        return bankIFSC;
    }

    public Double getAnnualIncome() {
        return annualIncome;
    }

    public String getBankName() {
        return bankName;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAnnualIncome(Double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public StudentFinance from(ResultSet resultSet) throws SQLException {
        this.emailId = resultSet.getString(1);
        this.accountNumber = resultSet.getString(2);
        this.bankIFSC = resultSet.getString(3);
        this.annualIncome = resultSet.getDouble(4);
        this.bankName = resultSet.getString(5);
        this.accountHolderName = resultSet.getString(6);
        return this;
    }
}
