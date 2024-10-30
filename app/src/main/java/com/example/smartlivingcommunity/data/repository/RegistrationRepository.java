package com.example.smartlivingcommunity.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import com.example.smartlivingcommunity.data.model.RegistrationModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentReference;

/**
 * Repository class handling data storage for resident registrations.
 *
 * @author Hasneen Tamanna Totinee
 * @version 1.0
 */
public class RegistrationRepository {
    private final FirebaseFirestore firestore;

    public RegistrationRepository() {
        this.firestore = FirebaseFirestore.getInstance();
    }

    /**
     * Stores the registration data in Firestore under the 'residents' collection.
     *
     * @param model   RegistrationModel containing resident information
     * @param onSuccess  Callback invoked on successful storage
     * @param onFailure  Callback invoked on failure
     */
    public void storeRegistrationData(RegistrationModel model, Runnable onSuccess, Runnable onFailure) {
        firestore.collection("residents")
                .add(model)
                .addOnSuccessListener(documentReference -> {
                    Log.d("Firestore", "Document added with ID: " + documentReference.getId());
                    onSuccess.run();  // Call the success callback
                })
                .addOnFailureListener(e -> {
                    Log.e("Firestore Error", "Error adding document", e);
                    onFailure.run();  // Call the failure callback
                });
    }
}

