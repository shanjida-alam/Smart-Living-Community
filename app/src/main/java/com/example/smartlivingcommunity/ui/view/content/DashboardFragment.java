package com.example.smartlivingcommunity.ui.view.content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.ResidentEvent;
import com.example.smartlivingcommunity.data.model.ResidentParkingSlot;
import com.example.smartlivingcommunity.data.model.ResidentServiceRequest;
import com.example.smartlivingcommunity.ui.viewmodel.DashboardViewModel;


import java.util.List;

/**
 * Fragment for the ResidentDashboard, displaying upcoming events, parking slots, and service requests.
 *
 * <p>This fragment initializes the ViewModel and sets up RecyclerViews to observe and display
 * lists of events, parking slots, and service requests.</p>
 *
 * @author Saon
 */
public class DashboardFragment extends Fragment {

    private DashboardViewModel viewModel;
    private EventAdapter eventAdapter;
    private ParkingSlotAdapter parkingSlotAdapter;
    private ServiceRequestAdapter serviceRequestAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        // Button initialization: Find the 'Show Monthly Bill' button in the view layout
        Button viewMonthlyBillButton = view.findViewById(R.id.buttonShowMonthlyBill);

        // Set an OnClickListener to handle the button click
        viewMonthlyBillButton.setOnClickListener(new View.OnClickListener() {
            /**
             * This method is triggered when the 'Show Monthly Bill' button is clicked.
             * It initiates the navigation to the CreateBillFragment to display the monthly bill.
             *
             * @param v The view that was clicked, which is the 'Show Monthly Bill' button in this case.
             */
            @Override
            public void onClick(View v) {
                // Call the method to navigate to the CreateBillFragment
                navigateToCreateBillFragment();
            }
        });

        // Set up RecyclerView for Service Requests
        RecyclerView serviceRecyclerView = view.findViewById(R.id.serviceRecyclerView);
        serviceRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModel.getServiceRequests().observe(getViewLifecycleOwner(), new Observer<List<ResidentServiceRequest>>() {
            @Override
            public void onChanged(List<ResidentServiceRequest> serviceRequests) {
                serviceRequestAdapter = new ServiceRequestAdapter(serviceRequests);
                serviceRecyclerView.setAdapter(serviceRequestAdapter);
            }
        });

        // Set up RecyclerView for Parking Slots
        RecyclerView parkingRecyclerView = view.findViewById(R.id.parkingRecyclerView);
        parkingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModel.getParkingSlots().observe(getViewLifecycleOwner(), new Observer<List<ResidentParkingSlot>>() {
            @Override
            public void onChanged(List<ResidentParkingSlot> parkingSlots) {
                parkingSlotAdapter = new ParkingSlotAdapter(parkingSlots);
                parkingRecyclerView.setAdapter(parkingSlotAdapter);
            }
        });

        // Set up RecyclerView for Events
        RecyclerView eventsRecyclerView = view.findViewById(R.id.eventsRecyclerView);
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModel.getEvents().observe(getViewLifecycleOwner(), new Observer<List<ResidentEvent>>() {
            @Override
            public void onChanged(List<ResidentEvent> events) {
                eventAdapter = new EventAdapter(events);
                eventsRecyclerView.setAdapter(eventAdapter);
            }
        });

        return view;
    }

    /**
     * Navigates from the current fragment (DashboardFragment) to the CreateBillFragment.
     * Passes a document ID (in this case, a placeholder) as an argument to the CreateBillFragment.
     * The navigation occurs using the FragmentTransaction to replace the current fragment and add it to the back stack.
     */
    private void navigateToCreateBillFragment() {
        // Create a new Bundle to pass data (documentID) to the CreateBillFragment
        Bundle bundle = new Bundle();
        bundle.putString("documentID", "mgrHWE3zkRmVgoIRj7R4");

        // Create a new instance of CreateBillFragment
        CreateBillFragment fragment = new CreateBillFragment();
        // Set the arguments (documentID) for the fragment
        fragment.setArguments(bundle);

        // Begin a FragmentTransaction to replace the current fragment with the CreateBillFragment
        // The 'fragment_container' is the ID of the container in the activity's layout where the fragment will be placed
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();  // Commit the transaction to complete the fragment transaction
    }

}
