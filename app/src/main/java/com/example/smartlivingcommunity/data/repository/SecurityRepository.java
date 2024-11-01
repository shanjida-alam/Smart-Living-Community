package com.example.smartlivingcommunity.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.smartlivingcommunity.data.model.ParkingList;
import com.example.smartlivingcommunity.data.model.SecurityLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository for managing data related to security logs and parking lists.
 * This class provides access to live data of security logs and parking lists.
 *
 * @author  Saon
 */
public class SecurityRepository {

    private final MutableLiveData<List<SecurityLog>> securityLogsLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<ParkingList>> parkingListLiveData = new MutableLiveData<>();

    /**
     * Initializes the repository and loads the initial data for security logs and parking lists.
     */
    public SecurityRepository() {
        loadSecurityLogs();
        loadParkingList();
    }

    /**
     * Returns the LiveData object containing a list of security logs.
     *
     * @return LiveData list of SecurityLog
     */
    public LiveData<List<SecurityLog>> getSecurityLogs() {
        return securityLogsLiveData;
    }

    /**
     * Returns the LiveData object containing a list of parking slots.
     *
     * @return LiveData list of ParkingList
     */
    public LiveData<List<ParkingList>> getParkingList() {
        return parkingListLiveData;
    }

    // Loads dummy data for security logs
    private void loadSecurityLogs() {
        List<SecurityLog> logs = new ArrayList<>();
        logs.add(new SecurityLog("John Doe", "2024-10-30", "2024-10-30", "08:00 AM", "06:00 PM"));
        logs.add(new SecurityLog("Jane Smith", "2024-10-30", "2024-10-30", "09:00 AM", "05:00 PM"));
        logs.add(new SecurityLog("Michael Brown", "2024-10-30", "2024-10-30", "07:30 AM", "04:30 PM"));
        logs.add(new SecurityLog("Emily Clark", "2024-10-31", "2024-10-31", "10:00 AM", "07:00 PM"));
        logs.add(new SecurityLog("Chris Wilson", "2024-10-31", "2024-10-31", "11:00 AM", "08:00 PM"));

        securityLogsLiveData.setValue(logs);
    }

    // Loads dummy data for parking list
    private void loadParkingList() {
        List<ParkingList> parkingData = new ArrayList<>();
        parkingData.add(new ParkingList("P1", "ABC1234", "Occupied", "R001"));
        parkingData.add(new ParkingList("P2", "XYZ5678", "Available", "R002"));
        parkingData.add(new ParkingList("P3", "LMN4321", "Occupied", "R003"));
        parkingData.add(new ParkingList("P4", "JKL8765", "Available", "R004"));
        parkingData.add(new ParkingList("P5", "DEF0987", "Occupied", "R005"));

        parkingListLiveData.setValue(parkingData);
    }
}
