package com.example.smartlivingcommunity.ui.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.RegistrationModel;
import com.example.smartlivingcommunity.ui.viewmodel.ResidentProfileViewModel;

/**
 * Represents the profile view for a resident.
 *
 * @author Shanjida Alam
 * @version 1.0
 */

public class ResidentProfileView extends AppCompatActivity {

    private ResidentProfileViewModel viewModel;
    private EditText fullName, nidBirthNumber, email, contactNumber, emergencyContact, profession, monthlyIncome, password;
    private Button btnSaveChanges;

    @Override

    /**
     * Called when the activity is first created.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resident_profile);

        viewModel = new ResidentProfileViewModel();
        initView();

        String userEmail = "user@example.com"; // Replace with actual email
        viewModel.getResident(userEmail).observe(this, resident -> {
            if (resident != null) {
                fillProfileData(resident);
            }
        });

        btnSaveChanges.setOnClickListener(v -> {
            saveResidentData();
        });
    }

    /**
     * Initializes the UI components.
     */
    private void initView() {
        fullName = findViewById(R.id.full_name);
        nidBirthNumber = findViewById(R.id.nid);
        email = findViewById(R.id.email);
        contactNumber = findViewById(R.id.number);
        emergencyContact = findViewById(R.id.emergency_number);
        profession = findViewById(R.id.profession);
        monthlyIncome = findViewById(R.id.monthly_income);
        password = findViewById(R.id.password);
        btnSaveChanges = findViewById(R.id.btn_save_changes);
    }

    /**
     * Fills the profile data in the UI.
     *
     * @param resident the resident model containing the profile data.
     */
    private void fillProfileData(RegistrationModel resident) {
        fullName.setText(resident.getName());
        nidBirthNumber.setText(resident.getNidOrBirthCertificate());
        email.setText(resident.getEmail());
        contactNumber.setText(resident.getContactNumber());
        emergencyContact.setText(resident.getEmergencyContact());
        profession.setText(resident.getProfession());
        monthlyIncome.setText(resident.getMonthlyIncome());
        password.setText(resident.getPassword());
    }

    /**
     * Saves the updated resident data.
     */
    private void saveResidentData() {
        RegistrationModel updatedResident = new RegistrationModel(
                fullName.getText().toString(),
                email.getText().toString(),
                contactNumber.getText().toString(),
                emergencyContact.getText().toString(),
                "", // NID/Birth Certificate (add if needed)
                profession.getText().toString(),
                monthlyIncome.getText().toString(),
                password.getText().toString(),
                "", // Image URL (add if needed)
                "UnitCode" // Unit code (add if needed)
        );

        // Update the resident data in the ViewModel
        viewModel.addResident(updatedResident);
    }
}
