package com.example.smartlivingcommunity.data.repository;


import com.example.smartlivingcommunity.data.model.AnnouncementModel;
import com.example.smartlivingcommunity.domain.model.Result;
import com.example.smartlivingcommunity.domain.repository.AnnouncementRepository;

public class TestAnnouncementRepository implements AnnouncementRepository {
    private boolean shouldReturnError = false;
    private long delayMillis = 0;
    private int createCallCount = 0;
    private AnnouncementModel lastCreatedAnnouncement;

    @Override
    public Result<AnnouncementModel> createAnnouncement(AnnouncementModel announcement) {
        createCallCount++;
        lastCreatedAnnouncement = announcement;

        if (delayMillis > 0) {
            try {
                Thread.sleep(delayMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        if (shouldReturnError) {
            return new Result.Error("Failed to create announcement");
        }

        return new Result.Success<>(announcement);
    }

    public void setShouldReturnError(boolean shouldReturnError) {
        this.shouldReturnError = shouldReturnError;
    }

    public void setDelayMillis(long delayMillis) {
        this.delayMillis = delayMillis;
    }

    public boolean wasCreateCalled() {
        return createCallCount > 0;
    }

    public int getCreateCallCount() {
        return createCallCount;
    }

    public AnnouncementModel getLastCreatedAnnouncement() {
        return lastCreatedAnnouncement;
    }
}