package com.example.smartlivingcommunity.UI.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.smartlivingcommunity.Data.Model.ServiceRequest;
import com.example.smartlivingcommunity.Data.Repository.ServiceRequestRepository;

import java.util.List;

/**
 * ViewModel for managing service requests.
 */
public class ManagerRequestViewModel extends ViewModel {
    private ServiceRequestRepository repository;
    private MutableLiveData<List<ServiceRequest>> managerRequests;

    public ManagerRequestViewModel() {
        repository = new ServiceRequestRepository();
        managerRequests = new MutableLiveData<>();
    }

    // Method to fetch all service requests for the manager
    public LiveData<List<ServiceRequest>> getManagerRequests() {
        managerRequests.setValue(repository.getManagerRequests());
        return managerRequests;
    }
}

