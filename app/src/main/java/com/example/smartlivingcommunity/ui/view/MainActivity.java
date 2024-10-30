package com.example.smartlivingcommunity.ui.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.ui.viewmodel.ResidentProfileViewModel;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {
    private ResidentProfileViewModel ResidentProfileViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);

        ResidentProfileViewModel = new ViewModelProvider(this).get(ResidentProfileViewModel.class);

        setContentView(R.layout.resident_profile);

        // Load ProfileFragmentView
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragment_container, new ResidentProfileView());
//        transaction.commit();
    }


}
