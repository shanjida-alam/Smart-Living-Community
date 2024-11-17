package com.example.smartlivingcommunity.data.model;

public class ParkingRegistrationModel {
    private String registrationId;
    private String residentName;
    private String residentId;
    private String vehicleType;    // Car, Motorcycle, etc.
    private String vehicleNumber;
    private String parkingStatus;  // PENDING, APPROVED, REJECTED
    private String requestDate;
    private String approvalDate;
    private String rejectionReason;

    // Add this no-argument constructor
    public ParkingRegistrationModel() {
        // Default constructor required for Firestore deserialization
    }

    public ParkingRegistrationModel(String registrationId, String residentName, String residentId, String vehicleType, String vehicleNumber, String parkingStatus, String requestDate, String approvalDate, String rejectionReason) {
        this.registrationId = registrationId;
        this.residentName = residentName;
        this.residentId = residentId;
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
        this.parkingStatus = parkingStatus;
        this.requestDate = requestDate;
        this.approvalDate = approvalDate;
        this.rejectionReason = rejectionReason;
    }

    public String getResidentName() {
        return residentName;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public String getResidentId() {
        return residentId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getParkingStatus() {
        return parkingStatus;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public void setResidentId(String residentId) {
        this.residentId = residentId;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public void setParkingStatus(String parkingStatus) {
        this.parkingStatus = parkingStatus;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }
}