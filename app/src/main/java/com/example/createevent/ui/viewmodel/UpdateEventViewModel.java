package com.example.createevent.ui.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.example.createevent.data.model.EventDataModel;
import com.example.createevent.data.repository.EventRepository;

/**
 * ViewModel for updating events in the Update Event view.
 * Manages event data updates through the EventRepository and exposes
 * LiveData for observing the update status.
 * This version does not handle image updates as per the current requirements.
 * @version 1.1
 * @since 1.0
 */
public class UpdateEventViewModel extends AndroidViewModel {

    private final EventRepository eventRepository;
    public MutableLiveData<Boolean> updateStatus = new MutableLiveData<>();

    public UpdateEventViewModel(@NonNull Application application) {
        super(application);
        eventRepository = new EventRepository();
    }

    /**
     * Updates an existing event in the repository without an image.
     *
     * @param key The unique identifier for the event to be updated.
     * @param eventData The updated data of the event.
     */
    public void updateEvent(String key, EventDataModel eventData) {
        eventRepository.updateEvent(key, eventData, success -> updateStatus.postValue(success));
    }
}
