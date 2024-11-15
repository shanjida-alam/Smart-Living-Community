package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartlivingcommunity.data.model.ResidentLoginModel;
import com.example.smartlivingcommunity.utils.Resource;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * ViewModel class that manages user login authentication.
 * Handles login requests, verifies credentials, and updates login status.
 */
public class ResidentLoginViewModel extends ViewModel {



    /** LiveData holding the login result status and associated message */
    private final MutableLiveData<Resource<Boolean>> loginResult = new MutableLiveData<>();

    /** Firestore instance for accessing the database */
    private final FirebaseFirestore db;

    public ResidentLoginViewModel() {
        this.db = FirebaseFirestore.getInstance();
    }

    // Add this protected method for testing
    protected FirebaseFirestore getFirebaseInstance() {
        return FirebaseFirestore.getInstance();
    }

    // Constructor now accepts a FirebaseFirestore instance for dependency injection
    public ResidentLoginViewModel(FirebaseFirestore db) {
        this.db = db;
    }
    /**
     * Returns the login result as LiveData, which indicates the success or failure
     * of a login attempt with an optional message.
     *
     * @return LiveData object containing Resource with Boolean status
     */
    public LiveData<Resource<Boolean>> getLoginResult() {
        return loginResult;
    }

    /**
     * Initiates a login request by checking Firestore for a user with the specified
     * email and password.
     *
     * @param email    the email provided by the user
     * @param password the password provided by the user
     */
    public void login(String email, String password) {
        // Set loading state
        loginResult.setValue(Resource.loading(null));

        // Query Firestore for the user with the given email
        db.collection("residents")
                .whereEqualTo("email", email)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Check if user exists
                        if (task.getResult().isEmpty()) {
                            loginResult.setValue(Resource.error("User not found", false));
                            return;
                        }

                        // Retrieve user document and validate password
                        DocumentSnapshot document = task.getResult().getDocuments().get(0);
                        ResidentLoginModel user = document.toObject(ResidentLoginModel.class);

                        if (user != null && user.getPassword().equals(password)) {
                            loginResult.setValue(Resource.success(true));
                        } else {
                            loginResult.setValue(Resource.error("Invalid password", false));
                        }
                    } else {
                        loginResult.setValue(Resource.error(task.getException().getMessage(), false));
                    }
                });
    }

    // Removed the abstract method since FirebaseFirestore is already initialized in the class
}