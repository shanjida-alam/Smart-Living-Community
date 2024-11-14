package com.example.smartlivingcommunity.data.model;

public class ComplaintModel {
    private String complaintId;
    private String unitCode;
    private String userName;
    private String userRole;
    private String phoneNumber;

    private String emailAddress;
    private String complaintDescription;

    public ComplaintModel() {}

    public ComplaintModel(String complaintId, String unitCode, String userName, String userRole, String phoneNumber, String emailAddress, String complaintDescription) {
        this.complaintId = complaintId;
        this.unitCode = unitCode;
        this.userName = userName;
        this.userRole = userRole;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.complaintDescription = complaintDescription;
    }

    public String getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(String complaintId) {
        this.complaintId = complaintId;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getComplaintDescription() {
        return complaintDescription;
    }

    public void setComplaintDescription(String complaintDescription) {
        this.complaintDescription = complaintDescription;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
