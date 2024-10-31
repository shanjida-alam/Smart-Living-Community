package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.smartlivingcommunity.data.model.EventModel;
import com.example.smartlivingcommunity.data.repository.EventRepository;
import java.util.List;

/**
 * ViewModel for managing UI-related data of events in a lifecycle-conscious way.
 * This ViewModel provides data to the UI and helps the UI survive configuration changes.
 */
public class EventViewModel extends ViewModel {

    private final EventRepository repository;
    private final LiveData<List<EventModel>> events;

    /**
     * Initializes the EventViewModel by creating an instance of the repository
     * and fetching the list of events as LiveData.
     */
    public EventViewModel() {
        repository = new EventRepository();
        events = repository.getEvents();
    }

    /**
     * Returns a LiveData object containing a list of events.
     * Observing this LiveData allows the UI to update automatically when data changes.
     *
     * @return LiveData containing a list of EventModel instances.
     */
    public LiveData<List<EventModel>> getEvents() {
        return events;
    }

    /**
     * Adds a new event to the repository.
     * This method is called by the UI when a new event is created.
     *
     * @param event EventModel object to be added to the repository.
     */
    public void createEvent(EventModel event) {
        repository.addEvent(event);
    }
}
