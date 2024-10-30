package com.example.smartlivingcommunity.UI.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.smartlivingcommunity.R;

public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        Button switchToManager = findViewById(R.id.switch_to_manager);
        Button switchToResident = findViewById(R.id.switch_to_resident);

        switchToManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.managerRequestFragment); // Replace with your Manager Fragment ID
            }
        });

        switchToResident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.residentRequestFragment); // Replace with your Resident Fragment ID
            }
        });
    }
}
