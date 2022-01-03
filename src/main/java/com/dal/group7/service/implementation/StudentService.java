package com.dal.group7.service.implementation;

import com.dal.group7.constants.StudentConstants;
import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.Student;
import com.dal.group7.service.interfaces.UserService;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;

import static com.dal.group7.constants.ViewConstants.INVALID_EMAIL;
import static com.dal.group7.constants.ViewConstants.INVALID_PARAMS;

public class StudentService implements UserService {

  private final Dao<Integer, Student> studentDao;
  private final JsonFileReader jsonFileReader;

  public StudentService(Dao<Integer, Student> studentDao,
                        JsonFileReader jsonFileReader) {
    this.studentDao = studentDao;
    this.jsonFileReader = jsonFileReader;
  }

  @Override
  public void signup(String filepath) throws IOException, SQLException {
    final JSONObject jsonObject = jsonFileReader.readJson(filepath);
    Student student = new Student().from(jsonObject);

    if (Boolean.TRUE.equals(isValid(student)) &&
            Boolean.FALSE
                    .equals(doesStudentExist(student.getEmailId())) &&
            Boolean.TRUE
                    .equals(isValidStudentEmail(student.getEmailId()))) {
      studentDao.insertOne(student);
    } else {
      throw new IllegalArgumentException(
              INVALID_PARAMS);
    }
  }

  public Boolean doesStudentExist(String emailId) throws SQLException {
    return studentDao.doesExist(emailId);
  }

  public Boolean isValidStudentEmail(String emailId) {
    try {
      if (!emailId.equals("")) {
        String[] emailSplits =
                emailId.split(StudentConstants.getEmailDelimiter());

        if (emailSplits.length > 0) {
          String userDomain = emailSplits[1];
          return (!StudentConstants.getInvalidDomains()
                  .contains(userDomain));
        }
      }
    } catch (Exception e) {
      throw new IllegalArgumentException(INVALID_EMAIL);
    }
    return false;
  }

  public Boolean isValid(Student student) {
    boolean names = !student.getFirstName().equals("") &&
            student.getFirstName() != null
            && !student.getLastName().equals("") &&
            student.getLastName() != null;
    boolean passwords =
            student.getPassword().equals(student.getReConfirmPassword())
                    && !student.getPassword().equals("") &&
                    student.getPassword() != null;
    boolean emailID = student.getEmailId() != null &&
            !student.getEmailId().equals("");
    boolean cities =
            student.getCity() != null && !student.getCity().equals("");
    boolean dob = student.getDob() != null && !student.getDob().equals("");
    boolean gender =
            student.getGender() != null && !student.getGender().equals("");
    boolean pincode = student.getPincode() != null &&
            !student.getPincode().equals("");
    boolean address =
            student.getState() != null && !student.getState().equals("")
                    && student.getCountry() != null &&
                    !student.getCountry().equals("");
    boolean securityAnswers = student.getSecurityAnswerOne() != null &&
            !student.getSecurityAnswerOne().equals("")
            && student.getSecurityAnswerTwo() != null &&
            !student.getSecurityAnswerTwo().equals("")
            && student.getSecurityAnswerThree() != null &&
            !student.getSecurityAnswerThree().equals("");

    return (names && passwords && emailID && cities && dob && gender &&
            pincode && address && securityAnswers);
  }

}
