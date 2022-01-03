package com.dal.group7.persistent.model;

import org.json.JSONObject;

import java.util.Objects;

public class Student {

  private String firstName;
  private String lastName;
  private String emailId;
  private String phoneNumber;
  private String passportNumber;
  private String password;
  private String reConfirmPassword;
  private String dob;
  private String gender;
  private String state;
  private String city;
  private String pincode;
  private String country;
  private String securityAnswerOne;
  private String securityAnswerTwo;
  private String securityAnswerThree;

  public Student() {
    // Empty
  }

  public Student(String firstName, String lastName, String emailId, String phoneNumber, String passportNumber,
                 String password, String reConfirmPassword, String dob, String gender, String state, String city,
                 String pincode, String country, String securityAnswerOne, String securityAnswerTwo,
                 String securityAnswerThree) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.emailId = emailId;
    this.phoneNumber = phoneNumber;
    this.passportNumber = passportNumber;
    this.password = password;
    this.reConfirmPassword = reConfirmPassword;
    this.dob = dob;
    this.gender = gender;
    this.state = state;
    this.city = city;
    this.pincode = pincode;
    this.country = country;
    this.securityAnswerOne = securityAnswerOne;
    this.securityAnswerTwo = securityAnswerTwo;
    this.securityAnswerThree = securityAnswerThree;
  }


  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmailId() {
    return emailId;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getPassportNumber() {
    return passportNumber;
  }

  public String getPassword() {
    return password;
  }

  public String getReConfirmPassword() {
    return reConfirmPassword;
  }

  public String getDob() {
    return dob;
  }

  public String getGender() {
    return gender;
  }

  public String getState() {
    return state;
  }

  public String getCity() {
    return city;
  }

  public String getPincode() {
    return pincode;
  }

  public String getCountry() {
    return country;
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


  public Student from(JSONObject jsonObject) {
    this.firstName = jsonObject.getString("first_name");
    this.lastName = jsonObject.getString("last_name");
    this.emailId = jsonObject.getString("email_id");
    this.phoneNumber = jsonObject.getString("phone_number");
    this.passportNumber = jsonObject.getString("passport_number");
    this.password = jsonObject.getString("password");
    this.reConfirmPassword = jsonObject.getString("re_confirm_password");
    this.dob = jsonObject.getString("dob");
    this.gender = jsonObject.getString("gender");
    this.state = jsonObject.getString("state");
    this.city = jsonObject.getString("city");
    this.country = jsonObject.getString("country");
    this.pincode = jsonObject.getString("pincode");
    this.securityAnswerOne = jsonObject.getString("security_answer_one");
    this.securityAnswerTwo = jsonObject.getString("security_answer_two");
    this.securityAnswerThree =
            jsonObject.getString("security_answer_three");

    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Student)) return false;
    Student student = (Student) o;
    return Objects.equals(getFirstName(), student.getFirstName()) &&
            Objects.equals(getLastName(), student.getLastName()) &&
            Objects.equals(getEmailId(), student.getEmailId()) &&
            Objects
                    .equals(getPhoneNumber(), student.getPhoneNumber()) &&
            Objects.equals(getPassportNumber(),
                    student.getPassportNumber()) &&
            Objects.equals(getPassword(), student.getPassword()) &&
            Objects.equals(getReConfirmPassword(),
                    student.getReConfirmPassword()) &&
            Objects.equals(getDob(), student.getDob()) &&
            Objects.equals(getGender(), student.getGender()) &&
            Objects.equals(getState(), student.getState()) &&
            Objects.equals(getCity(), student.getCity()) &&
            Objects.equals(getPincode(), student.getPincode()) &&
            Objects.equals(getCountry(), student.getCountry()) &&
            Objects.equals(getSecurityAnswerOne(),
                    student.getSecurityAnswerOne()) && Objects
            .equals(getSecurityAnswerTwo(),
                    student.getSecurityAnswerTwo()) &&
            Objects.equals(getSecurityAnswerThree(),
                    student.getSecurityAnswerThree());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getFirstName(), getLastName(), getEmailId(),
            getPhoneNumber(), getPassportNumber(), getPassword(),
            getReConfirmPassword(), getDob(), getGender(), getState(),
            getCity(), getPincode(), getCountry(), getSecurityAnswerOne(),
            getSecurityAnswerTwo(), getSecurityAnswerThree());
  }
}
