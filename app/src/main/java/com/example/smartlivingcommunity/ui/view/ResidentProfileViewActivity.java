package com.example.smartlivingcommunity.ui.view;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.ResidentProfileModel;
import com.example.smartlivingcommunity.ui.viewmodel.ResidentProfileViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


public class ResidentProfileViewActivity extends AppCompatActivity {
    private ResidentProfileViewModel residentProfileViewModel;
    private EditText nameEditText, emailEditText, contactNumberEditText, emergencyContactEditText,
            nidEditText, professionEditText, monthlyIncomeEditText, passwordEditText;
    private ImageView profileImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resident_profile);

        // Initialize UI components (EditText fields)
        nameEditText = findViewById(R.id.full_name);
        emailEditText = findViewById(R.id.email);
        contactNumberEditText = findViewById(R.id.number);
        emergencyContactEditText = findViewById(R.id.emergency_number);
        nidEditText = findViewById(R.id.nid);
        professionEditText = findViewById(R.id.profession);
        monthlyIncomeEditText = findViewById(R.id.monthly_income);
        passwordEditText = findViewById(R.id.password);
        profileImageView = findViewById(R.id.profile_image);

        // Get the unit code to fetch specific resident data
        String unitCode = "R-Z31A"; // Example unit code; replace it with a dynamic value if needed

        // Set up ViewModel
        residentProfileViewModel = new ViewModelProvider(this).get(ResidentProfileViewModel.class);
        residentProfileViewModel.fetchResidentDataByUnitCode(unitCode);

        // Observe data from ViewModel
        residentProfileViewModel.getResidentLiveData().observe(this, new Observer<ResidentProfileModel>() {
            @Override
            public void onChanged(ResidentProfileModel resident) {
                if (resident != null) {
                    // Display the data in the UI components (EditText fields)
                    nameEditText.setText(resident.getName());
                    emailEditText.setText(resident.getEmail());
                    contactNumberEditText.setText(resident.getContactNumber());
                    emergencyContactEditText.setText(resident.getEmergencyContact());
                    nidEditText.setText(resident.getNidOrBirthCertificate());
                    professionEditText.setText(resident.getProfession());
                    monthlyIncomeEditText.setText(resident.getMonthlyIncome());
                    passwordEditText.setText(resident.getPassword());


                    // Load the profile image if imageUrl is available
//                    if (resident.getImageUrl() != null && !resident.getImageUrl().isEmpty()) {
//                        Glide.with(ResidentProfileViewActivity.this)
//                                .load(resident.getImageUrl())
//                                .into(profileImageView);
//                    } else {
//                        profileImageView.setImageResource(R.drawable.default_profile); // Placeholder image
//                    }
                } else {
                    // Handle case where resident data is not found
                    // You could show a message or clear fields if needed
                }
            }
        });
    }
}
