package com.example.smartlivingcommunity.data.model;

public class ParkingSlot {
    private String slotID;
    private String status;

    public ParkingSlot(String slotID, String status) {
        this.slotID = slotID;
        this.status = status;
    }

    public String getSlotID() {
        return slotID;
    }

    public String getStatus() {
        return status;
    }
}
