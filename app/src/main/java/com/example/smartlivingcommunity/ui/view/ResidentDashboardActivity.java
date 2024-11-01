package com.example.smartlivingcommunity.ui.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.Event;
import com.example.smartlivingcommunity.data.model.ParkingSlot;
import com.example.smartlivingcommunity.data.model.ServiceRequest;
import com.example.smartlivingcommunity.ui.viewmodel.ResidentDashboardViewModel;

import java.util.List;

/**
 * Activity for the Resident Dashboard, displaying events, parking slots, and service requests.
 *
 * <p>This activity initializes the ViewModel and sets up RecyclerViews to observe and display
 * lists of events, parking slots, and service requests.</p>
 *
 * @author Saon
 */
public class ResidentDashboardActivity extends AppCompatActivity {
    private ResidentDashboardViewModel viewModel;
    private EventAdapter eventAdapter;
    private ParkingSlotAdapter parkingSlotAdapter;
    private ServiceRequestAdapter serviceRequestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident_dashboard);

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(ResidentDashboardViewModel.class);

        // Set up RecyclerView for Events
        RecyclerView eventsRecyclerView = findViewById(R.id.eventsRecyclerView);
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModel.getEvents().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {
                eventAdapter = new EventAdapter(events);
                eventsRecyclerView.setAdapter(eventAdapter);
            }
        });

        // Set up RecyclerView for Parking Slots
        RecyclerView parkingRecyclerView = findViewById(R.id.parkingRecyclerView);
        parkingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModel.getParkingSlots().observe(this, new Observer<List<ParkingSlot>>() {
            @Override
            public void onChanged(List<ParkingSlot> parkingSlots) {
                parkingSlotAdapter = new ParkingSlotAdapter(parkingSlots);
                parkingRecyclerView.setAdapter(parkingSlotAdapter);
            }
        });

        // Set up RecyclerView for Service Requests
        RecyclerView serviceRecyclerView = findViewById(R.id.serviceRecyclerView);
        serviceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModel.getServiceRequests().observe(this, new Observer<List<ServiceRequest>>() {
            @Override
            public void onChanged(List<ServiceRequest> serviceRequests) {
                serviceRequestAdapter = new ServiceRequestAdapter(serviceRequests);
                serviceRecyclerView.setAdapter(serviceRequestAdapter);
            }
        });
    }
}
