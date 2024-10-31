package com.example.smartlivingcommunity.data.model;

/**
 * EventModel class representing the details of an event.
 */
public class EventModel {
    private String id;          // Unique identifier for the event
    private String title;       // Title of the event
    private String description; // Description of the event
    private String date;        // Date of the event
    private String location;    // Location of the event

    /**
     * Default constructor required for Firestore.
     */
    public EventModel() {}

    /**
     * Constructs an EventModel object with specified details.
     *
     * @param id          Unique identifier for the event
     * @param title       Title of the event
     * @param description Description of the event
     * @param date        Date of the event
     * @param location    Location where the event is held
     */
    public EventModel(String id, String title, String description, String date, String location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
    }

    // Getters and Setters

    /**
     * Gets the unique identifier of the event.
     *
     * @return the event ID
     */
    public String getId() { return id; }

    /**
     * Sets the unique identifier of the event.
     *
     * @param id the event ID
     */
    public void setId(String id) { this.id = id; }

    /**
     * Gets the title of the event.
     *
     * @return the event title
     */
    public String getTitle() { return title; }

    /**
     * Sets the title of the event.
     *
     * @param title the event title
     */
    public void setTitle(String title) { this.title = title; }

    /**
     * Gets the description of the event.
     *
     * @return the event description
     */
    public String getDescription() { return description; }

    /**
     * Sets the description of the event.
     *
     * @param description the event description
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Gets the date of the event.
     *
     * @return the event date
     */
    public String getDate() { return date; }

    /**
     * Sets the date of the event.
     *
     * @param date the event date
     */
    public void setDate(String date) { this.date = date; }

    /**
     * Gets the location of the event.
     *
     * @return the event location
     */
    public String getLocation() { return location; }

    /**
     * Sets the location of the event.
     *
     * @param location the event location
     */
    public void setLocation(String location) { this.location = location; }
}
