package com.example.smartlivingcommunity.data.model;

/**
 * The {@code LoginModel} class represents a model for user login information.
 * It contains fields for the user's email and password and provides
 * constructors, getters, and setters for accessing and modifying these fields.
 */
public class ResidentLoginModel {

    /** The user's email address. */
    private String email;

    /** The user's password. */
    private String password;

    /**
     * Default constructor required for Firestore.
     */
    public ResidentLoginModel() {
        // Empty constructor required for Firestore
    }

    /**
     * Constructs a {@code LoginModel} for SmartLiv with the specified email and password.
     *
     * @param residentEmail the email address of the user
     * @param residentPassword the password of the user
     */
    public ResidentLoginModel(String residentEmail, String residentPassword) {
        this.email = residentEmail;
        this.password = residentPassword;
    }

    /**
     * Returns the email address of the Resident.
     *
     * @return the email address of the Resident
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the Resident.
     *
     * @param residentEmail the new email address for the user
     */
    public void setEmail(String residentEmail) {
        this.email = residentEmail;
    }

    /**
     * Returns the password of the Resident
     *
     * @return the password of the Resident
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the Resident
     *
     * @param residentPassword the new password for the Resident
     *
     */
    public void setPassword(String residentPassword) {
        this.password = residentPassword;
    }
}
