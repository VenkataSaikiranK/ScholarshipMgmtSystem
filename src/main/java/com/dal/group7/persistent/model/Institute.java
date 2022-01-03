package com.dal.group7.persistent.model;


import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Random;

import static com.dal.group7.constants.FieldConstants.ID;
import static com.dal.group7.constants.FieldConstants.NAME;

public class Institute {
    private Integer id;
    private String name;
    private String emailId;
    private String registrationCode;
    private String password;
    private String reConfirmPassword;
    private String address;
    private String city;
    private String state;
    private String phoneNumber;
    private String country;
    private String pinCode;
    private String securityAnswerOne;
    private String securityAnswerTwo;
    private String securityAnswerThree;


    public Institute() {
    }

    public Institute(Integer id, String name, String emailId,
                     String registrationCode, String password,
                     String reConfirmPassword, String phoneNumber,
                     String address, String city, String state,
                     String country, String pinCode, String securityAnswerOne,
                     String securityAnswerTwo,
                     String securityAnswerThree) {
        this.id = id;
        this.name = name;
        this.emailId = emailId;
        this.registrationCode = registrationCode;
        this.password = password;
        this.reConfirmPassword = reConfirmPassword;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
        this.securityAnswerOne = securityAnswerOne;
        this.securityAnswerTwo = securityAnswerTwo;
        this.securityAnswerThree = securityAnswerThree;

    }

    public Institute from(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt(ID);
        name = resultSet.getString(NAME);
        return this;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmailId() { return emailId;}

    public String getRegistrationCode() {
        return registrationCode;
    }

    public String getPassword() {
        return password;
    }

    public String getReConfirmPassword() { return reConfirmPassword; }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getSecurityAnswerOne() {
        return securityAnswerOne;
    }

    public String getSecurityAnswerTwo() {
        return securityAnswerTwo;
    }

    public String getSecurityAnswerThree() {
        return securityAnswerThree;
    }


    public Institute from(JSONObject jsonObject) {
        this.id = new Random().nextInt() & Integer.MAX_VALUE;
        this.name = jsonObject.getString("name");
        this.emailId = jsonObject.getString("email");
        this.registrationCode = jsonObject.getString("regd_code");
        this.password = jsonObject.getString("password");
        this.reConfirmPassword = jsonObject.getString("re_confirm_password");
        this.address = jsonObject.getString("address");
        this.city = jsonObject.getString("city");
        this.state = jsonObject.getString("institute_state");
        this.phoneNumber = jsonObject.getString("institute_contact");
        this.country = jsonObject.getString("country");
        this.pinCode = jsonObject.getString("pincode");
        this.securityAnswerOne = jsonObject.getString("security_answer_one");
        this.securityAnswerTwo = jsonObject.getString("security_answer_two");
        this.securityAnswerThree = jsonObject.getString("security_answer_three");

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Institute that = (Institute) o;
        return Objects.equals(name, that.name) && Objects.equals(emailId , that.emailId) && Objects.equals(registrationCode, that.registrationCode)
                && Objects.equals(password, that.password) && Objects.equals(reConfirmPassword, that.reConfirmPassword)
                && Objects.equals(phoneNumber, that.phoneNumber ) && Objects.equals(address, that.address)
                && Objects.equals(state, that.state) && Objects.equals(city, that.city) && Objects.equals(country, that.country)
                && Objects.equals(pinCode, that.pinCode) && Objects.equals(securityAnswerOne, that.securityAnswerOne)
                && Objects.equals(securityAnswerTwo, that.securityAnswerTwo) && Objects.equals(securityAnswerThree, that.securityAnswerThree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, emailId, registrationCode, password, reConfirmPassword, phoneNumber, address, state, city, country,
                pinCode, securityAnswerOne, securityAnswerTwo, securityAnswerThree);
    }
}