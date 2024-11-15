package com.example.smartlivingcommunity.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

import java.util.Date;

/**
 * Represents an Announcement entity for the Smart Living Community app.
 * This class is mapped to the "announcements" table in the Room database.
 *
 * Author: Irtifa
 */
@Entity(tableName = "announcements")
public class AnnouncementModel {

    @PrimaryKey(autoGenerate = true)
    private int id; // Unique ID for each announcement (auto-generated)

    @ColumnInfo(name = "title")
    private String title; // Title of the announcement

    @ColumnInfo(name = "description")
    private String description; // Description of the announcement

    @ColumnInfo(name = "date")
    private Date date; // Date of the announcement

    /**
     * Constructor for creating a new AnnouncementModel instance.
     *
     * @param title       The title of the announcement.
     * @param description The description of the announcement.
     * @param date        The date of the announcement.
     */
    public AnnouncementModel(String title, String description, Date date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    // Getters and setters

    /**
     * Returns the ID of the announcement.
     *
     * @return The unique ID of the announcement.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the announcement.
     *
     * @param id The unique ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the title of the announcement.
     *
     * @return The title of the announcement.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the announcement.
     *
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the description of the announcement.
     *
     * @return The description of the announcement.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the announcement.
     *
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the date of the announcement.
     *
     * @return The date of the announcement.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date of the announcement.
     *
     * @param date The date to set.
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
