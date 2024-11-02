package com.example.smartlivingcommunity.data.model;

/**
 * Model class representing a request for an event within the community.
 * Contains information about the request ID, event name, and request date.
 *
 * @author Saon
 */
public class EventRequest {

    private String requestId;
    private String eventName;
    private String requestDate;

    /**
     * Constructs an EventRequest with the specified details.
     *
     * @param requestId   Unique identifier for the event request.
     * @param eventName   Name of the event for which the request is made.
     * @param requestDate Date on which the request was submitted.
     */
    public EventRequest(String requestId, String eventName, String requestDate) {
        this.requestId = requestId;
        this.eventName = eventName;
        this.requestDate = requestDate;
    }

    /**
     * Returns the unique request ID of the event request.
     *
     * @return A String representing the request ID.
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Returns the name of the event related to this request.
     *
     * @return A String representing the event name.
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Returns the date on which the event request was made.
     *
     * @return A String representing the request date.
     */
    public String getRequestDate() {
        return requestDate;
    }
}
