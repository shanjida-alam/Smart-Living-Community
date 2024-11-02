package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.smartlivingcommunity.data.model.ServiceRequestModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentReference;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * ViewModel class for managing service requests.
 */
public class ResidentRequestViewModel extends ViewModel {

    private final FirebaseFirestore firestore;

    /**
     * Constructor for ServiceRequestViewModel.
     * Initializes Firestore instance.
     */
    public ResidentRequestViewModel() {
        firestore = FirebaseFirestore.getInstance();
    }

    /**
     * Submits a new service request to Firestore.
     *
     * @param requestModel The service request model containing the details of the request.
     * @param callback The callback to handle success or failure.
     */
    public void submitServiceRequest(ServiceRequestModel requestModel, final ServiceRequestCallback callback) {
        // Create a new document reference in the "serviceRequests" collection
        DocumentReference newRequestRef = firestore.collection("serviceRequests").document();

        // Set the data for the new document
        newRequestRef.set(requestModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Notify the callback of success
                        callback.onSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Notify the callback of failure
                        callback.onFailure(e);
                    }
                });
    }

    /**
     * Callback interface for service request submission.
     */
    public interface ServiceRequestCallback {
        void onSuccess();
        void onFailure(Exception e);
    }
}
