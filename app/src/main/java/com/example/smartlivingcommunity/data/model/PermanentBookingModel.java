package com.example.smartlivingcommunity.data.model;

public class PermanentBookingModel {
    private String bookingId;
    private String residentId;
    private String residentName;
    private String vehicle;
    private String startDate;
    private String endDate;
    private String status;

    public PermanentBookingModel(String bookingId, String residentId, String residentName, String vehicle, String startDate, String endDate, String status) {
        this.bookingId = bookingId;
        this.residentId = residentId;
        this.residentName = residentName;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    // Getters and setters

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getResidentId() {
        return residentId;
    }

    public void setResidentId(String residentId) {
        this.residentId = residentId;
    }

    public String getResidentName() {
        return residentName;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}