package com.example.smartlivingcommunity.data.model;

/**
 * Represents a parking slot within the Smart Living Community resident dashboard system,
 * detailing the slot ID, current status, and associated vehicle ID.
 *
 * @author Saon
 */
public class ResidentParkingSlot {
    private String slotID;
    private String status;
    private String vehicleID;
    /**
     * Constructs a new {@code ResidentParkingSlot} with specified details including slot ID, status, and vehicle ID.
     *
     * @param slotID    the unique identifier for the parking slot
     * @param status    the current status of the parking slot (e.g., available, occupied)
     * @param vehicleID the ID of the vehicle occupying the slot, or {@code null} if the slot is vacant
     */
    public ResidentParkingSlot(String slotID, String status, String vehicleID) {
        this.slotID = slotID;
        this.status = status;
        this.vehicleID = vehicleID;
    }

    /**
     * Returns the unique identifier for this parking slot.
     *
     * @return the slot ID
     */
    public String getSlotID() {
        return slotID;
    }

    /**
     * Returns the current status of this parking slot.
     *
     * @return the parking slot status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns the vehicle ID associated with this parking slot, if any.
     *
     * @return the vehicle ID, or {@code null} if no vehicle is occupying the slot
     */
    public String getVehicleID() {
        return vehicleID;
    }
}
