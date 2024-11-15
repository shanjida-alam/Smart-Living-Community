package com.example.smartlivingcommunity.ui.view.content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.ui.viewmodel.ResidentProfileViewModel;

/**
 * ProfileFragment responsible for displaying and managing user profile information.
 *
 * <p>
 *  This activity initializes ResidentProfile components and interacts with the ResidentProfileViewModel
 *  to perform ResidentProfile-related actions and display results.
 * </p>
 */
public class ProfileFragment extends Fragment {
    /**
     * ResidentProfileViewModel that handles the profile information logic.
     */
    private ResidentProfileViewModel viewModel;

    /**
     * EditText for editing user name
     */
    private EditText nameField;
    /**
     * EditText for editing user email
     */
    private EditText emailField;
    /**
     * EditText for editing user contact number
     */
    private EditText contactNumberField;
    /**
     * EditText for editing user emergency contact number
     */
    private EditText emergencyContactField;
    /**
     * EditText for editing user nid or birth certificate number
     */
    private EditText nidField;
    /**
     * EditText for editing user profession
     */
    private EditText professionField;
    /**
     * EditText for editing user monthly income
     */
    private EditText monthlyIncomeField;
    /**
     * EditText for editing user password
     */
    private EditText passwordField;
    /**
     * Button to enable editing of profile information
     */
    private Button editButton;
    /**
     * Button to save changes made to the profile
     */
    private Button saveButton;

    /**
     * Called when the activity is first created. Sets up the view, initializes components,
     * and configures ViewModel observers and click listeners.
     *
     * @param savedInstanceState if non-null, this activity is being re-initialized
     *                           after previously being shut down
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
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
         * Initialize the ViewModel
         */
        viewModel = new ViewModelProvider(this).get(ResidentProfileViewModel.class);

        /**
         * Initialize views
         */
        initializeViews(view);
        setupObservers();
        setupClickListeners();

        /**
         * Fetch user profile data using the unitCode
         */
        String unitCode = "A-H5XS"; // Replace with actual unitCode
        viewModel.fetchUserProfile(unitCode);

        /**
         * Observe error messages
         */
        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), error -> {
            if (error != null && !error.isEmpty()) {
                Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show();
            }
        });

        /**
         * Observe loading state
         */
        viewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                /**
                 * Disable editing and save buttons when loading
                 */
                editButton.setEnabled(false);
                saveButton.setEnabled(false);
            } else {
                /**
                 * Enable editing and save buttons when not loading
                 */
                editButton.setEnabled(true);
                saveButton.setEnabled(true);
            }
        });
    }

    /**
     * Initializes the views used in the fragment.
     *
     * @param view The root view of the fragment.
     */
    private void initializeViews(View view) {
        nameField = view.findViewById(R.id.full_name);
        emailField = view.findViewById(R.id.email);
        contactNumberField = view.findViewById(R.id.number);
        emergencyContactField = view.findViewById(R.id.emergency_number);
        nidField = view.findViewById(R.id.nid);
        professionField = view.findViewById(R.id.profession);
        monthlyIncomeField = view.findViewById(R.id.monthly_income);
        passwordField = view.findViewById(R.id.password);
        editButton = view.findViewById(R.id.btn_edit);
        saveButton = view.findViewById(R.id.btn_save_changes);
    }

    /**
     * Sets up observers for ViewModel data changes.
     */
    private void setupObservers() {
        viewModel.getIsEditing().observe(getViewLifecycleOwner(), isEditing -> {
            enableEditing(isEditing);
            saveButton.setVisibility(isEditing ? View.VISIBLE : View.GONE);
            editButton.setVisibility(isEditing ? View.GONE : View.VISIBLE);
        });

        /**
         * Observe profile data changes
         */
        viewModel.getProfileData().observe(getViewLifecycleOwner(), profile -> {
            if (profile != null) {
                /**
                 * Populate EditText fields with profile data
                 */
                populateFields(profile);
            }
        });
    }

    /**
     * Sets up click listeners for the edit and save buttons.
     */
    private void setupClickListeners() {
        /**
         * Set click listeners for the edit button
         */
        editButton.setOnClickListener(v -> viewModel.enableEditing());
        /**
         * Set click listener for the save button
         */
        saveButton.setOnClickListener(v -> {
            /**
             * Get the current profile data and update it with new values from EditText fields
             */
            ResidentProfileModel currentProfile = viewModel.getProfileData().getValue();
            if (currentProfile != null) {
                /**
                 * Update the profile with new values from EditText fields
                 */
                updateProfileFromFields(currentProfile);
                /**
                 * Save the updated profile to the database
                 */
                viewModel.saveUserProfile();
            }
        });
    }

    /**
     * Populates the EditText fields with the provided profile data.
     *
     * @param profile The profile data to be displayed in the EditText fields.
     */
    private void populateFields(ResidentProfileModel profile) {
        /**
         * Set the text of EditText fields with the corresponding profile data
         */
        nameField.setText(profile.getName());
        emailField.setText(profile.getEmail());
        contactNumberField.setText(profile.getContactNumber());
        emergencyContactField.setText(profile.getEmergencyContact());
        nidField.setText(profile.getNidOrBirthCertificate());
        professionField.setText(profile.getProfession());
        monthlyIncomeField.setText(profile.getMonthlyIncome());
        passwordField.setText(profile.getPassword());
    }

    /**
     * Update the EditText fields when click the save button
     *
     * @param profile The profile data to be displayed in the EditText fields.
     */
    private void updateProfileFromFields(ResidentProfileModel profile) {
        profile.setName(nameField.getText().toString().trim());
        profile.setEmail(emailField.getText().toString().trim());
        profile.setContactNumber(contactNumberField.getText().toString().trim());
        profile.setEmergencyContact(emergencyContactField.getText().toString().trim());
        profile.setNidOrBirthCertificate(nidField.getText().toString().trim());
        profile.setProfession(professionField.getText().toString().trim());
        profile.setMonthlyIncome(monthlyIncomeField.getText().toString().trim());
        profile.setPassword(passwordField.getText().toString().trim());
    }

    /**
     * Enables or disables editing of profile information.
     *
     * @param enable True to enable editing, false to disable.
     */
    private void enableEditing(boolean enable) {
        nameField.setEnabled(enable);
        emailField.setEnabled(enable);
        contactNumberField.setEnabled(enable);
        emergencyContactField.setEnabled(enable);
        nidField.setEnabled(enable);
        professionField.setEnabled(enable);
        monthlyIncomeField.setEnabled(enable);
        passwordField.setEnabled(enable);
    }
}