package com.example.smartlivingcommunity.Data.Model;

/**
 * Represents a service request in the system.
 */
public class ServiceRequest {
    private String id; // Unique ID for the request
    private String residentId; // Resident who made the request
    private String serviceType; // Type of service (e.g., plumbing, electrical)
    private String status; // Current status of the request
    private long timestamp; // Time when the request was made

    // Constructor
    public ServiceRequest(String id, String residentId, String serviceType, String status, long timestamp) {
        this.id = id;
        this.residentId = residentId;
        this.serviceType = serviceType;
        this.status = status;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getResidentId() {
        return residentId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public String getStatus() {
        return status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
