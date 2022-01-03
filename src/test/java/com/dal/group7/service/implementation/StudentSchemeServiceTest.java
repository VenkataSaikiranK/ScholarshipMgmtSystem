package com.dal.group7.service.implementation;

import com.dal.group7.persistent.model.Application;
import com.dal.group7.persistent.model.Scheme;
import com.dal.group7.persistent.model.UserCredential;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.sql.Date;

class StudentSchemeServiceTest {
    private static final String FILE_PATH = "file-path";
    private static final Scheme scheme =
            new Scheme(1, "bruce@dal.ca", "male", 4, 1, 8.7, 7.9, 7.5, "CBSE",
                    "BEAP", 0, 1, 3,
                    Date.valueOf("2016-09-11"), Date.valueOf("2020-05-05"), 0,
                    2, 5, 6, 2, 9, "ABC123FGH", "ICICI0003", 680000, "ICICI",
                    "Bruce Wayne");
    private static final Scheme SCHEME_1=
            new Scheme(1, "bruce@dal.ca", "male", 4, 1, 8.7, 7.9, 7.5, "CBSE",
                    "", 0, 1, 3,
                    Date.valueOf("2016-09-11"), Date.valueOf("2020-05-05"), 0,
                    2, 5, 6, 2, 9, "", "ICICI0003", 680000, "ICICI",
                    "");
    private static final Application APPLICATION =
            new Application("ABC123", 4, "bruce@dal.ca", 1, "2021-05-05",
                    "2021-07-08", "SUBMITTED", "APPROVED", "REJECTED", 2.0, 3.0,
                    2.5, scheme);
    private static final String USERNAME = "userName123";
    private static final String PASSWORD = "password@123";
    private static final UserCredential
            USER_1 = new UserCredential(USERNAME, PASSWORD, "NO", "NO",
            "2020-01-10", "1", "NONE", "NONE",
            "NONE", "0", "institute", "NO");
    private static final UserCredential
            USER_2 = new UserCredential(USERNAME, PASSWORD, "YES", "YES",
            "2020-01-10", "1", "NONE", "NONE",
            "NONE", "0", "institute", "YES");


    @InjectMocks
    private StudentSchemeService studentSchemeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void shouldCheckEligibilityOfUserPositive() {
        Boolean status = studentSchemeService.isUserEligible(USER_1);
        Assertions.assertTrue(status, "Invalid user has been approved");
    }

    @Test
    void shouldCheckEligibilityOfUserNegative() {

        Boolean status = studentSchemeService.isUserEligible(USER_2);
        Assertions.assertFalse(status, "Eligible user has been disapproved");
    }

    @Test
    void shouldCheckIfApplicationIsValidPositive() {
        Assertions.assertTrue(studentSchemeService.isApplicationValid(scheme),
                "Correct details not approved");
    }

    @Test
    void shouldCheckIfApplicationIsValidNegative() {
        Assertions.assertFalse(studentSchemeService.isApplicationValid(SCHEME_1),
                "Correct details not approved");
    }

    @Test
    void shouldCheckAcademicProfileScore(){
        double academicScore = studentSchemeService.calculateAcademicScore(APPLICATION);
        Assertions.assertEquals(70.541,academicScore);
    }

    @Test
    void shouldCheckNonAcademicSportsProfileScore(){
        double academicScore = studentSchemeService.calculateSportsScholarshipScore(APPLICATION);
        Assertions.assertEquals(23.450,academicScore);
    }

    @Test
    void shouldCheckNonAcademicArtsProfileScore(){
        double academicScore = studentSchemeService.calculateArtsScholarshipScore(APPLICATION);
        Assertions.assertEquals(56.95,academicScore);
    }

}