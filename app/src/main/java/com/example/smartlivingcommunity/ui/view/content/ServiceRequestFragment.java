package com.example.smartlivingcommunity.ui.view.content;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.ServiceRequestModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author solaimi
 * The fragment that handles the service request submission.
 * This fragment allows users to submit service requests by providing a description,
 * selecting a request type, and specifying urgency. It also displays the timestamp of
 * the submission.
 */
public class ServiceRequestFragment extends Fragment {

    private EditText editTextDescription; // Field for entering request description
    private Spinner spinnerRequestType; // Spinner for selecting request type
    private EditText editTextUrgency; // Field for entering urgency level
    private Button buttonSubmit; // Button to submit the request
    private TextView textViewTimestamp; // TextView to display submission timestamp
    private FirebaseFirestore firestore; // Firestore instance for database operations

    private static final String TAG = "ServiceRequestFragment"; // Tag for logging

    /**
     * Called to create the view hierarchy associated with the fragment.
     *
     * This method inflates the layout, initializes views, sets up the spinner with
     * request types, initializes Firestore, and sets up the submit button.
     *
     * @param inflater The LayoutInflater object that can be used to inflate views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     * @return The View for the fragment's UI.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_request, container, false);
        initViews(view); // Initialize the UI components
        setupSpinner(); // Set up the request type spinner
        setupFirestore(); // Initialize Firestore for database operations
        setupSubmitButton(); // Configure the submit button action
        return view; // Return the inflated view
    }

    /**
     * Initializes the views from the layout.
     *
     * This method retrieves the references to the UI components defined in the XML layout.
     *
     * @param view The root view of the fragment's layout.
     */
    private void initViews(View view) {
        editTextDescription = view.findViewById(R.id.editTextDescription); // Description input
        spinnerRequestType = view.findViewById(R.id.spinnerRequestType); // Request type spinner
        editTextUrgency = view.findViewById(R.id.editTextUrgency); // Urgency input
        buttonSubmit = view.findViewById(R.id.buttonSubmit); // Submit button
        textViewTimestamp = view.findViewById(R.id.textViewTimestamp); // Timestamp display
    }

    /**
     * Sets up the spinner with available request types.
     *
     * This method populates the spinner with predefined service request types such as Cleaning,
     * Maintenance, Repair, and Other.
     */
    private void setupSpinner() {
        List<String> requestTypes = Arrays.asList("Cleaning", "Maintenance", "Repair", "Other");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, requestTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Set dropdown view
        spinnerRequestType.setAdapter(adapter); // Attach adapter to the spinner
    }

    /**
     * Initializes Firebase Firestore instance.
     *
     * This method sets up Firestore for database operations related to service requests.
     */
    private void setupFirestore() {
        firestore = FirebaseFirestore.getInstance(); // Create Firestore instance
    }

    /**
     * Sets up the submit button to handle click events.
     *
     * This method defines the action to be performed when the submit button is clicked.
     */
    private void setupSubmitButton() {
        buttonSubmit.setOnClickListener(v -> submitRequest()); // Set click listener to submit request
    }

    /**
     * Submits the service request to Firestore.
     *
     * This method retrieves input values from the UI, validates them, creates a ServiceRequestModel,
     * and submits it to the Firestore database. It also handles success and failure callbacks.
     */
    private void submitRequest() {
        String description = editTextDescription.getText().toString().trim(); // Get description
        String requestType = spinnerRequestType.getSelectedItem().toString(); // Get selected request type
        String urgency = editTextUrgency.getText().toString().trim(); // Get urgency level

        if (description.isEmpty() || urgency.isEmpty()) {
            Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show(); // Notify user
            return; // Exit if input fields are empty
        }

        // Create a timestamp for the request
        Date timestamp = new Date(); // Get the current timestamp

        // Create a ServiceRequestModel with the timestamp
        ServiceRequestModel request = new ServiceRequestModel(requestType, description, urgency, timestamp);
        firestore.collection("serviceRequests").add(request)
                .addOnSuccessListener(documentReference -> {
                    // Show timestamp after submission
                    String formattedTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(timestamp); // Format timestamp
                    textViewTimestamp.setText("Submitted at: " + formattedTimestamp); // Display the timestamp
                    textViewTimestamp.setVisibility(View.VISIBLE); // Show the timestamp
                    Toast.makeText(getContext(), "Request submitted successfully!", Toast.LENGTH_SHORT).show(); // Notify success
                    clearFields(); // Clear input fields after submission
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Error adding document", e); // Log error
                    Toast.makeText(getContext(), "Failed to submit request. Please try again.", Toast.LENGTH_SHORT).show(); // Notify failure
                });
    }

    /**
     * Clears the input fields and hides the timestamp.
     *
     * This method resets the input fields to their default state after submission.
     */
    private void clearFields() {
        editTextDescription.setText(""); // Clear description field
        spinnerRequestType.setSelection(0); // Reset to first item in spinner
        editTextUrgency.setText(""); // Clear urgency field
        textViewTimestamp.setVisibility(View.GONE); // Hide the timestamp view
    }
}
