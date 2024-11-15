package com.example.smartlivingcommunity.data.repository;

import androidx.lifecycle.LiveData;

import com.example.smartlivingcommunity.data.model.ComplaintModel;

import java.util.List;

/**
 * Interface defining the methods for interacting with complaint data.
 * <p>
 * This interface specifies the operations available for handling complaints in the system,
 * including submitting complaints, verifying unit code ownership, and retrieving complaints from the data repository.
 */
public interface ComplaintRepository {

    /**
     * Submits a complaint to the repository.
     * <p>
     * This method allows the submission of a complaint to the underlying data source (e.g., Firestore).
     * It returns a `LiveData` object that reflects the success (true) or failure (false) of the submission process.
     *
     * @param complaint The complaint to be submitted to the repository.
     * @return A `LiveData<Boolean>` indicating the success (true) or failure (false) of the complaint submission.
     */
    LiveData<Boolean> submitComplaint(ComplaintModel complaint);

    /**
     * Verifies the ownership of a unit code.
     * <p>
     * This method checks whether the given unit code belongs to the user associated with the specified email address.
     * It returns a boolean indicating whether the user owns the unit code.
     *
     * @param unitCode The unit code to verify.
     * @param emailAddress The email address of the user whose ownership is to be verified.
     * @return A boolean indicating the ownership of the unit code.
     *         - `true` if the user owns the unit code.
     *         - `false` if the user does not own the unit code.
     */
    boolean verifyUnitCodeOwnership(String unitCode, String emailAddress);

    /**
     * Verifies the existence of a unit code in Firebase.
     * <p>
     * This method checks if the specified unit code exists in the Firebase Firestore database for the given email address.
     * It returns a `LiveData<Boolean>` indicating whether the unit code exists in Firebase.
     *
     * @param unitCode The unit code to verify.
     * @param email The email address of the user for which the verification is to be performed.
     * @return A `LiveData<Boolean>` indicating:
     *         - `true` if the unit code exists in Firebase.
     *         - `false` if the unit code does not exist or an error occurs.
     */
    LiveData<Boolean> verifyUnitCodeInFirebase(String unitCode, String email);

    /**
     * Retrieves a list of complaints from the repository.
     * <p>
     * This method fetches a list of complaints from the data source (e.g., Firestore) and returns them as `LiveData`.
     * The list of complaints can be observed and updated asynchronously in the UI.
     *
     * @return A `LiveData<List<ComplaintModel>>` containing the list of complaints from the repository.
     *         - The list may be empty if no complaints are available.
     */
    LiveData<List<ComplaintModel>> getComplaints();
}
