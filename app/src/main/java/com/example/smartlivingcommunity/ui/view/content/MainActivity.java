package com.example.smartlivingcommunity.ui.view.content;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.smartlivingcommunity.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton drawerToggle;
    NavigationView navView;
    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;
    private boolean isAppInitialized = false;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            drawerLayout = findViewById(R.id.drawer_layout);
            drawerToggle = findViewById(R.id.drawerToggle);

            drawerToggle.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    drawerLayout.open();
                }
            });

            navView = findViewById(R.id.bottom_nav_view);
            frameLayout = findViewById(R.id.fragment_container);
            FragmentManager fragmentManager = getSupportFragmentManager();
            navView.setNavigationItemSelectedListener(new BottomNavHandler(this, fragmentManager));

        }

}