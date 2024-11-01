package com.example.smartlivingcommunity.ui.view.content;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.ResidentProfileModel;
import com.example.smartlivingcommunity.ui.viewmodel.ResidentProfileViewModel;


public class ProfileFragment extends Fragment {
    private ResidentProfileViewModel residentProfileViewModel;
    private EditText nameEditText, emailEditText, contactNumberEditText, emergencyContactEditText,
            nidEditText, professionEditText, monthlyIncomeEditText, passwordEditText;
    private ImageView profileImageView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize UI components using the root view
        nameEditText = view.findViewById(R.id.full_name);
        emailEditText = view.findViewById(R.id.email);
        contactNumberEditText = view.findViewById(R.id.number);
        emergencyContactEditText = view.findViewById(R.id.emergency_number);
        nidEditText = view.findViewById(R.id.nid);
        professionEditText = view.findViewById(R.id.profession);
        monthlyIncomeEditText = view.findViewById(R.id.monthly_income);
        passwordEditText = view.findViewById(R.id.password);
        profileImageView = view.findViewById(R.id.profile_image);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get the unit code to fetch specific resident data
        String unitCode = "R-N8GQ"; // Example unit code; replace it with a dynamic value if needed

        // Set up ViewModel
        residentProfileViewModel = new ViewModelProvider(this).get(ResidentProfileViewModel.class);
        residentProfileViewModel.fetchResidentDataByUnitCode(unitCode);

        // Observe data from ViewModel
        residentProfileViewModel.getResidentLiveData().observe(getViewLifecycleOwner(), new Observer<ResidentProfileModel>() {
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
//                        Glide.with(requireContext())
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
