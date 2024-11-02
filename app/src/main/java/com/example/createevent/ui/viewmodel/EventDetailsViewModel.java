package com.example.createevent.ui.viewmodel;

import android.app.Application;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.example.createevent.data.repository.EventRepository;

/**
 * ViewModel for managing event details in the Event Details view.
 * Handles event deletion through the EventRepository and exposes
 * LiveData for observing deletion status.
 * @author Irtifa Haider
 */
public class EventDetailsViewModel extends AndroidViewModel {

    private final EventRepository eventRepository; // Repository for handling data operations
    public MutableLiveData<Boolean> deletionStatus = new MutableLiveData<>(); // LiveData to track deletion status

    /**
     * Constructor for EventDetailsViewModel, initializing the repository.
     * @param application The application context.
     */
    public EventDetailsViewModel(@NonNull Application application) {
        super(application);
        eventRepository = new EventRepository();
    }

    /**
     * Initiates the deletion of an event.
     * @param key The key of the event to delete.
     * @param imageUri The URI of the event's associated image.
     */
    public void deleteEvent(String key, Uri imageUri) {
        // Calls the repository to delete the event and updates deletionStatus
        eventRepository.deleteEvent(key, imageUri, success -> deletionStatus.postValue(success));
    }
}
