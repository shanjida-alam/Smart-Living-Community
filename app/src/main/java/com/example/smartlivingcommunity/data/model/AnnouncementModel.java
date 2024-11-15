package com.example.smartlivingcommunity.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

import java.util.Date;
import java.util.Objects;

/**
 * Represents an Announcement entity for the Smart Living Community app.
 * This class is mapped to the "announcements" table in the Room database.
 * @author Irtifa
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Override equals() for meaningful object comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnnouncementModel that = (AnnouncementModel) o;

        return Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(date, that.date);
    }

    // Override hashCode() for consistent hashing
    @Override
    public int hashCode() {
        return Objects.hash(title, description, date);
    }
}
