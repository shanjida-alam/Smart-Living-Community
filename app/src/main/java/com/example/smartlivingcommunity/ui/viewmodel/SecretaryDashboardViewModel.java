package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartlivingcommunity.data.model.EventRequest;
import com.example.smartlivingcommunity.data.model.RegistrationRequest;
import com.example.smartlivingcommunity.data.repository.SecretaryRepository;

import java.util.List;

/**
 * ViewModel class for managing data and actions related to the Secretary's dashboard in the Smart Living Community app.
 * <p>
 * This ViewModel provides live data for registration and event requests, as well as methods to approve these requests.
 * </p>
 *
 * @author Saon
 */
public class SecretaryDashboardViewModel extends ViewModel {
    private final SecretaryRepository repository;

    /**
     * Constructs a new {@link SecretaryDashboardViewModel} and initializes the repository for data access.
     */
    public SecretaryDashboardViewModel() {
        repository = new SecretaryRepository();
    }

    /**
     * Retrieves the live data list of registration requests.
     *
     * @return A {@link LiveData} list of {@link RegistrationRequest} objects.
     */
    public LiveData<List<RegistrationRequest>> getRegistrationRequests() {
        return repository.getRegistrationRequests();
    }

    /**
     * Retrieves the live data list of event requests.
     *
     * @return A {@link LiveData} list of {@link EventRequest} objects.
     */
    public LiveData<List<EventRequest>> getEventRequests() {
        return repository.getEventRequests();
    }

    /**
     * Approves a registration request by its unique identifier.
     *
     * @param requestId The unique ID of the registration request to approve.
     */
    public void approveRegistration(String requestId) {
        // Add logic to approve registration
    }

    /**
     * Approves an event request by its unique identifier.
     *
     * @param requestId The unique ID of the event request to approve.
     */
    public void approveEvent(String requestId) {
        // Add logic to approve event
    }
}
