package com.example.smartlivingcommunity.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "announcements")
public class AnnouncementModel {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String title;
    private String description;
    private Date date;

    public AnnouncementModel (String title, String description, Date date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    // Getters and Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
}