package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.smartlivingcommunity.data.model.ComplaintModel;
import com.example.smartlivingcommunity.data.repository.ComplaintRepository;

import java.util.List;

// ComplaintViewModel.java
// ComplaintViewModel.java
public class ComplaintViewModel extends ViewModel {
    private final ComplaintRepository repository;

    public ComplaintViewModel(ComplaintRepository repository) {
        this.repository = repository;
    }

    public LiveData<Boolean> submitComplaint(ComplaintModel complaint) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();

        // First check basic validation
        if (!isValid(complaint)) {
            result.setValue(false);
            return result;
        }

        // For Residents, verify unit code ownership
        if ("Resident".equals(complaint.getUserRole())) {
            LiveData<Boolean> verificationResult = repository.verifyUnitCodeInFirebase(
                    complaint.getUnitCode(),
                    complaint.getEmailAddress()
            );

            if (Boolean.FALSE.equals(verificationResult.getValue())) {
                result.setValue(false);
                return result;
            }
        }

        return repository.submitComplaint(complaint);
    }

    private boolean isValid(ComplaintModel complaint) {
        if (complaint == null) {
            return false;
        }
        if (complaint.getUnitCode() == null || complaint.getUnitCode().trim().isEmpty()) {
            return false;
        }
        if (complaint.getUserName() == null || complaint.getUserName().trim().isEmpty()) {
            return false;
        }
        if (complaint.getUserRole() == null || complaint.getUserRole().trim().isEmpty()) {
            return false;
        }
        if (complaint.getPhoneNumber() == null || !complaint.getPhoneNumber().matches("\\d{10}")) return false;

        if (complaint.getComplaintDescription() == null ||
                complaint.getComplaintDescription().trim().isEmpty() ||
                complaint.getComplaintDescription().length() > 1000) {
            return false;
        }

        // Email validation
        String email = complaint.getEmailAddress();
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        // Email format validation using regex
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!email.matches(emailRegex)) {
            return false;
        }

        return true;
    }
}