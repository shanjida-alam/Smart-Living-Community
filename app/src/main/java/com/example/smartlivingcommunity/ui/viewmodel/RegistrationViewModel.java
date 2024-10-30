package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.ViewModel;
import com.example.smartlivingcommunity.data.model.RegistrationModel;
import com.example.smartlivingcommunity.data.repository.RegistrationRepository;

/**
 * ViewModel for handling registration data and business logic.
 *
 * @author Hasneen Tamanna Totinee
 * @version 1.0
 */
public class RegistrationViewModel extends ViewModel {
    private final RegistrationRepository repository;

    public RegistrationViewModel() {
        repository = new RegistrationRepository();
    }

    /**
     * Stores the registration model to Firebase and handles callbacks.
     *
     * @param model     The registration model with user data
     * @param onSuccess Callback on successful data storage
     * @param onFailure Callback on failure during data storage
     */
    public void registerResident(RegistrationModel model, Runnable onSuccess, Runnable onFailure) {
        repository.storeRegistrationData(model, onSuccess, onFailure);
    }
}
