package com.dal.group7.service.implementation;

import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.Application;
import com.dal.group7.persistent.model.Scholarship;
import com.dal.group7.persistent.model.StudentFeedback;
import com.dal.group7.persistent.model.StudentFinance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static com.dal.group7.persistent.implementations.DaoFactory.INSTITUTE;
import static com.dal.group7.persistent.model.ApplicationStatus.FUND_ISSUED;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MinistryApplicationServiceTest {

    private static final String ID = "id";
    private static final Application APPLICATION = new Application();
    private static final Scholarship SCHOLARSHIP = new Scholarship(1, "name", null,
            10000D, 10000D, 10000D, 10000D, true,
            true, true);
    private static final StudentFinance STUDENT_FINANCE = new StudentFinance();
    @Mock
    private Dao<String, Application> applicationDao;

    @Mock
    private Dao<Integer, Scholarship> scholarshipDao;

    @Mock
    private Dao<String, StudentFinance> studentFinanceDao;

    @Mock
    private Dao<Integer, StudentFeedback> studentFeedbackDao;

    @InjectMocks
    MinistryApplicationService ministryApplicationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ministryApplicationService =
                new MinistryApplicationService(applicationDao, scholarshipDao,
                        studentFinanceDao,
                        INSTITUTE.createDao(), studentFeedbackDao);
    }

    @Test
    void shouldGetApplicationWithAmountForSlabA() throws SQLException {
        STUDENT_FINANCE.setAnnualIncome(2000001D);
        when(applicationDao.findById(any())).thenReturn(of(APPLICATION));
        when(scholarshipDao.findById(any())).thenReturn(of(SCHOLARSHIP));
        when(studentFinanceDao.findById(any())).thenReturn(of(STUDENT_FINANCE));
        final Application applicationWithAmount = ministryApplicationService.getApplicationWithAmount(ID);

        assertEquals(6000D, applicationWithAmount.getTuitionAmount());
        assertEquals(10000D, applicationWithAmount.getInsuranceAmount());
        assertEquals(0D, applicationWithAmount.getTravelAmount());
        assertEquals(0D, applicationWithAmount.getLivingExpensesAmount());
    }

    @Test
    void shouldGetApplicationWithAmountForSlabB() throws SQLException {
        STUDENT_FINANCE.setAnnualIncome(1600000D);
        when(applicationDao.findById(any())).thenReturn(of(APPLICATION));
        when(scholarshipDao.findById(any())).thenReturn(of(SCHOLARSHIP));
        when(studentFinanceDao.findById(any())).thenReturn(of(STUDENT_FINANCE));
        final Application applicationWithAmount = ministryApplicationService.getApplicationWithAmount(ID);

        assertEquals(7000D, applicationWithAmount.getTuitionAmount());
        assertEquals(5000D, applicationWithAmount.getInsuranceAmount());
        assertEquals(5000D, applicationWithAmount.getTravelAmount());
        assertEquals(0D, applicationWithAmount.getLivingExpensesAmount());
    }

    @Test
    void shouldGetApplicationWithAmountForSlabBWithUpperLimit() throws SQLException {
        STUDENT_FINANCE.setAnnualIncome(2000000D);
        when(applicationDao.findById(any())).thenReturn(of(APPLICATION));
        when(scholarshipDao.findById(any())).thenReturn(of(SCHOLARSHIP));
        when(studentFinanceDao.findById(any())).thenReturn(of(STUDENT_FINANCE));
        final Application applicationWithAmount = ministryApplicationService.getApplicationWithAmount(ID);

        assertEquals(7000D, applicationWithAmount.getTuitionAmount());
        assertEquals(5000D, applicationWithAmount.getInsuranceAmount());
        assertEquals(5000D, applicationWithAmount.getTravelAmount());
        assertEquals(0D, applicationWithAmount.getLivingExpensesAmount());
    }

    @Test
    void shouldGetApplicationWithAmountForSlabBWithLowerLimit() throws SQLException {
        STUDENT_FINANCE.setAnnualIncome(1500001D);
        when(applicationDao.findById(any())).thenReturn(of(APPLICATION));
        when(scholarshipDao.findById(any())).thenReturn(of(SCHOLARSHIP));
        when(studentFinanceDao.findById(any())).thenReturn(of(STUDENT_FINANCE));
        final Application applicationWithAmount =
                ministryApplicationService.getApplicationWithAmount(ID);

        assertEquals(7000D, applicationWithAmount.getTuitionAmount());
        assertEquals(5000D, applicationWithAmount.getInsuranceAmount());
        assertEquals(5000D, applicationWithAmount.getTravelAmount());
        assertEquals(0D, applicationWithAmount.getLivingExpensesAmount());
    }

    @Test
    void shouldGetApplicationWithAmountForSlabC() throws SQLException {
        STUDENT_FINANCE.setAnnualIncome(1200000D);
        when(applicationDao.findById(any())).thenReturn(of(APPLICATION));
        when(scholarshipDao.findById(any())).thenReturn(of(SCHOLARSHIP));
        when(studentFinanceDao.findById(any())).thenReturn(of(STUDENT_FINANCE));
        final Application applicationWithAmount =
                ministryApplicationService.getApplicationWithAmount(ID);

        assertEquals(10000D, applicationWithAmount.getTuitionAmount());
        assertEquals(7500D, applicationWithAmount.getInsuranceAmount());
        assertEquals(7500D, applicationWithAmount.getTravelAmount());
        assertEquals(0D, applicationWithAmount.getLivingExpensesAmount());
    }

    @Test
    void shouldGetApplicationWithAmountForSlabCWithUpperLimit()
            throws SQLException {
        STUDENT_FINANCE.setAnnualIncome(1500000D);
        when(applicationDao.findById(any())).thenReturn(of(APPLICATION));
        when(scholarshipDao.findById(any())).thenReturn(of(SCHOLARSHIP));
        when(studentFinanceDao.findById(any())).thenReturn(of(STUDENT_FINANCE));
        final Application applicationWithAmount =
                ministryApplicationService.getApplicationWithAmount(ID);

        assertEquals(10000D, applicationWithAmount.getTuitionAmount());
        assertEquals(7500D, applicationWithAmount.getInsuranceAmount());
        assertEquals(7500D, applicationWithAmount.getTravelAmount());
        assertEquals(0D, applicationWithAmount.getLivingExpensesAmount());
    }

    @Test
    void shouldIssueFunds() throws SQLException {
        ministryApplicationService.issueFundToApplication(APPLICATION);

        verify(applicationDao)
                .updateValue(null, "ministry_status", FUND_ISSUED.toString());
        verify(applicationDao).setValues(APPLICATION);
    }

    @Test
    void shouldGetApplicationWithAmountForSlabD() throws SQLException {
        STUDENT_FINANCE.setAnnualIncome(300000D);
        when(applicationDao.findById(any())).thenReturn(of(APPLICATION));
        when(scholarshipDao.findById(any())).thenReturn(of(SCHOLARSHIP));
        when(studentFinanceDao.findById(any())).thenReturn(of(STUDENT_FINANCE));
        final Application applicationWithAmount =
                ministryApplicationService.getApplicationWithAmount(ID);

        assertEquals(10000D, applicationWithAmount.getTuitionAmount());
        assertEquals(10000D, applicationWithAmount.getInsuranceAmount());
        assertEquals(10000D, applicationWithAmount.getTravelAmount());
        assertEquals(10000D, applicationWithAmount.getLivingExpensesAmount());
    }

    @Test
    void calculateTenPercentGrantAmountPositive() {
        Application localApp = new Application();
        localApp.setTuitionAmount(230000);
        localApp.setTravelAmount(15000);
        localApp.setInsuranceAmount(50000);

        double amt = ministryApplicationService
                .calculateTenPercentGrantAmount(localApp);

        assertEquals(amt, 29500.0,
                "Incorrect Percent is used to calculate the grant amount");
    }

    @Test
    void calculateTenPercentGrantAmountNegative() {
        Application localApp = new Application();
        localApp.setTuitionAmount(230000);
        localApp.setTravelAmount(15000);
        localApp.setInsuranceAmount(50000);

        double amt = ministryApplicationService
                .calculateTenPercentGrantAmount(localApp);

        assertNotEquals(amt, 5478,
                "Incorrect Percent is used to calculate the grant amount");
    }
}