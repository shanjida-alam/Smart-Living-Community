package com.example.smartlivingcommunity.ui.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.ui.view.content.MainActivity;
import com.example.smartlivingcommunity.ui.viewmodel.ResidentLoginViewModel;
import com.example.smartlivingcommunity.utils.Resource;

import com.example.smartlivingcommunity.ui.view.registration.RegistrationActivity;

/**
 * Activity responsible for handling user login.
 * <p>
 * This activity initializes login components and interacts with the LoginViewModel to
 * perform login actions and display results. Provides user feedback on the login status,
 * validates user input, and navigates to the MainActivity on successful login.
 * </p>
 */
public class LoginActivity extends AppCompatActivity {

    /** ViewModel that handles the login logic */
    private ResidentLoginViewModel viewModel;

    /** EditText for user email input */
    private EditText emailEditText;

    /** EditText for user password input */
    private EditText passwordEditText;

    /** Button to initiate login */
    private Button loginButton;

    /** ProgressBar to indicate loading status */
    private ProgressBar progressBar;

    /** Button to initiate account creation */
    private Button createAccountButton;

    /**
     * Called when the activity is first created. Sets up the view, initializes components,
     * and configures ViewModel observers and click listeners.
     *
     * @param savedInstanceState if non-null, this activity is being re-initialized
     *                           after previously being shut down
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        progressBar = findViewById(R.id.progressBar);
        createAccountButton = findViewById(R.id.createAccountButton);

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(ResidentLoginViewModel.class);

        // Set up observers
        viewModel.getLoginResult().observe(this, result -> {
            if (result.status == Resource.Status.LOADING) {
                progressBar.setVisibility(View.VISIBLE);
                loginButton.setEnabled(false);
            } else {
                progressBar.setVisibility(View.GONE);
                loginButton.setEnabled(true);

                if (result.status == Resource.Status.SUCCESS) {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                } else if (result.status == Resource.Status.ERROR) {
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set up click listener
        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (validateInput(email, password)) {
                viewModel.login(email, password);
            }
        });

        //Set up click listener for create account button
        createAccountButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this,RegistrationActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Validates the email and password inputs.
     * <p>
     * Checks if the email and password fields are empty and sets error messages if necessary.
     * </p>
     *
     * @param email    the user's email address
     * @param password the user's password
     * @return {@code true} if input is valid, {@code false} otherwise
     */
    private boolean validateInput(String email, String password) {
        if (email.isEmpty()) {
            emailEditText.setError("Email is required");
            return false;
        }
        if (password.isEmpty()) {
            passwordEditText.setError("Password is required");
            return false;
        }
        return true;
    }
}
