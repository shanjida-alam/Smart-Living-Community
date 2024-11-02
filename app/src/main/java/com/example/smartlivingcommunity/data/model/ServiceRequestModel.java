package com.example.smartlivingcommunity.data.model;

import com.google.firebase.firestore.PropertyName;
import java.util.Date;

/**
 *
 * @author solaimi
 * @property requestType The type of service requested.
 * @property description A detailed description of the service request.
 * @property urgency The urgency level of the service request.
 * @property timestamp The time when the request was created.
 */
public class ServiceRequestModel {

    @PropertyName("requestType")
    private String requestType; // The type of service requested.

    @PropertyName("description")
    private String description; // A detailed description of the service request.

    @PropertyName("urgency")
    private String urgency; // The urgency level of the service request.

    @PropertyName("timestamp")
    private Date timestamp; // The time when the request was created.

    /**
     * Default constructor required for Firestore.
     * This constructor is needed for Firestore to deserialize the document into a ServiceRequestModel object.
     */
    public ServiceRequestModel() {
    }

    /**
     * Parameterized constructor to initialize a ServiceRequestModel instance.
     *
     * @param requestType The type of service requested.
     * @param description A detailed description of the service request.
     * @param urgency The urgency level of the service request.
     * @param timestamp The time when the request was created.
     */
    public ServiceRequestModel(String requestType, String description, String urgency, Date timestamp) {
        this.requestType = requestType; // Initialize request type.
        this.description = description; // Initialize description.
        this.urgency = urgency; // Initialize urgency.
        this.timestamp = timestamp; // Initialize timestamp.
    }

    /**
     * Gets the request type.
     *
     * @return The type of service requested.
     */
    public String getRequestType() {
        return requestType;
    }

    /**
     * Sets the request type.
     *
     * @param requestType The type of service to be set.
     */
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    /**
     * Gets the description of the service request.
     *
     * @return The description of the service request.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the service request.
     *
     * @param description The description to be set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the urgency level of the service request.
     *
     * @return The urgency level of the service request.
     */
    public String getUrgency() {
        return urgency;
    }

    /**
     * Sets the urgency level of the service request.
     *
     * @param urgency The urgency level to be set.
     */
    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    /**
     * Gets the timestamp of when the request was created.
     *
     * @return The timestamp of the service request.
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of when the request was created.
     *
     * @param timestamp The timestamp to be set.
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}

