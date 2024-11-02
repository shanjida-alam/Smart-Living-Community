package com.example.createevent.ui.view;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.createevent.R;

/**
 * Activity class for adding a new event.
 * Handles the layout and UI setup for adding event details.
 * @author Irtifa Haider
 */
public class AddEventView extends AppCompatActivity {

    /**
     * Called when the activity is first created.
     * Sets up the UI and applies edge-to-edge window insets handling.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this Bundle contains the data it most recently supplied.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enables edge-to-edge layout in this activity
        EdgeToEdge.enable(this);

        // Sets the content view for the activity
        setContentView(R.layout.activity_add_event);

        // Applies window insets to the main view to handle system bars' padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
