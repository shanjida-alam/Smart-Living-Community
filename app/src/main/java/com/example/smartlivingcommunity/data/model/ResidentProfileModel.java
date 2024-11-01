package com.example.smartlivingcommunity.data.model;

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

    //No-argument constructor, required for firebase
    public ResidentProfileModel() {}

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

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getNidOrBirthCertificate() {
        return nidOrBirthCertificate;
    }

    public void setNidOrBirthCertificate(String nidOrBirthCertificate) {
        this.nidOrBirthCertificate = nidOrBirthCertificate;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(String monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
