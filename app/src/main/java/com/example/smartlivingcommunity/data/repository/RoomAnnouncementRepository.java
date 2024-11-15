package com.example.smartlivingcommunity.data.repository;

import android.content.Context;

import com.example.smartlivingcommunity.data.model.AnnouncementModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A repository class for managing announcement data in the Smart Living Community app.
 * This class is responsible for retrieving, storing, and managing announcements.
 *
 * Author: Irtifa
 */
public class RoomAnnouncementRepository {

    // A local list to store announcements in memory
    private final List<AnnouncementModel> announcements = new ArrayList<>();

    /**
     * Constructor for the repository.
     *
     * @param context The application context. Can be used for initializing database connections or other resources.
     */
    public RoomAnnouncementRepository(Context context) {
        // Placeholder for database initialization or other setup operations
    }

    /**
     * Retrieves the list of all announcements.
     *
     * @return A new list containing all announcements.
     */
    public List<AnnouncementModel> getAnnouncements() {
        return new ArrayList<>(announcements); // Return a copy of the announcements list
    }

    /**
     * Adds a new announcement to the repository.
     *
     * @param announcement The AnnouncementModel object to add.
     */
    public void createAnnouncement(AnnouncementModel announcement) {
        announcements.add(announcement); // Add the announcement to the local list
    }
}
