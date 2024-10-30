package com.example.smartlivingcommunity.data.model;

/**
 * Represents a service request within the  dashboard system, detailing the type
 * of service requested, a description of the request, and its current status.
 * @author Saon
 */
public class ServiceRequest {

    /** Type of the service requested (e.g., "plumbing", "electrical") */
    private String serviceType;

    /** Description providing details of the service request */
    private String description;

    /** Current status of the service request (e.g., "pending", "completed") */
    private String status;

    /**
     * Constructs a new {@code ServiceRequest} with specified details including service type, description, and status.
     *
     * @param serviceType the type of service requested
     * @param description a detailed description of the service request
     * @param status      the current status of the request
     */
    public ServiceRequest(String serviceType, String description, String status) {
        this.serviceType = serviceType;
        this.description = description;
        this.status = status;
    }

    /**
     * Returns the type of service requested.
     *
     * @return the service type
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * Returns the description of the service request.
     *
     * @return the service request description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the current status of the service request.
     *
     * @return the service request status
     */
    public String getStatus() {
        return status;
    }
}
