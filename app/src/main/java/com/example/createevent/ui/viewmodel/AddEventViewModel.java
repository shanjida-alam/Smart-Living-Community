package com.example.createevent.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.createevent.data.repository.EventRepository;
import com.example.createevent.data.model.EventDataModel;

/**
 * ViewModel for managing event data in the Add Event feature.
 * This ViewModel communicates with the EventRepository for data operations
 * and maintains LiveData objects for observing data changes in the UI.
 * @author Irtifa Haider
 */
public class AddEventViewModel extends ViewModel {

    private final EventRepository eventRepository; // Repository for handling data operations
    private final MutableLiveData<String> title = new MutableLiveData<>();
    private final MutableLiveData<String> description = new MutableLiveData<>();
    private final MutableLiveData<String> time = new MutableLiveData<>();
    private final MutableLiveData<String> location = new MutableLiveData<>();
    private final MutableLiveData<String> imageUrl = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isSaved = new MutableLiveData<>(false);

    /**
     * Initializes the ViewModel and its dependencies.
     */
    public AddEventViewModel() {
        eventRepository = new EventRepository(); // Initializes the EventRepository
    }

    // Getters for LiveData, used to observe data changes in the UI

    /**
     * @return LiveData for the event title.
     */
    public LiveData<String> getTitle() {
        return title;
    }

    /**
     * @return LiveData for the event description.
     */
    public LiveData<String> getDescription() {
        return description;
    }

    /**
     * @return LiveData for the event time.
     */
    public LiveData<String> getTime() {
        return time;
    }

    /**
     * @return LiveData for the event location.
     */
    public LiveData<String> getLocation() {
        return location;
    }

    /**
     * @return LiveData for the event image URL.
     */
    public LiveData<String> getImageUrl() {
        return imageUrl;
    }

    /**
     * @return LiveData for the save status of the event.
     */
    public LiveData<Boolean> isSaved() {
        return isSaved;
    }

    // Setters for updating event data in LiveData

    /**
     * Sets the event title.
     * @param title The title of the event.
     */
    public void setTitle(String title) {
        this.title.setValue(title);
    }

    /**
     * Sets the event description.
     * @param description The description of the event.
     */
    public void setDescription(String description) {
        this.description.setValue(description);
    }

    /**
     * Sets the event time.
     * @param time The time of the event.
     */
    public void setTime(String time) {
        this.time.setValue(time);
    }

    /**
     * Sets the event location.
     * @param location The location of the event.
     */
    public void setLocation(String location) {
        this.location.setValue(location);
    }

    /**
     * Sets the event image URL.
     * @param url The URL of the event image.
     */
    public void setImageUrl(String url) {
        this.imageUrl.setValue(url);
    }

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

            // Save the event and update the isSaved LiveData
            eventRepository.saveEvent(newEvent, imageUrl.getValue(), success -> isSaved.setValue(success));
        }
    }
}
