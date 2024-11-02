package com.example.createevent.ui.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.example.createevent.data.repository.EventRepository;
import com.example.createevent.data.model.EventDataModel;

/**
 * ViewModel for uploading new events in the Upload Event view.
 * Manages event data uploads through the EventRepository and exposes
 * LiveData for observing the upload status.
 *
 * This ViewModel separates the UI from data operations, enabling easier testing and
 * lifecycle awareness when uploading events to Firebase.
 *
 * @see EventRepository
 * @see EventDataModel
 *
 * @see androidx.lifecycle.ViewModel
 * @see MutableLiveData
 *
 * @author Irtifa
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

}
