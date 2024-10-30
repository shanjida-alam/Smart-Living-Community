package com.example.smartlivingcommunity.ui.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.RegistrationModel;
import com.example.smartlivingcommunity.ui.viewmodel.ResidentProfileViewModel;

public class ResidentProfileView extends Fragment {
    private EditText fullNameEditText, emailEditText, contactNumberEditText, emergencyContactEditText,
            nidEditText, professionEditText, monthlyIncomeEditText, passwordEditText;
    private Button saveButton;
    private ResidentProfileViewModel viewModel;

    private final String RESIDENT_ID = "pVF8YAXinQTSchHssvaFm"; // Replace with actual resident ID

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.resident_profile, container, false);

        // Initialize UI elements
        fullNameEditText = view.findViewById(R.id.full_name);
        emailEditText = view.findViewById(R.id.email);
        contactNumberEditText = view.findViewById(R.id.number);
        emergencyContactEditText = view.findViewById(R.id.emergency_number);
        nidEditText = view.findViewById(R.id.nid);
        professionEditText = view.findViewById(R.id.profession);
        monthlyIncomeEditText = view.findViewById(R.id.monthly_income);
        passwordEditText = view.findViewById(R.id.password);
        saveButton = view.findViewById(R.id.btn_save_changes);

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(ResidentProfileViewModel.class);

        // Observe and display resident data
        viewModel.getResidentData().observe(getViewLifecycleOwner(), resident -> {
            if (resident != null) {
                fullNameEditText.setText(resident.getName());
                emailEditText.setText(resident.getEmail());
                contactNumberEditText.setText(resident.getContactNumber());
                emergencyContactEditText.setText(resident.getEmergencyContact());
                nidEditText.setText(resident.getNidOrBirthCertificate());
                professionEditText.setText(resident.getProfession());
                monthlyIncomeEditText.setText(resident.getMonthlyIncome());
                passwordEditText.setText(resident.getPassword());
                Log.d("ResidentProfileView", "Data displayed in UI successfully.");
            } else {
                Log.d("ResidentProfileView", "No data available to display.");
            }
        });

        // Fetch resident data from Firestore using the actual resident ID
        Log.d("ResidentProfileView", "Fetching data for Resident ID: " + RESIDENT_ID);
        viewModel.fetchResidentData(RESIDENT_ID);

        // Save updated data
        saveButton.setOnClickListener(v -> {
            RegistrationModel updatedResident = new RegistrationModel(
                    fullNameEditText.getText().toString(),
                    emailEditText.getText().toString(),
                    contactNumberEditText.getText().toString(),
                    emergencyContactEditText.getText().toString(),
                    nidEditText.getText().toString(),
                    professionEditText.getText().toString(),
                    monthlyIncomeEditText.getText().toString(),
                    passwordEditText.getText().toString(),
                    null, // image URL (not handled in this example)
                    null  // unit code (not handled in this example)
            );
            Log.d("ResidentProfileView", "Updating data for Resident ID: " + RESIDENT_ID);
            viewModel.updateResidentData(RESIDENT_ID, updatedResident);
        });

        return view;
    }
}
