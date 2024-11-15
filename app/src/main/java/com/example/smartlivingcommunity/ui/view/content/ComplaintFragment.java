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
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.ComplaintModel;
import com.example.smartlivingcommunity.data.repository.ComplaintRepositoryImplementation;
import com.example.smartlivingcommunity.ui.viewmodel.ComplaintViewModel;

/**
 * Fragment class for complaint submission functionality.
 * <p>
 * This fragment provides a user interface for submitting complaints. It includes fields for
 * inputting complaint details and a button to submit the complaint. It uses a ViewModel
 * to handle the business logic and interact with the repository.
 */
public class ComplaintFragment extends Fragment {

    // Input fields for complaint details
    /**
     * Input fields for user Unit Code
     */
    private EditText etUnitCode;     // Unit Code field
    /**
     * Input fields for user Name
     */
    private EditText etUserName;     // User Name field
    /**
     * Input fields for user Phone Number
     */
    private EditText etPhone;        // Phone Number field
    /**
     * Input fields for user Email Address
     */
    private EditText etEmail;        // Email Address field
    /**
     * Input fields for user Complaint Description
     */
    private EditText etDescription;  // Complaint Description field
    /**
     * Input fields for user Role
     */
    private EditText etUserRole;     // User Role field
    /**
     * Submit Button
     */
    private Button btnSubmit;        // Submit Button

    // ViewModel for complaint operations
    private ComplaintViewModel viewModel;

    /**
     * Called to initialize the fragment's state.
     * <p>
     * Sets up the ViewModel using a custom repository for dependency injection.
     *
     * @param savedInstanceState The previously saved instance state, if available.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ComplaintRepositoryImplementation repository = new ComplaintRepositoryImplementation();
        viewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @Override
            public <T extends ViewModel> T create(Class<T> modelClass) {
                return (T) new ComplaintViewModel(repository);
            }
        }).get(ComplaintViewModel.class);
    }

    /**
     * Inflates the fragment's layout.
     *
     * @param inflater  The LayoutInflater object for inflating the layout.
     * @param container The parent view that the fragment's UI should attach to.
     * @param savedInstanceState The previously saved instance state, if available.
     * @return The View representing the fragment's UI.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_complaint, container, false);
    }

    /**
     * Called after the fragment's view has been created.
     * <p>
     * Initializes the UI components and sets up the submit button functionality.
     *
     * @param view The root view of the fragment.
     * @param savedInstanceState The previously saved instance state, if available.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        setupSubmitButton();
    }

    /**
     * Initializes the input fields and button in the fragment's layout.
     *
     * @param view The root view of the fragment.
     */
    private void initializeViews(View view) {
        etUnitCode = view.findViewById(R.id.etUnitCode);       // Unit Code field
        etUserName = view.findViewById(R.id.etUserName);       // User Name field
        etPhone = view.findViewById(R.id.etPhone);             // Phone Number field
        etEmail = view.findViewById(R.id.etEmail);             // Email Address field
        etDescription = view.findViewById(R.id.etDescription); // Complaint Description field
        etUserRole = view.findViewById(R.id.etUserRole);       // User Role field
        btnSubmit = view.findViewById(R.id.btnSubmit);         // Submit Button
    }

    /**
     * Sets up the submit button's click functionality.
     * <p>
     * Collects complaint details, validates the inputs, and submits the complaint through the ViewModel.
     */
    private void setupSubmitButton() {
        btnSubmit.setOnClickListener(v -> {
            ComplaintModel complaint = collectComplaintDetails();
            if (complaint != null) {
                viewModel.submitComplaint(complaint).observe(getViewLifecycleOwner(), isSuccess -> {
                    String message = isSuccess ? "Complaint Submitted Successfully" :
                            "Submission failed. Please verify your unit code and email.";
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
                    if (isSuccess) clearForm();
                });
            }
        });
    }

    /**
     * Collects complaint details entered by the user.
     *
     * @return A {@link ComplaintModel} containing the collected details, or null if validation fails.
     */
    private ComplaintModel collectComplaintDetails() {
        String unitCode = etUnitCode.getText().toString().trim();
        String userName = etUserName.getText().toString().trim();
        String userRole = etUserRole.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        // Validate input fields
        if (unitCode.isEmpty() || userName.isEmpty() || userRole.isEmpty() ||
                phone.isEmpty() || email.isEmpty() || description.isEmpty()) {
            Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show();
            return null;
        }

        // Validate phone number format
        if (!phone.matches("\\d{11}")) {
            Toast.makeText(requireContext(), "Phone number must be 11 digits", Toast.LENGTH_SHORT).show();
            return null;
        }

        // Create and return the ComplaintModel
        return new ComplaintModel(unitCode, userName, userRole, phone, email, description);
    }

    /**
     * Clears the input fields in the form after successful submission.
     */
    private void clearForm() {
        etUnitCode.setText("");       // Clear Unit Code field
        etUserName.setText("");       // Clear User Name field
        etPhone.setText("");          // Clear Phone Number field
        etEmail.setText("");          // Clear Email Address field
        etDescription.setText("");    // Clear Complaint Description field
        etUserRole.setText("");       // Clear User Role field
    }
}
