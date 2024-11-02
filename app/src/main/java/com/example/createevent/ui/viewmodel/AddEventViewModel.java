package com.example.createevent.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.createevent.data.model.EventDataModel;
import com.example.createevent.data.repository.EventRepository;

/**
 * ViewModel for managing event data in the Add Event feature.
 * This ViewModel communicates with the EventRepository for data operations
 * and maintains LiveData objects for observing data changes in the UI.
 * @author Irtifa
 */
public class AddEventViewModel extends ViewModel {

    private final EventRepository eventRepository; // Repository for handling data operations
    private final MutableLiveData<String> title = new MutableLiveData<>();
    private final MutableLiveData<String> description = new MutableLiveData<>();
    private final MutableLiveData<String> time = new MutableLiveData<>();
    private final MutableLiveData<String> location = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isSaved = new MutableLiveData<>(false);

    public AddEventViewModel(EventRepository eventRepository) {
        this.eventRepository = new EventRepository(); // Initializes the EventRepository
    }

    // Getters for LiveData
    public LiveData<String> getTitle() { return title; }
    public LiveData<String> getDescription() { return description; }
    public LiveData<String> getTime() { return time; }
    public LiveData<String> getLocation() { return location; }
    public LiveData<Boolean> isSaved() { return isSaved; }

    // Setters for updating event data in LiveData
    public void setTitle(String title) { this.title.setValue(title); }
    public void setDescription(String description) { this.description.setValue(description); }
    public void setTime(String time) { this.time.setValue(time); }
    public void setLocation(String location) { this.location.setValue(location); }

    /**
     * Saves the event data using the EventRepository.
     * Constructs an EventDataModel with the current field values
     * and passes it to the repository for saving.
     */
    public void saveEvent() {
        if (title.getValue() != null && description.getValue() != null &&
                time.getValue() != null && location.getValue() != null) {

            EventDataModel newEvent = new EventDataModel(
                    title.getValue(),
                    description.getValue(),
                    time.getValue(),
                    location.getValue()
            );

            // Pass event data to repository
            eventRepository.saveEvent(newEvent, success -> isSaved.setValue(success));
        }
    }
}
