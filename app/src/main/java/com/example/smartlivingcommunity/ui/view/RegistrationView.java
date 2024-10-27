package com.example.smartlivingcommunity.ui.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.ui.viewmodel.RegistrationViewModel;

public class RegistrationView extends AppCompatActivity {

    private RegistrationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        viewModel = new RegistrationViewModel();

        EditText nameField = findViewById(R.id.name);
        EditText emailField = findViewById(R.id.email);
        EditText contactField = findViewById(R.id.contactNumber);
        EditText emergencyContactField = findViewById(R.id.emergencyContact);
        EditText idNumberField = findViewById(R.id.idNumber);
        EditText professionField = findViewById(R.id.profession);
        EditText monthlyIncomeField = findViewById(R.id.monthlyIncome);
        EditText passwordField = findViewById(R.id.password);
        EditText confirmPasswordField = findViewById(R.id.confirmPassword);
        Button registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(view -> {
            String name = nameField.getText().toString();
            String email = emailField.getText().toString();
            String contactNumber = contactField.getText().toString();
            String emergencyContact = emergencyContactField.getText().toString();
            String idNumber = idNumberField.getText().toString();
            String profession = professionField.getText().toString();
            String monthlyIncome = monthlyIncomeField.getText().toString();
            String password = passwordField.getText().toString();
            String confirmPassword = confirmPasswordField.getText().toString();

            if (password.equals(confirmPassword)) {
                viewModel.registerUser(name, email, contactNumber, emergencyContact, idNumber, profession, monthlyIncome, password);
                Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
