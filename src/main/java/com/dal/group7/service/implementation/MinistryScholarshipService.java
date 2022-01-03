package com.dal.group7.service.implementation;

import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.Scholarship;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.dal.group7.constants.ViewConstants.NO;

public class MinistryScholarshipService {

  private final Dao<Integer, Scholarship> scholarshipDao;
  private final JsonFileReader jsonFileReader;
  private static final String MALE = "male";

  public MinistryScholarshipService(Dao<Integer, Scholarship> scholarshipDao,
                                    JsonFileReader jsonFileReader) {
    this.scholarshipDao = scholarshipDao;
    this.jsonFileReader = jsonFileReader;
  }

  public void saveScholarship(String filePath)
          throws SQLException, IOException {
    var jsonObject = jsonFileReader.readJson(filePath);
    var scholarship = new Scholarship().from(jsonObject);
    scholarshipDao.insertOne(scholarship);
  }

  public List<Scholarship> displayScholarships() throws SQLException {
    return scholarshipDao.getAll();
  }

  public int genderParameterMapping(String gender) {
    if (gender.equalsIgnoreCase(MALE)) {
      return 0;
    } else {
      return 1;
    }
  }

  public int academicParameterMapping(String academicParameter) {
    if (academicParameter.equalsIgnoreCase(NO)) {
      return 0;
    } else {
      return 1;
    }
  }

  public int sportsParameterMapping(String sportsParameter) {
    if (sportsParameter.equalsIgnoreCase(NO)) {
      return 0;
    } else {
      return 1;
    }
  }

  public List<Scholarship> displayScholarshipsByCriteria(String girlCriteria, String academicCriteria, String sportsCriteria) throws SQLException {
    return scholarshipDao.getAllScholarshipsByCriteria(girlCriteria, academicCriteria, sportsCriteria);
  }
}
