package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.smartlivingcommunity.data.model.ParkingList;
import com.example.smartlivingcommunity.data.model.SecurityLog;
import com.example.smartlivingcommunity.data.repository.SecurityRepository;

import java.util.List;

/**
 * ViewModel for managing the data of the Security Dashboard.
 * This ViewModel interacts with the SecurityRepository to fetch
 * security logs and parking list data.
 *
 * @author Saon
 */
public class SecurityDashboardViewModel extends ViewModel {

    private final SecurityRepository repository;
    private final LiveData<List<SecurityLog>> securityLogs;
    private final LiveData<List<ParkingList>> parkingList;

    /**
     * Constructs a SecurityDashboardViewModel instance and initializes
     * the SecurityRepository to fetch data.
     */
    public SecurityDashboardViewModel() {
        repository = new SecurityRepository();
        securityLogs = repository.getSecurityLogs();
        parkingList = repository.getParkingList();
    }

    /**
     * Returns the LiveData object containing the list of security logs.
     *
     * @return LiveData<List<SecurityLog>> - A LiveData object that holds
     *         the list of security logs which can be observed by UI components.
     */
    public LiveData<List<SecurityLog>> getSecurityLogs() {
        return securityLogs;
    }

    /**
     * Returns the LiveData object containing the list of parking information.
     *
     * @return LiveData<List<ParkingList>> - A LiveData object that holds
     *         the list of parking details which can be observed by UI components.
     */
    public LiveData<List<ParkingList>> getParkingList() {
        return parkingList;
    }
}
