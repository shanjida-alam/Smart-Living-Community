package com.example.smartlivingcommunity.ui.view.content;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.smartlivingcommunity.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private BottomNavigationView bottomNavigationView;
    private NavigationView drawerNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        drawerNavigationView = findViewById(R.id.drawer_nav_view);

        // Set up navigation to open the drawer
        ImageButton drawerToggle = findViewById(R.id.drawerToggle);
        drawerToggle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                drawerLayout.open();
            }
        });

        // Load ProfileFragment (or DashboardFragment) by default
        if (savedInstanceState == null) { // Only load the fragment if activity is created for the first time
            loadFragment(new ProfileFragment());
            bottomNavigationView.setSelectedItemId(R.id.navDashboard); // Adjust as needed
            drawerNavigationView.setCheckedItem(R.id.itemDashboard);  // Adjust as needed
        }

        setupBottomNavigation();
        setupDrawerNavigation();
    }

    private void setupBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                int itemId = item.getItemId();

                if (itemId == R.id.navDashboard) {
                    selectedFragment = new DashboardFragment();
                    drawerNavigationView.setCheckedItem(R.id.itemDashboard); // Sync with drawer
                } else if (itemId == R.id.navServiceRequest) {
                    selectedFragment = new ServiceRequestFragment();
                    drawerNavigationView.setCheckedItem(R.id.itemServiceRequest); // Sync with drawer
                } else if (itemId == R.id.navManageEvent) {
                    selectedFragment = new ManageEventFragment();
                    drawerNavigationView.setCheckedItem(R.id.itemManageEvent); // Sync with drawer
                }

                if (selectedFragment != null) {
                    loadFragment(selectedFragment);
                }
                return true;
            }
        });
    }

    private void setupDrawerNavigation() {
        drawerNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                int itemId = item.getItemId();

                if (itemId == R.id.itemDashboard) {
                    selectedFragment = new DashboardFragment();
                    bottomNavigationView.setSelectedItemId(R.id.navDashboard); // Sync with bottom nav
                } else if (itemId == R.id.itemManageProfile) {
                    selectedFragment = new ProfileFragment();
                } else if (itemId == R.id.itemServiceRequest) {
                    selectedFragment = new ServiceRequestFragment();
                    bottomNavigationView.setSelectedItemId(R.id.navServiceRequest); // Sync with bottom nav
                } else if (itemId == R.id.itemManageEvent) {
                    selectedFragment = new ManageEventFragment();
                    bottomNavigationView.setSelectedItemId(R.id.navManageEvent); // Sync with bottom nav
                }

                if (selectedFragment != null) {
                    loadFragment(selectedFragment);
                }
                drawerLayout.close();
                return true;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
