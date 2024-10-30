package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartlivingcommunity.data.model.Complaint;
import com.example.smartlivingcommunity.data.model.Event;
import com.example.smartlivingcommunity.data.model.ParkingRequest;
import com.example.smartlivingcommunity.data.repository.ManagerRepository;

import java.util.List;

public class ManagerDashboardViewModel extends ViewModel {

    private final ManagerRepository repository;

    private final LiveData<List<ParkingRequest>> parkingRequests;
    private final LiveData<List<Complaint>> complaints;
    private final LiveData<List<Event>> events;

    public ManagerDashboardViewModel() {
        repository = new ManagerRepository();

        // Initialize LiveData for parking requests, complaints, and events
        parkingRequests = repository.getParkingRequests();
        complaints = repository.getComplaints();
        events = new MutableLiveData<>(repository.getEvents());
    }

    // Expose LiveData for parking requests
    public LiveData<List<ParkingRequest>> getParkingRequests() {
        return parkingRequests;
    }

    // Expose LiveData for complaints
    public LiveData<List<Complaint>> getComplaints() {
        return complaints;
    }

    // Expose LiveData for events
    public LiveData<List<Event>> getEvents() {
        return events;
    }
}
