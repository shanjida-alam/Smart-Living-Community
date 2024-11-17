package com.example.smartlivingcommunity.data.repository;

import com.example.smartlivingcommunity.data.model.BookingModel;
import com.example.smartlivingcommunity.data.model.InstantBookingModel;
import com.example.smartlivingcommunity.data.model.PermanentBookingModel;
import com.example.smartlivingcommunity.data.model.ParkingRegistrationModel;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingRepository {
    private final FirebaseFirestore db;
    private static final String COLLECTION_PARKING = "parking";
    private static final String COLLECTION_INSTANT_BOOKINGS = "instantBookings";
    private static final String COLLECTION_PERMANENT_BOOKINGS = "permanentBookings";

    public ParkingRepository() {
        this.db = FirebaseFirestore.getInstance();
    }

    public Task<Void> submitParkingRegistration(ParkingRegistrationModel registration) {
        return db.collection(COLLECTION_PARKING)
                .document()
                .set(registration);
    }

    public Task<Void> bookInstantly(InstantBookingModel booking) {
        if (booking == null) {
            return Tasks.forException(new IllegalArgumentException("Booking is null"));
        }
        return db.collection(COLLECTION_INSTANT_BOOKINGS)
                .document()
                .set(booking);
    }

    public Task<Void> bookPermanently(PermanentBookingModel booking) {
        return db.collection(COLLECTION_PERMANENT_BOOKINGS)
                .document()
                .set(booking);
    }

    public Task<List<BookingModel>> getRecentBookings(String userId) {
        return db.collection(COLLECTION_INSTANT_BOOKINGS)
                .whereEqualTo("residentId", userId)
                .orderBy("date", Query.Direction.DESCENDING)
                .limit(10)
                .get()
                .continueWith(task -> {
                    List<BookingModel> bookings = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        bookings.add(document.toObject(BookingModel.class));
                    }
                    return bookings;
                });
    }
    public Task<QuerySnapshot> getParkingRegistrations(String residentId) {
        return db.collection(COLLECTION_PARKING)
                .whereEqualTo("residentId", "sample")
                .get();
    }

    public Task<QuerySnapshot> getPendingRegistrations() {
        return db.collection(COLLECTION_PARKING)
                .whereEqualTo("parkingStatus", "PENDING")
                .get();
    }

    public Task<QuerySnapshot> getResidentParkingRegistrations(String residentId) {
        return db.collection(COLLECTION_PARKING)
                .whereEqualTo("residentId", "sample")
                .orderBy("requestDate", Query.Direction.DESCENDING)
                .get();
    }

    public Task<Void> updateRegistrationStatus(String registrationId, String status, String reason) {
        Map<String, Object> updates = new HashMap<>();
        updates.put("parkingStatus", status);
        if (status.equals("REJECTED")) {
            updates.put("rejectionReason", reason);
        }
        if (status.equals("APPROVED")) {
            updates.put("approvalDate", new Date().toString());
        }

        return db.collection(COLLECTION_PARKING)
                .document(registrationId)
                .update(updates);
    }
}