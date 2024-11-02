package com.example.smartlivingcommunity.utils;

import android.net.Uri;
import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for uploading images to Firebase Storage and storing the image URL in Firestore.
 * Provides methods to handle the image upload and database update operations asynchronously.
 *
 * @author Hasneen Tamanna Totinee
 * @version 1.0
 * @since 2024-10-30
 */
public class ImageUploadUtils {

    /**
     * Uploads a user profile image to Firebase Storage and saves the image URL to Firestore.
     * The image is stored under a specified directory with the user's unique ID.
     *
     * @param imageUri  URI of the image to be uploaded.
     * @param userId    Unique identifier for the user, used as part of the file path and document ID.
     * @param onSuccess Callback to be run on successful upload and Firestore update.
     * @param onFailure Callback to be run if upload or Firestore update fails.
     */
    public static void uploadImageToFirebase(Uri imageUri, String userId, Runnable onSuccess, Runnable onFailure) {
        if (imageUri != null) {
            // Reference to the Firebase Storage location where the image will be saved.
            StorageReference storageReference = FirebaseStorage.getInstance()
                    .getReference("profile_images/" + userId + ".jpg");

            // Uploads the image to Firebase Storage.
            storageReference.putFile(imageUri)
                    .addOnSuccessListener(taskSnapshot ->
                            // Retrieves the image's download URL upon successful upload.
                            storageReference.getDownloadUrl()
                                    .addOnSuccessListener(uri -> {
                                        String imageUrl = uri.toString();
                                        // Calls a helper method to save the image URL to Firestore.
                                        saveImageUrlToFirestore(userId, imageUrl, onSuccess, onFailure);
                                    })
                                    .addOnFailureListener(e -> {
                                        Log.e("Image Upload", "Failed to get download URL", e);
                                        onFailure.run(); // Triggers failure callback if download URL retrieval fails.
                                    })
                    )
                    .addOnFailureListener(e -> {
                        Log.e("Image Upload", "Failed to upload image", e);
                        onFailure.run(); // Triggers failure callback if image upload fails.
                    });
        } else {
            Log.e("Image Upload", "No image selected");
            onFailure.run(); // Triggers failure callback if no image URI is provided.
        }
    }

    /**
     * Saves the image URL to Firestore in the user's document under the 'residents' collection.
     *
     * @param userId    Unique identifier for the user, used to locate the user's document in Firestore.
     * @param imageUrl  URL of the uploaded image to be saved in Firestore.
     * @param onSuccess Callback to be run on successful Firestore update.
     * @param onFailure Callback to be run if Firestore update fails.
     */
    private static void saveImageUrlToFirestore(String userId, String imageUrl, Runnable onSuccess, Runnable onFailure) {
        // Gets an instance of Firestore.
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Map containing the field to be updated with the image URL.
        Map<String, Object> updateData = new HashMap<>();
        updateData.put("imageUrl", imageUrl);

        // Updates the user's document with the new image URL in Firestore.
        db.collection("residents").document(userId)
                .update(updateData)
                .addOnSuccessListener(aVoid -> {
                    Log.d("Firestore", "Image URL successfully updated!");
                    onSuccess.run(); // Triggers success callback if Firestore update is successful.
                })
                .addOnFailureListener(e -> {
                    Log.e("Firestore", "Error updating image URL", e);
                    onFailure.run(); // Triggers failure callback if Firestore update fails.
                });
    }
}
