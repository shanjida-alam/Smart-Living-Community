package com.example.smartlivingcommunity.ui.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartlivingcommunity.data.model.RegistrationModel;
import com.example.smartlivingcommunity.ui.viewmodel.RegistrationViewModel;
import com.example.smartlivingcommunity.utils.EmailSenderUtils;
import com.example.smartlivingcommunity.utils.ValidationUtils;
import com.example.smartlivingcommunity.R;

import java.util.Random;
import java.security.SecureRandom;

/**
 * Activity for user registration where all form fields are validated
 * and data is stored in Firebase Firestore upon successful registration.
 *
 * @author Hasneen Tamanna Totinee
 * @version 1.0
 */
public class RegistrationView extends AppCompatActivity {

    // EditText fields for each input
    private EditText nameEditText, emailEditText, contactEditText, emergencyEditText,
            nidEditText, professionEditText, incomeEditText, passwordEditText, confirmPasswordEditText;
    private Button submitButton;

    // ViewModel instance
    private RegistrationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Initialize ViewModel and UI components
        viewModel = new RegistrationViewModel();
        initViews();

        // Set listener for the submit button
        submitButton.setOnClickListener(view -> {
            if (validateInputs()) {
                // Generate a residents' unit code starting with R-
                String unitCode = "R-" + generateRandomCode();

                // Create a new RegistrationModel instance with the user's inputs
                RegistrationModel model = new RegistrationModel(
                        nameEditText.getText().toString(),
                        emailEditText.getText().toString(),
                        contactEditText.getText().toString(),
                        emergencyEditText.getText().toString(),
                        nidEditText.getText().toString(),
                        professionEditText.getText().toString(),
                        incomeEditText.getText().toString(),
                        passwordEditText.getText().toString(),
                        null, // Image URL placeholder, can be updated with image upload functionality
                        unitCode
                );
// Call ViewModel to handle registration and Firebase Firestore storage
                viewModel.registerResident(model,
                        () -> {
                            // On success, display a success message
                            Toast.makeText(this, "Registration successful! Check your E-mail ", Toast.LENGTH_SHORT).show();
                            // Code to send unitcode to email
                            EmailSenderUtils.sendEmail(model.getEmail(), unitCode, this);
                        },
                        () -> {
                            // On failure, display an error message
                            Toast.makeText(this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
                        });
            }
        });
    }

    /**
     * Initializes EditText and Button views in the registration form layout.
     */
    private void initViews() {
        nameEditText = findViewById(R.id.name_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);
        contactEditText = findViewById(R.id.contact_edit_text);
        emergencyEditText = findViewById(R.id.emergency_contact_edit_text);
        nidEditText = findViewById(R.id.nid_edit_text);
        professionEditText = findViewById(R.id.profession_edit_text);
        incomeEditText = findViewById(R.id.income_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        confirmPasswordEditText = findViewById(R.id.confirm_password_edit_text);
        submitButton = findViewById(R.id.submit_button);
    }

    /**
     * Validates all input fields in the registration form.
     *
     * @return true if all inputs are valid; false otherwise
     */
    private boolean validateInputs() {
        // Validate name field
        if (nameEditText.getText().toString().isEmpty()) {
            nameEditText.setError("Name is required");
            return false;
        }
// Validate email format
        if (!ValidationUtils.isValidEmail(emailEditText.getText().toString())) {
            emailEditText.setError("Invalid email format");
            return false;
        }

        // Validate contact number length (11 digits)
        if (!ValidationUtils.isValidPhoneNumber(contactEditText.getText().toString())) {
            contactEditText.setError("Contact number must be 11 digits");
            return false;
        }

        // Validate emergency contact number length (11 digits)
        if (!ValidationUtils.isValidPhoneNumber(emergencyEditText.getText().toString())) {
            emergencyEditText.setError("Emergency contact number must be 11 digits");
            return false;
        }

        // Validate NID or Birth Certificate number length (13 digits)
        if (nidEditText.getText().toString().length() != 13) {
            nidEditText.setError("NID/Birth Certificate number must be 13 digits");
            return false;
        }

        // Validate monthly income field
        if (incomeEditText.getText().toString().isEmpty()) {
            incomeEditText.setError("Monthly income is required");
            return false;
        }

        // Validate password strength
        if (!ValidationUtils.isValidPassword(passwordEditText.getText().toString())) {
            passwordEditText.setError("Password must be 8 characters, including uppercase, lowercase, number, and special character");
            return false;
        }

        // Validate confirm password match
        if (!passwordEditText.getText().toString().equals(confirmPasswordEditText.getText().toString())) {
            confirmPasswordEditText.setError("Passwords do not match");
            return false;
        }

        // All validations passed
        return true;
    }
    /**
     * Generates a random alphanumeric code for resident registration.
     *
     * @return String - Random 4-character alphanumeric code
     */
    public String generateRandomCode() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // Letters
        String numbers = "0123456789"; // Numbers
        StringBuilder code = new StringBuilder();

        // To ensure at least one letter and one number are included
        SecureRandom random = new SecureRandom();

        // To add one random letter
        char randomLetter = letters.charAt(random.nextInt(letters.length()));
        code.append(randomLetter);

        // To add one random number
        char randomNumber = numbers.charAt(random.nextInt(numbers.length()));
        code.append(randomNumber);

        // To generate the rest of the code (adjust the length as needed)
        String combinedChars = letters + numbers;
        for (int i = 0; i < 2; i++) { // Generates 2 more characters
            char randomChar = combinedChars.charAt(random.nextInt(combinedChars.length()));
            code.append(randomChar);
        }

        return code.toString(); // Prefix with "R-"
    }
}
