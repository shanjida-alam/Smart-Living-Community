package com.example.smartlivingcommunity.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartlivingcommunity.data.model.ComplaintModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

// ComplaintRepositoryImpl.java
public class ComplaintRepositoryImpl implements ComplaintRepository {
    private final MutableLiveData<Boolean> submissionStatus = new MutableLiveData<>();
    private final MutableLiveData<List<ComplaintModel>> complaints = new MutableLiveData<>();

//    @Override
//    public LiveData<Boolean> submitComplaint(ComplaintModel complaint) {
//        // Firebase/API implementation would go here
//        return submissionStatus;
//    }

    @Override
    public LiveData<List<ComplaintModel>> getComplaints() {
        return complaints;
    }

    @Override
    public boolean verifyUnitCodeOwnership(String unitCode, String emailAddress) {
        return false;
    }

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public LiveData<Boolean> verifyUnitCodeInFirebase(String unitCode, String email) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();

        db.collection("resident")
                .whereEqualTo("unitCode", unitCode)
                .whereEqualTo("email", email)
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    result.setValue(!querySnapshot.isEmpty());
                })
                .addOnFailureListener(e -> {
                    result.setValue(false);
                });

        return result;
    }

    @Override
    public LiveData<Boolean> submitComplaint(ComplaintModel complaint) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();

        db.collection("complaints")
                .document(complaint.getComplaintId())
                .set(complaint)
                .addOnSuccessListener(aVoid -> {
                    result.setValue(true);
                })
                .addOnFailureListener(e -> {
                    result.setValue(false);
                });

        return result;
    }

    private void saveComplaint(ComplaintModel complaint, MutableLiveData<Boolean> result) {
        db.collection("complaints")
                .document(complaint.getComplaintId())
                .set(complaint)
                .addOnSuccessListener(aVoid -> result.setValue(true))
                .addOnFailureListener(e -> result.setValue(false));
    }
}
