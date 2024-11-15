package com.example.smartlivingcommunity.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.smartlivingcommunity.data.dao.AnnouncementDao;
import com.example.smartlivingcommunity.data.model.AnnouncementModel;
import com.example.smartlivingcommunity.utils.DateConverter;

/**
 * Room Database class for the application.
 */
@Database(entities = {AnnouncementModel.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase instance;

    public abstract AnnouncementDao announcementDao();

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "smart_living_database"
                    ).build();
                }
            }
        }
        return instance;
    }
}
