package com.example.smartlivingcommunity.data.repository;

import static android.content.ContentValues.TAG;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartlivingcommunity.data.model.ComplaintModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

/**
 * Implementation of the ComplaintRepository interface that handles complaint-related data operations.
 * <p>
 * This class is responsible for interacting with Firebase Firestore to submit complaints and perform
 * additional operations such as verifying unit codes and retrieving complaints.
 */
public class ComplaintRepositoryImplementation implements ComplaintRepository {

    /**
     * Instance of the Firebase Firestore database.
     * <p>
     * This instance allows access to Firestore's collections and documents to store and retrieve data.
     */
    private final FirebaseFirestore db;

    /**
     * Constructs a new instance of the ComplaintRepositoryImplementation class.
     * <p>
     * Initializes the Firebase Firestore database instance for interaction with the Firestore service.
     */
    public ComplaintRepositoryImplementation() {
        // Initialize the Firebase Firestore database
        this.db = FirebaseFirestore.getInstance();
    }

    /**
     * Submits a complaint to the Firestore database.
     * <p>
     * This method stores the complaint in the "complaints" collection within Firestore.
     * If the complaint does not already have an ID, a unique ID is generated based on the current timestamp.
     * Once the submission is complete, a success or failure result is returned via LiveData.
     *
     * @param complaint The complaint to be submitted to the database.
     * @return A LiveData object that holds a Boolean indicating the success (true) or failure (false) of the submission.
     */
    @Override
    public LiveData<Boolean> submitComplaint(ComplaintModel complaint) {
        // Create a MutableLiveData to hold the result (success or failure)
        MutableLiveData<Boolean> result = new MutableLiveData<>();

        // Ensure the complaint has a valid ID. If not, generate a unique ID.
        if (complaint.getComplaintId() == null || complaint.getComplaintId().isEmpty()) {
            complaint.setComplaintId("COMP" + System.currentTimeMillis()); // Generate a unique complaint ID
        }

        /**
         * Submit the complaint to Firebase Firestore.
         * The complaint is added to the "complaints" collection with its unique complaint ID.
         */
        db.collection("complaints")
                .document(complaint.getComplaintId())
                .set(complaint)
                .addOnSuccessListener(aVoid -> {
                    // Log success and update the result LiveData
                    Log.d(TAG, "Complaint submitted successfully");
                    result.setValue(true);
                })
                .addOnFailureListener(e -> {
                    // Log failure and update the result LiveData
                    Log.e(TAG, "Error submitting complaint", e);
                    result.setValue(false);
                });

        // Return the result LiveData
        return result;
    }

    /**
     * Verifies the ownership of a unit code.
     * <p>
     * This method checks whether the unit code provided belongs to the user associated with the given email address.
     * (This is a placeholder for future functionality.)
     *
     * @param unitCode The unit code to verify.
     * @param emailAddress The email address of the user for verification.
     * @return A boolean value indicating whether the unit code ownership is valid (false by default).
     */
    @Override
    public boolean verifyUnitCodeOwnership(String unitCode, String emailAddress) {
        // Placeholder logic, needs to be implemented based on actual business logic
        return false;
    }

    /**
     * Verifies the existence of a unit code in Firebase Firestore.
     * <p>
     * This method checks the "residents" collection to see if the given unit code exists and is associated
     * with the provided email address.
     *
     * @param unitCode The unit code to verify.
     * @param email The email address of the user for verification.
     * @return A LiveData object containing a Boolean value:
     *         - true if the unit code exists in Firebase Firestore.
     *         - false if the unit code does not exist or an error occurs.
     */
    @Override
    public LiveData<Boolean> verifyUnitCodeInFirebase(String unitCode, String email) {
        // Create a MutableLiveData to hold the result (existence check)
        MutableLiveData<Boolean> result = new MutableLiveData<>();

        /**
         * Query the "residents" collection in Firestore to check if the unit code exists.
         * The query checks for the matching unit code and email.
         */
        db.collection("residents")
                .whereEqualTo("unitCode", unitCode)
                .whereEqualTo("email", email)
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    // If the query result is not empty, the unit code exists
                    result.setValue(!querySnapshot.isEmpty());
                })
                .addOnFailureListener(e -> {
                    // Log error and update the result LiveData to false
                    Log.e(TAG, "Error verifying unit code", e);
                    result.setValue(false);
                });

        // Return the result LiveData
        return result;
    }

    /**
     * Retrieves a list of complaints from the repository.
     * <p>
     * This method returns a LiveData object that holds a list of all complaints stored in the Firestore database.
     * (Currently, the method is not implemented.)
     *
     * @return A LiveData object containing a list of ComplaintModel objects (null for now).
     */
    @Override
    public LiveData<List<ComplaintModel>> getComplaints() {
        // Return null for now as the method is not yet implemented
        return null;
    }
}
