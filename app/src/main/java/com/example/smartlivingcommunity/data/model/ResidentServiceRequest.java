package com.example.smartlivingcommunity.data.model;

/**
 * Represents a service request within the Smart Living Community dashboard system,
 * detailing the type of service requested, a description of the request, and its current status.
 *
 * <p>This class provides information on the service requested by residents, including
 * the request type, a description of the service, and the status of the request.</p>
 *
 * @author Saon
 */
public class ResidentServiceRequest {
    private String serviceType;
    private String description;
    private String status;

    /**
     * Constructs a new {@code ResidentServiceRequest} with specified details including service type, description, and status.
     *
     * @param serviceType the type of service requested, such as maintenance or support
     * @param description a detailed description of the service request
     * @param status      the current status of the request (e.g., pending, in progress, completed)
     */
    public ResidentServiceRequest(String serviceType, String description, String status) {
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
