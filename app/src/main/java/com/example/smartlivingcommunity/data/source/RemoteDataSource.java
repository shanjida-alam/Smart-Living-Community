package com.example.smartlivingcommunity.data.source;

import android.util.Log;

import com.example.smartlivingcommunity.data.model.RegistrationModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;

/**
 * Represents a remote data source for interacting with Firebase Firestore.
 *
 * @author Shanjida Alam
 * @version 1.0
 */
public class RemoteDataSource {
    private final FirebaseFirestore db;

    /**
     * Constructs a new RemoteDataSource instance.
     */
    public RemoteDataSource() {
        db = FirebaseFirestore.getInstance();
    }

    /**
     * Adds a new resident to the database.
     *
     * @param registrationModel the resident's registration information.
     * @param listener the listener to receive the result of the operation.
     */
    public void addResident(RegistrationModel registrationModel, OnCompleteListener<Void> listener) {
        db.collection("residents")
                .document(registrationModel.getEmail())
                .set(registrationModel)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d("Firebase", "Resident added successfully.");
                        listener.onComplete(task);
                    } else {
                        Log.e("Firebase", "Error adding resident", task.getException());
                        listener.onComplete(task);
                    }
                });
    }

    /**
     * Retrieves a resident from the database by their email.
     *
     * @param email the email of the resident to retrieve.
     * @param listener the listener to receive the result of the operation.
     */
    public void getResident(String email, OnCompleteListener<RegistrationModel> listener) {
        db.collection("residents")
                .document(email)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            RegistrationModel model = document.toObject(RegistrationModel.class);
                            // Create a successful task containing the model
                            Task<RegistrationModel> resultTask = Tasks.forResult(model);
                            listener.onComplete(resultTask);
                        } else {
                            // If document doesn't exist, pass a null model
                            listener.onComplete(Tasks.forResult(null));
                        }
                    } else {
                        // Handle the error case
                        listener.onComplete(Tasks.forException(task.getException()));
                    }
                });
    }
}
