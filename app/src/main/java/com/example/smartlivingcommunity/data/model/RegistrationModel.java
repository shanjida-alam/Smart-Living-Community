package com.example.smartlivingcommunity.data.model;

/**
 * Data class representing a user's registration information.
 * Contains essential fields required for registration, such as contact details, identification,
 * and role in the community.
 *
 * @author Hasneen Tamanna Totinee
 * @version 1.0
 * @since 2024-10-30
 */
public class RegistrationModel {

    /**
     * User's full name.
     */
    private String name;

    /**
     * User's email address.
     */
    private String email;

    /**
     * User's primary contact number.
     */
    private String contactNumber;

    /**
     * User's emergency contact number.
     */
    private String emergencyContact;

    /**
     * User's's National ID or Birth Certificate number.
     */
    private String nidOrBirthCertificate;

    /**
     * User's's profession or occupation.
     */
    private String profession;

    /**
     * User's monthly income.
     */
    private String monthlyIncome;

    /**
     * User's chosen password for account security.
     */
    private String password;

    /**
     * URL of the User's profile image.
     */
    private String imageUrl;

    /**
     * Unique unit code assigned to the User.
     */
    private String unitCode;

    /**
     * Role of the user (e.g., Resident, Manager, Admin).
     */
    private String userRole;

    /**
     * No-argument constructor required for Firebase data model mapping.
     */
    public RegistrationModel() {}

    /**
     * Constructs a RegistrationModel with specified details.
     *
     * @param name User's full name
     * @param email User's email address
     * @param contactNumber User's contact number
     * @param emergencyContact User's emergency contact number
     * @param nidOrBirthCertificate User's ID (National ID or Birth Certificate)
     * @param profession User's profession or occupation
     * @param monthlyIncome User's monthly income
     * @param password User's chosen password
     * @param imageUrl URL of User's profile image
     * @param unitCode Unique unit code assigned to the User
     * @param userRole Role of the user in the community (e.g., Resident, Manager, Admin)
     */
    public RegistrationModel(String name, String email, String contactNumber, String emergencyContact,
                             String nidOrBirthCertificate, String profession, String monthlyIncome,
                             String password, String imageUrl, String unitCode, String userRole) {
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
    }

    /**
     * Gets the user's name.
     * @return User's full name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name.
     * @param name Full name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the user's email address.
     * @return User's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     * @param email User's email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's contact number.
     * @return User's primary contact number.
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Sets the user's contact number.
     * @param contactNumber Primary contact number for the user.
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * Gets the user's emergency contact number.
     * @return User's emergency contact number.
     */
    public String getEmergencyContact() {
        return emergencyContact;
    }

    /**
     * Sets the user's emergency contact number.
     * @param emergencyContact Emergency contact number for the user.
     */
    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    /**
     * Gets the user's national identification number.
     * @return User's National ID or Birth Certificate number.
     */
    public String getNidOrBirthCertificate() {
        return nidOrBirthCertificate;
    }

    /**
     * Sets the user's national identification number.
     * @param nidOrBirthCertificate User's National ID or Birth Certificate number.
     */
    public void setNidOrBirthCertificate(String nidOrBirthCertificate) {
        this.nidOrBirthCertificate = nidOrBirthCertificate;
    }

    /**
     * Gets the user's profession.
     * @return User's profession or occupation.
     */
    public String getProfession() {
        return profession;
    }

    /**
     * Sets the user's profession.
     * @param profession User's profession or occupation.
     */
    public void setProfession(String profession) {
        this.profession = profession;
    }

    /**
     * Gets the user's monthly income.
     * @return User's monthly income.
     */
    public String getMonthlyIncome() {
        return monthlyIncome;
    }

    /**
     * Sets the user's monthly income.
     * @param monthlyIncome User's monthly income.
     */
    public void setMonthlyIncome(String monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    /**
     * Gets the user's password.
     * @return User's account password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     * @param password User's chosen password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the URL of the user's profile image.
     * @return URL of the user's profile image.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the URL of the user's profile image.
     * @param imageUrl URL of the user's profile image.
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Gets the user's unique unit code.
     * @return Unique unit code.
     */
    public String getUnitCode() {
        return unitCode;
    }

    /**
     * Sets the user's unique unit code.
     * @param unitCode Unique unit code.
     */
    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    /**
     * Gets the user's role in the community.
     * @return User role (e.g., Resident, Manager, Admin).
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * Sets the user's role in the community.
     * @param userRole User role in the community.
     */
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}

