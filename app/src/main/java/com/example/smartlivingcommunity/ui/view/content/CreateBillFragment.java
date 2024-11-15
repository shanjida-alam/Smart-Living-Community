package com.example.smartlivingcommunity.ui.view.content;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.CreateBillModel;
import com.example.smartlivingcommunity.ui.viewmodel.CreateBillViewModel;

/**
 * This fragment handles generating monthly bill activity
 *
 * @author Hasneen Tamanna Totinee
 * @version 1.0
 * @since 15-11-2024
 */

public class CreateBillFragment extends Fragment {

    // UI Elements for displaying bill details
    private TextView waterBill, gasBill, electricityBill, wifiBill, serviceFee, totalBill;

    // ViewModel instance for fetching and managing bill data
    private CreateBillViewModel createBillViewModel;

    // Document ID passed from the DashboardFragment
    private String documentID;

    /**
     * This method is called when the fragment's view is created. It initializes the UI elements,
     * retrieves the document ID passed from the DashboardFragment, and fetches the bill data
     * using the ViewModel.
     *
     * @param inflater           The LayoutInflater used to inflate the fragment's layout
     * @param container          The parent view that the fragment's UI will be attached to
     * @param savedInstanceState A Bundle containing saved state information, if any
     * @return The root view of the fragment's layout
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment's layout
        View view = inflater.inflate(R.layout.fragment_create_bill, container, false);

        // Initialize the UI elements
        initViews(view);

        // Retrieve documentID from the fragment's arguments
        if (getArguments() != null) {
            documentID = getArguments().getString("documentID");
        }

        // Check if the documentID is valid
        if (documentID == null || documentID.isEmpty()) {
            Log.e("CreateBillFragment", "Document ID is null or empty");
            // Show an error message to the user
            new AlertDialog.Builder(getContext())
                    .setTitle("Error")
                    .setMessage("Document ID is invalid.")
                    .setPositiveButton("OK", null)
                    .show();
            return view; // Return early if documentID is invalid
        }

        // Initialize the ViewModel to fetch bill data
        createBillViewModel = new ViewModelProvider(this).get(CreateBillViewModel.class);
        createBillViewModel.fetchBillData(documentID);

        // Observe the bill data and update the UI when it changes
        createBillViewModel.getBillData().observe(getViewLifecycleOwner(), this::displayBill);

        // Observe if the monthly bill is already generated and show a dialog if it is
        createBillViewModel.isMonthlyBillGenerated().observe(getViewLifecycleOwner(), isGenerated -> {
            if (isGenerated != null && isGenerated) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Monthly Bill Generated")
                        .setMessage("Monthly Bill for this User is already generated.")
                        .setPositiveButton("OK", null)
                        .show();
            }
        });

        return view; // Return the root view
    }

    /**
     * Initializes the UI elements by finding them in the fragment's layout.
     * These elements will be used to display the bill data.
     *
     * @param view The root view of the fragment's layout
     */
    private void initViews(View view) {
        // Initialize the TextViews for displaying bill data
        waterBill = view.findViewById(R.id.waterBill);
        gasBill = view.findViewById(R.id.gasBill);
        electricityBill = view.findViewById(R.id.electricityBill);
        wifiBill = view.findViewById(R.id.wifiBill);
        serviceFee = view.findViewById(R.id.serviceFee);
        totalBill = view.findViewById(R.id.totalBill);
    }

    /**
     * Displays the bill data in the corresponding UI elements.
     * This method is called when the bill data is fetched and available.
     *
     * @param bill The CreateBillModel object containing the bill data
     */
    private void displayBill(CreateBillModel bill) {
        // Check if the bill data is not null
        if (bill != null) {
            // Update the UI elements with the bill details
            waterBill.setText("Water Bill: " + bill.getWaterBill() + " BDT");
            gasBill.setText("Gas Bill: " + bill.getGasBill() + " BDT");
            electricityBill.setText("Electricity Bill: " + bill.getElectricityBill() + " BDT");
            wifiBill.setText("Wi-Fi Bill: " + bill.getWifiBill() + " BDT");
            serviceFee.setText("Service Fee: " + bill.getServiceFee() + " BDT");
            totalBill.setText("Total Bill: " + bill.getTotalBill() + " BDT");
        } else {
            // Handle the case where the bill data is null or empty
            Log.e("CreateBillFragment", "Bill data is null or empty");
        }
    }
}
