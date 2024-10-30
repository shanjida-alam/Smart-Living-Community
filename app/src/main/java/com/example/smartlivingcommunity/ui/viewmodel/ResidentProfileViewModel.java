package com.example.smartlivingcommunity.ui.viewmodel;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.smartlivingcommunity.data.model.RegistrationModel;
import com.google.firebase.firestore.FirebaseFirestore;

public class ResidentProfileViewModel extends ViewModel {
    private final MutableLiveData<RegistrationModel> residentData = new MutableLiveData<>();
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public LiveData<RegistrationModel> getResidentData() {
        return residentData;
    }

    public void fetchResidentData(String residentId) {
        db.collection("residents").document(residentId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        RegistrationModel resident = documentSnapshot.toObject(RegistrationModel.class);
                        residentData.setValue(resident);
                        Log.d("ResidentProfileViewModel", "Fetched data successfully: " + resident.toString());
                    } else {
                        Log.d("ResidentProfileViewModel", "No document found with ID: " + residentId);
                    }
                })
                .addOnFailureListener(e -> Log.e("ResidentProfileViewModel", "Error fetching resident data", e));
    }

    public void updateResidentData(String residentId, RegistrationModel updatedResident) {
        db.collection("residents").document(residentId)
                .set(updatedResident)
                .addOnSuccessListener(aVoid -> Log.d("ResidentProfileViewModel", "Resident updated successfully"))
                .addOnFailureListener(e -> Log.e("ResidentProfileViewModel", "Error updating resident", e));
    }
}
