package com.example.smartlivingcommunity.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartlivingcommunity.data.model.SecurityLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository class that provides access to the security log data.
 * This class serves as the data layer for security logs, providing
 * access to dummy security log data through LiveData.
 *
 * @author Saon
 */
public class SecurityLogRepository {
    private final MutableLiveData<List<SecurityLog>> securityLogs;

    /**
     * Constructs a SecurityLogRepository instance and initializes
     * the dummy list of security logs.
     */
    public SecurityLogRepository() {
        List<SecurityLog> dummyLogs = new ArrayList<>();
        dummyLogs.add(new SecurityLog("Ahona", "Visitor", "09:00", "11:00", "2024-11-11", "2024-11-11"));
        dummyLogs.add(new SecurityLog("Saon", "Contractor", "10:30", "12:00", "2024-11-11", "2024-11-11"));
        dummyLogs.add(new SecurityLog("Jubayer", "Delivery", "08:15", "09:15", "2024-11-12", "2024-11-12"));
        securityLogs = new MutableLiveData<>(dummyLogs);
    }

    /**
     * Gets the security logs wrapped in LiveData.
     *
     * @return a LiveData object containing a list of security logs
     */
    public LiveData<List<SecurityLog>> getSecurityLogs() {
        return securityLogs;
    }
}
