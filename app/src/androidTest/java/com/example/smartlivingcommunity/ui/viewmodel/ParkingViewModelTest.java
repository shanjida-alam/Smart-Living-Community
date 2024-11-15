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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RunWith(AndroidJUnit4.class)
public class ParkingViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private ParkingRepository mockRepository;

    private ParkingViewModel viewModel;
    private CountDownLatch latch;

    private LifecycleOwner getTestLifecycleOwner() {
        return new LifecycleOwner() {
            private LifecycleRegistry lifecycle = new LifecycleRegistry(this);
            {
                lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
            }
            @Override
            public Lifecycle getLifecycle() {
                return lifecycle;
            }
        };
    }

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        viewModel = new ParkingViewModel(mockRepository);
        latch = new CountDownLatch(1);
    }

    @Test
    public void testInstantBookingSuccess() throws Exception {
        // Arrange
        InstantBookingModel booking = new InstantBookingModel(
                "123", "456", "John Doe", "Car", "2024-01-01", "10:00-11:00", "PENDING"
        );
        Task<Void> successTask = Tasks.forResult(null);
        when(mockRepository.bookInstantly(any(InstantBookingModel.class))).thenReturn(successTask);

        // Act
        viewModel.bookInstantly(booking);

        // Wait for async operation
        Tasks.await(successTask, 5, TimeUnit.SECONDS);

        // Get final value after async completion
        Resource<String> result = TestUtils.getOrAwaitValue(viewModel.getBookingStatus());

        // Assert
        assertEquals(Resource.Status.SUCCESS, result.status);
        assertEquals("Booking successful", result.data);
    }

    @Test
    public void testInstantBookingFailure() throws Exception {
        // Arrange
        InstantBookingModel booking = new InstantBookingModel(
                "123", "456", "John Doe", "Car", "2024-01-01", "10:00-11:00", "PENDING"
        );
        Exception mockException = new Exception("Network error");
        Task<Void> failureTask = Tasks.forException(mockException);
        when(mockRepository.bookInstantly(any(InstantBookingModel.class))).thenReturn(failureTask);

        // Add observer to handle async state changes
        viewModel.getBookingStatus().observeForever(result -> {
            if (result.status != Resource.Status.LOADING) {
                latch.countDown();
            }
        });

        // Act
        viewModel.bookInstantly(booking);

        // Wait for non-loading state
        latch.await(5, TimeUnit.SECONDS);

        // Get final value
        Resource<String> result = TestUtils.getOrAwaitValue(viewModel.getBookingStatus());

        // Assert
        assertEquals(Resource.Status.ERROR, result.status);
        assertEquals("Booking failed", result.message);
    }

    @Test
    public void testSubmitParkingRegistration() {
        // Arrange
        ParkingRegistrationModel registration = new ParkingRegistrationModel();
        Task<Void> successTask = Tasks.forResult(null);
        when(mockRepository.submitParkingRegistration(any(ParkingRegistrationModel.class)))
                .thenReturn(successTask);

        // Act
        viewModel.submitParkingRegistration(registration);

        // Assert
        Resource<String> result = TestUtils.getOrAwaitValue(viewModel.getRegistrationStatus());
        assertNotNull(result);
        assertEquals(Resource.Status.SUCCESS, result.status);
        assertEquals("Registration submitted successfully", result.data);
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
        Tasks.await(successTask, 5, TimeUnit.SECONDS);

        Resource<List<BookingModel>> result = TestUtils.getOrAwaitValue(viewModel.getRecentBookings());
        assertEquals(Resource.Status.SUCCESS, result.status);
        assertEquals(2, result.data.size());
        assertEquals("John Doe", result.data.get(0).getResidentName());
        assertEquals("2024-01-02", result.data.get(1).getDate());
    }

    @Test
    public void testLoadRecentBookings_Error() throws Exception {
        Task<List<BookingModel>> failureTask = Tasks.forException(new Exception("Network error"));
        when(mockRepository.getRecentBookings(anyString())).thenReturn(failureTask);

        viewModel.loadRecentBookings("userId");
        try {
            Tasks.await(failureTask, 5, TimeUnit.SECONDS);
        } catch (Exception ignored) {}

        Resource<List<BookingModel>> result = TestUtils.getOrAwaitValue(viewModel.getRecentBookings());
        assertEquals(Resource.Status.ERROR, result.status);
        assertEquals("Network error", result.message);
        assertNull(result.data);
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
        Tasks.await(successTask, 5, TimeUnit.SECONDS);

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

        Exception mockException = new Exception("Network error");
        Task<Void> failureTask = Tasks.forException(mockException);
        when(mockRepository.submitParkingRegistration(any(ParkingRegistrationModel.class)))
                .thenReturn(failureTask);

        viewModel.submitParkingRegistration(registration);
        try {
            Tasks.await(failureTask, 5, TimeUnit.SECONDS);
        } catch (Exception ignored) {}

        Resource<String> result = TestUtils.getOrAwaitValue(viewModel.getRegistrationStatus());
        assertEquals(Resource.Status.ERROR, result.status);
        assertEquals("Failed to submit registration", result.message);
    }


    @Test
    public void testLoadRecentBookings_Failure() throws Exception {
        // Arrange
        String errorMessage = "Network error";
        Task<List<BookingModel>> failureTask = Tasks.forException(new Exception(errorMessage));
        when(mockRepository.getRecentBookings(anyString())).thenReturn(failureTask);

        // Act
        viewModel.loadRecentBookings("userId");
        try {
            Tasks.await(failureTask, 5, TimeUnit.SECONDS);
        } catch (Exception ignored) {}

        // Assert
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
        Tasks.await(successTask, 5, TimeUnit.SECONDS);

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
            Tasks.await(failureTask, 5, TimeUnit.SECONDS);
        } catch (Exception ignored) {}

        Resource<String> result = TestUtils.getOrAwaitValue(viewModel.getBookingStatus());
        assertEquals(Resource.Status.ERROR, result.status);
        assertEquals("Booking request failed", result.message);
    }
}