package com.example.smartlivingcommunity.data.model;

import com.google.firebase.firestore.PropertyName;

/**
 * Data class representing a service request.
 */
public class ServiceRequestModel {

    @PropertyName("requestType")
    private String requestType;

    @PropertyName("description")
    private String description;

    @PropertyName("urgency")
    private String urgency;

    /**
     * Default constructor required for Firestore.
     */
    public ServiceRequestModel() {
        // Default constructor
    }

    /**
     * Constructor for creating a new ServiceRequestModel instance.
     *
     * @param requestType The type of service requested.
     * @param description A detailed description of the service request.
     * @param urgency The urgency level of the service request (e.g., High, Medium, Low).
     */
    public ServiceRequestModel(String requestType, String description, String urgency) {
        this.requestType = requestType;
        this.description = description;
        this.urgency = urgency;
    }

    /**
     * Gets the type of service requested.
     *
     * @return The request type.
     */
    public String getRequestType() {
        return requestType;
    }

    /**
     * Sets the type of service requested.
     *
     * @param requestType The request type to set.
     */
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    /**
     * Gets the detailed description of the service request.
     *
     * @return The service request description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the detailed description of the service request.
     *
     * @param description The service request description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the urgency level of the service request.
     *
     * @return The urgency level (e.g., High, Medium, Low).
     */
    public String getUrgency() {
        return urgency;
    }

    /**
     * Sets the urgency level of the service request.
     *
     * @param urgency The urgency level to set.
     */
    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }
}
