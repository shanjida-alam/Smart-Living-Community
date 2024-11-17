package com.example.smartlivingcommunity.ui.view.content;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.smartlivingcommunity.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton drawerToggle;
    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;
    NavigationView drawerNavigationView;
    TextView toolbarTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = findViewById(R.id.drawerToggle);
        frameLayout = findViewById(R.id.fragment_container);
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        drawerNavigationView = findViewById(R.id.drawer_nav_view);
        toolbarTitle = findViewById(R.id.toolbarTitle);

        drawerToggle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                drawerLayout.open();
            }
        });

        // Load DashboardFragment by default
        if (savedInstanceState == null) { // Ensures it only loads once on app start
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new DashboardFragment());
            fragmentTransaction.commit();
            bottomNavigationView.setSelectedItemId(R.id.navDashboard); // Set the bottom navigation to Dashboard
            drawerNavigationView.setCheckedItem(R.id.itemDashboard); // Set the drawer navigation to Dashboard
        }

        setupBottomNavigation();
        setupDrawerNavigation();
    }

    private void setupBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                if (itemId == R.id.navDashboard) {
                    fragmentTransaction.replace(R.id.fragment_container, new DashboardFragment());
                    setToolbarTitle("Dashboard");
                    drawerNavigationView.setCheckedItem(R.id.itemDashboard); // Sync with drawer
                } else if (itemId == R.id.navServiceRequest) {
                    fragmentTransaction.replace(R.id.fragment_container, new ServiceRequestFragment());
                    setToolbarTitle("Service Request");
                    drawerNavigationView.setCheckedItem(R.id.itemServiceRequest); // Sync with drawer
                } else if (itemId == R.id.navManageEvent) {
                    fragmentTransaction.replace(R.id.fragment_container, new ManageEventFragment());
                    setToolbarTitle("Manage Events");
                    drawerNavigationView.setCheckedItem(R.id.itemManageEvent); // Sync with drawer
                }

                fragmentTransaction.commit();
                return true;
            }
        });
    }

    private void setupDrawerNavigation() {
        drawerNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                if (itemId == R.id.itemDashboard) {
                    fragmentTransaction.replace(R.id.fragment_container, new DashboardFragment());
                    setToolbarTitle("Dashboard");
                    bottomNavigationView.setSelectedItemId(R.id.navDashboard); // Sync with bottom nav
                } else if (itemId == R.id.itemManageProfile) {
                    setToolbarTitle("Manage Profile");
                    fragmentTransaction.replace(R.id.fragment_container, new ProfileFragment());
                } else if (itemId == R.id.itemServiceRequest) {
                    fragmentTransaction.replace(R.id.fragment_container, new ServiceRequestFragment());
                    setToolbarTitle("Service Request");
                    bottomNavigationView.setSelectedItemId(R.id.navServiceRequest); // Sync with bottom nav
                } else if (itemId == R.id.itemManageEvent) {
                    fragmentTransaction.replace(R.id.fragment_container, new ManageEventFragment());
                    setToolbarTitle("Manage Events");
                    bottomNavigationView.setSelectedItemId(R.id.navManageEvent); // Sync with bottom nav
                } else if (itemId == R.id.itemManageParking) {
                    setToolbarTitle("Manage Parking");
                    fragmentTransaction.replace(R.id.fragment_container, new ParkingFragment());
                }

                fragmentTransaction.commit();
                drawerLayout.close();


                return true;
            }
        });
    }

    // Helper method to set the toolbar title
    private void setToolbarTitle(String title) {
        toolbarTitle.setText(title);
    }


}
