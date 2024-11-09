package com.example.smartlivingcommunity.data.model;

import java.util.Date;

public class BulletinBoardModel {

    private String title;
    private String description;
    private Date date;
    private String category;

    // Constructor with title, description, and date
    public BulletinBoardModel(String title, String description, Date date) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title;
        this.description = description;
        this.date = date;
    }

    // No-argument constructor (if required for Firebase)
    public BulletinBoardModel() {}

    // Getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    // Setter for category
    public void setCategory(String category) {
        this.category = category;
    }

    // Setter for attachment (if necessary)
    public void setAttachment(String attachmentUrl) {
        // Logic for handling attachment, if required
    }
}
