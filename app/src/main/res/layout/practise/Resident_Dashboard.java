package com.example.practise;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.practise.databinding.ActivityResidentDashboardBinding;

public class Resident_Dashboard extends AppCompatActivity {
    ActivityResidentDashboardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityResidentDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new ServiceRequestFragment());

        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.service_request) {
                replaceFragment(new ServiceRequestFragment());
            } else if (id == R.id.payment_status) {
                replaceFragment(new PaymentStatusFragment());
            } else if (id == R.id.community_events) {
                replaceFragment(new CommunityEventsFragment());
            } else if (id == R.id.parking_request) {
                replaceFragment(new ParkingRequestFragment());
            } else if (id == R.id.community_board) {
                replaceFragment(new CommunityBoardFragment());
            }
            return true;
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}