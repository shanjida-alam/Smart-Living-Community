package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.ViewModel;

public class CreateAnnouncementViewModel extends ViewModel {
    private final MutableLiveData<Result<Announcement>> createResult = new MutableLiveData<>();

    public void createAnnouncement(String title, String description, Date date) {
        Announcement announcement = new Announcement(title, description, date);
        createResult.setValue(Result.success(announcement));
    }

    public LiveData<Result<Announcement>> getCreateResult() {
        return createResult;
    }
}