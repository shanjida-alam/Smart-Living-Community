package com.example.smartlivingcommunity.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.smartlivingcommunity.data.model.Complaint;
import com.example.smartlivingcommunity.data.model.Event;
import com.example.smartlivingcommunity.data.model.ParkingRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for managing data related to parking requests, complaints, and events
 * for the Manager dashboard in the Smart Living Community system.
 *
 * <p>This repository provides access to parking requests, complaints, and events,
 * typically to be observed by UI components.</p>
 *
 * @author Saon
 */
public class ManagerRepository {

    /**
     * Retrieves a list of current parking requests as LiveData.
     *
     * @return a {@link MutableLiveData} object containing a list of parking requests
     */
    public MutableLiveData<List<ParkingRequest>> getParkingRequests() {
        List<ParkingRequest> requests = new ArrayList<>();
        requests.add(new ParkingRequest("Resident1", "Slot1", "Requested"));
        requests.add(new ParkingRequest("Resident2", "Slot2", "Occupied"));
        return new MutableLiveData<>(requests);
    }

    /**
     * Retrieves a list of current complaints as LiveData.
     *
     * @return a {@link MutableLiveData} object containing a list of complaints
     */
    public MutableLiveData<List<Complaint>> getComplaints() {
        List<Complaint> complaints = new ArrayList<>();
        complaints.add(new Complaint("C001", "Water leakage", "2023-10-10"));
        complaints.add(new Complaint("C002", "Broken lift", "2023-10-11"));
        return new MutableLiveData<>(complaints);
    }
    /**
     * Retrieves a list of upcoming community events.
     *
     * @return a list of {@link Event} objects representing upcoming events
     */
    public List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        events.add(new Event("E1", "Community Hall", "2024-11-01", "Festival Celebration"));
        events.add(new Event("E2", "Garden", "2024-11-05", "Yoga Workshop"));
        return events;
    }
}
