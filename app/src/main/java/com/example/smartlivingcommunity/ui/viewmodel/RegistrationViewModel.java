package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.ViewModel;
import com.example.smartlivingcommunity.data.model.RegistrationModel;
import com.example.smartlivingcommunity.data.repository.RegistrationRepository;
import com.example.smartlivingcommunity.utils.EmailUtils;

import java.util.Random;

public class RegistrationViewModel extends ViewModel {

    private final RegistrationRepository repository;

    public RegistrationViewModel() {
        repository = new RegistrationRepository();
    }

    private String generateUnitCode() {
        int randomNumber = 1000 + new Random().nextInt(9000);
        char randomLetter = (char) ('A' + new Random().nextInt(26));
        return "R-" + randomNumber + randomLetter;
    }

    public void registerUser(String name, String email, String contactNumber, String emergencyContact,
                             String idNumber, String profession, String monthlyIncome, String password) {
        String unitCode = generateUnitCode();
        RegistrationModel user = new RegistrationModel(name, email, contactNumber, emergencyContact, idNumber,
                profession, monthlyIncome, password, unitCode);

        repository.registerUser(user).addOnSuccessListener(aVoid -> {
            // Send email on successful registration
            EmailUtils.sendEmail(email, "Registration Successful", "Your unit code: " + unitCode);
        }).addOnFailureListener(e -> {
            // Handle registration failure
        });
    }
}
