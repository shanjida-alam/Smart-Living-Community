package com.example.createevent.ui.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.example.createevent.data.repository.EventRepository;

/**
 * ViewModel for managing event details in the Event Details view.
 * Handles event deletion through the EventRepository and exposes
 * LiveData for observing deletion status.
 * @author Irtifa
 */
public class EventDetailsViewModel extends AndroidViewModel {

    public final EventRepository eventRepository;
    public MutableLiveData<Boolean> deletionStatus = new MutableLiveData<>();

    public EventDetailsViewModel(@NonNull Application application) {
        super(application);
        eventRepository = new EventRepository();
    }

    /**
     * Initiates the deletion of an event.
     * Calls the repository's delete function, which attempts to delete
     * the event data from Firebase Database and the associated image from Firebase Storage.
     */
    public void deleteEvent(String key) {
        eventRepository.deleteEvent(key, success -> deletionStatus.postValue(success));
    }
}
