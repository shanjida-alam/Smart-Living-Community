package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartlivingcommunity.data.model.AnnouncementModel;
import com.example.smartlivingcommunity.data.repository.RoomAnnouncementRepository;
import com.example.smartlivingcommunity.domain.model.Result;
import com.example.smartlivingcommunity.domain.model.Result.Success;
import com.example.smartlivingcommunity.domain.model.Result.Error;

import java.util.Date;
import java.util.List;

/**
 * ViewModel for managing announcements in the Smart Living Community app.
 * It interacts with the RoomAnnouncementRepository to fetch, create, and manage announcements.
 *
 * Author: Irtifa
 */
public class CreateAnnouncementViewModel extends ViewModel {

    private final RoomAnnouncementRepository repository; // Repository for announcement data operations
    private final MutableLiveData<List<AnnouncementModel>> announcements = new MutableLiveData<>(); // LiveData for list of announcements
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(); // LiveData for loading status
    private final MutableLiveData<Result<AnnouncementModel>> createResult = new MutableLiveData<>(); // LiveData for result of create operation

    /**
     * Constructor for the ViewModel.
     * Initializes the repository and loads the existing announcements.
     *
     * @param repository Repository for managing announcements.
     */
    public CreateAnnouncementViewModel(RoomAnnouncementRepository repository) {
        this.repository = repository;
        loadAnnouncements();
    }

    /**
     * Loads announcements from the repository and updates the LiveData.
     */
    public void loadAnnouncements() {
        List<AnnouncementModel> loadedAnnouncements = repository.getAnnouncements(); // Fetch announcements
        announcements.setValue(loadedAnnouncements); // Update LiveData
    }

    /**
     * Returns a LiveData object containing the list of announcements.
     *
     * @return LiveData object with a list of AnnouncementModel objects.
     */
    public LiveData<List<AnnouncementModel>> getAnnouncements() {
        return announcements;
    }

    /**
     * Returns a LiveData object indicating the loading state.
     *
     * @return LiveData<Boolean> representing whether the operation is loading.
     */
    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    /**
     * Returns a LiveData object containing the result of the create announcement operation.
     *
     * @return LiveData<Result<AnnouncementModel>> representing the result of the create operation.
     */
    public LiveData<Result<AnnouncementModel>> getCreateResult() {
        return createResult;
    }

    /**
     * Creates a new announcement with the provided title, description, and date.
     * Handles success and error cases, updates the result LiveData, and reloads the announcements.
     *
     * @param title       The title of the announcement.
     * @param description The description of the announcement.
     * @param date        The date of the announcement.
     */
    public void createAnnouncement(String title, String description, Date date) {
        isLoading.setValue(true); // Set loading state to true
        try {
            // Create a new AnnouncementModel
            AnnouncementModel newAnnouncement = new AnnouncementModel(title, description, date);
            // Save the announcement in the repository
            repository.createAnnouncement(newAnnouncement);
            // Update the result LiveData with success
            createResult.setValue(new Success<>(newAnnouncement));
            // Reload the list of announcements
            loadAnnouncements();
        } catch (Exception e) {
            // Update the result LiveData with an error message in case of failure
            createResult.setValue(new Error<>("Failed to create announcement: " + e.getMessage()));
        } finally {
            // Set loading state to false
            isLoading.setValue(false);
        }
    }
}
