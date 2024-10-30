package com.example.smartlivingcommunity.data.model;

/**
 * Represents a parking slot within the resident dashboard system, detailing the slot ID,
 * current status, and associated vehicle ID.
 * @author Saon
 */
public class ParkingSlot {

    /** Unique identifier for the parking slot */
    private String slotID;

    /** Current status of the parking slot (e.g., "occupied", "available") */
    private String status;

    /** ID of the vehicle occupying the slot, if any */
    private String vehicleID;

    /**
     * Constructs a new {@code ParkingSlot} with specified details including slot ID, status, and vehicle ID.
     *
     * @param slotID    the unique identifier for the parking slot
     * @param status    the current status of the parking slot
     * @param vehicleID the ID of the vehicle occupying the slot, if any
     */
    public ParkingSlot(String slotID, String status, String vehicleID) {
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
