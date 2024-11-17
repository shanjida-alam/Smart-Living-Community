package com.example.smartlivingcommunity.ui.view.content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.PermanentBookingModel;
import com.example.smartlivingcommunity.ui.viewmodel.ParkingViewModel;
import com.example.smartlivingcommunity.utils.Resource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PermanentBookingFragment extends Fragment {
    private ParkingViewModel viewModel;
    private Spinner vehicleSpinner;
    private EditText startDateEditText;
    private EditText endDateEditText;
    private Button bookButton;
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
        return inflater.inflate(R.layout.fragment_permanent_booking, container, false);
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
        vehicleSpinner = view.findViewById(R.id.vehicleSpinner);
        startDateEditText = view.findViewById(R.id.startDateEditText);
        endDateEditText = view.findViewById(R.id.endDateEditText);
        bookButton = view.findViewById(R.id.bookButton);
        progressBar = view.findViewById(R.id.progressBar);

        bookButton.setOnClickListener(v -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                bookPermanently(user);
            } else {
                Toast.makeText(requireContext(), "Please sign in to book permanently", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setupObservers() {
        viewModel.getBookingStatus().observe(getViewLifecycleOwner(), result -> {
            if (result.status == Resource.Status.LOADING) {
                progressBar.setVisibility(View.VISIBLE);
                bookButton.setEnabled(false);
            } else {
                progressBar.setVisibility(View.GONE);
                bookButton.setEnabled(true);

                if (result.status == Resource.Status.SUCCESS) {
                    Toast.makeText(requireContext(), result.data, Toast.LENGTH_SHORT).show();
                    clearForm();
                } else if (result.status == Resource.Status.ERROR) {
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void bookPermanently(FirebaseUser user) {
        String vehicle = vehicleSpinner.getSelectedItem().toString();
        String startDate = startDateEditText.getText().toString();
        String endDate = endDateEditText.getText().toString();

        if (validateInput(startDate, endDate)) {
            PermanentBookingModel booking = new PermanentBookingModel(
                    "",
                    user.getUid(),
                    user.getDisplayName() != null ? user.getDisplayName() : "Resident",
                    vehicle,
                    startDate,
                    endDate,
                    "REQUESTED"
            );

            viewModel.bookPermanently(booking);
        }
    }

    private boolean validateInput(String startDate, String endDate) {
        if (startDate.isEmpty()) {
            startDateEditText.setError("Start date is required");
            return false;
        }
        if (endDate.isEmpty()) {
            endDateEditText.setError("End date is required");
            return false;
        }
        return true;
    }

    private void clearForm() {
        startDateEditText.setText("");
        endDateEditText.setText("");
    }
}