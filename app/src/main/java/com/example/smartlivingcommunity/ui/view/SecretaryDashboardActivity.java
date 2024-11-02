package com.example.smartlivingcommunity.ui.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.ui.view.EventRequestAdapter;
import com.example.smartlivingcommunity.ui.view.RegistrationRequestAdapter;
import com.example.smartlivingcommunity.ui.viewmodel.SecretaryDashboardViewModel;

/**
 * Activity class for displaying the Secretary's dashboard in the Smart Living Community app.
 * <p>
 * This activity includes views for handling registration requests and event requests.
 * It uses the {@link SecretaryDashboardViewModel} to fetch data and display it in RecyclerViews.
 * </p>
 *
 * @author Saon
 */
public class SecretaryDashboardActivity extends AppCompatActivity {

    private SecretaryDashboardViewModel viewModel;

    /**
     * Called when the activity is first created.
     * Initializes the layout, sets up RecyclerViews for registration and event requests,
     * and observes data from the ViewModel to populate the adapters.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being
     *                           shut down, this Bundle contains the data it most recently supplied;
     *                           otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secretary_dashboard);

        // Initialize the ViewModel
        viewModel = new SecretaryDashboardViewModel();

        // Set up RecyclerViews for registration requests and event requests
        RecyclerView registrationRequestRecyclerView = findViewById(R.id.registration_request_recycler_view);
        RecyclerView eventRequestRecyclerView = findViewById(R.id.event_request_recycler_view);

        // Set LinearLayoutManagers for RecyclerViews
        registrationRequestRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        eventRequestRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Observe registration requests and set adapter with click listener to approve requests
        viewModel.getRegistrationRequests().observe(this, requests -> {
            RegistrationRequestAdapter adapter = new RegistrationRequestAdapter(requests, requestId -> {
                viewModel.approveRegistration(requestId);
                // Logic to refresh the list if necessary
            });
            registrationRequestRecyclerView.setAdapter(adapter);
        });

        // Observe event requests and set adapter with click listener to approve requests
        viewModel.getEventRequests().observe(this, events -> {
            EventRequestAdapter adapter = new EventRequestAdapter(events, requestId -> {
                viewModel.approveEvent(requestId);
                // Logic to refresh the list if necessary
            });
            eventRequestRecyclerView.setAdapter(adapter);
        });
    }
}
