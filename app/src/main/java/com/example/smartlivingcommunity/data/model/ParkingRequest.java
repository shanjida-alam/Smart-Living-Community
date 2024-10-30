package com.example.smartlivingcommunity.data.model;

public class ParkingRequest {
    private String residentId;
    private String slotId;
    private String slotStatus;

    public ParkingRequest(String residentId, String slotId, String slotStatus) {
        this.residentId = residentId;
        this.slotId = slotId;
        this.slotStatus = slotStatus;
    }

    public String getResidentId() { return residentId; }
    public String getSlotId() { return slotId; }
    public String getSlotStatus() { return slotStatus; }
}
