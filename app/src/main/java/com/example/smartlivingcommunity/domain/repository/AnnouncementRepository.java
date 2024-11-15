package com.example.smartlivingcommunity.domain.repository;

import com.example.smartlivingcommunity.data.model.AnnouncementModel;
import com.example.smartlivingcommunity.domain.model.Result;

public interface AnnouncementRepository {
    Result<AnnouncementModel> createAnnouncement(AnnouncementModel announcement);
}