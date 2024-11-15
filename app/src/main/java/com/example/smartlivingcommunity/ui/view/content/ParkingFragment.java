package com.example.smartlivingcommunity.ui.view.content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.ui.viewmodel.ParkingViewModel;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ParkingFragment extends Fragment {
    private ParkingViewModel viewModel;
    private RecyclerView registrationsRecyclerView;
    private ProgressBar progressBar;
    private ParkingRegistrationAdapter adapter;
    private FirebaseAuth firebaseAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_parking, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(ParkingViewModel.class);

        // Initialize views
        initViews(view);
        setupRecyclerView();
    }

    private void initViews(View view) {
        registrationsRecyclerView = view.findViewById(R.id.registrationsRecyclerView);
        progressBar = view.findViewById(R.id.progressBar);

        // Register Vehicle Click
        MaterialCardView registerVehicleCard = view.findViewById(R.id.registerVehicleCard);
        registerVehicleCard.setOnClickListener(v -> openFragment(new RegisterVehicleFragment()));

        // Book Slot Instantly Click
        MaterialCardView instantBookingCard = view.findViewById(R.id.instantBookingCard);
        instantBookingCard.setOnClickListener(v -> openFragment(new InstantBookingFragment()));

        // Book Permanent Slot Click
        MaterialCardView permanentBookingCard = view.findViewById(R.id.permanentBookingCard);
        permanentBookingCard.setOnClickListener(v -> openFragment(new PermanentBookingFragment()));

        // Recent Slot Bookings Click
        MaterialCardView recentBookingsCard = view.findViewById(R.id.recentBookingsCard);
        recentBookingsCard.setOnClickListener(v -> openFragment(new RecentBookingsFragment()));

        // Payment History Click
        MaterialCardView paymentHistoryCard = view.findViewById(R.id.paymentHistoryCard);
        paymentHistoryCard.setOnClickListener(v -> openFragment(new PaymentHistoryFragment()));

        // Feedback Click
        MaterialCardView feedbackCard = view.findViewById(R.id.feedbackCard);
        feedbackCard.setOnClickListener(v -> openFragment(new FeedbackFragment()));
    }

    private void setupRecyclerView() {
        adapter = new ParkingRegistrationAdapter(new ArrayList<>());
        registrationsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        registrationsRecyclerView.setAdapter(adapter);
    }

    private void openFragment(Fragment fragment) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void loadRegistrations() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            viewModel.getRegistrations(user.getUid()).observe(getViewLifecycleOwner(),
                    registrations -> adapter.updateRegistrations(registrations));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            loadRegistrations();
        }
    }
}