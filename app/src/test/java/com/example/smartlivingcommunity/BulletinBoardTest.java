package com.example.smartlivingcommunity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import com.example.smartlivingcommunity.data.model.BulletinBoardModel;
import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Unit tests for the BulletinBoard class to validate correct data properties
 * and behavior with valid and invalid data.
 */
public class BulletinBoardTest {

    /**
     * Tests that a BulletinBoard object is correctly created with valid data.
     */
    @Test
    public void bulletinBoard_CreateWithValidData_ShouldSetAllProperties() {
        // Arrange
        String title = "Community Meeting";
        String description = "A community meeting will be held on Friday.";
        Date date = new Date();
        String category = "Announcement";

        // Act
        BulletinBoardModel bulletin = new BulletinBoardModel(title, description, date);
        bulletin.setCategory(category);

        // Assert
        assertEquals("Title should match", title, bulletin.getTitle());
        assertEquals("Description should match", description, bulletin.getDescription());
        assertEquals("Date should match", date, bulletin.getDate());
        assertEquals("Category should match", category, bulletin.getCategory());
    }

    /**
     * Tests that creating a BulletinBoard object with an empty title throws an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void bulletinBoard_CreateWithEmptyTitle_ShouldThrowException() {
        // Arrange
        String emptyTitle = "";
        String description = "A notice description";
        Date date = new Date();

        // Act
        new BulletinBoardModel(emptyTitle, description, date);
    }

    /**
     * Tests that creating a BulletinBoard object with a null title throws an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void bulletinBoard_CreateWithNullTitle_ShouldThrowException() {
        // Arrange
        String nullTitle = null;
        String description = "A notice description";
        Date date = new Date();

        // Act
        new BulletinBoardModel(nullTitle, description, date);
    }

    /**
     * Tests that the setCategory method correctly assigns a category.
     */
    @Test
    public void bulletinBoard_SetCategory_ShouldUpdateCategory() {
        // Arrange
        BulletinBoardModel bulletin = new BulletinBoardModel("Meeting", "Team meeting", new Date());
        String category = "Update";

        // Act
        bulletin.setCategory(category);

        // Assert
        assertEquals("Category should be updated", category, bulletin.getCategory());
    }

    /**
     * Tests that a BulletinBoard object can be created without a category initially.
     */
    @Test
    public void bulletinBoard_CreateWithoutCategory_ShouldHaveNullCategory() {
        // Arrange
        BulletinBoardModel bulletin = new BulletinBoardModel("Meeting", "Team meeting", new Date());

        // Assert
        assertNull("Category should be null initially", bulletin.getCategory());
    }
}