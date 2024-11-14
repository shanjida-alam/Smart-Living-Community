package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.smartlivingcommunity.data.model.ComplaintModel;
import com.example.smartlivingcommunity.data.repository.ComplaintRepository;

public class ComplaintViewModel extends ViewModel {
    private final ComplaintRepository repository;
    private final MutableLiveData<Boolean> isComplaintSaved = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public ComplaintViewModel(ComplaintRepository repository) {
        this.repository = repository;
    }

    public ComplaintViewModel(ComplaintRepository mockRepository, ComplaintRepository repository) {
        this.repository = repository;
    }

    public LiveData<Boolean> getIsComplaintSaved() {
        return isComplaintSaved;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void submitComplaint(ComplaintModel complaint) {
        if (complaint == null || complaint.getComplaintDescription().isEmpty()) {
            errorMessage.setValue("Invalid input");
            return;
        }

        repository.addComplaint(complaint).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                isComplaintSaved.setValue(true);
            } else {
                errorMessage.setValue("Failed to save complaint");
            }
        });
    }
}
