package com.example.smartlivingcommunity.UI.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.smartlivingcommunity.Data.Model.ServiceRequest;
import com.example.smartlivingcommunity.Data.Repository.ServiceRequestRepository;

import java.util.List;

/**
 * ViewModel for handling service requests from residents.
 */
public class ResidentRequestViewModel extends ViewModel {
    private ServiceRequestRepository repository;
    private MutableLiveData<List<ServiceRequest>> residentRequests;

    public ResidentRequestViewModel() {
        repository = new ServiceRequestRepository();
        residentRequests = new MutableLiveData<>();
    }

    // Method to submit a request
    public void submitRequest(ServiceRequest request) {
        repository.submitRequest(request);
    }

    // Method to fetch requests for the resident
    public LiveData<List<ServiceRequest>> getResidentRequests(String residentId) {
        residentRequests.setValue(repository.getResidentRequests(residentId));
        return residentRequests;
    }
}

