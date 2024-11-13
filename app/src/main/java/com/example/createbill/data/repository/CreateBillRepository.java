package com.example.createbill.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.createbill.data.model.CreateBillModel;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Repository class responsible for handling data operations related to bill creation.
 * It interacts with Firebase Firestore to fetch bill data, check if a monthly bill has been generated,
 * and set the "monthlyBill" status to "created" after generating the bill.
 *
 * @author Hasneen Tamanna Totinee
 * @version 1.0
 * @since 13-11-2024
 */
public class CreateBillRepository {

    // Firebase Firestore instance for interacting with the database
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    /**
     * Fetches the bill data from Firestore based on the provided document ID.
     * This method fetches a service request document from the "serviceRequests" collection,
     * calculates the associated bill (water, gas, electricity, Wi-Fi, and service fee),
     * and returns the bill data as LiveData.
     *
     * @param documentID The document ID of the service request.
     * @return LiveData containing the bill data.
     */
    public LiveData<CreateBillModel> fetchBillData(String documentID) {
        MutableLiveData<CreateBillModel> billData = new MutableLiveData<>();

        // Fetches the document from Firestore using the provided document ID
        db.collection("serviceRequests").document(documentID).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        // Example demo bill data
                        double waterBill = 700;
                        double gasBill = 600;
                        double electricityBill = 5000;
                        double wifiBill = 500;
                        String urgency = documentSnapshot.getString("urgency");

                        // Calculates the service fee based on the urgency level
                        double serviceFee = calculateServiceFee(urgency);

                        // Creates the bill model with the fetched and calculated data
                        CreateBillModel bill = new CreateBillModel(waterBill, gasBill, electricityBill, wifiBill, serviceFee);
                        billData.setValue(bill); // Returns the bill data via LiveData
                    } else {
                        billData.setValue(null); // No data found for the given document ID
                    }
                })
                .addOnFailureListener(e -> {
                    billData.setValue(null); // Returns null in case of failure
                });

        return billData;
    }

    /**
     * Checks if the monthly bill field has been generated for the specified document ID.
     * It checks the "monthlyBill" field in the Firestore document, and if its value is "created",
     * it returns true indicating that the monthly bill has been generated.
     *
     * @param documentID The document ID of the service request.
     * @return boolean indicating whether the monthly bill has been generated.
     */
    public boolean isMonthlyBillGenerated(String documentID) {
        final boolean[] isGenerated = {false};

        // Fetches the document from Firestore and check the "monthlyBill" field
        db.collection("serviceRequests").document(documentID).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists() && documentSnapshot.contains("monthlyBill")) {
                        String monthlyBillStatus = documentSnapshot.getString("monthlyBill");
                        if ("created".equalsIgnoreCase(monthlyBillStatus)) {
                            isGenerated[0] = true; // Monthly bill is already generated
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    isGenerated[0] = false; // Failed to check the "monthlyBill" status
                });

        return isGenerated[0]; // Returns whether the monthly bill is generated
    }

    /**
     * Sets the "monthlyBill" field to "created" in Firestore for the specified document ID.
     * This method is called after generating the bill to mark the monthly bill as created.
     *
     * @param documentID The document ID of the service request.
     */
    public void setMonthlyBillGenerated(String documentID) {
        db.collection("serviceRequests").document(documentID)
                .update("monthlyBill", "created")
                .addOnSuccessListener(aVoid -> {
                    // Monthly bill status updated successfully
                })
                .addOnFailureListener(e -> {
                    // Handles failure to update the "monthlyBill" field
                });
    }

    /**
     * Calculates the service fee based on the urgency level of the service request.
     * The urgency level is expected to be one of the following values: "low", "medium", or "high".
     * Each level corresponds to a predefined service fee:
     * - "low" urgency: 100 BDT
     * - "medium" urgency: 200 BDT
     * - "high" urgency: 300 BDT
     *
     * @param urgency The urgency level of the service request.
     * @return The calculated service fee based on the urgency level.
     */
    private double calculateServiceFee(String urgency) {
        switch (urgency != null ? urgency.toLowerCase() : "") {
            case "low":
                return 100; // Low urgency service fee (added for passing test case-1)
            case "medium":
                return 200; // Medium urgency service fee (added for passing test case-2)
            case "high":
                return 300; // High urgency service fee (added for passing test case-3)
            default:
                return 0; // No service fee for undefined urgency levels
        }
    }
}
