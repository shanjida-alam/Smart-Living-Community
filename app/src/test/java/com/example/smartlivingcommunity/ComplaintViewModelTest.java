package com.example.smartlivingcommunity;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.example.smartlivingcommunity.data.model.ComplaintModel;
import com.example.smartlivingcommunity.data.repository.ComplaintRepository;
import com.example.smartlivingcommunity.ui.viewmodel.ComplaintViewModel;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ComplaintViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private ComplaintRepository mockRepository;

    @InjectMocks
    private ComplaintViewModel viewModel;

    private ComplaintModel validComplaint;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        validComplaint = new ComplaintModel("1", "U101", "John Doe", "Resident", "1234567890", "john@example.com", "Issue description");
        viewModel = new ComplaintViewModel(mockRepository);
    }

    @Test
    public void testSubmitComplaint_withValidData_shouldSaveSuccessfully() {
        // Arrange
        Task<Void> mockTask = Tasks.forResult(null); // Simulate successful save
        when(mockRepository.addComplaint(validComplaint)).thenReturn(mockTask);

        Observer<Boolean> observer = mock(Observer.class);
        viewModel.getIsComplaintSaved().observeForever(observer);

        // Act
        viewModel.submitComplaint(validComplaint);

        // Assert
        // Ensure that the observer is notified with 'true' after the task completes.
        verify(observer, timeout(5000)).onChanged(true);
    }


    @Test
    @SuppressWarnings("unchecked") // Suppresses the unchecked cast warning
    public void testSubmitComplaint_withInvalidData_shouldReturnError() {
        // Arrange
        ComplaintModel invalidComplaint = new ComplaintModel("1", "U101", "John Doe", "Resident", "1234567890", "john@example.com", "");

        Observer<String> observer = mock(Observer.class);
        viewModel.getErrorMessage().observeForever(observer);

        // Act
        viewModel.submitComplaint(invalidComplaint);

        // Assert
        verify(observer).onChanged("Invalid input");
    }
}