package com.example.smartlivingcommunity.data.model;

/**
 * Represents a parking request within the Smart Living Community.
 * Each parking request includes the resident's ID, the slot ID, and the slot status.
 *
 * @author Saon
 */
public class ParkingRequest {
    private String residentId;
    private String slotId;
    private String slotStatus;

    /**
     * Constructs a new {@code ParkingRequest} with specified resident ID, slot ID, and slot status.
     *
     * @param residentId the unique identifier for the resident making the request
     * @param slotId     the identifier for the parking slot requested
     * @param slotStatus the current status of the parking slot
     */
    public ParkingRequest(String residentId, String slotId, String slotStatus) {
        this.residentId = residentId;
        this.slotId = slotId;
        this.slotStatus = slotStatus;
    }

    /**
     * Gets the resident's ID associated with this parking request.
     *
     * @return the resident ID
     */
    public String getResidentId() {
        return residentId;
    }

    /**
     * Gets the ID of the requested parking slot.
     *
     * @return the slot ID
     */
    public String getSlotId() {
        return slotId;
    }

    /**
     * Gets the current status of the parking slot.
     *
     * @return the slot status
     */
    public String getSlotStatus() {
        return slotStatus;
    }
}
