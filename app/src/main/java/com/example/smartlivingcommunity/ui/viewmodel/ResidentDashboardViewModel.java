package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.smartlivingcommunity.data.model.Event;
import com.example.smartlivingcommunity.data.model.ParkingSlot;
import com.example.smartlivingcommunity.data.model.ServiceRequest;
import com.example.smartlivingcommunity.data.repository.ResidentRepository;
import java.util.List;

/**
 * ViewModel for the Resident Dashboard, managing and preparing data related to parking slots, events,
 * and service requests for display in the UI.
 *
 * @author Saon
 */
public class ResidentDashboardViewModel extends ViewModel {

    /** Repository instance providing resident-related data */
    private ResidentRepository repository;

    /** LiveData list of parking slots */
    private MutableLiveData<List<ParkingSlot>> parkingSlots;

    /** LiveData list of community events */
    private MutableLiveData<List<Event>> events;

    /** LiveData list of service requests */
    private MutableLiveData<List<ServiceRequest>> serviceRequests;

    /**
     * Initializes the ViewModel, loading data from the repository and populating
     * LiveData objects for parking slots, events, and service requests.
     */
    public ResidentDashboardViewModel() {
        repository = new ResidentRepository();
        parkingSlots = new MutableLiveData<>(repository.getParkingSlots());
        events = new MutableLiveData<>(repository.getEvents());
        serviceRequests = new MutableLiveData<>(repository.getServiceRequests());
    }

    /**
     * Returns a LiveData list of {@code ParkingSlot} objects to be observed by the UI.
     *
     * @return LiveData containing a list of parking slots
     */
    public LiveData<List<ParkingSlot>> getParkingSlots() {
        return parkingSlots;
    }

    /**
     * Returns a LiveData list of {@code Event} objects to be observed by the UI.
     *
     * @return LiveData containing a list of events
     */
    public LiveData<List<Event>> getEvents() {
        return events;
    }

    /**
     * Returns a LiveData list of {@code ServiceRequest} objects to be observed by the UI.
     *
     * @return LiveData containing a list of service requests
     */
    public LiveData<List<ServiceRequest>> getServiceRequests() {
        return serviceRequests;
    }
}
