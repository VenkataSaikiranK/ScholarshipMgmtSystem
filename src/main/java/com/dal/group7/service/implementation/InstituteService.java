package com.dal.group7.service.implementation;

import com.dal.group7.constants.InstituteConstants;
import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.Institute;
import com.dal.group7.service.interfaces.UserService;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;

import static com.dal.group7.constants.ViewConstants.INVALID_EMAIL;
import static com.dal.group7.constants.ViewConstants.INVALID_PARAMS;


public class InstituteService implements UserService {
    private final Dao<Integer, Institute> instituteDao;
    private final JsonFileReader jsonFileReader;

    public InstituteService(Dao<Integer, Institute> instituteDao,
                            JsonFileReader jsonFileReader) {
        this.instituteDao = instituteDao;
        this.jsonFileReader = jsonFileReader;

    }

    public boolean isValid(Institute institute) {
        boolean idFlag = (institute.getId() > 0);
        boolean nameFlag = (!institute.getName().equals("")) &&
                (institute.getName() != null);
        boolean emailIdFlag = (!institute.getEmailId().equals("")) &&
                (institute.getEmailId() != null);
        boolean registrationCodeFlag =
                (!institute.getRegistrationCode().isBlank());
        boolean phoneNumberFlag = (!institute.getPhoneNumber().equals(""));
        boolean addressFlag = (!institute.getAddress().equals("")) &&
                (institute.getAddress() != null);
        boolean stateFlag = (!institute.getState().equals("")) &&
                (institute.getState() != null);
        boolean cityFlag = (!institute.getCity().equals("")) &&
                (institute.getCity() != null);
        boolean countryFlag = (!institute.getCountry().equals("")) &&
                (institute.getCountry() != null);
        boolean pinCodeFlag = (!institute.getPinCode().equals(""));
        boolean securityAnswers = institute.getSecurityAnswerOne() != null &&
                !institute.getSecurityAnswerOne().equals("")
                && institute.getSecurityAnswerTwo() != null &&
                !institute.getSecurityAnswerTwo().equals("")
                && institute.getSecurityAnswerThree() != null &&
                !institute.getSecurityAnswerThree().equals("");


        return (idFlag && nameFlag && emailIdFlag && registrationCodeFlag &&
                phoneNumberFlag && addressFlag && stateFlag && cityFlag &&
                countryFlag && pinCodeFlag && securityAnswers);
    }

    public Boolean isValidInstituteEmail(String emailId) {
        try {
            if (!emailId.equals("")) {
                String[] emailSplits =
                        emailId.split(InstituteConstants.getInstEmailDelimiter());

                if (emailSplits.length > 0) {
                    String userDomain = emailSplits[1];
                    return (!InstituteConstants.getInvalidDomains()
                            .contains(userDomain));
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(INVALID_EMAIL);
        }
        return false;
    }

    @Override
    public void signup(String filepath) throws SQLException, IOException {
        final JSONObject jsonObject = jsonFileReader.readJson(filepath);
        Institute institute = new Institute().from(jsonObject);

        boolean isEligible =
                Boolean.TRUE.equals(isValid(institute)) && Boolean.FALSE
                        .equals(doesInstituteExist(
                                institute.getEmailId())) && Boolean.TRUE
                        .equals(isValidInstituteEmail(
                                institute.getEmailId()));

        if (isEligible) {
            instituteDao.insertOne(institute);
        } else {
            throw new InvalidPropertiesFormatException(
                    INVALID_PARAMS);
        }
    }

    public Boolean doesInstituteExist(String emailId) throws SQLException {
        return instituteDao.doesEmailExist(emailId);
    }

}
