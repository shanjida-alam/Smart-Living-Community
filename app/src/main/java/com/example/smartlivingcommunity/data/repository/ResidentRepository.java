package com.example.smartlivingcommunity.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.smartlivingcommunity.data.model.RegistrationModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentReference;

/**
 * Repository class for resident data
 *
 * @author Shanjida Alam
 * @version 1.0
 */
public class ResidentRepository {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final MutableLiveData<RegistrationModel> residentData = new MutableLiveData<>();



    /**
     * Fetch resident data from Firestore
     *
     * @param residentId The ID of the resident
     * @return LiveData containing the resident data
     */
    public LiveData<RegistrationModel> getResidentData(String residentId) {
        db.collection("residents").document(residentId).get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                RegistrationModel resident = documentSnapshot.toObject(RegistrationModel.class);
                Log.d("ResidentRepository", "Fetched data successfully: " + resident.toString());
                residentData.setValue(resident);
            }
            else {
                Log.d("ResidentRepository", "No document found with ID: " + residentId);
            }
        });
        return residentData;
    }

    /**
     * Update resident data in Firestore
     *
     * @param residentId The ID of the resident
     * @param updatedData The updated resident data
     */
    public void updateResidentData(String residentId, RegistrationModel updatedData) {
        DocumentReference docRef = db.collection("residents").document(residentId);
        docRef.set(updatedData).addOnSuccessListener(aVoid -> {
            // Handle success
        }).addOnFailureListener(e -> {
            // Handle failure
        });
    }
}
