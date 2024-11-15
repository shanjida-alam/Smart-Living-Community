package com.example.smartlivingcommunity.utils;

import androidx.room.TypeConverter;

import java.util.Date;

/**
 * TypeConverter for Room to handle Date objects.
 */
public class DateConverter {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
