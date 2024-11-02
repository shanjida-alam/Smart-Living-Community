package com.example.smartlivingcommunity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


import androidx.lifecycle.LiveData;

import com.example.smartlivingcommunity.data.model.ResidentEvent;
import com.example.smartlivingcommunity.data.model.ResidentServiceRequest;
import com.example.smartlivingcommunity.data.repository.ResidentRepository;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ResidentRepositoryTest {

    private ResidentRepository repository;

    @Before
    public void setUp() {
        repository = new ResidentRepository();
    }

    @Test
    public void testGetEvents_Success() {
        // Arrange & Act
        List<ResidentEvent> events = repository.getEvents();
        // Assert - Expecting at least one event in the dummy data
        assertNotNull("Events list should not be null", events);
        assertTrue("Events list should contain items", events.size() > 0);
        assertEquals("First event name should be 'Festival Celebration'", "Festival Celebration", events.get(0).getEventName());
    }

    @Test
    public void testGetEvents_EmptyListFailure() {
        // Arrange & Act
        List<ResidentEvent> events = repository.getEvents();
        // Assert - Expecting the list to be empty (this will fail if it contains events)
        assertEquals("Expected no events, but some were found", 0, events.size());
    }

}
