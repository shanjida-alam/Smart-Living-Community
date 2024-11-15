package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.ViewModel;

/**
 * ViewModel class for managing registration-related data and business logic.
 * This class acts as a bridge between the UI layer and the repository layer, handling
 * the business logic associated with user registration, including email validation
 * and data storage operations in Firebase.
 *
 * @author Hasneen Tamanna Totinee
 * @version 1.0
 * @since 2024-10-30
 */
public class RegistrationViewModel extends ViewModel {
    private final RegistrationRepository repository;
    private String userRole;    // Role assigned to the user during registration (e.g., Resident, Manager, Admin)
    private boolean isResident; // Boolean flag indicating if the user is also a resident

    /**
     * Constructor for RegistrationViewModel.
     * Initializes a RegistrationRepository instance for data operations.
     */
    public RegistrationViewModel() {
        repository = new RegistrationRepository();
    }

    /**
     * Handles the registration process for a user, including checking for duplicate emails and
     * storing the registration data in Firebase Firestore.
     * The method first verifies if the email already exists in the database. If it exists, the
     * onEmailExists callback is triggered. If it does not exist, it proceeds to store the data
     * in Firestore and invokes the appropriate success or failure callback.
     *
     * @param model          The RegistrationModel containing the user's registration details.
     * @param additionalUnitCode  An additional unit code if the user has multiple roles (e.g., both Manager and Resident).
     * @param onSuccess      Callback triggered on successful data storage.
     * @param onEmailExists  Callback triggered if the email is already registered.
     * @param onFailure      Callback triggered if there is an error during data storage.
     */
    public void registerResident(RegistrationModel model, String additionalUnitCode, Runnable onSuccess, Runnable onEmailExists, Runnable onFailure) {
        // Check if the email already exists in the Firestore database
        repository.checkEmailExists(model.getEmail(),
                onEmailExists, // If email exists, invoke the onEmailExists callback
                () -> repository.storeRegistrationData(model, additionalUnitCode, onSuccess, onFailure) // If email does not exist, proceed to store data
        );
    }

    /**
     * Sets the user's role during the registration process.
     * This can be used to adjust registration requirements or UI display based on role.
     *
     * @param userRole The role of the user (e.g., Resident, Manager, Admin).
     */
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    /**
     * Sets the flag indicating whether the user is also a resident.
     * This flag is useful in cases where the user may have dual roles, such as being both
     * a Manager and a Resident.
     *
     * @param isResident Boolean flag indicating if the user is also a resident.
     */
    public void setIsResident(boolean isResident) {
        this.isResident = isResident;
    }
}

