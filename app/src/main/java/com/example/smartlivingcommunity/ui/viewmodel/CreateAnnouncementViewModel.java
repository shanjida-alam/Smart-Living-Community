package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartlivingcommunity.data.model.AnnouncementModel;
import com.example.smartlivingcommunity.domain.model.Result;
import com.example.smartlivingcommunity.domain.repository.AnnouncementRepository;

import java.util.Date;

public class CreateAnnouncementViewModel extends ViewModel {
    private final AnnouncementRepository repository;
    private final MutableLiveData<Result<AnnouncementModel>> createResult = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);

    public CreateAnnouncementViewModel(AnnouncementRepository repository) {
        this.repository = repository;
    }

    public void createAnnouncement(String title, String description, Date date) {
        // Start loading
        isLoading.setValue(true);

        // Input validation
        if (title == null || title.trim().isEmpty()) {
            createResult.setValue(new Result.Error("Title cannot be empty"));
            isLoading.setValue(false);
            return;
        }

        if (title.length() > 100) {
            createResult.setValue(new Result.Error("Title cannot exceed 100 characters"));
            isLoading.setValue(false);
            return;
        }

        if (description == null || description.trim().isEmpty()) {
            createResult.setValue(new Result.Error("Description cannot be empty"));
            isLoading.setValue(false);
            return;
        }

        if (description.length() > 1000) {
            createResult.setValue(new Result.Error("Description cannot exceed 1000 characters"));
            isLoading.setValue(false);
            return;
        }

        if (date == null) {
            createResult.setValue(new Result.Error("Date cannot be null"));
            isLoading.setValue(false);
            return;
        }

        // Create announcement model
        AnnouncementModel announcement = new AnnouncementModel(title.trim(), description.trim(), date);

        // Call repository
        try {
            Result<AnnouncementModel> result = repository.createAnnouncement(announcement);
            createResult.setValue(result);
        } catch (Exception e) {
            createResult.setValue(new Result.Error("Failed to create announcement"));
        } finally {
            // Set loading state to false after the operation is complete
            isLoading.setValue(false);
        }
    }

    public LiveData<Result<AnnouncementModel>> getCreateResult() {
        return createResult;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }
}