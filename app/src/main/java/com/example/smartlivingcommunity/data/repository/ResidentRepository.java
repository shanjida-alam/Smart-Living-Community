package com.example.smartlivingcommunity.data.repository;

import com.example.smartlivingcommunity.data.model.Event;
import com.example.smartlivingcommunity.data.model.ParkingSlot;
import com.example.smartlivingcommunity.data.model.ServiceRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository class that provides data for resident-related features in the dashboard.
 * Supplies lists of parking slots, events, and service requests with dummy data for demonstration purposes.
 * @author Saon
 */
public class ResidentRepository {

    /**
     * Returns a list of {@code ParkingSlot} objects, each representing a parking slot's
     * ID, status, and associated vehicle ID.
     *
     * @return a list of parking slots
     */
    public List<ParkingSlot> getParkingSlots() {
        List<ParkingSlot> parkingSlots = new ArrayList<>();
        parkingSlots.add(new ParkingSlot("A1", "Occupied", "V1234"));
        parkingSlots.add(new ParkingSlot("A2", "Available", "V5678"));
        parkingSlots.add(new ParkingSlot("A3", "Available", "V4678"));
        return parkingSlots;
    }

    /**
     * Returns a list of {@code Event} objects, each representing an event's ID, location, date, and name.
     *
     * @return a list of events
     */
    public List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        events.add(new Event("E1", "Community Hall", "2024-11-01", "Festival Celebration"));
        events.add(new Event("E2", "Garden", "2024-11-05", "Yoga Workshop"));
        return events;
    }

    /**
     * Returns a list of {@code ServiceRequest} objects, each representing a service request's
     * type, description, and current status.
     *
     * @return a list of service requests
     */
    public List<ServiceRequest> getServiceRequests() {
        List<ServiceRequest> serviceRequests = new ArrayList<>();
        serviceRequests.add(new ServiceRequest("Plumbing", "Fix leaking pipe", "Pending"));
        serviceRequests.add(new ServiceRequest("Electric", "Replace fuse", "Completed"));
        return serviceRequests;
    }
}
