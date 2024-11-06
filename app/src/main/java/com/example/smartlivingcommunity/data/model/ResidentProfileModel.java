package com.example.smartlivingcommunity.data.model;

/**
 * Model class for Resident Profile
 *
 * @author Shanjida Alam
 * @version 1.0
 */
public class ResidentProfileModel {
    /**
     * The user's name
     */
    private String name;
    /**
     * The user's email address
     */
    private String email;
    /**
     * The user's contact number
     */
    private String contactNumber;
    /**
     * The user's emergency contact number
     */
    private String emergencyContact;
    /**
     * The user's National ID or Birth Certificate number
     */
    private String nidOrBirthCertificate;
    /**
     * The user's profession
     */
    private String profession;
    /**
     * The user's monthly income
     */
    private String monthlyIncome;
    /**
     * The user's password
     */
    private String password;
    /**
     * The URL of the user's profile image
     */
    private String imageUrl;
    /**
     * The user's unit code
     */
    private String unitCode;
    /**
     * The user's role
     */
    private String userRole;
    /**
     * The user's additional unit code for multiple rules
     */
    private String additionalUnitCode;

    /**
     * No-argument constructor for ResidentProfileModel.
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
     * @param unitCode the unit code of the user
     * @param userRole the role of the user
     * @param additionalUnitCode the additional unit code of the user
     */
    public ResidentProfileModel(String name, String email, String contactNumber, String emergencyContact,
                                String nidOrBirthCertificate, String profession, String monthlyIncome,
                                String password, String imageUrl, String unitCode, String userRole,
                                String additionalUnitCode) {
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.emergencyContact = emergencyContact;
        this.nidOrBirthCertificate = nidOrBirthCertificate;
        this.profession = profession;
        this.monthlyIncome = monthlyIncome;
        this.password = password;
        this.imageUrl = imageUrl;
        this.unitCode = unitCode;
        this.userRole = userRole;
        this.additionalUnitCode = additionalUnitCode;
    }

    /**
     * Getter method for returning the name of the user.
     *
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the user
     *
     * @param name the name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for returning the email address of the user.
     *
     * @return the email address of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email address of the user
     *
     * @param email the email address of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter method for returning the contact number of the user.
     *
     * @return the contact number of the user
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Set the contact number of the user
     *
     * @param contactNumber the contact number of the user
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * Getter method for returning the emergency contact number of the user.
     *
     * @return the emergency contact number of the user
     */
    public String getEmergencyContact() {
        return emergencyContact;
    }

    /**
     * Set the emergency contact number of the user
     *
     * @param emergencyContact the emergency contact number of the user
     */
    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    /**
     * Getter method for returning the National ID or Birth Certificate number of the user.
     *
     * @return the National ID or Birth Certificate number of the user
     */
    public String getNidOrBirthCertificate() {
        return nidOrBirthCertificate;
    }

    /**
     * Set the National ID or Birth Certificate number of the user
     *
     * @param nidOrBirthCertificate the National ID or Birth Certificate number of the user
     */
    public void setNidOrBirthCertificate(String nidOrBirthCertificate) {
        this.nidOrBirthCertificate = nidOrBirthCertificate;
    }

    /**
     * Getter method for returning the profession of the user.
     *
     * @return the profession of the user
     */
    public String getProfession() {
        return profession;
    }

    /**
     * Set the profession of the user
     *
     * @param profession the profession of the user
     */
    public void setProfession(String profession) {
        this.profession = profession;
    }

    /**
     * Getter method for returning the monthly income of the user.
     *
     * @return the monthly income of the user
     */
    public String getMonthlyIncome() {
        return monthlyIncome;
    }

    /**
     * Set the monthly income of the user
     *
     * @param monthlyIncome the monthly income of the user
     */
    public void setMonthlyIncome(String monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    /**
     * Getter method for returning the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter method for returning the URL of the user's profile image.
     *
     * @return the URL of the user's profile image
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Set the URL of the user's profile image
     *
     * @param imageUrl the URL of the user's profile image
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Getter method for returning the unit code of the user.
     *
     * @return the unit code of the user
     */
    public String getUnitCode() {
        return unitCode;
    }

    /**
     * Set the unit code of the user
     *
     * @param unitCode the unit code of the user
     */
    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    /**
     * Getter method for returning the role of the user.
     *
     * @return the role of the user
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * Set the role of the user
     *
     * @param userRole the role of the user
     */
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    /**
     * Getter method for returning the additionalUnitcode of the user
     *
     * @return the additionalUnitCode of the user
     */
    public String getAdditionalUnitCode() {
        return additionalUnitCode;
    }

    /**
     * Set the additionalUnitCode of the user
     *
     * @param additionalUnitCode of the user
     */
    public void setAdditionalUnitCode(String additionalUnitCode) {
        this.additionalUnitCode = additionalUnitCode;
    }
}