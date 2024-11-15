package com.example.smartlivingcommunity.data.repository;

import com.example.smartlivingcommunity.data.model.AnnouncementModel;
import com.example.smartlivingcommunity.domain.model.Result;
import com.example.smartlivingcommunity.domain.repository.AnnouncementRepository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * A test implementation of the AnnouncementRepository interface.
 */
public class TestAnnouncementRepository implements AnnouncementRepository {
    private boolean shouldReturnError = false;
    private long delayMillis = 0;
    private int createCallCount = 0;
    private AnnouncementModel lastCreatedAnnouncement;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    public Result<AnnouncementModel> createAnnouncement(AnnouncementModel announcement) {
        throw new UnsupportedOperationException("Use createAnnouncementAsync for asynchronous operations.");
    }

    /**
     * Simulates the creation of an announcement asynchronously.
     *
     * @param announcement The AnnouncementModel to create.
     * @param callback     A Consumer that receives the Result of the operation.
     */
    public void createAnnouncementAsync(AnnouncementModel announcement, Consumer<Result<AnnouncementModel>> callback) {
        createCallCount++;
        lastCreatedAnnouncement = announcement;

        executorService.submit(() -> {
            if (delayMillis > 0) {
                try {
                    Thread.sleep(delayMillis);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    callback.accept(new Result.Error<>("Operation interrupted"));
                    return;
                }
            }

            if (shouldReturnError) {
                callback.accept(new Result.Error<>("Failed to create announcement"));
            } else {
                callback.accept(new Result.Success<>(announcement));
            }
        });
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
