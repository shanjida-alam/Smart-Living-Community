package com.example.smartlivingcommunity.data.repository;

import android.util.Log;

import com.example.smartlivingcommunity.data.model.RegistrationModel;
import com.example.smartlivingcommunity.data.source.RemoteDataSource;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/**
 * Represents a repository for interacting with Firebase Firestore.
 *
 * @author Shanjida
 * @version 1.0
 */

public class ResidentRepository {
    private final RemoteDataSource remoteDataSource;

    /**
     * Constructs a new instance of the ResidentRepository.
     */
    public ResidentRepository() {
        remoteDataSource = new RemoteDataSource();
    }

    /**
     * Adds a resident to the Firebase Firestore database.
     * @param registrationModel the resident's registration information.
     * @param listener a listener to receive the result of the operation.
     */
    public void addResident(RegistrationModel registrationModel, OnCompleteListener<Void> listener) {
        remoteDataSource.addResident(registrationModel, task -> {
            if (task.isSuccessful()) {
                Log.d("Firebase", "Document successfully written!");
            } else {
                Log.e("Firebase", "Error writing document", task.getException());
            }
        });
    }

    /**
     * Retrieves a resident from the Firebase Firestore database.
     * @param email the email of the resident.
     * @param listener a listener to receive the result of the operation.
     */
    public void getResident(String email, OnCompleteListener<RegistrationModel> listener) {
        remoteDataSource.getResident(email, listener);
    }
}
