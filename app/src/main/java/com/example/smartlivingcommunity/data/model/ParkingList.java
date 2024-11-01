package com.example.smartlivingcommunity.data.model;

/**
 * Model class representing a parking slot.
 *
 * @author Saon
 */
public class ParkingList {
    private String slotID;
    private String vehicleID;
    private String status;
    private String residentID;

    /**
     * Constructs a ParkingList object with specified details.
     *
     * @param slotID     the ID of the parking slot
     * @param vehicleID  the ID of the vehicle parked
     * @param status     the status of the parking slot (e.g., available, occupied)
     * @param residentID the ID of the resident associated with the parking slot
     */
    public ParkingList(String slotID, String vehicleID, String status, String residentID) {
        this.slotID = slotID;
        this.vehicleID = vehicleID;
        this.status = status;
        this.residentID = residentID;
    }

    // Getters and Setters

    /**
     * Gets the slot ID.
     *
     * @return the slot ID
     */
    public String getSlotID() { return slotID; }
    public void setSlotID(String slotID) { this.slotID = slotID; }

    /**
     * Gets the vehicle ID.
     *
     * @return the vehicle ID
     */
    public String getVehicleID() { return vehicleID; }
    public void setVehicleID(String vehicleID) { this.vehicleID = vehicleID; }

    /**
     * Gets the status of the parking slot.
     *
     * @return the status of the parking slot
     */
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    /**
     * Gets the resident ID associated with the parking slot.
     *
     * @return the resident ID
     */
    public String getResidentID() { return residentID; }
    public void setResidentID(String residentID) { this.residentID = residentID; }
}
