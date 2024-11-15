package com.example.smartlivingcommunity.domain.repository;

import com.example.smartlivingcommunity.data.model.AnnouncementModel;
import com.example.smartlivingcommunity.domain.model.Result;

import java.util.function.Consumer;

/**
 * Interface for managing announcements.
 */
public interface AnnouncementRepository {
    void createAnnouncementAsync(AnnouncementModel announcement, Consumer<Result<AnnouncementModel>> callback);
}
