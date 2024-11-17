package com.example.smartlivingcommunity.ui.view.content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.ParkingRegistrationModel;
import com.example.smartlivingcommunity.ui.viewmodel.ParkingViewModel;
import com.example.smartlivingcommunity.utils.Resource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RegisterVehicleFragment extends Fragment {
    private ParkingViewModel viewModel;
    private EditText vehicleTypeEditText;
    private EditText vehicleNumberEditText;
    private Button submitButton;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register_vehicle, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(ParkingViewModel.class);

        // Initialize views
        initViews(view);
        setupObservers();
    }

    private void initViews(View view) {
        vehicleTypeEditText = view.findViewById(R.id.vehicleTypeEditText);
        vehicleNumberEditText = view.findViewById(R.id.vehicleNumberEditText);
        submitButton = view.findViewById(R.id.submitButton);
        progressBar = view.findViewById(R.id.progressBar);

        submitButton.setOnClickListener(v -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                submitRegistration(user);
            } else {
                Toast.makeText(requireContext(), "Please sign in to submit registration", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setupObservers() {
        viewModel.getRegistrationStatus().observe(getViewLifecycleOwner(), result -> {
            if (result.status == Resource.Status.LOADING) {
                progressBar.setVisibility(View.VISIBLE);
                submitButton.setEnabled(false);
            } else {
                progressBar.setVisibility(View.GONE);
                submitButton.setEnabled(true);

                if (result.status == Resource.Status.SUCCESS) {
                    Toast.makeText(requireContext(), result.data, Toast.LENGTH_SHORT).show();
                    clearForm();
                } else if (result.status == Resource.Status.ERROR) {
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void submitRegistration(FirebaseUser user) {
        String vehicleType = vehicleTypeEditText.getText().toString().trim();
        String vehicleNumber = vehicleNumberEditText.getText().toString().trim();

        if (validateInput(vehicleType, vehicleNumber)) {
            String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                    .format(new Date());

            ParkingRegistrationModel registration = new ParkingRegistrationModel(
                    "",
                    user.getUid(),
                    user.getDisplayName() != null ? user.getDisplayName() : "Resident",
                    vehicleType,
                    vehicleNumber,
                    "PENDING",
                    currentDate,
                    "",
                    ""
            );

            viewModel.submitParkingRegistration(registration);
        }
    }

    private boolean validateInput(String vehicleType, String vehicleNumber) {
        if (vehicleType.isEmpty()) {
            vehicleTypeEditText.setError("Vehicle type is required");
            return false;
        }
        if (vehicleNumber.isEmpty()) {
            vehicleNumberEditText.setError("Vehicle number is required");
            return false;
        }
        if (!vehicleType.equalsIgnoreCase("car") && !vehicleType.equalsIgnoreCase("motorcycle")) {
            vehicleTypeEditText.setError("Vehicle type must be either Car or Motorcycle");
            return false;
        }
        if (vehicleNumber.length() < 5) {
            vehicleNumberEditText.setError("Please enter a valid vehicle number");
            return false;
        }
        return true;
    }

    private void clearForm() {
        vehicleTypeEditText.setText("");
        vehicleNumberEditText.setText("");
    }
}