package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.smartlivingcommunity.data.model.BookingModel;
import com.example.smartlivingcommunity.data.model.InstantBookingModel;
import com.example.smartlivingcommunity.data.model.ParkingRegistrationModel;
import com.example.smartlivingcommunity.data.model.PermanentBookingModel;
import com.example.smartlivingcommunity.data.repository.ParkingRepository;
import com.example.smartlivingcommunity.utils.Resource;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(AndroidJUnit4.class)
public class ParkingViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private ParkingRepository mockRepository;

    private ParkingViewModel viewModel;
    private AutoCloseable mockCloseable;

    @Before
    public void setup() throws Exception {
        mockCloseable = MockitoAnnotations.openMocks(this);
        viewModel = new ParkingViewModel(mockRepository);
    }

    @After
    public void tearDown() throws Exception {
        mockCloseable.close();
        clearInvocations(mockRepository);
        reset(mockRepository);
    }

    private void waitForAsyncOperation(Task<?> task) throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        task.addOnCompleteListener(t -> latch.countDown());
        if (!latch.await(5, TimeUnit.SECONDS)) {
            throw new RuntimeException("Task did not complete within 5 seconds");
        }
    }

    @Test
    public void testInstantBookingSuccess() throws Exception {
        InstantBookingModel booking = new InstantBookingModel(
                "123", "456", "John Doe", "Car", "2024-01-01", "10:00-11:00", "PENDING"
        );
        Task<Void> successTask = Tasks.forResult(null);
        when(mockRepository.bookInstantly(any(InstantBookingModel.class))).thenReturn(successTask);

        viewModel.bookInstantly(booking);
        waitForAsyncOperation(successTask);

        Resource<String> result = TestUtils.getOrAwaitValue(viewModel.getBookingStatus());
        assertEquals(Resource.Status.SUCCESS, result.status);
        assertEquals("Booking successful", result.data);
    }

    @Test
    public void testInstantBookingFailure() throws Exception {
        InstantBookingModel booking = new InstantBookingModel(
                "123", "456", "John Doe", "Car", "2024-01-01", "10:00-11:00", "PENDING"
        );
        Task<Void> failureTask = Tasks.forException(new Exception("Network error"));
        when(mockRepository.bookInstantly(any(InstantBookingModel.class))).thenReturn(failureTask);

        viewModel.bookInstantly(booking);
        try {
            waitForAsyncOperation(failureTask);
        } catch (Exception ignored) {}

        Resource<String> result = TestUtils.getOrAwaitValue(viewModel.getBookingStatus());
        assertEquals(Resource.Status.ERROR, result.status);
        assertEquals("Booking failed", result.message);
    }

    @Test
    public void testSubmitParkingRegistration_Success() throws Exception {
        ParkingRegistrationModel registration = new ParkingRegistrationModel(
                "1", "John", "123", "Car", "ABC123",
                "PENDING", "2024-01-01", null, null
        );

        Task<Void> successTask = Tasks.forResult(null);
        when(mockRepository.submitParkingRegistration(any(ParkingRegistrationModel.class)))
                .thenReturn(successTask);

        viewModel.submitParkingRegistration(registration);
        waitForAsyncOperation(successTask);

        Resource<String> result = TestUtils.getOrAwaitValue(viewModel.getRegistrationStatus());
        assertEquals(Resource.Status.SUCCESS, result.status);
        assertEquals("Registration submitted successfully", result.data);
    }

    @Test
    public void testSubmitParkingRegistration_Failure() throws Exception {
        ParkingRegistrationModel registration = new ParkingRegistrationModel(
                "1", "John", "123", "Car", "ABC123",
                "PENDING", "2024-01-01", null, null
        );

        Task<Void> failureTask = Tasks.forException(new Exception("Network error"));
        when(mockRepository.submitParkingRegistration(any(ParkingRegistrationModel.class)))
                .thenReturn(failureTask);

        viewModel.submitParkingRegistration(registration);
        try {
            waitForAsyncOperation(failureTask);
        } catch (Exception ignored) {}

        Resource<String> result = TestUtils.getOrAwaitValue(viewModel.getRegistrationStatus());
        assertEquals(Resource.Status.ERROR, result.status);
        assertEquals("Failed to submit registration", result.message);
    }

    @Test
    public void testLoadRecentBookings_Success() throws Exception {
        List<BookingModel> mockBookings = Arrays.asList(
                new BookingModel("1", "user123", "John Doe", "Car", "2024-01-01", "10:00-11:00", "Pending"),
                new BookingModel("2", "user456", "Jane Smith", "Bike", "2024-01-02", "14:00-15:00", "Confirmed")
        );
        Task<List<BookingModel>> successTask = Tasks.forResult(mockBookings);
        when(mockRepository.getRecentBookings(anyString())).thenReturn(successTask);

        viewModel.loadRecentBookings("userId");
        waitForAsyncOperation(successTask);

        Resource<List<BookingModel>> result = TestUtils.getOrAwaitValue(viewModel.getRecentBookings());
        assertEquals(Resource.Status.SUCCESS, result.status);
        assertNotNull(result.data);
        assertEquals(2, result.data.size());
        assertEquals("John Doe", result.data.get(0).getResidentName());
        assertEquals("2024-01-02", result.data.get(1).getDate());
    }

    @Test
    public void testLoadRecentBookings_Failure() throws Exception {
        String errorMessage = "Network error";
        Task<List<BookingModel>> failureTask = Tasks.forException(new Exception(errorMessage));
        when(mockRepository.getRecentBookings(anyString())).thenReturn(failureTask);

        viewModel.loadRecentBookings("userId");
        try {
            waitForAsyncOperation(failureTask);
        } catch (Exception ignored) {}

        Resource<List<BookingModel>> result = TestUtils.getOrAwaitValue(viewModel.getRecentBookings());
        assertEquals(Resource.Status.ERROR, result.status);
        assertEquals(errorMessage, result.message);
        assertNull(result.data);
    }

    @Test
    public void testBookPermanently_Success() throws Exception {
        PermanentBookingModel booking = new PermanentBookingModel(
                "123", "456", "John Doe", "Car", "2024-01-01", "2024-12-31", "PENDING"
        );
        Task<Void> successTask = Tasks.forResult(null);
        when(mockRepository.bookPermanently(any(PermanentBookingModel.class))).thenReturn(successTask);

        viewModel.bookPermanently(booking);
        waitForAsyncOperation(successTask);

        Resource<String> result = TestUtils.getOrAwaitValue(viewModel.getBookingStatus());
        assertEquals(Resource.Status.SUCCESS, result.status);
        assertEquals("Booking request submitted", result.data);
    }

    @Test
    public void testBookPermanently_Failure() throws Exception {
        PermanentBookingModel booking = new PermanentBookingModel(
                "123", "456", "John Doe", "Car", "2024-01-01", "2024-12-31", "PENDING"
        );
        Task<Void> failureTask = Tasks.forException(new Exception("Network error"));
        when(mockRepository.bookPermanently(any(PermanentBookingModel.class))).thenReturn(failureTask);

        viewModel.bookPermanently(booking);
        try {
            waitForAsyncOperation(failureTask);
        } catch (Exception ignored) {}

        Resource<String> result = TestUtils.getOrAwaitValue(viewModel.getBookingStatus());
        assertEquals(Resource.Status.ERROR, result.status);
        assertEquals("Booking request failed", result.message);
    }
}