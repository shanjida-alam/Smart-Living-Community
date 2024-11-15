package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartlivingcommunity.data.model.ResidentProfileModel;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * ViewModel class that manages user profile information.
 *
 * Handles information edit requests, and updates the profile information in the database.
 */
public class ResidentProfileViewModel extends ViewModel {
    /**
     * Firebase Firestore instance
     */
    private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    /**
     * LiveData holding the update result status and associated message
     */
    private final MutableLiveData<ResidentProfileModel> profileData = new MutableLiveData<>();
    /**
     * LiveData indicating whether the profile is being edited or not
     */
    private final MutableLiveData<Boolean> isEditing = new MutableLiveData<>(false);
    /**
     * LiveData holding the document ID of the profile
     */
    private final MutableLiveData<String> documentId = new MutableLiveData<>();
    /**
     * LiveData holding the error message if any
     */
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    /**
     * LiveData holding the success message if any
     */
    private final MutableLiveData<String> successMessage = new MutableLiveData<>();
    /**
     * LiveData indicating whether the profile is being edited or not
     */
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);

    /**
     * LiveData holding the profile data
     *
     * @return the profile data
     */
    public LiveData<ResidentProfileModel> getProfileData() {
        return profileData;
    }

    /**
     * LiveData holding the document ID of the profile
     *
     * @return the document ID of the profile
     */
    public LiveData<Boolean> getIsEditing() {
        return isEditing;
    }

    /**
     * LiveData holding the document ID of the profile
     *
     * @return the document ID of the profile
     */
    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    /**
     * LiveData holding the document ID of the profile
     */
    public LiveData<String> getSuccessMessage() {
        return successMessage;
    }

    /**
     * LiveData loading the document ID of the profile
     *
     * @return the document ID of the profile
     */
    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    /**
     * LiveData holding the document ID of the profile
     *
     * @return the document ID of the profile to fetch data
     */
    public void fetchUserProfile(String unitCode) {
        isLoading.setValue(true);
        /**
         * Query in the database to fetch data using unitCode
         */
        firestore.collection("residents")
                .whereEqualTo("unitCode", unitCode)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        DocumentReference docRef = queryDocumentSnapshots.getDocuments().get(0).getReference();
                        /**
                         * Set the document ID and profile data
                         */
                        documentId.setValue(docRef.getId());
                        /**
                         * Convert the document to a ResidentProfileModel object
                         */
                        ResidentProfileModel model = queryDocumentSnapshots.getDocuments().get(0).toObject(ResidentProfileModel.class);
                        /**
                         * Set the profile data
                         */
                        profileData.setValue(model);
                    } else {
                        /**
                         * Set error message if profile not found
                         */
                        errorMessage.setValue("Profile not found");
                    }
                    /**
                     * Set loading state to false
                     */
                    isLoading.setValue(false);
                })
                .addOnFailureListener(e -> {
                    /**
                     * Set error message if fetching fails
                     */
                    errorMessage.setValue("Failed to fetch profile: " + e.getMessage());
                    isLoading.setValue(false);
                });
    }

    /**
     * Enable editing of the profile
     */
    public void enableEditing() {
        isEditing.setValue(true);
    }

    /**
     * Disable editing of the profile
     */
    public void disableEditing() {
        isEditing.setValue(false);
    }

    /**
     * Save the updated profile information to the database
     */
    public void saveUserProfile() {
        /**
         * Get the profile data and document ID
         */
        ResidentProfileModel profile = profileData.getValue();
        /**
         * Get the document ID
         */
        String docId = documentId.getValue();

        /**
         * Validate the profile data
         */
        if (profile != null && docId != null && validateProfile(profile)) {
            isLoading.setValue(true);
            /**
             * Update the profile in the database
             */
            firestore.collection("residents").document(docId)
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        /**
                         * Create a map of updates to be made
                         */
                        Map<String, Object> updates = new HashMap<>();
                        updates.put("name", profile.getName());
                        updates.put("email", profile.getEmail());
                        updates.put("contactNumber", profile.getContactNumber());
                        updates.put("emergencyContact", profile.getEmergencyContact());
                        updates.put("nidOrBirthCertificate", profile.getNidOrBirthCertificate());
                        updates.put("profession", profile.getProfession());
                        updates.put("monthlyIncome", profile.getMonthlyIncome());
                        updates.put("password", profile.getPassword());
                        updates.put("imageUrl", profile.getImageUrl());

                        /**
                         * Preserve existing non-editable fields
                         */
                        String existingUnitCode = documentSnapshot.getString("unitCode");
                        String existingUserRole = documentSnapshot.getString("userRole");
                        String existingAdditionalUnitCode = documentSnapshot.getString("additionalUnitCode");

                        /**
                         * Update the fields if they are not null
                         */
                        if (existingUnitCode != null) updates.put("unitCode", existingUnitCode);
                        if (existingUserRole != null) updates.put("userRole", existingUserRole);
                        if (existingAdditionalUnitCode != null) updates.put("additionalUnitCode", existingAdditionalUnitCode);

                        /**
                         * Update the profile in the database
                         */
                        firestore.collection("residents").document(docId)
                                .update(updates)
                                .addOnSuccessListener(aVoid -> {
                                    successMessage.setValue("Profile updated successfully");
                                    disableEditing();
                                    fetchUserProfile(existingUnitCode); // Refresh the profile data
                                    isLoading.setValue(false);
                                })
                                .addOnFailureListener(e -> {
                                    errorMessage.setValue("Failed to save profile: " + e.getMessage());
                                    isLoading.setValue(false);
                                });
                    })
                    .addOnFailureListener(e -> {
                        errorMessage.setValue("Failed to retrieve current profile: " + e.getMessage());
                        isLoading.setValue(false);
                    });
        } else {
            errorMessage.setValue("Invalid profile data");
        }
    }

    /**
     * Validate the profile data
     *
     * @param profile the profile to validate
     * @return true if the profile is valid, false otherwise
     */
    private boolean validateProfile(ResidentProfileModel profile) {
        return profile.getName() != null && !profile.getName().isEmpty() &&
                profile.getEmail() != null && !profile.getEmail().isEmpty() &&
                profile.getContactNumber() != null && !profile.getContactNumber().isEmpty() &&
                profile.getEmergencyContact() != null && !profile.getEmergencyContact().isEmpty() &&
                profile.getNidOrBirthCertificate() != null && !profile.getNidOrBirthCertificate().isEmpty() &&
                profile.getProfession() != null && !profile.getProfession().isEmpty() &&
                profile.getMonthlyIncome() != null && !profile.getMonthlyIncome().isEmpty() &&
                profile.getPassword() != null && !profile.getPassword().isEmpty();
    }
}