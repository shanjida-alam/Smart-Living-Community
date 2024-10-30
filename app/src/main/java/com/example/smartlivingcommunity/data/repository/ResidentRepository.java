package com.example.smartlivingcommunity.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.smartlivingcommunity.data.model.RegistrationModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentReference;

public class ResidentRepository {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final MutableLiveData<RegistrationModel> residentData = new MutableLiveData<>();

    public LiveData<RegistrationModel> getResidentData(String residentId) {
        db.collection("residents").document(residentId).get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                RegistrationModel resident = documentSnapshot.toObject(RegistrationModel.class);
                residentData.setValue(resident);
            }
        });
        return residentData;
    }

    public void updateResidentData(String residentId, RegistrationModel updatedData) {
        DocumentReference docRef = db.collection("residents").document(residentId);
        docRef.set(updatedData).addOnSuccessListener(aVoid -> {
            // Handle success
        }).addOnFailureListener(e -> {
            // Handle failure
        });
    }
}
