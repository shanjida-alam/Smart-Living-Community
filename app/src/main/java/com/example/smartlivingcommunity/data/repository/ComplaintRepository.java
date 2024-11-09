package com.example.smartlivingcommunity.data.repository;

import com.example.smartlivingcommunity.data.model.ComplaintModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;
import java.util.UUID;

public class ComplaintRepository {
    private final FirebaseFirestore firestore;
    private static final String COLLECTION_NAME = "complaints";

    public ComplaintRepository(FirebaseFirestore firestore) {
        this.firestore = firestore;
    }

    public Task<String> registerComplaint(ComplaintModel complaint) {
        String complaintId = UUID.randomUUID().toString();
        complaint.setComplaintId(complaintId);

        return firestore.collection(COLLECTION_NAME)
                .document(complaintId)
                .set(complaint)
                .continueWith(task -> {
                    if (task.isSuccessful()) {
                        return complaintId;
                    }
                    throw Objects.requireNonNull(task.getException());
                });
    }

    public Task<Boolean> deleteComplaint(String complaintId) {
        return firestore.collection(COLLECTION_NAME)
                .document(complaintId)
                .delete()
                .continueWith(task -> {
                    if (task.isSuccessful()) {
                        return true;
                    }
                    throw Objects.requireNonNull(task.getException());
                });
    }

    public Task<ComplaintModel> getComplaint(String complaintId) {
        return firestore.collection(COLLECTION_NAME)
                .document(complaintId)
                .get()
                .continueWith(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            return document.toObject(ComplaintModel.class);
                        }
                        return null;
                    }
                    throw Objects.requireNonNull(task.getException());
                });
    }
}