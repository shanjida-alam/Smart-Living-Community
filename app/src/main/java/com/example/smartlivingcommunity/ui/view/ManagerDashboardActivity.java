package com.example.smartlivingcommunity.ui.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.Complaint;
import com.example.smartlivingcommunity.data.model.Event;
import com.example.smartlivingcommunity.data.model.ParkingRequest;
import com.example.smartlivingcommunity.ui.viewmodel.ManagerDashboardViewModel;

import java.util.List;

/**
 * Activity that displays the Manager Dashboard, showing events, parking requests, and complaints.
 *
 * <p>This activity sets up the ViewModel and initializes RecyclerViews to display the respective data.</p>
 *
 * @author Saon
 */
public class ManagerDashboardActivity extends AppCompatActivity {
    private ManagerDashboardViewModel managerDashboardViewModel;
    private EventAdapter eventAdapter;
    private ParkingRequestAdapter parkingRequestAdapter;
    private ComplaintAdapter complaintAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_dashboard);

        // Initialize ViewModel
        managerDashboardViewModel = new ViewModelProvider(this).get(ManagerDashboardViewModel.class);

        // Set up RecyclerView for Events
        RecyclerView eventsRecyclerView = findViewById(R.id.eventsRecyclerView);
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        managerDashboardViewModel.getEvents().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {
                eventAdapter = new EventAdapter(events);
                eventsRecyclerView.setAdapter(eventAdapter);
            }
        });

        // Set up RecyclerView for Parking Requests
        RecyclerView parkingRequestsRecyclerView = findViewById(R.id.parkingRequestsRecyclerView);
        parkingRequestsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        managerDashboardViewModel.getParkingRequests().observe(this, new Observer<List<ParkingRequest>>() {
            @Override
            public void onChanged(List<ParkingRequest> parkingRequests) {
                parkingRequestAdapter = new ParkingRequestAdapter(parkingRequests);
                parkingRequestsRecyclerView.setAdapter(parkingRequestAdapter);
            }
        });

        // Set up RecyclerView for Complaints
        RecyclerView complaintsRecyclerView = findViewById(R.id.complaintsRecyclerView);
        complaintsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        managerDashboardViewModel.getComplaints().observe(this, new Observer<List<Complaint>>() {
            @Override
            public void onChanged(List<Complaint> complaints) {
                complaintAdapter = new ComplaintAdapter(complaints);
                complaintsRecyclerView.setAdapter(complaintAdapter);
            }
        });
    }
}
