package com.example.smartlivingcommunity.ui.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartlivingcommunity.data.model.ResidentProfileModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class ResidentProfileViewModel extends ViewModel {
    private final MutableLiveData<ResidentProfileModel> residentLiveData = new MutableLiveData<>();
    private final FirebaseFirestore db;

    public ResidentProfileViewModel() {
        db = FirebaseFirestore.getInstance();
    }

    public LiveData<ResidentProfileModel> getResidentLiveData() {
        return residentLiveData;
    }

    public void fetchResidentDataByUnitCode(String unitCode) {
        db.collection("residents")
                .whereEqualTo("unitCode", unitCode)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                        QuerySnapshot snapshot = task.getResult();
                        DocumentSnapshot document = snapshot.getDocuments().get(0);
                        ResidentProfileModel resident = document.toObject(ResidentProfileModel.class);
                        residentLiveData.setValue(resident);
                    } else {
                        // Handle the case where no document matches the unitCode
                        residentLiveData.setValue(null);
                    }
                });
    }
}

