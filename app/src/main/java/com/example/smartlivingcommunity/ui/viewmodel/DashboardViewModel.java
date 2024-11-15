package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.smartlivingcommunity.data.model.ResidentEvent;
import com.example.smartlivingcommunity.data.model.ResidentParkingSlot;
import com.example.smartlivingcommunity.data.model.ResidentServiceRequest;

import java.util.List;

/**
 * ViewModel for the ResidentDashboard, managing and preparing data related to parking slots, events,
 * and service requests for display in the UI.
 *
 * @author Saon
 */
public class DashboardViewModel extends ViewModel {

    /** Repository instance providing data for the dashboard */
    private ResidentRepository repository;

    /** LiveData list of parking slots */
    private MutableLiveData<List<ResidentParkingSlot>> parkingSlots;

    /** LiveData list of community events */
    private MutableLiveData<List<ResidentEvent>> events;

    /** LiveData list of service requests */
    private MutableLiveData<List<ResidentServiceRequest>> serviceRequests;

    /**
     * Initializes the ViewModel, loading data from the repository and populating
     * LiveData objects for parking slots, events, and service requests.
     */
    public DashboardViewModel() {
        repository = new ResidentRepository();
        parkingSlots = new MutableLiveData<>(repository.getParkingSlots());
        events = new MutableLiveData<>(repository.getEvents());
        serviceRequests = new MutableLiveData<>(repository.getServiceRequests());
    }

    /**
     * Returns a LiveData list of {@code ResidentParkingSlot} objects to be observed by the UI.
     *
     * @return LiveData containing a list of parking slots
     */
    public LiveData<List<ResidentParkingSlot>> getParkingSlots() {
        return parkingSlots;
    }

    /**
     * Returns a LiveData list of {@code ResidentEvent} objects to be observed by the UI.
     *
     * @return LiveData containing a list of events
     */
    public LiveData<List<ResidentEvent>> getEvents() {
        return events;
    }

    /**
     * Returns a LiveData list of {@code ResidentServiceRequest} objects to be observed by the UI.
     *
     * @return LiveData containing a list of service requests
     */
    public LiveData<List<ResidentServiceRequest>> getServiceRequests() {
        return serviceRequests;
    }
}
