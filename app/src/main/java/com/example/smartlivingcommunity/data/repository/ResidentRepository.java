package com.example.smartlivingcommunity.data.repository;

import com.example.smartlivingcommunity.data.model.ResidentEvent;
import com.example.smartlivingcommunity.data.model.ResidentParkingSlot;
import com.example.smartlivingcommunity.data.model.ResidentServiceRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository class that provides data for resident-related features in the Smart Living Community dashboard.
 * Supplies lists of parking slots, events, and service requests with dummy data for demonstration purposes.
 *
 * <p>This repository serves as a data source for the Resident dashboard, enabling the display of
 * parking slots, community events, and service requests.</p>
 *
 * @author Saon
 */
public class ResidentRepository {

    /**
     * Returns a list of {@code ParkingSlot} objects, each representing a parking slot's
     * ID, status, and associated vehicle ID.
     *
     * @return a list of parking slots available in the community
     */
    public List<ResidentParkingSlot> getParkingSlots() {
        List<ResidentParkingSlot> parkingSlots = new ArrayList<>();
        parkingSlots.add(new ResidentParkingSlot("A1", "Occupied", "V1234"));
        parkingSlots.add(new ResidentParkingSlot("A2", "Available", "V5678"));
        parkingSlots.add(new ResidentParkingSlot("A3", "Available", "V4678"));
        return parkingSlots;
    }

    /**
     * Returns a list of {@code Event} objects, each representing an event's ID, location, date, and name.
     *
     * @return a list of upcoming community events
     */
    public List<ResidentEvent> getEvents() {
        List<ResidentEvent> events = new ArrayList<>();
        events.add(new ResidentEvent("E1", "Community Hall", "2024-11-01", "Festival Celebration"));
        events.add(new ResidentEvent("E2", "Garden", "2024-11-05", "Yoga Workshop"));
        return events;
    }

    /**
     * Returns a list of {@code ServiceRequest} objects, each representing a service request's
     * type, description, and current status.
     *
     * @return a list of service requests made by residents
     */
    public List<ResidentServiceRequest> getServiceRequests() {
        List<ResidentServiceRequest> serviceRequests = new ArrayList<>();
        serviceRequests.add(new ResidentServiceRequest("Plumbing", "Fix leaking pipe", "Pending"));
        serviceRequests.add(new ResidentServiceRequest("Electric", "Replace fuse", "Completed"));
        return serviceRequests;
    }
}