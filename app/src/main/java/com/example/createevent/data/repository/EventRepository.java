package com.example.createevent.data.repository;

import android.net.Uri;

import com.example.createevent.data.model.EventDataModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Repository class for handling event data with Firebase Database and Firebase Storage.
 * Provides methods for saving, updating, and deleting events.
 * @author Irtifa Haider
 */
public class EventRepository {

    // Reference to the Firebase Database node for events
    private final DatabaseReference databaseReference;

    // Reference to the Firebase Storage location for event images
    private final StorageReference storageReference;

    /**
     * Constructor for initializing Firebase Database and Storage references.
     */
    public EventRepository() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Events");
        storageReference = FirebaseStorage.getInstance().getReference("Event Images");
    }

    /**
     * Deletes an event from Firebase Database and its associated image from Firebase Storage.
     *
     * @param key      The unique key of the event to be deleted in the database.
     * @param imageUri The URI of the image associated with the event.
     * @param callback The callback interface to handle the completion of the delete operation.
     */
    public void deleteEvent(String key, Uri imageUri, OnEventCallback callback) {
        storageReference.child(imageUri.getLastPathSegment()).delete().addOnSuccessListener(aVoid ->
                databaseReference.child(key).removeValue()
                        .addOnCompleteListener(task -> callback.onComplete(task.isSuccessful())));
    }

    /**
     * Saves a new event to Firebase Database and uploads its image to Firebase Storage.
     *
     * @param newEvent The event data model containing details of the event.
     * @param imageUrl The URL of the image associated with the event.
     * @param callback The callback to handle the success or failure of the save operation.
     */
    public void saveEvent(EventDataModel newEvent, String imageUrl, OnEventCallback callback) {
        // Implementation to save event to Firebase with the imageUrl, if available
    }

    /**
     * Updates an existing event in Firebase Database with new data.
     *
     * @param key        The unique key of the event to be updated.
     * @param eventData  The updated event data model.
     * @param callback   The callback to handle the success or failure of the update operation.
     */
    public void updateEvent(String key, EventDataModel eventData, OnEventCallback callback) {
        // Implementation to update the event in Firebase with the provided eventData
    }

    /**
     * Callback interface for handling asynchronous operations on events.
     */
    public interface OnEventCallback {
        /**
         * Called when an operation (save, delete, or update) is completed.
         *
         * @param success True if the operation was successful, false otherwise.
         */
        void onComplete(boolean success);
    }
}
