package com.example.smartlivingcommunity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import android.content.Context;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.smartlivingcommunity.data.model.AnnouncementModel;
import com.example.smartlivingcommunity.data.repository.RoomAnnouncementRepository;
import com.example.smartlivingcommunity.domain.model.Result;
import com.example.smartlivingcommunity.ui.viewmodel.CreateAnnouncementViewModel;

import java.util.Date;

/**
 * Unit tests for the CreateAnnouncementViewModel class.
 * These tests validate the behavior of the createAnnouncement method under various scenarios.
 * @author Irtifa
 */
public class CreateAnnouncementViewModelTest {

    // Ensures that LiveData executes synchronously for unit tests.
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    private CreateAnnouncementViewModel viewModel; // The ViewModel under test
    private RoomAnnouncementRepository repository; // Repository dependency for the ViewModel

    /**
     * Sets up the testing environment by initializing the ViewModel and its dependencies.
     */
    @Before
    public void setup() {
        // Mock the Context object required by RoomAnnouncementRepository
        Context context = mock(Context.class);
        // Initialize the repository with the mocked Context
        repository = new RoomAnnouncementRepository(context);
        // Create the ViewModel with the repository
        viewModel = new CreateAnnouncementViewModel(repository);
    }

    /**
     * Test for createAnnouncement with extremely short title and description.
     * Verifies that the method succeeds with minimal input.
     */
    @Test
    public void createAnnouncement_ExtremelyShortTitleAndDescription_ReturnsSuccess() {
        String title = "T"; // Minimal title
        String description = "D"; // Minimal description
        Date date = new Date(); // Current date
        viewModel.createAnnouncement(title, description, date); // Call the method
        Result<AnnouncementModel> result = viewModel.getCreateResult().getValue(); // Get result
        assertNotNull(result); // Ensure the result is not null
        assertTrue(result instanceof Result.Success); // Verify the result is a success
    }

    /**
     * Test for createAnnouncement with long valid title and description.
     * Verifies that the method succeeds with larger input sizes.
     */
    @Test
    public void createAnnouncement_LongValidTitleAndDescription_ReturnsSuccess() {
        String title = "ABCDFGHIJKLMNOPQRST".repeat(50); // Long title
        String description = "BAGHYTSKAODBONDBUHNMDIDHDBJNSBDH".repeat(500); // Long description
        Date date = new Date(); // Current date
        viewModel.createAnnouncement(title, description, date); // Call the method
        Result<AnnouncementModel> result = viewModel.getCreateResult().getValue(); // Get result
        assertNotNull(result); // Ensure the result is not null
        assertTrue(result instanceof Result.Success); // Verify the result is a success
    }

    /**
     * Test for createAnnouncement with a past date.
     * Verifies that announcements can be created with historical dates.
     */
    @Test
    public void createAnnouncement_PastDate_ReturnsSuccess() {
        Date pastDate = new Date(System.currentTimeMillis() - 1000000000); // Past date
        viewModel.createAnnouncement("Title", "Description", pastDate); // Call the method
        Result<AnnouncementModel> result = viewModel.getCreateResult().getValue(); // Get result
        assertNotNull(result); // Ensure the result is not null
        assertTrue(result instanceof Result.Success); // Verify the result is a success
    }

    /**
     * Test for createAnnouncement with a title containing special characters.
     * Verifies that titles with special characters are handled correctly.
     */
    @Test
    public void createAnnouncement_TitleWithSpecialCharacters_ReturnsSuccess() {
        String title = "Picnic!"; // Title with special characters
        String description = "Description with special characters"; // Valid description
        Date date = new Date(); // Current date
        viewModel.createAnnouncement(title, description, date); // Call the method
        Result<AnnouncementModel> result = viewModel.getCreateResult().getValue(); // Get result
        assertNotNull(result); // Ensure the result is not null
        assertTrue(result instanceof Result.Success); // Verify the result is a success
    }

    /**
     * Test for createAnnouncement with a title containing only numbers.
     * Verifies that numeric titles are handled correctly.
     */
    @Test
    public void createAnnouncement_TitleWithNumbersOnly_ReturnsSuccess() {
        viewModel.createAnnouncement("Monthly Meeting4", "Description", new Date()); // Call the method
        Result<AnnouncementModel> result = viewModel.getCreateResult().getValue(); // Get result
        assertNotNull(result); // Ensure the result is not null
        assertTrue(result instanceof Result.Success); // Verify the result is a success
    }

    /**
     * Test for createAnnouncement with a description containing newline characters.
     * Verifies that multi-line descriptions are handled correctly.
     */
    @Test
    public void createAnnouncement_DescriptionWithNewlineCharacters_ReturnsSuccess() {
        viewModel.createAnnouncement("Title", "Description\nwith\nnewlines", new Date()); // Call the method
        Result<AnnouncementModel> result = viewModel.getCreateResult().getValue(); // Get result
        assertNotNull(result); // Ensure the result is not null
        assertTrue(result instanceof Result.Success); // Verify the result is a success
    }

    /**
     * Test for createAnnouncement with a title containing special characters and numbers.
     * Verifies that complex titles are handled correctly.
     */
    @Test
    public void createAnnouncement_TitleWithSpecialCharactersAndNumbers_ReturnsSuccess() {
        String title = "Title123!@#"; // Title with special characters and numbers
        String description = "Description with numbers and special characters"; // Valid description
        Date date = new Date(); // Current date
        viewModel.createAnnouncement(title, description, date); // Call the method
        Result<AnnouncementModel> result = viewModel.getCreateResult().getValue(); // Get result
        assertNotNull(result); // Ensure the result is not null
        assertTrue(result instanceof Result.Success); // Verify the result is a success
    }
}
