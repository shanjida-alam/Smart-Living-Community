package com.example.createevent.ui.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.createevent.R;
import com.example.createevent.data.model.EventDataModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * Activity class for uploading a new event.
 * Provides a form to input event details and saves them to Firebase Database.
 * @author Irtifa Haider
 */
public class UploadEventView extends AppCompatActivity {

    // UI elements for event input fields
    private Button saveButton;
    private EditText uploadTitle, uploadDesc, uploadTime, uploadLocation;
    private Uri uri; // Uri for event image (if applicable)

    /**
     * Called when the activity is created.
     * Initializes UI components and sets up the save button listener.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this Bundle contains the data it most recently supplied.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        // Initialize UI components
        saveButton = findViewById(R.id.saveButton);
        uploadTitle = findViewById(R.id.uploadTitle);
        uploadDesc = findViewById(R.id.uploadDesc);
        uploadTime = findViewById(R.id.uploadTime);
        uploadLocation = findViewById(R.id.uploadLocation);

        // Set up the save button to trigger data upload
        saveButton.setOnClickListener(view -> uploadData());
    }

    /**
     * Validates and uploads event data to Firebase Database.
     * Ensures all required fields are filled before saving the event.
     */
    private void uploadData() {
        // Retrieve input data from fields
        String title = uploadTitle.getText().toString().trim();
        String desc = uploadDesc.getText().toString().trim();
        String time = uploadTime.getText().toString().trim();
        String location = uploadLocation.getText().toString().trim();

        // Validate that all fields are filled
        if (title.isEmpty() || desc.isEmpty() || time.isEmpty() || location.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create an EventDataModel instance with the input data
        EventDataModel eventData = new EventDataModel(title, desc, time, location);

        // Generate a unique key based on the current date and time for the event entry
        String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        // Save the event data to Firebase Database under "Events"
        FirebaseDatabase.getInstance().getReference("Events").child(currentDate)
                .setValue(eventData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(UploadEventView.this, "Event saved successfully", Toast.LENGTH_SHORT).show();
                            finish(); // Close activity upon successful upload
                        } else {
                            Toast.makeText(UploadEventView.this, "Failed to save event", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UploadEventView.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
