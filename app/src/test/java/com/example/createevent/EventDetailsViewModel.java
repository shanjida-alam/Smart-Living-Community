package com.example.createevent;
import android.app.Application;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import com.example.createevent.data.repository.EventRepository;
import com.example.createevent.ui.viewmodel.EventDetailsViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class EventDetailsViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private Application mockApplication;

    @Mock
    private EventRepository eventRepository;

    private EventDetailsViewModel eventDetailsViewModel;

    @Mock
    private Observer<Boolean> deletionStatusObserver;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        eventDetailsViewModel = new EventDetailsViewModel(mockApplication);
        eventDetailsViewModel.eventRepository = eventRepository;  // Inject mock repository
        eventDetailsViewModel.deletionStatus.observeForever(deletionStatusObserver);
    }

    @Test
    public void testDeleteEvent_successfulDeletion() {
        // Arrange
        String testKey = "testKey";

        // Act
        eventDetailsViewModel.deleteEvent(testKey);

        // Simulate successful deletion from repository
        Mockito.doAnswer(invocation -> {
            ((EventRepository.DeleteCallback) invocation.getArgument(1)).onDelete(true);
            return null;
        }).when(eventRepository).deleteEvent(Mockito.eq(testKey), Mockito.any());

        // Assert
        verify(eventRepository).deleteEvent(testKey, eventRepository.DeleteCallback.class);
        verify(deletionStatusObserver).onChanged(true);
    }

    @Test
    public void testDeleteEvent_unsuccessfulDeletion() {
        // Arrange
        String testKey = "testKey";

        // Act
        eventDetailsViewModel.deleteEvent(testKey);

        // Simulate unsuccessful deletion from repository
        Mockito.doAnswer(invocation -> {
            ((EventRepository.DeleteCallback) invocation.getArgument(1)).onDelete(false);
            return null;
        }).when(eventRepository).deleteEvent(Mockito.eq(testKey), Mockito.any());

        // Assert
        verify(eventRepository).deleteEvent(testKey, eventRepository.DeleteCallback.class);
        verify(deletionStatusObserver).onChanged(false);
    }
}
