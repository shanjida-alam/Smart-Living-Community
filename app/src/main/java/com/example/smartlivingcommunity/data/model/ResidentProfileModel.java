package com.example.smartlivingcommunity.data.model;

/**
 * Model class for Resident Profile
 *
 * @author Shanjida Alam
 * @version 1.0
 */
public class ResidentProfileModel {
    private String name;
    private String email;
    private String contactNumber;
    private String emergencyContact;
    private String nidOrBirthCertificate;
    private String profession;
    private String monthlyIncome;
    private String password;
    private String imageUrl;

    /**
     * No-argument constructor for ResidentProfileModel.
     *
     */
    public ResidentProfileModel() {}

    /**
     * Constructor for ResidentProfileModel.
     *
     * @param name the name of the user
     * @param email the email address of the user
     * @param contactNumber the contact number of the user
     * @param emergencyContact the emergency contact number of the user
     * @param nidOrBirthCertificate the National ID or Birth Certificate number of the user
     * @param profession the profession of the user
     * @param monthlyIncome the monthly income of the user
     * @param password the password of the user
     * @param imageUrl the URL of the user's profile image
     */
    public ResidentProfileModel(String name, String email, String contactNumber, String emergencyContact,
                                String nidOrBirthCertificate, String profession, String monthlyIncome,
                                String password, String imageUrl) {
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.emergencyContact = emergencyContact;
        this.nidOrBirthCertificate = nidOrBirthCertificate;
        this.profession = profession;
        this.monthlyIncome = monthlyIncome;
        this.password = password;
        this.imageUrl = imageUrl;
    }

    /**
     * Getter method for the name of the user.
     *
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for the name of the user.
     *
     * @param name the new name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for the email address of the user.
     *
     * @return the email address of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method for the email address of the user.
     *
     * @param email the new email address of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter method for the contact number of the user.
     *
     * @return the contact number of the user
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Setter method for the contact number of the user.
     *
     * @param contactNumber the new contact number of the user
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * Getter method for the emergency contact number of the user.
     *
     * @return the emergency contact number of the user
     */
    public String getEmergencyContact() {
        return emergencyContact;
    }

    /**
     * Setter method for the emergency contact number of the user.
     *
     * @param emergencyContact the new emergency contact number of the user
     */
    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    /**
     * Getter method for the National ID or Birth Certificate number of the user.
     *
     * @return the National ID or Birth Certificate number of the user
     */
    public String getNidOrBirthCertificate() {
        return nidOrBirthCertificate;
    }

    /**
     * Setter method for the National ID or Birth Certificate number of the user.
     *
     * @param nidOrBirthCertificate the new National ID or Birth Certificate number of the user
     */
    public void setNidOrBirthCertificate(String nidOrBirthCertificate) {
        this.nidOrBirthCertificate = nidOrBirthCertificate;
    }

    /**
     * Getter method for the profession of the user.
     *
     * @return the profession of the user
     */
    public String getProfession() {
        return profession;
    }

    /**
     * Setter method for the profession of the user.
     *
     * @param profession the new profession of the user
     */
    public void setProfession(String profession) {
        this.profession = profession;
    }

    /**
     * Getter method for the monthly income of the user.
     *
     * @return the monthly income of the user
     */
    public String getMonthlyIncome() {
        return monthlyIncome;
    }

    /**
     * Setter method for the monthly income of the user.
     *
     * @param monthlyIncome the new monthly income of the user
     */
    public void setMonthlyIncome(String monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    /**
     * Getter method for the password of the user
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method for the password of the user
     *
     * @param password the new password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter method for the URL of the user's profile image.
     *
     * @return the URL of the user's profile image
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Setter method for the URL of the user's profile image.
     *
     * @param imageUrl the new URL of the user's profile image
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}