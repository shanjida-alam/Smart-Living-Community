package com.example.createevent.ui.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.createevent.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Activity class for displaying and managing the details of a specific event.
 * Provides options to view, edit, and delete event details.
 * @author Irtifa
 */
public class EventDetailsView extends AppCompatActivity {

    // UI elements
    private TextView detailDesc, detailTitle, detailTime, detailLocation;
    private FloatingActionButton deleteButton, editButton;
    private String key = "";

    /**
     * Called when the activity is first created.
     * Initializes UI components and sets up event data display and actions.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this Bundle contains the data it most recently supplied.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Initialize views
        detailDesc = findViewById(R.id.detailDesc);
        detailTitle = findViewById(R.id.detailTitle);
        deleteButton = findViewById(R.id.deleteButton);
        editButton = findViewById(R.id.editButton);
        detailTime = findViewById(R.id.detailTime);
        detailLocation = findViewById(R.id.detailLocation);

        // Retrieve and display event details passed from the previous activity
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            detailDesc.setText(bundle.getString("Description"));
            detailTitle.setText(bundle.getString("Title"));
            detailTime.setText(bundle.getString("Time"));
            detailLocation.setText(bundle.getString("Location"));
            key = bundle.getString("Key");
        }

        // Set up delete button to handle event deletion
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteEvent();
            }
        });

        // Set up edit button to navigate to the event editing activity
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editEvent();
            }
        });
    }

    /**
     * Deletes the event from the Firestore database.
     */
    private void deleteEvent() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Delete the document in Firestore using the key as the document ID
        db.collection("events").document(key).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(EventDetailsView.this, "Event Deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(EventDetailsView.this, "Failed to delete event", Toast.LENGTH_SHORT).show();
                });
    }

    /**
     * Navigates to the AddEventView activity to edit the current event details.
     * Passes the current event data to the editing activity.
     */
    private void editEvent() {
        Intent intent = new Intent(EventDetailsView.this, AddEventView.class)
                .putExtra("Title", detailTitle.getText().toString())
                .putExtra("Description", detailDesc.getText().toString())
                .putExtra("Time", detailTime.getText().toString())
                .putExtra("Location", detailLocation.getText().toString())
                .putExtra("Key", key);
        startActivity(intent);
    }
}
