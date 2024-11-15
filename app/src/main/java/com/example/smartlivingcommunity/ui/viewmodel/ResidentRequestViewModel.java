package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentReference;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * @author 
 * ViewModel class for managing service requests.
 * This class handles the submission of service requests to Firestore.
 */
public class ResidentRequestViewModel extends ViewModel {

    private final FirebaseFirestore firestore; // Instance of Firestore for database operations.

    /**
     * Constructor for ResidentRequestViewModel.
     * Initializes Firestore instance to enable database operations.
     */
    public ResidentRequestViewModel() {
        firestore = FirebaseFirestore.getInstance(); // Initialize Firestore instance.
    }

    /**
     * Submits a new service request to Firestore.
     *
     * @param requestModel The service request model containing the details of the request.
     * @param callback The callback to handle success or failure of the submission.
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
                        callback.onSuccess(); // Trigger success callback.
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Notify the callback of failure
                        callback.onFailure(e); // Trigger failure callback.
                    }
                });
    }

    /**
     * Callback interface for service request submission.
     * This interface provides methods to handle success and failure responses.
     */
    public interface ServiceRequestCallback {
        /**
         * Called when the service request submission is successful.
         */
        void onSuccess();

        /**
         * Called when the service request submission fails.
         *
         * @param e The exception thrown during the submission.
         */
        void onFailure(Exception e);
    }
}

