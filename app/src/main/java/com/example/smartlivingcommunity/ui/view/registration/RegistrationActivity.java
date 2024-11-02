package com.example.smartlivingcommunity.ui.view.registration;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.smartlivingcommunity.data.model.RegistrationModel;
import com.example.smartlivingcommunity.ui.viewmodel.RegistrationViewModel;
import com.example.smartlivingcommunity.utils.EmailSenderUtils;
import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.utils.ImageUploadUtils;
import com.example.smartlivingcommunity.ui.view.login.LoginActivity;

import java.security.SecureRandom;

/**
 * The RegistrationView activity provides the UI and user interaction logic
 * for a registration form, allowing users to input and validate their information
 * and submit the data to Firebase Firestore.
 *
 * Upon successful registration, an email is sent to the user with their
 * generated unit code and registration details.
 *
 * @author Hasneen Tamanna Totinee
 * @version 1.0
 * @since 2024-10-30
 */
public class RegistrationActivity extends AppCompatActivity {

    // UI components for user input
    private EditText nameEditText, emailEditText, contactEditText, emergencyEditText,
            nidOrBirthCertEditText, professionEditText, incomeEditText, passwordEditText, confirmPasswordEditText;
    private Button submitButton, upload_image_button;
    private ImageView dropdownIcon, imageView;

    // URI for selected image
    private Uri imageUri;

    // Request code for starting the image picker activity
    private static final int PICK_IMAGE_REQUEST = 100;

    // Firebase Firestore ViewModel instance
    private RegistrationViewModel viewModel;

    // User-selected role (Resident, Manager, Admin)
    private String userRole;

    // Boolean flag for residency status of the user
    private boolean isResident;

    // Image upload launcher using Android's activity result API
    private ActivityResultLauncher<Intent> uploadImageLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Initialize ViewModel and UI components
        viewModel = new RegistrationViewModel();
        initViews();

        // Prompt the user to select a role (Resident, Manager, Admin)
        showRoleSelectionDialog();

        // Set listener for the submit button to validate inputs and register user
        submitButton.setOnClickListener(view -> {
            if (validateInputs()) {
                // Generate primary unit code based on the selected user role
                String primaryUnitCode = (userRole.equals("Resident") ? "R-" : userRole.equals("Manager") ? "M-" : "A-") + generateRandomCode();

                // Generate additional unit code for Manager or Admin with residency
                final String additionalUnitCode = (userRole.equals("Manager") || userRole.equals("Admin")) && isResident ? "R-" + generateRandomCode() : null;

                // Create a new RegistrationModel instance with the user's input data
                RegistrationModel model = new RegistrationModel(
                        nameEditText.getText().toString(),
                        emailEditText.getText().toString(),
                        contactEditText.getText().toString(),
                        emergencyEditText.getText().toString(),
                        nidOrBirthCertEditText.getText().toString(),
                        professionEditText.getText().toString(),
                        incomeEditText.getText().toString(),
                        passwordEditText.getText().toString(),
                        imageUri.toString(),
                        primaryUnitCode,
                        userRole
                );

                // Register user in Firestore and handle success or failure feedback
                viewModel.registerResident(model, additionalUnitCode,
                        () -> {
                            Toast.makeText(this, "Registration successful! Check your email.", Toast.LENGTH_SHORT).show();

                            String firstMessage = "<p> Congratulations " + model.getName() + ". </p>";

                            String unitCodeMessage;
                            if (additionalUnitCode != null) {
                                unitCodeMessage = "<p>Your unit code as " + userRole + " is: <span style='background-color: lightgray;'>" + primaryUnitCode + "</span><p>"
                                        + "Your unit code as Resident is: <span style='background-color: lightgray;'>" + additionalUnitCode + "</span></p>";
                            } else {
                                unitCodeMessage = "<p>Your unit code as " + userRole + " is: <span style='background-color: lightgray;'>" + primaryUnitCode + "</span></p>";
                            }

                            // Send confirmation email with unit codes
                            EmailSenderUtils.sendEmail(model.getEmail(), firstMessage, unitCodeMessage, this);

                            // Navigate back to LoginAcitvity
                            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        },
                        () -> Toast.makeText(this, "This email is already registered. Please use a different email.", Toast.LENGTH_SHORT).show(),
                        () -> Toast.makeText(this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show()
                );
            }
        });

        // Dropdown to select either NID or Birth Certificate
        final String[] options = getResources().getStringArray(R.array.nid_or_birthCertificate);
        dropdownIcon.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select an option")
                    .setItems(options, (dialog, which) -> {
                        String selectedOption = options[which];
                        nidOrBirthCertEditText.setHint(selectedOption.equals("NID") ? "NID number" : "Birth Certificate number");
                        nidOrBirthCertEditText.setText(""); // Clear previous input if necessary
                    })
                    .show();
        });

        // Launch image picker on button click
        upload_image_button.setOnClickListener(this::onUploadImageClick);

        // Register image upload activity result handler
        uploadImageLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        imageUri = result.getData().getData();
                        imageView.setImageURI(imageUri);  // Display selected image
                    }
                }
        );
    }

    /**
     * Opens the image picker for the user to select an image.
     *
     * @param view The "Upload Image" button
     */
    public void onUploadImageClick(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        uploadImageLauncher.launch(Intent.createChooser(intent, "Select Picture"));
    }

    /**
     * Callback for handling image selection result.
     *
     * @param requestCode Request code of the intent
     * @param resultCode Result code of the intent
     * @param data Intent data returned from image picker
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);  // Display selected image
        }
    }

    /**
     * Called after successful user data storage in Firestore to upload the image.
     *
     * @param userId ID of the registered user
     */
    public void onRegistrationSuccess(String userId) {
        if (imageUri != null) {
            ImageUploadUtils.uploadImageToFirebase(imageUri, userId,
                    () -> Toast.makeText(this, "Image uploaded successfully!", Toast.LENGTH_SHORT).show(),
                    () -> Toast.makeText(this, "Failed to upload image.", Toast.LENGTH_SHORT).show());
        } else {
            Toast.makeText(this, "No image selected.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Initializes all UI components from the layout resource.
     */
    private void initViews() {
        nameEditText = findViewById(R.id.name_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);
        contactEditText = findViewById(R.id.contact_edit_text);
        emergencyEditText = findViewById(R.id.emergency_contact_edit_text);
        nidOrBirthCertEditText = findViewById(R.id.nidOrBirthCertEditText);
        dropdownIcon = findViewById(R.id.dropdownIcon);
        professionEditText = findViewById(R.id.profession_edit_text);
        incomeEditText = findViewById(R.id.income_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        confirmPasswordEditText = findViewById(R.id.confirm_password_edit_text);
        submitButton = findViewById(R.id.submit_button);
        imageView = findViewById(R.id.imageView);
        upload_image_button = findViewById(R.id.upload_image_button);
    }

    /**
     * Validates user input fields and returns true if all inputs are valid.
     *
     * @return true if all inputs are valid; false otherwise
     */
    private boolean validateInputs() {
        if (nameEditText.getText().toString().isEmpty()) {
            nameEditText.setError("Name is required");
            return false;
        }

        String email = emailEditText.getText().toString();
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            emailEditText.setError("Invalid email format");
            return false;
        }

        String contactNumber = contactEditText.getText().toString();
        if (contactNumber.length() != 11) {
            contactEditText.setError("Contact number must be 11 digits");
            return false;
        }

        String emergencyContact = emergencyEditText.getText().toString();
        if (emergencyContact.length() != 11) {
            emergencyEditText.setError("Emergency contact number must be 11 digits");
            return false;
        }

        String nidOrBirthCert = nidOrBirthCertEditText.getText().toString();
        if (nidOrBirthCert.length() != 13 || !nidOrBirthCert.matches("\\d+")) {
            nidOrBirthCertEditText.setError("NID/Birth Certificate number must be 13 digits and contain only numbers");
            return false;
        }

        if ("Resident".equals(userRole) && incomeEditText.getText().toString().isEmpty()) {
            incomeEditText.setError("Monthly income is required");
            return false;
        }

        String password = passwordEditText.getText().toString();
        if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\\$%^&*]).{8,}$")) {
            passwordEditText.setError("Password must be at least 8 characters, including uppercase, lowercase, number, and special character");
            return false;
        }

        String confirmPassword = confirmPasswordEditText.getText().toString();
        if (!password.equals(confirmPassword)) {
            confirmPasswordEditText.setError("Passwords do not match");
            return false;
        }

        return true;
    }

    /**
     * Displays a dialog for selecting the user's role in the system.
     */
    private void showRoleSelectionDialog() {
        String[] roles = {"Resident", "Manager", "Admin"};
        new AlertDialog.Builder(this)
                .setTitle("Select Your Role")
                .setItems(roles, (dialog, which) -> {
                    userRole = roles[which];
                    if (userRole.equals("Manager") || userRole.equals("Admin")) {
                        showResidentConfirmationDialog();
                        professionEditText.setVisibility(View.GONE);
                        incomeEditText.setVisibility(View.GONE);
                    } else {
                        professionEditText.setVisibility(View.VISIBLE);
                        incomeEditText.setVisibility(View.VISIBLE);
                    }
                })
                .setCancelable(false)
                .show();
    }

    /**
     * Displays a dialog to confirm whether the user is also a resident.
     */
    private void showResidentConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Are you also a Resident?")
                .setPositiveButton("Yes", (dialog, which) -> isResident = true)
                .setNegativeButton("No", (dialog, which) -> isResident = false)
                .setCancelable(false)
                .show();
    }

    /**
     * Generates a random 4-character alphanumeric code for user registration.
     *
     * @return a random alphanumeric string
     */
    public String generateRandomCode() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        SecureRandom random = new SecureRandom();

        char randomLetter = letters.charAt(random.nextInt(letters.length()));
        char randomNumber = numbers.charAt(random.nextInt(numbers.length()));
        char randomChar1 = (letters + numbers).charAt(random.nextInt(letters.length() + numbers.length()));
        char randomChar2 = (letters + numbers).charAt(random.nextInt(letters.length() + numbers.length()));

        return "" + randomLetter + randomNumber + randomChar1 + randomChar2;
    }
}

