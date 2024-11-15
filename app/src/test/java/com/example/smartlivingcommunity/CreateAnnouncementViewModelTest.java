package com.example.smartlivingcommunity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.smartlivingcommunity.data.model.AnnouncementModel;
import com.example.smartlivingcommunity.data.repository.TestAnnouncementRepository;
import com.example.smartlivingcommunity.domain.model.Result;
import com.example.smartlivingcommunity.ui.viewmodel.CreateAnnouncementViewModel;

import java.util.Date;

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

    // Success Case Tests
    @Test
    public void createAnnouncement_ValidInput_ReturnsSuccess() {
        // Arrange
        String title = "Test Title";
        String description = "Test Description";
        Date date = new Date();

        // Act
        viewModel.createAnnouncement(title, description, date);

        // Assert
        Result<AnnouncementModel> result = viewModel.getCreateResult().getValue();
        assertTrue(result.isSuccess());
        assertNotNull(result.getData());
        assertEquals(title, result.getData().getTitle());
        assertEquals(description, result.getData().getDescription());
        assertEquals(date, result.getData().getDate());
    }

    @Test
    public void createAnnouncement_MinimumValidInput_ReturnsSuccess() {
        // Arrange
        String title = "A";
        String description = "B";
        Date date = new Date();

        // Act
        viewModel.createAnnouncement(title, description, date);

        // Assert
        Result<AnnouncementModel> result = viewModel.getCreateResult().getValue();
        assertTrue(result.isSuccess());
    }

    // Title Validation Tests
    @Test
    public void createAnnouncement_EmptyTitle_ReturnsError() {
        // Act
        viewModel.createAnnouncement("", "Description", new Date());

        // Assert
        Result<AnnouncementModel> result = viewModel.getCreateResult().getValue();
        assertFalse(result.isSuccess());
        assertEquals("Title cannot be empty", result.getError());
    }

    @Test
    public void createAnnouncement_NullTitle_ReturnsError() {
        // Act
        viewModel.createAnnouncement(null, "Description", new Date());

        // Assert
        Result<AnnouncementModel> result = viewModel.getCreateResult().getValue();
        assertFalse(result.isSuccess());
        assertEquals("Title cannot be empty", result.getError());
    }

    @Test
    public void createAnnouncement_WhitespaceTitle_ReturnsError() {
        // Act
        viewModel.createAnnouncement("   ", "Description", new Date());

        // Assert
        Result<AnnouncementModel> result = viewModel.getCreateResult().getValue();
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
        Result<AnnouncementModel> result = viewModel.getCreateResult().getValue();
        assertFalse(result.isSuccess());
        assertEquals("Title cannot exceed 100 characters", result.getError());
    }

    // Description Validation Tests
    @Test
    public void createAnnouncement_EmptyDescription_ReturnsError() {
        // Act
        viewModel.createAnnouncement("Title", "", new Date());

        // Assert
        Result<AnnouncementModel> result = viewModel.getCreateResult().getValue();
        assertFalse(result.isSuccess());
        assertEquals("Description cannot be empty", result.getError());
    }

    @Test
    public void createAnnouncement_NullDescription_ReturnsError() {
        // Act
        viewModel.createAnnouncement("Title", null, new Date());

        // Assert
        Result<AnnouncementModel> result = viewModel.getCreateResult().getValue();
        assertFalse(result.isSuccess());
        assertEquals("Description cannot be empty", result.getError());
    }

    @Test
    public void createAnnouncement_WhitespaceDescription_ReturnsError() {
        // Act
        viewModel.createAnnouncement("Title", "    ", new Date());

        // Assert
        Result<AnnouncementModel> result = viewModel.getCreateResult().getValue();
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
        Result<AnnouncementModel> result = viewModel.getCreateResult().getValue();
        assertFalse(result.isSuccess());
        assertEquals("Description cannot exceed 1000 characters", result.getError());
    }

    // Date Validation Test
    @Test
    public void createAnnouncement_NullDate_ReturnsError() {
        // Act
        viewModel.createAnnouncement("Title", "Description", null);

        // Assert
        Result<AnnouncementModel> result = viewModel.getCreateResult().getValue();
        assertFalse(result.isSuccess());
        assertEquals("Date cannot be null", result.getError());
    }

    // Loading State Tests
    @Test
    public void createAnnouncement_HidesLoadingStateAfterCompletion() {
        // Act
        viewModel.createAnnouncement("Title", "Description", new Date());

        // Assert
        assertFalse(viewModel.getIsLoading().getValue());
    }

    @Test
    public void createAnnouncement_HidesLoadingStateAfterError() {
        // Act
        viewModel.createAnnouncement("", "Description", new Date());

        // Assert
        assertFalse(viewModel.getIsLoading().getValue());
    }

    @Test
    public void createAnnouncement_HidesLoadingStateAfterRepositoryError() {
        // Arrange
        repository.setShouldReturnError(true);

        // Act
        viewModel.createAnnouncement("Title", "Description", new Date());

        // Assert
        assertFalse(viewModel.getIsLoading().getValue());
    }

    // Repository Tests
    @Test
    public void createAnnouncement_RepositorySuccess_ReturnsSuccess() {
        // Arrange
        String title = "Test Title";
        String description = "Test Description";
        Date date = new Date();
        AnnouncementModel announcement = new AnnouncementModel(title, description, date);

        // Act
        viewModel.createAnnouncement(title, description, date);

        // Assert
        Result<AnnouncementModel> result = viewModel.getCreateResult().getValue();
        assertTrue(result.isSuccess());
        assertNotNull(result.getData());
        assertEquals(title, result.getData().getTitle());
        assertEquals(description, result.getData().getDescription());
        assertEquals(date, result.getData().getDate());
    }

    @Test
    public void createAnnouncement_RepositoryError_ReturnsError() {
        // Arrange
        repository.setShouldReturnError(true);
        String expectedError = "Failed to create announcement";

        // Act
        viewModel.createAnnouncement("Title", "Description", new Date());

        // Assert
        Result<AnnouncementModel> result = viewModel.getCreateResult().getValue();
        assertFalse(result.isSuccess());
        assertEquals(expectedError, result.getError());
    }

    @Test
    public void createAnnouncement_VerifyRepositoryCall() {
        // Arrange
        String title = "Test Title";
        String description = "Test Description";
        Date date = new Date();

        // Act
        viewModel.createAnnouncement(title, description, date);

        // Assert
        assertTrue(repository.wasCreateCalled());
        AnnouncementModel lastAnnouncement = repository.getLastCreatedAnnouncement();
        assertNotNull(lastAnnouncement);
        assertEquals(title, lastAnnouncement.getTitle());
        assertEquals(description, lastAnnouncement.getDescription());
        assertEquals(date, lastAnnouncement.getDate());
    }

    @Test
    public void createAnnouncement_MultipleCallsTracking() {
        // Arrange
        int expectedCalls = 3;

        // Act
        viewModel.createAnnouncement("Title1", "Description1", new Date());
        viewModel.createAnnouncement("Title2", "Description2", new Date());
        viewModel.createAnnouncement("Title3", "Description3", new Date());

        // Assert
        assertEquals(expectedCalls, repository.getCreateCallCount());
    }
}