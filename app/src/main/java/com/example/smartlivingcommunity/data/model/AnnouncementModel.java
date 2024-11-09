package com.example.smartlivingcommunity.data.model;

import java.util.Date;

public class AnnouncementModel {
    private long id;
    private String title;
    private String description;
    private Date date;

    public AnnouncementModel(String title, String description, Date date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Date getDate() { return date; }
}