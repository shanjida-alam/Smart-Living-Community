package com.example.smartlivingcommunity.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartlivingcommunity.data.model.CreateBillModel;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

/**
 * This viewmodel class handles the logic and communication between CreareBillModel and CreateBillFragment
 *
 * @author Hasneen Tamanna Totinee
 * @version 1.0
 * @since 15-11-2024
 */

public class CreateBillViewModel extends ViewModel {

    // Tag for logging
    private static final String TAG = "CreateBillViewModel";

    // Firestore instance to access Firebase database
    private FirebaseFirestore firestore;

    // MutableLiveData to store and observe bill data
    private MutableLiveData<CreateBillModel> billData;

    // MutableLiveData to observe if the monthly bill has already been generated
    private MutableLiveData<Boolean> monthlyBillGenerated;

    // ListenerRegistration to manage Firestore snapshot listener
    private ListenerRegistration listenerRegistration;

    /**
     * Constructor initializes Firestore, LiveData objects and prepares for listening to Firestore updates.
     */
    public CreateBillViewModel() {
        firestore = FirebaseFirestore.getInstance(); // Initialize Firestore instance
        billData = new MutableLiveData<>(); // Initialize LiveData to hold the bill data
        monthlyBillGenerated = new MutableLiveData<>(); // Initialize LiveData for monthly bill generation status
    }

    /**
     * Fetches bill data from Firestore for a given document ID and listens to any changes.
     * The method retrieves bill data, calculates service fee, and checks if the bill has already been generated.
     *
     * @param documentID The document ID of the service request to fetch data for
     */
    public void fetchBillData(String documentID) {
        // Check if the document ID is valid
        if (documentID == null || documentID.isEmpty()) {
            Log.e(TAG, "Document ID is null or empty");
            return; // Exit if the document ID is invalid
        }

        // Get a reference to the document in the Firestore collection
        DocumentReference documentReference = firestore.collection("serviceRequests").document(documentID);

        // Listen to changes in the document
        listenerRegistration = documentReference.addSnapshotListener((documentSnapshot, e) -> {
            // Handle errors in listening
            if (e != null) {
                Log.w(TAG, "Listen failed", e);
                return;
            }

            // Check if the document exists and extract its data
            if (documentSnapshot != null && documentSnapshot.exists()) {
                try {
                    // Extract urgency from the document to calculate service fee
                    String urgency = documentSnapshot.getString("urgency");
                    double serviceFee = calculateServiceFee(urgency); // Calculate service fee based on urgency

                    // Calculate bill amounts (dummy values for now)
                    double waterBill = 800; // Demo value for water bill
                    double gasBill = 600;   // Demo value for gas bill
                    double electricityBill = 5000; // Demo value for electricity bill
                    double wifiBill = 900;  // Demo value for Wi-Fi bill

                    // Calculate the total bill by summing up all the charges
                    double totalBill = waterBill + gasBill + electricityBill + wifiBill + serviceFee;

                    // Create the CreateBillModel to hold the bill data
                    CreateBillModel bill = new CreateBillModel(waterBill, gasBill, electricityBill, wifiBill, serviceFee, totalBill);
                    billData.setValue(bill); // Update LiveData with the new bill data

                    // Check if the monthly bill has already been generated and update the LiveData accordingly
                    Boolean isBillGenerated = documentSnapshot.getBoolean("isBillGenerated");
                    monthlyBillGenerated.setValue(isBillGenerated != null && isBillGenerated);

                } catch (Exception ex) {
                    // Log errors in processing the document data
                    Log.e(TAG, "Error processing document data", ex);
                }
            } else {
                Log.d(TAG, "No such document"); // Log if the document does not exist
            }
        });
    }

    /**
     * Calculates the service fee based on the urgency level.
     * The urgency level determines the fee: low = 100 BDT, medium = 200 BDT, high = 300 BDT.
     *
     * @param urgency The urgency level (low, medium, high)
     * @return The calculated service fee based on urgency
     */
    private double calculateServiceFee(String urgency) {
        double fee = 150; // Default service fee if urgency is not specified
        if (urgency != null) {
            // Determine the service fee based on urgency level
            switch (urgency.toLowerCase()) {
                case "low":
                    fee = 100;
                    break;
                case "medium":
                    fee = 200;
                    break;
                case "high":
                    fee = 300;
                    break;
            }
        }
        return fee; // Return the calculated service fee
    }

    /**
     * Returns the LiveData object containing the bill data, allowing observers to listen for changes.
     *
     * @return LiveData object containing the bill data
     */
    public LiveData<CreateBillModel> getBillData() {
        return billData; // Return LiveData for bill data observation
    }

    /**
     * Returns the LiveData object that indicates whether the monthly bill has already been generated.
     * This allows observers to react to the status of the bill generation.
     *
     * @return LiveData object containing a boolean indicating if the bill has been generated
     */
    public LiveData<Boolean> isMonthlyBillGenerated() {
        return monthlyBillGenerated; // Return LiveData for bill generation status observation
    }

    /**
     * Cleans up the ViewModel by removing the Firestore listener when the ViewModel is cleared.
     * This is important for avoiding memory leaks and unnecessary network calls.
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        // Remove Firestore listener to clean up when ViewModel is no longer in use
        if (listenerRegistration != null) {
            listenerRegistration.remove(); // Detach the listener
        }
        firestore = null; // Nullify Firestore instance
    }
}
