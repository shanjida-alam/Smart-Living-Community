package com.example.createevent;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import com.example.createevent.data.model.EventDataModel;
import com.example.createevent.data.repository.EventRepository;
import com.example.createevent.ui.viewmodel.AddEventViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class) // Automatically initializes mocks
public class AddEventViewModelTest1 {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private EventRepository eventRepository;

    private AddEventViewModel addEventViewModel;

    @Mock
    private Observer<Boolean> isSavedObserver;

    @Before
    public void setUp() {
        addEventViewModel = new AddEventViewModel(eventRepository);
        addEventViewModel.isSaved().observeForever(isSavedObserver);
    }

    @Test
    public void testSetTitle() {
        String testTitle = "Event Title";
        addEventViewModel.setTitle(testTitle);
        assertEquals(testTitle, addEventViewModel.getTitle().getValue());
    }

    @Test
    public void testSetDescription() {
        String testDescription = "Event Description";
        addEventViewModel.setDescription(testDescription);
        assertEquals(testDescription, addEventViewModel.getDescription().getValue());
    }

    @Test
    public void testSetTime() {
        String testTime = "12:00 PM";
        addEventViewModel.setTime(testTime);
        assertEquals(testTime, addEventViewModel.getTime().getValue());
    }

    @Test
    public void testSetLocation() {
        String testLocation = "Event Location";
        addEventViewModel.setLocation(testLocation);
        assertEquals(testLocation, addEventViewModel.getLocation().getValue());
    }

    @Test
    public void testSaveEvent_withAllFieldsSet() {
        String title = "Event Title";
        String description = "Event Description";
        String time = "12:00 PM";
        String location = "Event Location";

        addEventViewModel.setTitle(title);
        addEventViewModel.setDescription(description);
        addEventViewModel.setTime(time);
        addEventViewModel.setLocation(location);

        addEventViewModel.saveEvent();

        EventDataModel expectedEvent = new EventDataModel(title, description, time, location);
        verify(eventRepository).saveEvent(Mockito.eq(expectedEvent), Mockito.any());

        addEventViewModel.isSaved().getValue();
        verify(isSavedObserver).onChanged(true);
    }

    @Test
    public void testSaveEvent_withMissingFields() {
        String title = "Event Title";
        String description = "Event Description";

        addEventViewModel.setTitle(title);
        addEventViewModel.setDescription(description);

        addEventViewModel.saveEvent();

        assertEquals(false, addEventViewModel.isSaved().getValue());
        Mockito.verify(eventRepository, Mockito.never()).saveEvent(Mockito.any(), Mockito.any());
    }
}
