package com.example.smartlivingcommunity.data.repository;

import android.util.Log;

import com.example.smartlivingcommunity.data.model.RegistrationModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * Repository class responsible for handling data storage and retrieval operations for user registrations.
 * This class interfaces with Firebase Firestore to store and manage registration details.
 * It provides methods to store user data and check for email existence in the Firestore database.
 *
 * @author Hasneen Tamanna Totinee
 * @version 1.0
 * @since 2024-10-30
 */
public class RegistrationRepository {
    private final FirebaseFirestore firestore;

    /**
     * Constructor for RegistrationRepository.
     * Initializes the Firebase Firestore instance for data operations.
     */
    public RegistrationRepository() {
        this.firestore = FirebaseFirestore.getInstance();
    }

    /**
     * Stores the registration data in Firestore under the "residents" collection.
     * It takes a RegistrationModel with user information, adds additional details if applicable,
     * and attempts to save the data in Firestore. Success or failure triggers the respective callbacks.
     *
     * @param model       RegistrationModel containing the user's registration details.
     * @param additionalUnitCode  Additional unit code if the user has a dual role (e.g., both Manager and Resident).
     * @param onSuccess   Callback invoked upon successful data storage in Firestore.
     * @param onFailure   Callback invoked if there is an error in storing the data.
     */
    public void storeRegistrationData(RegistrationModel model, String additionalUnitCode, Runnable onSuccess, Runnable onFailure) {
        // Map to hold registration data fields
        Map<String, Object> registrationData = new HashMap<>();
        registrationData.put("name", model.getName());
        registrationData.put("email", model.getEmail());
        registrationData.put("contactNumber", model.getContactNumber());
        registrationData.put("emergencyNumber", model.getEmergencyContact());
        registrationData.put("nidOrBirthCertificate", model.getNidOrBirthCertificate());
        registrationData.put("profession", model.getProfession());
        registrationData.put("monthlyIncome", model.getMonthlyIncome());
        registrationData.put("password", model.getPassword());
        registrationData.put("imageUrl", model.getImageUrl());
        registrationData.put("userRole", model.getUserRole());
        registrationData.put("unitCode", model.getUnitCode());

        // Add additional unit code if the user has dual roles
        if (additionalUnitCode != null) {
            registrationData.put("unitCodeAdditional", additionalUnitCode);
        }

        // Attempt to store data in Firestore under the "residents" collection
        firestore.collection("residents")
                .add(registrationData)
                .addOnSuccessListener(documentReference -> {
                    Log.d("Firestore", "Document added with ID: " + documentReference.getId());
                    onSuccess.run();  // Trigger success callback on successful data storage
                })
                .addOnFailureListener(e -> {
                    Log.e("Firestore Error", "Error adding document", e);
                    onFailure.run();  // Trigger failure callback if an error occurs
                });
    }

    /**
     * Checks if an email already exists in the "residents" collection.
     * Queries Firestore for any documents with a matching email field.
     * If a match is found, it triggers the onExists callback; otherwise, the onNotExists callback is triggered.
     *
     * @param email       The email to check for existence in the database.
     * @param onExists    Callback invoked if the email is already registered.
     * @param onNotExists Callback invoked if the email is not found in the database.
     */
    public void checkEmailExists(String email, Runnable onExists, Runnable onNotExists) {
        firestore.collection("residents")
                .whereEqualTo("email", email)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                        // Email already exists in the database
                        onExists.run();
                    } else {
                        // Email does not exist in the database
                        onNotExists.run();
                    }
                })
                .addOnFailureListener(e -> Log.e("Firestore Error", "Error checking email existence", e));
    }
}
