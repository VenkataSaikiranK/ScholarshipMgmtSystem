package com.dal.group7.service.implementation;

import com.dal.group7.persistent.implementations.ScholarshipDao;
import com.dal.group7.persistent.model.Scholarship;
import com.dal.group7.persistent.model.ScholarshipHandle;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static java.sql.Date.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class MinistryScholarshipServiceTest {

    private static final String FILE_PATH = "file-path";
    private static final Scholarship scholarship = new Scholarship(1, "name", valueOf("2021-12-12"),
            5000D,5000D, 5000D,5000D, true,
            true, true);

    private static final List<Scholarship> scholarships = Arrays.asList(new ScholarshipHandle());

    @Mock
    private ScholarshipDao scholarshipDao;

    @Mock
    private JsonFileReader jsonFileReader;

    @InjectMocks
    private MinistryScholarshipService ministryScholarshipService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReadFromJsonFileAndSaveScholarship()
            throws SQLException, IOException {
        Mockito.when(jsonFileReader.readJson(any()))
                .thenReturn(new JSONObject(getSource()));

        ministryScholarshipService.saveScholarship(FILE_PATH);

        Mockito.verify(scholarshipDao).insertOne(scholarship);
    }

    @Test
    void shouldGetScholarshipList() throws SQLException {
        ministryScholarshipService.displayScholarships();
        Mockito.verify(scholarshipDao, Mockito.atLeastOnce()).getAll();
    }

    @Test
    void shouldGetScholarshipListByCriteria() throws SQLException {

        Mockito.when(scholarshipDao.getAllScholarshipsByCriteria(any(), any(), any())).thenReturn(scholarships);

        assertEquals(scholarships, ministryScholarshipService.displayScholarshipsByCriteria("1", "1", "1"));


    }

    private String getSource() {
        return "{\n" +
                "  \"form_name\": \"CREATE SCHOLARSHIP\",\n" +
                "  \"scholarship_name\": \"name\",\n" +
                "  \"effective_date\": \"2021-12-12\",\n" +
                "  \"tuition_amount\": 5000,\n" +
                "  \"insurance_amount\": 5000,\n" +
                "  \"travel_amount\": 5000,\n" +
                "  \"living_expenses_amount\": 5000,\n" +
                "  \"scholarship_criteria_girl_child\": \"true\",\n" +
                "  \"scholarship_criteria_sports\": \"true\",\n" +
                "  \"scholarship_criteria_academics\": \"true\" \n" +
                "}";
    }
}