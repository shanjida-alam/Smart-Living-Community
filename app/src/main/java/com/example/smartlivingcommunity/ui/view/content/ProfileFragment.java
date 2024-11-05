package com.example.smartlivingcommunity.ui.view.content;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.ResidentModel;
import com.example.smartlivingcommunity.databinding.FragmentProfileBinding;
import com.example.smartlivingcommunity.ui.viewmodel.ResidentProfileViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

public class ProfileFragment extends Fragment {
    private ResidentProfileViewModel viewModel;
    private FragmentProfileBinding binding;
    private String unitCode;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ResidentProfileViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get unitCode from arguments or shared preferences
        unitCode = "R-O573"; // Replace with your method of getting the unit code

        setupObservers();
        setupClickListeners();

        // Fetch initial data
        viewModel.fetchResident(unitCode);
    }

    private void setupObservers() {
        viewModel.getResidentData().observe(getViewLifecycleOwner(), this::populateFields);

        viewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading ->
                binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE));

        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), message -> {
            if (message != null && !message.isEmpty()) {
                Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void setupClickListeners() {
        binding.buttonUpdate.setOnClickListener(v -> updateResident());
    }

    private void populateFields(ResidentModel resident) {
        if (resident != null) {
            binding.editTextName.setText(resident.getName());
            binding.editTextContact.setText(resident.getContactNumber());
            binding.editTextEmergency.setText(resident.getEmergencyNumber());
            binding.editTextEmail.setText(resident.getEmail());
            binding.editTextIncome.setText(resident.getMonthlyIncome());
            binding.editTextProfession.setText(resident.getProfession());
            binding.editTextNidBirth.setText(resident.getNidOrBirthCertificate());
            binding.editTextPassword.setText(resident.getPassword());
        }
    }

    private void updateResident() {
        // Validate fields first
        if (!validateFields()) {
            return;
        }

        Map<String, Object> updates = new HashMap<>();
        updates.put("name", binding.editTextName.getText().toString().trim());
        updates.put("contactNumber", binding.editTextContact.getText().toString().trim());
        updates.put("emergencyNumber", binding.editTextEmergency.getText().toString().trim());
        updates.put("email", binding.editTextEmail.getText().toString().trim());
        updates.put("monthlyIncome", binding.editTextIncome.getText().toString().trim());
        updates.put("profession", binding.editTextProfession.getText().toString().trim());
        updates.put("nidOrBirthCertificate", binding.editTextNidBirth.getText().toString().trim());
        updates.put("password", binding.editTextPassword.getText().toString().trim());

        viewModel.updateResident(unitCode, updates);
    }

    private boolean validateFields() {
        boolean isValid = true;

        // Name validation
        if (binding.editTextName.getText().toString().trim().isEmpty()) {
            binding.editTextName.setError("Name is required");
            isValid = false;
        }

        // Contact number validation
        String contact = binding.editTextContact.getText().toString().trim();
        if (contact.isEmpty()) {
            binding.editTextContact.setError("Contact number is required");
            isValid = false;
        } else if (contact.length() < 10) {
            binding.editTextContact.setError("Invalid contact number");
            isValid = false;
        }

        // Emergency number validation
        String emergency = binding.editTextEmergency.getText().toString().trim();
        if (emergency.isEmpty()) {
            binding.editTextEmergency.setError("Emergency number is required");
            isValid = false;
        } else if (emergency.length() < 10) {
            binding.editTextEmergency.setError("Invalid emergency number");
            isValid = false;
        }

        // Email validation
        String email = binding.editTextEmail.getText().toString().trim();
        if (email.isEmpty()) {
            binding.editTextEmail.setError("Email is required");
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.editTextEmail.setError("Invalid email address");
            isValid = false;
        }

        // NID/Birth Certificate validation
        if (binding.editTextNidBirth.getText().toString().trim().isEmpty()) {
            binding.editTextNidBirth.setError("NID/Birth Certificate is required");
            isValid = false;
        }

        // Password validation
        String password = binding.editTextPassword.getText().toString().trim();
        if (password.isEmpty()) {
            binding.editTextPassword.setError("Password is required");
            isValid = false;
        } else if (password.length() < 6) {
            binding.editTextPassword.setError("Password must be at least 6 characters");
            isValid = false;
        }

        return isValid;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}