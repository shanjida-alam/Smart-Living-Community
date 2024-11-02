package com.example.createevent.data.repository;

import androidx.annotation.NonNull;
import com.example.createevent.data.model.EventDataModel;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Repository class for handling event data operations in Firebase.
 * Provides methods for adding, updating, and deleting events from Firebase Firestore.
 * This class interacts with Firebase Firestore and serves as a data layer for the application's ViewModel.
 *
 * @author Irtifa
 */
public class EventRepository {

    private final CollectionReference eventsCollection;

    /**
     * Initializes the EventRepository, setting up references to the Firebase Firestore
     * collection for event data storage.
     */
    public EventRepository() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        eventsCollection = db.collection("events");
    }

    /**
     * Saves an event to Firestore.
     *
     * @param eventDataModel The event data to save.
     * @param callback       A callback to notify the result of the save operation.
     */
    public void saveEvent(EventDataModel eventDataModel, OnEventCallback callback) {
        eventsCollection.add(eventDataModel)
                .addOnSuccessListener(documentReference -> callback.onCallback(true))
                .addOnFailureListener(e -> callback.onCallback(false));
    }

    /**
     * Updates an existing event in Firestore.
     *
     * @param key            The document ID of the event to update.
     * @param eventDataModel The updated event data.
     * @param callback       A callback to notify the result of the update operation.
     */
    public void updateEvent(String key, EventDataModel eventDataModel, OnEventCallback callback) {
        if (key == null || key.isEmpty()) {
            callback.onCallback(false);
            return;
        }

        eventsCollection.document(key)
                .set(eventDataModel)
                .addOnCompleteListener(task -> callback.onCallback(task.isSuccessful()))
                .addOnFailureListener(e -> callback.onCallback(false));
    }

    /**
     * Deletes an event from Firestore.
     *
     * @param key      The document ID of the event to delete.
     * @param callback A callback to notify the result of the delete operation.
     */
    public void deleteEvent(@NonNull String key, OnEventCallback callback) {
        if (key == null || key.isEmpty()) {
            callback.onCallback(false);
            return;
        }

        eventsCollection.document(key)
                .delete()
                .addOnSuccessListener(aVoid -> callback.onCallback(true))
                .addOnFailureListener(e -> callback.onCallback(false));
    }

    /**
     * Interface for callback to handle asynchronous event operations.
     */
    public interface OnEventCallback {
        void onCallback(boolean success);
    }
}
