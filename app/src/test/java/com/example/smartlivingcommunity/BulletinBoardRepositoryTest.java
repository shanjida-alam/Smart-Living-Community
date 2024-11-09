package com.example.smartlivingcommunity;

import static org.junit.Assert.assertNotNull;

import com.example.smartlivingcommunity.data.model.BulletinBoardModel;
import com.example.smartlivingcommunity.data.repository.BulletinBoardRepository;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * Unit tests for the BulletinBoardRepository to validate repository actions
 * such as adding a notice.
 */
public class BulletinBoardRepositoryTest {

    private BulletinBoardRepository repository;

    @Before
    public void setup() {
        // Initialize the repository.
        repository = new BulletinBoardRepository();
    }

    /**
     * Test to verify that adding a valid notice returns a non-null task.
     */
    @Test
    public void addNotice_ValidNotice_ShouldReturnNonNullTask() {
        // Arrange
        BulletinBoardModel notice = new BulletinBoardModel(
                "Test Notice",
                "Test Description",
                new Date()
        );

        // Act
        // Explicitly specifying the type instead of `var` for compatibility
        Task<DocumentReference> result = repository.addNotice(notice);

        // Assert
        assertNotNull("Task should not be null", result);
    }
}
