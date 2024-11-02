package com.example.smartlivingcommunity.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.smartlivingcommunity.data.model.EventRequest;
import com.example.smartlivingcommunity.data.model.RegistrationRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for managing and providing data related to registration and event requests.
 * This repository contains dummy data for testing purposes.
 *
 * @author Saon
 */
public class SecretaryRepository {

    private final MutableLiveData<List<RegistrationRequest>> registrationRequests = new MutableLiveData<>();
    private final MutableLiveData<List<EventRequest>> eventRequests = new MutableLiveData<>();

    /**
     * Initializes the repository with dummy data for registration and event requests.
     */
    public SecretaryRepository() {
        loadDummyData();
    }

    /**
     * Loads dummy data for registration and event requests into LiveData objects.
     * This method is primarily for testing and demonstration purposes.
     */
    private void loadDummyData() {
        // Initialize a list with dummy RegistrationRequest data
        List<RegistrationRequest> regRequests = new ArrayList<>();
        regRequests.add(new RegistrationRequest("1", "Tanvir Hossain", "Resident", "1234567890"));
        registrationRequests.setValue(regRequests);

        // Initialize a list with dummy EventRequest data
        List<EventRequest> eventReqs = new ArrayList<>();
        eventReqs.add(new EventRequest("1", "Community Picnic", "2024-11-01"));
        eventRequests.setValue(eventReqs);
    }

    /**
     * Returns a LiveData object containing a list of registration requests.
     *
     * @return LiveData list of RegistrationRequest objects.
     */
    public LiveData<List<RegistrationRequest>> getRegistrationRequests() {
        return registrationRequests;
    }

    /**
     * Returns a LiveData object containing a list of event requests.
     *
     * @return LiveData list of EventRequest objects.
     */
    public LiveData<List<EventRequest>> getEventRequests() {
        return eventRequests;
    }
}
