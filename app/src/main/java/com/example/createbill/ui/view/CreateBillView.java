package com.example.createbill.ui.view;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.createbill.R;
import com.example.createbill.data.model.CreateBillModel;
import com.example.createbill.ui.viewmodel.CreateBillViewModel;

public class CreateBillView extends AppCompatActivity {
    private TextView waterBill, gasBill, electricityBill, wifiBill, serviceFee, totalBill;
    private CreateBillViewModel createBillViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        waterBill = findViewById(R.id.waterBill);
        gasBill = findViewById(R.id.gasBill);
        electricityBill = findViewById(R.id.electricityBill);
        wifiBill = findViewById(R.id.wifiBill);
        serviceFee = findViewById(R.id.serviceFee);
        totalBill = findViewById(R.id.totalBill);

        createBillViewModel = new ViewModelProvider(this).get(CreateBillViewModel.class);
        String documentID = "fRNhJyfSD5mVkBK4RMaa"; // Replace with actual documentID

        createBillViewModel.fetchBillData(documentID);

        createBillViewModel.getBillData().observe(this, this::displayBill);
    }

    private void displayBill(CreateBillModel bill) {
        if (bill != null) {
            waterBill.setText("Water Bill: " + bill.getWaterBill() + " BDT");
            gasBill.setText("Gas Bill: " + bill.getGasBill() + " BDT");
            electricityBill.setText("Electricity Bill: " + bill.getElectricityBill() + " BDT");
            wifiBill.setText("Wi-Fi Bill: " + bill.getWifiBill() + " BDT");
            serviceFee.setText("Service Fee: " + bill.getServiceFee() + " BDT");
            totalBill.setText("Total Bill: " + bill.getTotalBill() + " BDT");
        }
    }
}
