package com.example.createevent.ui.viewmodel;

import android.app.Application;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.example.createevent.data.repository.EventRepository;
import com.example.createevent.data.model.EventDataModel;

/**
 * ViewModel for uploading new events in the Upload Event view.
 * Manages event data uploads through the EventRepository and exposes
 * LiveData for observing the upload status.
 * @author Irtifa Haider
 */
public class UploadEventViewModel extends AndroidViewModel {

    private final EventRepository eventRepository; // Repository for event data operations
    public MutableLiveData<Boolean> uploadStatus = new MutableLiveData<>(); // LiveData to track upload status

    /**
     * Constructor for UploadEventViewModel, initializing the repository.
     * @param application The application context.
     */
    public UploadEventViewModel(@NonNull Application application) {
        super(application);
        eventRepository = new EventRepository();
    }

    /**
     * Uploads a new event to the repository.
     * @param eventData The event data model to upload.
     * @param imageUri The URI of the event's associated image (optional).
     */
    public void uploadEvent(EventDataModel eventData, Uri imageUri) {
        // Calls the repository to upload the event and updates uploadStatus
        eventRepository.saveEvent(eventData, imageUri, success -> uploadStatus.postValue(success));
    }
}
