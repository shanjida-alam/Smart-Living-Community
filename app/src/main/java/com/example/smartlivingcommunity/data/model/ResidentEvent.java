package com.example.smartlivingcommunity.data.model;

/**
 * Represents an event within the Smart Living Community dashboard system, detailing the event's unique ID,
 * location, date, and name.
 *
 * @author Saon
 */
public class ResidentEvent {
    private String eventID;
    private String location;
    private String date;
    private String eventName;

    /**
     * Constructs a new {@code Event} with specified details, including event ID, location, date, and name.
     *
     * @param eventID   the unique identifier for the event
     * @param location  the location where the event will occur
     * @param date      the date of the event
     * @param eventName the name or title of the event
     */
    public ResidentEvent(String eventID, String location, String date, String eventName) {
        this.eventID = eventID;
        this.location = location;
        this.date = date;
        this.eventName = eventName;
    }

    /**
     * Returns the unique identifier for this event.
     *
     * @return the event ID
     */
    public String getEventID() {
        return eventID;
    }

    /**
     * Returns the location of the event.
     *
     * @return the event location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Returns the date of the event.
     *
     * @return the event date
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the name or title of the event.
     *
     * @return the event name
     */
    public String getEventName() {
        return eventName;
    }
}
