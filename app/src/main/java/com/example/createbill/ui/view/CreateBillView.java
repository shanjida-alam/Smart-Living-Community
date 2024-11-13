package com.example.createbill.ui.view;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.createbill.R;
import com.example.createbill.data.model.CreateBillModel;
import com.example.createbill.ui.viewmodel.CreateBillViewModel;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;

/**
 * This is the activity class for displaying the user's bill details.
 * It interacts with the CreateBillViewModel to fetch and display bill data.
 * It also observes the isMonthlyBillGenerated LiveData to show a dialog if the bill is already generated.
 *
 * @author Hasneen Tamanna Totinee
 * @version 1.0
 * @since 13-11-2024
 */
public class CreateBillView extends AppCompatActivity {

    // UI components to display the bill details
    private TextView waterBill, gasBill, electricityBill, wifiBill, serviceFee, totalBill;

    // ViewModel to manage and fetch bill data
    private CreateBillViewModel createBillViewModel;

    /**
     * This method is called when the activity is created.
     * Initializes the UI components and sets up ViewModel to fetch and observe bill data.
     *
     * @param savedInstanceState If the activity is being re-initialized after being shut down,
     *                           this Bundle contains the data it most recently supplied.
     *                           Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        // Initializes UI components
        waterBill = findViewById(R.id.waterBill);
        gasBill = findViewById(R.id.gasBill);
        electricityBill = findViewById(R.id.electricityBill);
        wifiBill = findViewById(R.id.wifiBill);
        serviceFee = findViewById(R.id.serviceFee);
        totalBill = findViewById(R.id.totalBill);

        // Initializes ViewModel to handle business logic and data
        createBillViewModel = new ViewModelProvider(this).get(CreateBillViewModel.class);

        // Fetches bill data from ViewModel using a specific document ID (example)
        String documentID = "khHifFLgZOkJGYWupJ5r";
        createBillViewModel.fetchBillData(documentID);

        // Observes the LiveData object for the bill data and update the UI when data changes
        createBillViewModel.getBillData().observe(this, this::displayBill);

        // Observes the LiveData object for whether the monthly bill has already been generated
        createBillViewModel.isMonthlyBillGenerated().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isGenerated) {
                if (isGenerated != null && isGenerated) {
                    // Show dialog if the bill is already generated for this document ID
                    new AlertDialog.Builder(CreateBillView.this)
                            .setTitle("Monthly Bill Generated")
                            .setMessage("Monthly Bill for this User is already generated.")
                            .setPositiveButton("OK", null)
                            .show();
                }
            }
        });
    }

    /**
     * Updates the UI with the fetched bill data.
     * Displays the water, gas, electricity, Wi-Fi bills, service fee, and total bill in their respective TextViews.
     *
     * @param bill The bill data model contains the user's bill details.
     */
    private void displayBill(CreateBillModel bill) {
        if (bill != null) {
            // Update the TextViews with the respective bill details
            waterBill.setText("Water Bill: " + bill.getWaterBill() + " BDT");
            gasBill.setText("Gas Bill: " + bill.getGasBill() + " BDT");
            electricityBill.setText("Electricity Bill: " + bill.getElectricityBill() + " BDT");
            wifiBill.setText("Wi-Fi Bill: " + bill.getWifiBill() + " BDT");
            serviceFee.setText("Service Fee: " + bill.getServiceFee() + " BDT");
            totalBill.setText("Total Bill: " + bill.getTotalBill() + " BDT");
        }
    }
}
