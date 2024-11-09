package com.example.smartlivingcommunity;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import org.junit.Rule;

public class CreateAnnouncementViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    private CreateAnnouncementViewModel viewModel;
    private TestAnnouncementRepository repository;

    @Before
    public void setup() {
        repository = new TestAnnouncementRepository();
        viewModel = new CreateAnnouncementViewModel(repository);
    }

    // Success Cases
    @Test
    public void createAnnouncement_ValidInput_ReturnsSuccess() {
        // Arrange
        String title = "Test Title";
        String description = "Test Description";
        Date date = new Date();

        // Act
        viewModel.createAnnouncement(title, description, date);

        // Assert
        Result<Announcement> result = viewModel.getCreateResult().getValue();
        assertTrue(result.isSuccess());
        assertNotNull(result.getData());
        assertEquals(title, result.getData().getTitle());
        assertEquals(description, result.getData().getDescription());
        assertEquals(date, result.getData().getDate());
    }

    @Test
    public void createAnnouncement_MinimumValidInput_ReturnsSuccess() {
        // Arrange
        String title = "A"; // Minimum length title
        String description = "B"; // Minimum length description
        Date date = new Date();

        // Act
        viewModel.createAnnouncement(title, description, date);

        // Assert
        Result<Announcement> result = viewModel.getCreateResult().getValue();
        assertTrue(result.isSuccess());
    }

    // Title Validation Tests
    @Test
    public void createAnnouncement_EmptyTitle_ReturnsError() {
        // Act
        viewModel.createAnnouncement("", "Description", new Date());

        // Assert
        Result<Announcement> result = viewModel.getCreateResult().getValue();
        assertFalse(result.isSuccess());
        assertEquals("Title cannot be empty", result.getError());
    }

    @Test
    public void createAnnouncement_NullTitle_ReturnsError() {
        // Act
        viewModel.createAnnouncement(null, "Description", new Date());

        // Assert
        Result<Announcement> result = viewModel.getCreateResult().getValue();
        assertFalse(result.isSuccess());
        assertEquals("Title cannot be empty", result.getError());
    }

    @Test
    public void createAnnouncement_WhitespaceTitle_ReturnsError() {
        // Act
        viewModel.createAnnouncement("   ", "Description", new Date());

        // Assert
        Result<Announcement> result = viewModel.getCreateResult().getValue();
        assertFalse(result.isSuccess());
        assertEquals("Title cannot be empty", result.getError());
    }

    @Test
    public void createAnnouncement_TooLongTitle_ReturnsError() {
        // Arrange
        String longTitle = "A".repeat(101); // Assuming max length is 100

        // Act
        viewModel.createAnnouncement(longTitle, "Description", new Date());

        // Assert
        Result<Announcement> result = viewModel.getCreateResult().getValue();
        assertFalse(result.isSuccess());
        assertEquals("Title cannot exceed 100 characters", result.getError());
    }

    // Description Validation Tests
    @Test
    public void createAnnouncement_EmptyDescription_ReturnsError() {
        // Act
        viewModel.createAnnouncement("Title", "", new Date());

        // Assert
        Result<Announcement> result = viewModel.getCreateResult().getValue();
        assertFalse(result.isSuccess());
        assertEquals("Description cannot be empty", result.getError());
    }

    @Test
    public void createAnnouncement_NullDescription_ReturnsError() {
        // Act
        viewModel.createAnnouncement("Title", null, new Date());

        // Assert
        Result<Announcement> result = viewModel.getCreateResult().getValue();
        assertFalse(result.isSuccess());
        assertEquals("Description cannot be empty", result.getError());
    }

    @Test
    public void createAnnouncement_WhitespaceDescription_ReturnsError() {
        // Act
        viewModel.createAnnouncement("Title", "    ", new Date());

        // Assert
        Result<Announcement> result = viewModel.getCreateResult().getValue();
        assertFalse(result.isSuccess());
        assertEquals("Description cannot be empty", result.getError());
    }

    @Test
    public void createAnnouncement_TooLongDescription_ReturnsError() {
        // Arrange
        String longDescription = "A".repeat(1001); // Assuming max length is 1000

        // Act
        viewModel.createAnnouncement("Title", longDescription, new Date());

        // Assert
        Result<Announcement> result = viewModel.getCreateResult().getValue();
        assertFalse(result.isSuccess());
        assertEquals("Description cannot exceed 1000 characters", result.getError());
    }

    // Date Validation Tests
    @Test
    public void createAnnouncement_NullDate_ReturnsError() {
        // Act
        viewModel.createAnnouncement("Title", "Description", null);

        // Assert
        Result<Announcement> result = viewModel.getCreateResult().getValue();
        assertFalse(result.isSuccess());
        assertEquals("Date cannot be null", result.getError());
    }

    // Repository Error Tests
    @Test
    public void createAnnouncement_RepositoryError_ReturnsError() {
        // Arrange
        repository.setShouldReturnError(true);

        // Act
        viewModel.createAnnouncement("Title", "Description", new Date());

        // Assert
        Result<Announcement> result = viewModel.getCreateResult().getValue();
        assertFalse(result.isSuccess());
        assertEquals("Failed to create announcement", result.getError());
    }

    // Loading State Tests
    @Test
    public void createAnnouncement_ShowsLoadingState() {
        // Arrange
        repository.setDelayMillis(100); // Add slight delay to test loading state

        // Act
        viewModel.createAnnouncement("Title", "Description", new Date());

        // Assert
        assertTrue(viewModel.getIsLoading().getValue());
    }

    @Test
    public void createAnnouncement_HidesLoadingStateAfterCompletion() {
        // Act
        viewModel.createAnnouncement("Title", "Description", new Date());

        // Assert
        assertFalse(viewModel.getIsLoading().getValue());
    }
}