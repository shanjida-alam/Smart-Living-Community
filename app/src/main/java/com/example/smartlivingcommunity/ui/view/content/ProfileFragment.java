package com.example.smartlivingcommunity.ui.view.content;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.ResidentProfileModel;
import com.example.smartlivingcommunity.ui.viewmodel.ResidentProfileViewModel;

/**
 * Profile Fragment View responsible for displaying and updating resident profile information.
 *
 * <p>
 *     This fragment retrieves resident profile data using the {@link ResidentProfileViewModel}
 *     This fragment also updated the UI components with the retrieved data.
 * </p>
 *
 * @author Shanjida Alam
 * @version 1.0
 */
public class ProfileFragment extends Fragment {

    /**
     * ViewModel for managing resident profile data.
     */
    private ResidentProfileViewModel residentProfileViewModel;

    /**
     * UI components for displaying and updating resident profile information.
     */
    private EditText nameEditText, emailEditText, contactNumberEditText, emergencyContactEditText,
            nidEditText, professionEditText, monthlyIncomeEditText, passwordEditText;

    /**
     * ImageView for displaying the resident's profile image.
     */
    private ImageView profileImageView;

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return the View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /**
         * Inflate the layout for this fragment
         */
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        /**
         * Initialize UI components from the inflated view
         */
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

    /**
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /**
         * Fetch resident data by unit code
         */
        String unitCode = "R-B0KF"; // Example unit code; replace it with a dynamic value if needed

        /**
         * Initialize ViewModel
         */
        residentProfileViewModel = new ViewModelProvider(this).get(ResidentProfileViewModel.class);
        residentProfileViewModel.fetchResidentDataByUnitCode(unitCode);

        /**
         * Observe the resident data changes and update UI components
         */
        residentProfileViewModel.getResidentLiveData().observe(getViewLifecycleOwner(), new Observer<ResidentProfileModel>() {
            @Override
            public void onChanged(ResidentProfileModel resident) {
                if (resident != null) {
                    /**
                     * Update UI components with resident data
                     */
                    nameEditText.setText(resident.getName());
                    emailEditText.setText(resident.getEmail());
                    contactNumberEditText.setText(resident.getContactNumber());
                    emergencyContactEditText.setText(resident.getEmergencyContact());
                    nidEditText.setText(resident.getNidOrBirthCertificate());
                    professionEditText.setText(resident.getProfession());
                    monthlyIncomeEditText.setText(resident.getMonthlyIncome());
                    passwordEditText.setText(resident.getPassword());

                    /**
                     * Load and display the resident's profile image
                     */
                    Log.d("ProfileFragment", "Image URL: " + resident.getImageUrl());
                    if (resident.getImageUrl() != null && !resident.getImageUrl().isEmpty()) {
                        Glide.with(requireActivity())
                                .load(resident.getImageUrl())
                                .into(profileImageView);
                    } else {
                        profileImageView.setImageResource(R.drawable.person); // Placeholder image
                    }
                } else {
                    /**
                     * Handle the case where resident data is null
                     */
                }
            }
        });
    }
}