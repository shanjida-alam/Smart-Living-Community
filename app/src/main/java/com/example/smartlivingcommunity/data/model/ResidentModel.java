package com.example.smartlivingcommunity.data.model;

public class ResidentModel {
    private String contactNumber;
    private String email;
    private String emergencyNumber;
    private String imageUrl;
    private String monthlyIncome;
    private String name;
    private String nidOrBirthCertificate;
    private String password;
    private String profession;
    private String unitCode;
    private String unitCodeAdditional;
    private String userRole;

    // Empty constructor for Firebase
    public ResidentModel() {}

    // Constructor with all fields
    public ResidentModel(String contactNumber, String email, String emergencyNumber,
                    String imageUrl, String monthlyIncome, String name,
                    String nidOrBirthCertificate, String password, String profession,
                    String unitCode, String unitCodeAdditional, String userRole) {
        this.contactNumber = contactNumber;
        this.email = email;
        this.emergencyNumber = emergencyNumber;
        this.imageUrl = imageUrl;
        this.monthlyIncome = monthlyIncome;
        this.name = name;
        this.nidOrBirthCertificate = nidOrBirthCertificate;
        this.password = password;
        this.profession = profession;
        this.unitCode = unitCode;
        this.unitCodeAdditional = unitCodeAdditional;
        this.userRole = userRole;
    }

    // Getters and Setters
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getEmergencyNumber() { return emergencyNumber; }
    public void setEmergencyNumber(String emergencyNumber) { this.emergencyNumber = emergencyNumber; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getMonthlyIncome() { return monthlyIncome; }
    public void setMonthlyIncome(String monthlyIncome) { this.monthlyIncome = monthlyIncome; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNidOrBirthCertificate() { return nidOrBirthCertificate; }
    public void setNidOrBirthCertificate(String nidOrBirthCertificate) { this.nidOrBirthCertificate = nidOrBirthCertificate; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getProfession() { return profession; }
    public void setProfession(String profession) { this.profession = profession; }

    public String getUnitCode() { return unitCode; }
    public void setUnitCode(String unitCode) { this.unitCode = unitCode; }

    public String getUnitCodeAdditional() { return unitCodeAdditional; }
    public void setUnitCodeAdditional(String unitCodeAdditional) { this.unitCodeAdditional = unitCodeAdditional; }

    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }
}