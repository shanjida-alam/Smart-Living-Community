package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartlivingcommunity.data.model.BookingModel;
import com.example.smartlivingcommunity.data.model.InstantBookingModel;
import com.example.smartlivingcommunity.data.model.ParkingRegistrationModel;
import com.example.smartlivingcommunity.data.model.PermanentBookingModel;
import com.example.smartlivingcommunity.data.repository.ParkingRepository;
import com.example.smartlivingcommunity.utils.Resource;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ParkingViewModel extends ViewModel {
    private final ParkingRepository repository;
    private final MutableLiveData<List<ParkingRegistrationModel>> registrations;
    private final MutableLiveData<Resource<String>> registrationStatus;
    private final MutableLiveData<Resource<String>> bookingStatus = new MutableLiveData<>();
    private final MutableLiveData<Resource<List<BookingModel>>> recentBookings = new MutableLiveData<>();
    private final MutableLiveData<Resource<String>> error = new MutableLiveData<>();

    public ParkingViewModel(ParkingRepository repository){
        this.repository = repository;
        registrations = new MutableLiveData<>();
        registrationStatus = new MutableLiveData<>();
    }

    public ParkingViewModel() {
        repository = new ParkingRepository();
        registrations = new MutableLiveData<>();
        registrationStatus = new MutableLiveData<>();
    }

    public LiveData<Resource<String>> getBookingStatus() {
        return bookingStatus;
    }
    public void bookInstantly(InstantBookingModel booking) {
        bookingStatus.setValue(Resource.loading(null));

        // Call the repository method to book instantly
        Task<Void> task = repository.bookInstantly(booking);

        // Add null check to prevent NullPointerException
        if (task != null) {
            task.addOnSuccessListener(aVoid -> {
                        bookingStatus.setValue(Resource.success("Booking successful"));
                    })
                    .addOnFailureListener(e -> {
                        bookingStatus.setValue(Resource.error("Booking failed", null));
                    });
        } else {
            // Handle the case where the task is null
            bookingStatus.setValue(Resource.error("Booking failed: Task is null", null));
        }
    }

    public void bookPermanently(PermanentBookingModel booking) {
        bookingStatus.setValue(Resource.loading(null));

        // Call the repository method to book permanently
        repository.bookPermanently(booking)
                .addOnSuccessListener(aVoid -> {
                    bookingStatus.setValue(Resource.success("Booking request submitted"));
                })
                .addOnFailureListener(e -> {
                    bookingStatus.setValue(Resource.error("Booking request failed", null));
                });
    }

    public LiveData<Resource<List<BookingModel>>> getRecentBookings() {
        return recentBookings;
    }

    public LiveData<Resource<String>> getError() {
        return error;
    }
    public void loadRecentBookings(String userId) {
        recentBookings.setValue(Resource.loading(null));
        repository.getRecentBookings(userId)
                .addOnSuccessListener(bookings ->
                        recentBookings.setValue(Resource.success(bookings)))
                .addOnFailureListener(e ->
                        recentBookings.setValue(Resource.error(e.getMessage(), null)));
    }

    public void submitParkingRegistration(ParkingRegistrationModel registration) {
        registrationStatus.setValue(Resource.loading(null));
        repository.submitParkingRegistration(registration)
                .addOnSuccessListener(aVoid ->
                        registrationStatus.setValue(Resource.success("Registration submitted successfully")))
                .addOnFailureListener(e ->
                        registrationStatus.setValue(Resource.error("Failed to submit registration", null)));
    }

    public LiveData<List<ParkingRegistrationModel>> getRegistrations(String residentId) {
        repository.getParkingRegistrations(residentId)
                .addOnSuccessListener(documents -> {
                    List<ParkingRegistrationModel> registrationList = new ArrayList<>();
                    for (DocumentSnapshot document : documents) {
                        registrationList.add(document.toObject(ParkingRegistrationModel.class));
                    }
                    registrations.setValue(registrationList);
                });
        return registrations;
    }

    public LiveData<Resource<String>> getRegistrationStatus() {
        return registrationStatus;
    }
}