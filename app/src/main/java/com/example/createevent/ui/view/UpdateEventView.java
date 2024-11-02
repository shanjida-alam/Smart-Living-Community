package com.example.createevent.ui.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.createevent.R;
import com.example.createevent.data.model.EventDataModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

/**
 * Activity class for updating an existing event.
 * Provides a form to edit and save updated event details to the database.
 * @author Irtifa Haider
 */
public class UpdateEventView extends AppCompatActivity {

    // UI elements for event details input
    private Button updateButton;
    private EditText updateTitle, updateDesc, updateTime, updateLocation;
    private String key; // Unique key for the event
    private Uri uri; // Uri for event image
    private DatabaseReference databaseReference; // Firebase Database reference
    private StorageReference storageReference; // Firebase Storage reference

    /**
     * Called when the activity is first created.
     * Initializes UI components and sets up existing event data for editing.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this Bundle contains the data it most recently supplied.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        // Initialize the UI elements
        initializeUI();

        // Retrieve and display data from the intent extras
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            updateTitle.setText(bundle.getString("Title"));
            updateDesc.setText(bundle.getString("Description"));
            updateTime.setText(bundle.getString("Time"));
            key = bundle.getString("Key");
            updateLocation.setText(bundle.getString("Location"));
        }

        // Initialize the Firebase database reference using the event's unique key
        databaseReference = FirebaseDatabase.getInstance().getReference("Android Tutorials").child(key);

        // Set up update button click listener to save updated data
        updateButton.setOnClickListener(view -> updateData());
    }

    /**
     * Initializes the UI elements by finding them by ID.
     */
    private void initializeUI() {
        updateButton = findViewById(R.id.updateButton);
        updateTitle = findViewById(R.id.updateTitle);
        updateDesc = findViewById(R.id.updateDesc);
        updateTime = findViewById(R.id.updateTime);
        updateLocation = findViewById(R.id.updateLocation);
    }

    /**
     * Displays a loading dialog while data is being saved to Firebase.
     */
    private void saveData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Updates the event data in Firebase Database with the current input values.
     * Displays success or error messages based on the operation outcome.
     */
    private void updateData() {
        // Retrieve updated data from input fields
        String title = updateTitle.getText().toString().trim();
        String desc = updateDesc.getText().toString().trim();
        String time = updateTime.getText().toString().trim();
        String location = updateLocation.getText().toString().trim();

        // Create a new EventDataModel with updated information
        EventDataModel eventData = new EventDataModel(title, desc, time, location);

        // Update the data in Firebase Database
        databaseReference.setValue(eventData)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(UpdateEventView.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                        finish(); // Close the activity upon successful update
                    } else {
                        Toast.makeText(UpdateEventView.this, "Failed to update", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(UpdateEventView.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}
