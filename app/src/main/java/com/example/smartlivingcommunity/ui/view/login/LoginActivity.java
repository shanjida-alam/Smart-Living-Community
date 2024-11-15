package com.example.smartlivingcommunity.ui.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.ui.view.content.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        // Initialize views
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        progressBar = findViewById(R.id.progressBar);

        // Check if user is already signed in
        if (firebaseAuth.getCurrentUser() != null) {
            startMainActivity();
            return;
        }

        loginButton.setOnClickListener(v -> handleLogin());
    }

    private void handleLogin() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (validateInput(email, password)) {
            progressBar.setVisibility(View.VISIBLE);
            loginButton.setEnabled(false);

            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        progressBar.setVisibility(View.GONE);
                        loginButton.setEnabled(true);

                        if (task.isSuccessful()) {
                            startMainActivity();
                        } else {
                            Toast.makeText(LoginActivity.this,
                                    "Authentication failed: " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void startMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

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