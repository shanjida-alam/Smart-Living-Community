package com.example.smartlivingcommunity.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.List;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.smartlivingcommunity.data.model.EventModel;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


/**
 * EventRepository class that manages data operations for events.
 * Utilizes Firebase Firestore to store and retrieve event data.
 */
public class EventRepository {
    private final CollectionReference eventsRef = FirebaseFirestore.getInstance().collection("events");

    /**
     * Retrieves a list of events from Firestore as LiveData.
     * Updates automatically when there are changes in the Firestore collection.
     *
     * @return LiveData object containing a list of EventModel instances
     */
    public LiveData<List<EventModel>> getEvents() {
        MutableLiveData<List<EventModel>> eventsLiveData = new MutableLiveData<>();

        // Add a listener to the Firestore collection for real-time updates
        eventsRef.addSnapshotListener((snapshot, e) -> {
            if (e != null) {
                // If there is an error, log it (optional: show an error message)
                return;
            }

            // Convert Firestore documents to a list of EventModel objects
            List<EventModel> events = new ArrayList<>();
            if (snapshot != null) {
                for (DocumentSnapshot doc : snapshot) {
                    EventModel event = doc.toObject(EventModel.class);
                    if (event != null) {
                        event.setId(doc.getId()); // Set Firestore document ID as event ID
                        events.add(event);
                    }
                }
            }
            // Update LiveData with the latest list of events
            eventsLiveData.setValue(events);
        });
        return eventsLiveData;
    }

    /**
     * Adds a new event to Firestore.
     *
     * @param event EventModel object to be added to Firestore
     */
    public void addEvent(EventModel event) {
        // Add the event to the Firestore collection
        eventsRef.add(event)
                .addOnSuccessListener(documentReference -> {
                    // Set Firestore ID to event's ID (optional)
                    event.setId(documentReference.getId());
                })
                .addOnFailureListener(e -> {
                    // Log error (optional: handle failure, e.g., show error message)
                });
    }
}
