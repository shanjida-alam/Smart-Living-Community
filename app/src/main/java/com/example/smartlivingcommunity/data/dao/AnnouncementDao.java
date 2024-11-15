package com.example.smartlivingcommunity.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.smartlivingcommunity.data.model.AnnouncementModel;

import java.util.List;

/**
 * DAO for Announcement-related database operations.
 */
@Dao
public interface AnnouncementDao {
    @Insert
    void insertAnnouncement(AnnouncementModel announcement);

    @Query("SELECT * FROM announcements")
    List<AnnouncementModel> getAllAnnouncements();
}
