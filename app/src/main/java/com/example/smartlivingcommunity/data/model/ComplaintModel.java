package com.example.smartlivingcommunity.data.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class ComplaintModel {
    private String complaintId;
    private String unitCode;
    private String userName;
    private String userRole;
    private String phoneNumber;
    private String emailAddress;
    private String complaintDescription;

    public ComplaintModel() {
        this.complaintId = generateUniqueId();
    }

    public ComplaintModel(String unitCode, String userName, String userRole,
                          String phoneNumber, String emailAddress, String complaintDescription) {
        this.complaintId = generateUniqueId();
        this.unitCode = unitCode;
        this.userName = userName;
        this.userRole = userRole;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.complaintDescription = complaintDescription;
    }

    private String generateUniqueId() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault());
        String timestamp = dateFormat.format(new Date());
        String uniqueSuffix = UUID.randomUUID().toString().substring(0, 4);
        return "COMP" + timestamp + "_" + uniqueSuffix;
    }

    public String getComplaintId() {
        return complaintId;
    }

    // Add back the setComplaintId method for Firebase
    public void setComplaintId(String complaintId) {
        this.complaintId = complaintId;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getComplaintDescription() {
        return complaintDescription;
    }

    // Setters
    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setComplaintDescription(String complaintDescription) {
        this.complaintDescription = complaintDescription;
    }
}