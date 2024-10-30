package com.example.smartlivingcommunity.data.model;

/**
 * Represents an event within the  dashboard system, detailing the event's unique ID,
 * location, date, and name.
 * @author Saon
 * version 1.0
 */
public class Event {

    /** Unique identifier for the event */
    private String eventID;

    /** Location where the event will take place */
    private String location;

    /** Date of the event in a specified format */
    private String date;

    /** Name or title of the event */
    private String eventName;

    /**
     * Constructs a new {@code Event} with specified details including event ID, location, date, and name.
     *
     * @param eventID    the unique identifier for the event
     * @param location   the location where the event will occur
     * @param date       the date of the event
     * @param eventName  the name or title of the event
     */
    public Event(String eventID, String location, String date, String eventName) {
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
