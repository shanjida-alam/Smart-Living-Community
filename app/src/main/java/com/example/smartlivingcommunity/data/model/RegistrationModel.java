package com.example.smartlivingcommunity.data.model;

public class RegistrationModel {
    private String name;
    private String email;
    private String contactNumber;
    private String emergencyContact;
    private String idNumber;
    private String profession;
    private String monthlyIncome;
    private String password;
    private String unitCode;

    public RegistrationModel() {} // Required for Firebase

    public RegistrationModel(String name, String email, String contactNumber, String emergencyContact,
                             String idNumber, String profession, String monthlyIncome, String password, String unitCode) {
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.emergencyContact = emergencyContact;
        this.idNumber = idNumber;
        this.profession = profession;
        this.monthlyIncome = monthlyIncome;
        this.password = password;
        this.unitCode = unitCode;
    }

    // Getters and setters


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

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
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

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }
}
