package com.example.smartlivingcommunity.ui.viewmodel;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.smartlivingcommunity.data.model.RegistrationModel;
import com.example.smartlivingcommunity.data.repository.ResidentRepository;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * ViewModel for managing resident profile data.
 *
 * @author Shanjida Alam
 * @version 1.0
 */

public class ResidentProfileViewModel extends ViewModel {
    private final MutableLiveData<RegistrationModel> residentData = new MutableLiveData<>();
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private ResidentRepository repository;

    /**
     * Retrieves the resident data LiveData.
     * @return LiveData containing the resident data.
     */
    public LiveData<RegistrationModel> getResidentData() {
        return residentData;
    }

    /**
     * Fetches resident data from Firestore using the provided resident ID.
     *
     * @param residentId The ID of the resident to fetch data for.
     */
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

    /**
     * Updates resident data in Firestore.
     *
     * @param residentId The ID of the resident to update.
     * @param updatedResident The updated resident data.
     */
    public void updateResidentData(String residentId, RegistrationModel updatedResident) {
        db.collection("residents").document(residentId)
                .set(updatedResident)
                .addOnSuccessListener(aVoid -> Log.d("ResidentProfileViewModel", "Resident updated successfully"))
                .addOnFailureListener(e -> Log.e("ResidentProfileViewModel", "Error updating resident", e));
    }
}
