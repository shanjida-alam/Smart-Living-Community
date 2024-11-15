package com.example.smartlivingcommunity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.example.smartlivingcommunity.data.model.SecurityLog;
import com.example.smartlivingcommunity.ui.viewmodel.SecurityLogViewModel;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SecurityLogViewModelTest {

    private SecurityLogViewModel viewModel;


    @Before
    public void setup() {
        viewModel = new SecurityLogViewModel();
    }

    @Test
    public void testAddSecurityLog() {
        SecurityLog log = new SecurityLog(
                "Tanvir", "Visitor",
                "08:00", "10:00",
                "2024-11-10", "2024-11-10");

        viewModel.addSecurityLog(log);
        List<SecurityLog> logs = viewModel.getSecurityLogs();
        assertEquals(1, logs.size());
        assertEquals("Tanvir", logs.get(0).getVisitorName());
    }

    @Test
    public void testGetSecurityLogs() {
        viewModel.addSecurityLog(new SecurityLog(
                "Saon", "Contractor",
                "09:00", "11:00",
                "2024-11-11", "2024-11-11"));

        viewModel.addSecurityLog(new SecurityLog(
                "Saon", "Delivery",
                "10:30", "11:30",
                "2024-11-11", "2024-11-11"));

        List<SecurityLog> logs = viewModel.getSecurityLogs();
        assertEquals(2, logs.size());
    }

    @Test
    public void testInvalidLogWithoutEntryTime() {
        // Exit time exists, but entry time is missing
        SecurityLog log = new SecurityLog(
                "Saon", "Visitor",
                null, "10:00",
                "2024-11-10", "2024-11-10");

        boolean isValid = viewModel.isValidSecurityLog(log);
        assertFalse(isValid);
    }

    @Test
    public void testInvalidLogWithFutureEntryDate() {
        // Entry date is after exit date, which is invalid
        SecurityLog log = new SecurityLog(
                "Saon", "Visitor",
                "08:00", "10:00",
                "2024-11-11", "2024-11-10");

        boolean isValid = viewModel.isValidSecurityLog(log);
        assertFalse(isValid);
    }


    @Test
    public void testLogWithNullValues() {
        // Some fields are null
        SecurityLog log = new SecurityLog(
                null, "Saon",
                "08:00", "10:00",
                "2024-11-10", "2024-11-10");

        // Call isValidSecurityLog outside of assert and check for false result
        boolean isValid = viewModel.isValidSecurityLog(log);
        assertFalse(isValid);

        log = new SecurityLog(
                "Saon", null,
                "08:00", "10:00",
                "2024-11-10", "2024-11-10");

        // Check for false result again with updated log
        isValid = viewModel.isValidSecurityLog(log);
        assertFalse(isValid);
    }


    @Test
    public void testLogWithInvalidMonth() {
        // Month exceeds 12, which is invalid
        SecurityLog log = new SecurityLog(
                "Saon", "Visitor",
                "08:00", "10:00",
                "2024-13-10", "2024-11-10");

        boolean isValid = viewModel.isValidSecurityLog(log);
        assertEquals(false, isValid);
    }

    @Test
    public void testLogWithInvalidHour() {
        // Hour exceeds 24, which is invalid
        SecurityLog log = new SecurityLog(
                "Saon", "Visitor",
                "26:00", "10:00",
                "2024-11-10", "2024-11-10");

        boolean isValid = viewModel.isValidSecurityLog(log);
        assertEquals(false, isValid);

        log = new SecurityLog(
                "Saon", "Visitor",
                "08:00", "25:00",
                "2024-11-10", "2024-11-10");

        isValid = viewModel.isValidSecurityLog(log);
        assertEquals(false, isValid);
    }

    @Test
    public void testValidLogEntry() {
        SecurityLog log = new SecurityLog(
                "Saon", "Visitor",
                "08:00", "10:00",
                "2024-11-10", "2024-11-10");

        boolean isValid = viewModel.isValidSecurityLog(log);
        assertEquals(true, isValid);
    }

}




