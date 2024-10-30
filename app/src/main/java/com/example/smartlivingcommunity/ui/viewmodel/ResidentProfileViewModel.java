package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartlivingcommunity.data.model.RegistrationModel;
import com.example.smartlivingcommunity.data.repository.ResidentRepository;
import com.google.android.gms.tasks.OnCompleteListener;

/**
 * Represents a ViewModel for managing resident profile data.
 *
 * @author Shanjida
 * @version 1.0
 */

public class ResidentProfileViewModel extends ViewModel {
    private final ResidentRepository repository;
    private final MutableLiveData<RegistrationModel> residentLiveData = new MutableLiveData<>();

    /**
     * Constructs a new instance of the ResidentProfileViewModel.
     */
    public ResidentProfileViewModel() {
        repository = new ResidentRepository();
    }

    /**
     * Retrieves resident data based on the provided email.
     *
     * @param email the email of the resident.
     * @return a LiveData object containing the resident data.
     */
    public LiveData<RegistrationModel> getResident(String email) {
        repository.getResident(email, task -> {
            if (task.isSuccessful()) {
                residentLiveData.setValue(task.getResult());
            }
        });
        return residentLiveData;
    }

    /**
     * Adds a new resident to the repository.
     *
     * @param registrationModel the resident data to be added.
     */
    public void addResident(RegistrationModel registrationModel) {
        repository.addResident(registrationModel, task -> {
            // Handle success or failure
        });
    }
}
