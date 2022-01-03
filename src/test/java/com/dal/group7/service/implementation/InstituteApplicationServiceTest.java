package com.dal.group7.service.implementation;

import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static com.dal.group7.persistent.model.ApplicationStatus.APPROVED;
import static com.dal.group7.persistent.model.ApplicationStatus.REJECTED;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class InstituteApplicationServiceTest {

    private static final String ID = "2137184";
    private static final String INSTITUTE_STATUS = "institute_status";
    private static final List<Application> applications = Arrays.asList(new Application());

    @Mock
    private Dao<String, Application> applicationDao;

    @InjectMocks
    private InstituteApplicationService instituteApplicationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnTrueIfExist() throws SQLException {
        Mockito.when(applicationDao.doesExist(any())).thenReturn(true);

        assertTrue(instituteApplicationService.doesExist(ID));
        Mockito.verify(applicationDao).doesExist(ID);
    }

    @Test
    void shouldReturnFalseIfNotExist() throws SQLException {
        Mockito.when(applicationDao.doesExist(any())).thenReturn(false);

        assertFalse(instituteApplicationService.doesExist(ID));
        Mockito.verify(applicationDao).doesExist(ID);
    }

    @Test
    void shouldApproveApplication() throws SQLException {
        instituteApplicationService.approveApplication(ID);

        Mockito.verify(applicationDao).updateValue(ID, INSTITUTE_STATUS, APPROVED.toString());
    }

    @Test
    void shouldRejectApplication() throws SQLException {
        instituteApplicationService.rejectApplication(ID);

        Mockito.verify(applicationDao).updateValue(ID, INSTITUTE_STATUS, REJECTED.toString());
    }

    @Test
    void shouldDisplayApprovedApplications() throws SQLException {
        Mockito.when(applicationDao.getAllApplicationByStatus()).thenReturn(applications);

        assertEquals(applications, instituteApplicationService.displayApprovedApplications());
    }

    @Test
    void shouldDisplayApplications() throws SQLException {
        Mockito.when(applicationDao.getAll()).thenReturn(applications);

        assertEquals(applications, instituteApplicationService.displayApplications());
    }

}