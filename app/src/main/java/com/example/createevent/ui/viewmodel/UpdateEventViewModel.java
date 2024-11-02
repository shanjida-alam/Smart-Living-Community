package com.example.createevent.ui.viewmodel;

import android.app.Application;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.example.createevent.data.repository.EventRepository;
import com.example.createevent.data.model.EventDataModel;

/**
 * ViewModel for updating events in the Update Event view.
 * Manages event data updates through the EventRepository and exposes
 * LiveData for observing the update status.
 * @author Irtifa Haider
 */
public class UpdateEventViewModel extends AndroidViewModel {

    private final EventRepository eventRepository; // Repository for event data operations
    public MutableLiveData<Boolean> updateStatus = new MutableLiveData<>(); // LiveData to track update status

    /**
     * Constructor for UpdateEventViewModel, initializing the repository.
     * @param application The application context.
     */
    public UpdateEventViewModel(@NonNull Application application) {
        super(application);
        eventRepository = new EventRepository();
    }

    /**
     * Updates an existing event in the repository.
     * @param key The key of the event to update.
     * @param eventData The updated event data model.
     * @param imageUri The URI of the event's associated image (optional).
     */
    public void updateEvent(String key, EventDataModel eventData, Uri imageUri) {
        // Calls the repository to update the event and updates updateStatus
        eventRepository.updateEvent(key, eventData, imageUri, success -> updateStatus.postValue(success));
    }
}
