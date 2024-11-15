package com.example.smartlivingcommunity.data.model;

public class InstantBookingModel {
    private String bookingId;
    private String residentId;
    private String residentName;
    private String vehicle;
    private String date;
    private String timeSlot;
    private String status;

    public InstantBookingModel() {

    }
    public InstantBookingModel(String bookingId, String residentId, String residentName, String vehicle, String date, String timeSlot, String status) {
        this.bookingId = bookingId;
        this.residentId = residentId;
        this.residentName = residentName;
        this.vehicle = vehicle;
        this.date = date;
        this.timeSlot = timeSlot;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}