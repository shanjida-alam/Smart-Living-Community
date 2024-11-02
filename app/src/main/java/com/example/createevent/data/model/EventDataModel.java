package com.example.createevent.data.model;

/**
 * Represents an Event data model with details about an event such as title, description, time, location, and key.
 */
public class EventDataModel {

    private String eventTitle; // The title of the event
    private String eventDesc;  // A brief description of the event
    private String eventTime;  // The time at which the event takes place
    private String eventLocation; // The location where the event is held
    private String key; // Unique key identifier for the event in the database

    /**
     * Default constructor for EventDataModel.
     */
    public EventDataModel() {
    }

    /**
     * Parameterized constructor for creating an EventDataModel with specific details.
     *
     * @param eventTitle     The title of the event.
     * @param eventDesc      The description of the event.
     * @param eventTime      The time of the event.
     * @param eventLocation  The location of the event.
     */
    public EventDataModel(String eventTitle, String eventDesc, String eventTime, String eventLocation) {
        this.eventTitle = eventTitle;
        this.eventDesc = eventDesc;
        this.eventTime = eventTime;
        this.eventLocation = eventLocation;
    }

    /**
     * Gets the unique key identifier for the event.
     *
     * @return The key of the event.
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the unique key identifier for the event.
     *
     * @param key The key to set for the event.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets the title of the event.
     *
     * @return The title of the event.
     */
    public String getEventTitle() {
        return eventTitle;
    }

    /**
     * Gets the description of the event.
     *
     * @return The description of the event.
     */
    public String getEventDesc() {
        return eventDesc;
    }

    /**
     * Gets the time of the event.
     *
     * @return The time of the event.
     */
    public String dataTime() {
        return eventTime;
    }

    /**
     * Gets the location of the event.
     *
     * @return The location of the event.
     */
    public String getEventLocation() {
        return eventLocation;
    }
}
