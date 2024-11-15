package com.example.smartlivingcommunity.ui.view.content;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
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

        drawerToggle.setOnClickListener(v -> drawerLayout.open());

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
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            if (itemId == R.id.navDashboard) {
                fragmentTransaction.replace(R.id.fragment_container, new DashboardFragment());
                setToolbarTitle("Dashboard");
                drawerNavigationView.setCheckedItem(R.id.itemDashboard); // Sync with drawer
                Log.d("MainActivity", "Dashboard selected");
            } else if (itemId == R.id.navServiceRequest) {
                fragmentTransaction.replace(R.id.fragment_container, new ServiceRequestFragment());
                setToolbarTitle("Service Request");
                drawerNavigationView.setCheckedItem(R.id.itemServiceRequest); // Sync with drawer
                Log.d("MainActivity", "Service Request selected");
            } else if (itemId == R.id.navManageEvent) {
                fragmentTransaction.replace(R.id.fragment_container, new ManageEventFragment());
                setToolbarTitle("Manage Events");
                drawerNavigationView.setCheckedItem(R.id.itemManageEvent); // Sync with drawer
                Log.d("MainActivity", "Manage Events selected");
            } else if (itemId == R.id.navDirectory) {
                fragmentTransaction.replace(R.id.fragment_container, new DirectoryFragment());
                setToolbarTitle("Directory");
                drawerNavigationView.setCheckedItem(R.id.navDirectory); // Sync with drawer
                Log.d("MainActivity", "Directory selected");
            }

            fragmentTransaction.commit();
            return true;
        });
    }


    private void setupDrawerNavigation() {
        drawerNavigationView.setNavigationItemSelectedListener(item -> {
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
            } else if (itemId == R.id.navDirectory) {
                fragmentTransaction.replace(R.id.fragment_container, new DirectoryFragment());
                setToolbarTitle("Directory");
                bottomNavigationView.setSelectedItemId(R.id.navDirectory); // Sync with bottom nav
            }

            fragmentTransaction.commit();
            drawerLayout.close();
            return true;
        });
    }

    private void setToolbarTitle(String title) {
        toolbarTitle.setText(title);
    }
}

