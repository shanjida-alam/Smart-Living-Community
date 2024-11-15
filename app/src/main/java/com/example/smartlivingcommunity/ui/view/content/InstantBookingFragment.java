package com.example.smartlivingcommunity.ui.view.content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.InstantBookingModel;
import com.example.smartlivingcommunity.ui.viewmodel.ParkingViewModel;
import com.example.smartlivingcommunity.utils.Resource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class InstantBookingFragment extends Fragment {
    private ParkingViewModel viewModel;
    private Spinner vehicleSpinner;
    private CalendarView calendarView;
    private Spinner timeSlotSpinner;
    private Button bookButton;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    private String selectedDate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_instant_booking, container, false);
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
        calendarView = view.findViewById(R.id.calendarView);
        timeSlotSpinner = view.findViewById(R.id.timeSlotSpinner);
        bookButton = view.findViewById(R.id.bookButton);
        progressBar = view.findViewById(R.id.progressBar);

        // Set initial selected date
        selectedDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Calendar.getInstance().getTime());

        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            // Update selected date when user selects a new date
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);
            selectedDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.getTime());
        });

        bookButton.setOnClickListener(v -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                bookInstantly(user);
            } else {
                Toast.makeText(requireContext(), "Please sign in to book instantly", Toast.LENGTH_LONG).show();
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
                } else if (result.status == Resource.Status.ERROR) {
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void bookInstantly(FirebaseUser user) {
        String vehicle = vehicleSpinner.getSelectedItem().toString();
        String timeSlot = timeSlotSpinner.getSelectedItem().toString();

        InstantBookingModel booking = new InstantBookingModel(
                "",
                user.getUid(),
                user.getDisplayName() != null ? user.getDisplayName() : "Resident",
                vehicle,
                selectedDate,
                timeSlot,
                "BOOKED"
        );

        viewModel.bookInstantly(booking);
    }
}