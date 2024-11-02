package com.example.smartlivingcommunity.ui.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartlivingcommunity.data.model.ResidentProfileModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * ViewModel class that manages resident profile data.
 * Handles fetching resident data by unit code and updating the UI components.
 *
 * @author Shanjida Alam
 * @version 1.0
 */
public class ResidentProfileViewModel extends ViewModel {
    /**
     * LiveData holding the resident profile data.
     */
    private final MutableLiveData<ResidentProfileModel> residentLiveData = new MutableLiveData<>();

    /**
     * Firestore instance for accessing the database.
     */
    private final FirebaseFirestore db;

    /**
     * Constructor that initializes the Firestore instance.
     */
    public ResidentProfileViewModel() {
        db = FirebaseFirestore.getInstance();
    }

    /**
     * Returns the resident profile data as LiveData.
     *
     * @return LiveData object containing ResidentProfileModel
     */
    public LiveData<ResidentProfileModel> getResidentLiveData() {
        return residentLiveData;
    }

    /**
     * Fetches resident data by unit code from Firestore.
     *
     * @param unitCode the unit code of the resident
     */
    public void fetchResidentDataByUnitCode(String unitCode) {
        /**
         * Query Firestore for the resident document with the given unitCode
         */
        db.collection("residents")
                .whereEqualTo("unitCode", unitCode)
                .get()
                .addOnCompleteListener(task -> {
                    /**
                     * Handle the result of the Firestore query
                     */
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                        QuerySnapshot snapshot = task.getResult();
                        DocumentSnapshot document = snapshot.getDocuments().get(0);
                        ResidentProfileModel resident = document.toObject(ResidentProfileModel.class);
                        residentLiveData.setValue(resident);
                    } else {
                        /**
                         * Handle the case where the resident document is not found
                         */
                        residentLiveData.setValue(null);
                    }
                });
    }
}
