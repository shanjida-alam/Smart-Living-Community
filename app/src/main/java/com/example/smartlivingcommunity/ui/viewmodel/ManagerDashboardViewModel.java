package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartlivingcommunity.data.model.Complaint;
import com.example.smartlivingcommunity.data.model.Event;
import com.example.smartlivingcommunity.data.model.ParkingRequest;
import com.example.smartlivingcommunity.data.repository.ManagerRepository;

import java.util.List;

/**
 * ViewModel for the Manager Dashboard in the Smart Living Community application.
 * This class acts as a bridge between the UI and the data layer, providing
 * access to parking requests, complaints, and events.
 *
 * <p>It utilizes the {@link ManagerRepository} to fetch and expose data to the UI.</p>
 *
 * @author Saon
 */
public class ManagerDashboardViewModel extends ViewModel {

    private final ManagerRepository repository;

    /** LiveData for observing a list of parking requests */
    private final LiveData<List<ParkingRequest>> parkingRequests;

    /** LiveData for observing a list of complaints */
    private final LiveData<List<Complaint>> complaints;

    /** LiveData for observing a list of events */
    private final LiveData<List<Event>> events;

    /**
     * Constructs a new {@code ManagerDashboardViewModel} and initializes the repository
     * along with LiveData for parking requests, complaints, and events.
     */
    public ManagerDashboardViewModel() {
        repository = new ManagerRepository();

        // Initialize LiveData for parking requests, complaints, and events
        parkingRequests = repository.getParkingRequests();
        complaints = repository.getComplaints();
        events = new MutableLiveData<>(repository.getEvents());
    }

    /**
     * Returns the LiveData object containing a list of parking requests.
     *
     * @return LiveData of parking requests for the manager
     */
    public LiveData<List<ParkingRequest>> getParkingRequests() {
        return parkingRequests;
    }

    /**
     * Returns the LiveData object containing a list of complaints.
     *
     * @return LiveData of complaints for the manager
     */
    public LiveData<List<Complaint>> getComplaints() {
        return complaints;
    }

    /**
     * Returns the LiveData object containing a list of events.
     *
     * @return LiveData of events for the manager
     */
    public LiveData<List<Event>> getEvents() {
        return events;
    }
}
