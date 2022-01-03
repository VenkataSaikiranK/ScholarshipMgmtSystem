package com.dal.group7.service.implementation;

import com.dal.group7.persistent.implementations.StudentDao;
import com.dal.group7.persistent.model.Student;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;

class StudentServiceTest {
    private static final String FILE_PATH = "file-path";
    private static final String EMAIL_ID_ONE = "batman@dal.ca";
    private static final String EMAIL_ID_TWO = "batman@gmail.com";
    private static final Student STUDENT =
            new Student("Bruce", "Wayne", "bruce@dal.ca",
                    "8579207387", "R931023", "batsy", "batsy",
                    "1980-10-03 00:00:00", "male", "Metropolis", "Gotham",
                    "3AT7A5",
                    "USA", "Liverpool", "Chicken", "Christian Bale");

    @Mock
    private StudentDao studentDao;

    @Mock
    private JsonFileReader jsonFileReader;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /* Returns true if studentService is able to read and
     * save the student to db */
    @Test
    void shouldReadFromJsonFileAndSaveScholarship() throws SQLException,
            IOException {
        Mockito.when(jsonFileReader.readJson(any()))
                .thenReturn(new JSONObject(getSource()));

        studentService.signup(FILE_PATH);
        Mockito.verify(studentDao).insertOne(STUDENT);
    }

    // Returns the Student JSON object
    private String getSource() {
        return "{\n" +
                "  \"first_name\": \"Bruce\",\n" +
                "  \"last_name\": \"Wayne\",\n" +
                "  \"email_id\": \"bruce@dal.ca\",\n" +
                "  \"phone_number\": \"8579207387\",\n" +
                "  \"passport_number\": \"R931023\",\n" +
                "  \"password\": \"batsy\",\n" +
                "  \"re_confirm_password\": \"batsy\", \n" +
                "  \"dob\": \"1980-10-03 00:00:00\", \n" +
                "  \"gender\": \"male\", \n" +
                "  \"state\": \"Metropolis\", \n" +
                "  \"city\": \"Gotham\", \n" +
                "  \"country\": \"USA\", \n" +
                "  \"pincode\": \"3AT7A5\", \n" +
                "  \"security_answer_one\": \"Liverpool\", \n" +
                "  \"security_answer_two\": \"Chicken\", \n" +
                "  \"security_answer_three\": \"Christian Bale\" \n" +
                "}";
    }

    /* Returns true if doesEmailExist() is invoked */
    @Test
    void shouldCheckStudentExists() throws SQLException {
        studentService.doesStudentExist(STUDENT.getEmailId());
        Mockito.verify(studentDao).doesExist(STUDENT.getEmailId());
    }

    /* returns true if student attributes are valid */
    @Test
    void isValidStudent() {
        Student student = new Student("Bruce", "Wayne", "bruce.wayne@dal.ca",
                "878-98287", "R198A2J", "somePass", "somePass",
                "2020-09-10 08:22:31", "male", "Metropolis", "Gotham",
                "3AT7A5",
                "USA", "Liverpool", "Cheese B", "Bale");

        StudentService ss = new StudentService(studentDao, jsonFileReader);
        Assertions.assertTrue(ss.isValid(student));
    }

    /* returns false if student attributes are invalid */
    @Test
    void isNotValidStudent() {
        Student student = new Student("Bruce", "Wayne", "bruce.wayne@dal.ca",
                "878-98287", "R198A2J", "differentPass", "somePass",
                "2020-09-10 08:22:31", "male", "Metropolis", "Gotham",
                "3AT7A5",
                "", "Liverpool", "", "");

        StudentService ss = new StudentService(studentDao, jsonFileReader);
        Assertions.assertFalse(ss.isValid(student));
    }

    /* returns true if student has valid email */
    @Test
    void shouldApproveValidEmail() {
        StudentService studentService =
                new StudentService(studentDao, jsonFileReader);
        Assertions.assertTrue(studentService.isValidStudentEmail(EMAIL_ID_ONE),
                "Invalid Email approved");
    }

    /* returns true if student has valid email */
    @Test
    void shouldNotApproveInValidEmail() {
        StudentService studentService =
                new StudentService(studentDao, jsonFileReader);
        Assertions.assertFalse(studentService.isValidStudentEmail(""),
                "Empty emailId approved");
        Assertions
                .assertFalse(studentService.isValidStudentEmail(EMAIL_ID_TWO),
                        "Failed to reject InValid Email");
    }

    /* returns true if exceptions are handled */
    @Test
    void shouldHandleInvalidEmailExceptions() {
        StudentService studentService =
                new StudentService(studentDao, jsonFileReader);
        try {
            Assertions.assertFalse(studentService.isValidStudentEmail(null),
                    "null check failed");
        } catch (Exception e) {
            // Do Nothing
        }
    }

}