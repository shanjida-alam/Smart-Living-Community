package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartlivingcommunity.data.model.ResidentModel;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Map;
public class ResidentProfileViewModel extends ViewModel {
    private final MutableLiveData<ResidentModel> residentData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public LiveData<ResidentModel> getResidentData() {
        return residentData;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void fetchResident(String unitCode) {
        isLoading.setValue(true);
        db.collection("residents")
                .whereEqualTo("unitCode", unitCode)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        ResidentModel resident = queryDocumentSnapshots.getDocuments().get(0).toObject(ResidentModel.class);
                        residentData.setValue(resident);
                    }
                    isLoading.setValue(false);
                })
                .addOnFailureListener(e -> {
                    errorMessage.setValue("Error fetching resident data: " + e.getMessage());
                    isLoading.setValue(false);
                });
    }

    public void updateResident(String unitCode, Map<String, Object> updates) {
        isLoading.setValue(true);
        db.collection("residents")
                .whereEqualTo("unitCode", unitCode)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        String documentId = queryDocumentSnapshots.getDocuments().get(0).getId();
                        db.collection("residents")
                                .document(documentId)
                                .update(updates)
                                .addOnSuccessListener(aVoid -> {
                                    fetchResident(unitCode); // Refresh the data
                                    errorMessage.setValue("Successfully updated resident information");
                                })
                                .addOnFailureListener(e ->
                                        errorMessage.setValue("Error updating resident: " + e.getMessage())
                                );
                    }
                    isLoading.setValue(false);
                })
                .addOnFailureListener(e -> {
                    errorMessage.setValue("Error finding resident: " + e.getMessage());
                    isLoading.setValue(false);
                });
    }
}