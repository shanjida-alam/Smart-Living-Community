package com.example.smartlivingcommunity.ui.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.ui.viewmodel.SecurityDashboardViewModel;

/**
 * Activity to display the Security Dashboard with security logs and parking list.
 * This activity manages the UI components and binds the data from the ViewModel
 * to the RecyclerViews for displaying security logs and parking information.
 *
 * @author Saon
 */
public class SecurityDashboardActivity extends AppCompatActivity {

    private SecurityDashboardViewModel viewModel;
    private RecyclerView securityLogRecyclerView;
    private RecyclerView parkingListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_dashboard);

        // Initialize RecyclerView for Security Logs with horizontal layout
        securityLogRecyclerView = findViewById(R.id.security_log_recycler_view);
        LinearLayoutManager securityLogLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        securityLogRecyclerView.setLayoutManager(securityLogLayoutManager);

        // Initialize RecyclerView for Parking List with horizontal layout
        parkingListRecyclerView = findViewById(R.id.parking_list_recycler_view);
        LinearLayoutManager parkingListLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        parkingListRecyclerView.setLayoutManager(parkingListLayoutManager);

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(SecurityDashboardViewModel.class);

        // Observe and set data for Security Logs
        viewModel.getSecurityLogs().observe(this, logs -> {
            SecurityLogAdapter securityLogAdapter = new SecurityLogAdapter(logs);
            securityLogRecyclerView.setAdapter(securityLogAdapter);
        });

        // Observe and set data for Parking List
        viewModel.getParkingList().observe(this, parkingList -> {
            ParkingListAdapter parkingListAdapter = new ParkingListAdapter(parkingList);
            parkingListRecyclerView.setAdapter(parkingListAdapter);
        });
    }
}
