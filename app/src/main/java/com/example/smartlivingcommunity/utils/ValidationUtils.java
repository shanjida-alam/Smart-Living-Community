package com.example.smartlivingcommunity.utils;

import java.util.regex.Pattern;

/**
 * Utility class for validating registration form fields.
 *
 * @author Hasneen Tamanna Totinee
 * @version 1.0
 */
public class ValidationUtils {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\\$%^&*]).{8,}$");

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPhoneNumber(String number) {
        return number.length() == 11;
    }

    public static boolean isValidNID(String nid) {
        return nid.length() == 13;
    }

    public static boolean isValidPassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }
}

