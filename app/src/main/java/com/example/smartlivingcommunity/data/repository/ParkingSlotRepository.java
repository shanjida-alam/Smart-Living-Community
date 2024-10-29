package com.example.smartlivingcommunity.data.repository;
import com.example.smartlivingcommunity.data.model.ParkingSlot;
import java.util.ArrayList;
import java.util.List;

public class ParkingSlotRepository {
    public List<ParkingSlot> getParkingSlots() {
        List<ParkingSlot> parkingSlots = new ArrayList<>();
        parkingSlots.add(new ParkingSlot("Slot 1", "Available"));
        parkingSlots.add(new ParkingSlot("Slot 2", "Occupied"));
        parkingSlots.add(new ParkingSlot("Slot 3", "Available"));
        return parkingSlots;
    }
}
