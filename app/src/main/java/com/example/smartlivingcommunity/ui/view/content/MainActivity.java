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

/**
 * @author solaimi
 * MainActivity is the main entry point of the Smart Living Community application.
 * It manages the navigation between fragments using both a BottomNavigationView and a DrawerLayout.
 * The activity includes functionality for loading different fragments and updating the toolbar title.
 */
public class MainActivity extends AppCompatActivity {

    // UI components
    private DrawerLayout drawerLayout;             // The drawer layout for the side navigation menu
    private ImageButton drawerToggle;              // Button to toggle the drawer open and close
    private FrameLayout frameLayout;               // Container for displaying fragments
    private BottomNavigationView bottomNavigationView; // Bottom navigation menu
    private NavigationView drawerNavigationView;   // Drawer navigation menu
    private TextView toolbarTitle;                 // Text view to set the title in the toolbar

    /**
     * Called when the activity is created. Initializes the UI components and sets up the fragment navigation.
     *
     * @param savedInstanceState Bundle containing the activity's previous state, if any.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = findViewById(R.id.drawerToggle);
        frameLayout = findViewById(R.id.fragment_container);
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        drawerNavigationView = findViewById(R.id.drawer_nav_view);
        toolbarTitle = findViewById(R.id.toolbarTitle);

        // Set click listener for the drawer toggle button
        drawerToggle.setOnClickListener(v -> drawerLayout.open());

        // Load the default fragment (Dashboard) only if it's the first time the activity is created
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new DashboardFragment());
            fragmentTransaction.commit();
            bottomNavigationView.setSelectedItemId(R.id.navDashboard); // Set bottom navigation to Dashboard
            drawerNavigationView.setCheckedItem(R.id.itemDashboard); // Set drawer navigation to Dashboard
        }

        // Set up listeners for bottom and drawer navigation
        setupBottomNavigation();
        setupDrawerNavigation();
    }

    /**
     * Sets up the BottomNavigationView to switch between fragments when an item is selected.
     */
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
            } else if (itemId == R.id.directory_menu) {
                fragmentTransaction.replace(R.id.fragment_container, new DirectoryFragment());
                setToolbarTitle("Directory");
                drawerNavigationView.setCheckedItem(R.id.directory_menu); // Sync with drawer
                Log.d("MainActivity", "Directory selected");
            }

            fragmentTransaction.commit();
            return true;
        });
    }

    /**
     * Sets up the DrawerLayout's navigation items. Replaces fragments when an item is selected.
     */
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
            } else if (itemId == R.id.directory_menu) {
                fragmentTransaction.replace(R.id.fragment_container, new DirectoryFragment());
                setToolbarTitle("Directory");
                bottomNavigationView.setSelectedItemId(R.id.directory_menu); // Sync with bottom nav
            }

            fragmentTransaction.commit();
            drawerLayout.close(); // Close the drawer after an item is selected
            return true;
        });
    }

    /**
     * Sets the title of the toolbar.
     *
     * @param title The title to be displayed on the toolbar.
     */
    private void setToolbarTitle(String title) {
        toolbarTitle.setText(title);
    }
}

