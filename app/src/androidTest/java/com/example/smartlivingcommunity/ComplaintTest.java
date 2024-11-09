package com.example.smartlivingcommunity;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.smartlivingcommunity.data.model.ComplaintModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ComplaintTest {

    private DatabaseReference databaseRef;
    private String testComplaintId;

    @Before
    public void setUp() {
        // Initialize Firebase database reference
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseRef = firebaseDatabase.getReference("complaints");
    }

    @Test
    public String RegisterComplaint(ComplaintModel testComplaint) {
        // Initialize a dummy ComplaintModel
        ComplaintModel dummyComplaint = new ComplaintModel("12345", "A-101", "John Doe", "Resident", "1234567890", "Test complaint description");

        // Push to Firebase and retrieve generated complaintId
        testComplaintId = databaseRef.push().getKey();
        assert testComplaintId != null;
        databaseRef.child(testComplaintId).setValue(dummyComplaint)
                .addOnSuccessListener(aVoid -> assertNotNull(testComplaintId))
                .addOnFailureListener(e -> fail("RegisterComplaint failed: " + e.getMessage()));
        return null;
    }

    @Test
    public void getComplaint(String complaintId) {
        // Fetch the data from Firebase based on complaintId
        databaseRef.child(testComplaintId).get()
                .addOnSuccessListener(dataSnapshot -> {
                    ComplaintModel fetchedComplaint = dataSnapshot.getValue(ComplaintModel.class);
                    assertNotNull(fetchedComplaint);
                    assertEquals("John Doe", fetchedComplaint.getUserName());
                })
                .addOnFailureListener(e -> fail("getComplaint failed: " + e.getMessage()));
    }

    @Test
    public void testCase1() {
        // Dummy data setup
        ComplaintModel testComplaint = new ComplaintModel("54321", "B-102", "Jane Doe", "Tenant", "0987654321", "Sample complaint description");

        // Register Complaint
        String complaintId = RegisterComplaint(testComplaint);

        // Retrieve and verify data
        getComplaint(complaintId);
    }

    @Test
    public void DeleteComplaint() {
        // Delete complaint from Firebase based on complaintId
        databaseRef.child(testComplaintId).removeValue()
                .addOnSuccessListener(aVoid -> assertNull(databaseRef.child(testComplaintId).get()))
                .addOnFailureListener(e -> fail("DeleteComplaint failed: " + e.getMessage()));
    }

    @After
    public void tearDown() {
        // Clean up the dummy data after testing
        if (testComplaintId != null) {
            databaseRef.child(testComplaintId).removeValue();
        }
    }
}
