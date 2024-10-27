package com.example.smartlivingcommunity.data.repository;

import com.example.smartlivingcommunity.data.model.RegistrationModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.Task;

public class RegistrationRepository {

    private final FirebaseFirestore firestore;

    public RegistrationRepository() {
        firestore = FirebaseFirestore.getInstance();
    }

    public Task<Void> registerUser(RegistrationModel resident) {
        return firestore.collection("residents").document(resident.getEmail()).set(resident);
    }
}
