package com.example.createbill.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.createbill.data.model.CreateBillModel;
import com.example.createbill.data.repository.CreateBillRepository;

/**
 * ViewModel class responsible for managing the UI-related data for the CreateBill feature.
 * It interacts with the CreateBillRepository to fetch the bill data and check if a monthly bill has already been generated.
 *
 * @author Hasneen Tamanna Totinee
 * @version 1.0
 * @since 13-11-2024
 */
public class CreateBillViewModel extends ViewModel {

    // Repository to handle the data logic and database operations
    private final CreateBillRepository repository;

    // LiveData to store and observe the fetched bill data
    private final MutableLiveData<CreateBillModel> billData = new MutableLiveData<>();

    // LiveData to track the loading state of the data
    private final MutableLiveData<Boolean> isDataLoading = new MutableLiveData<>(false);

    // LiveData to store any error messages encountered while fetching data
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    // LiveData to track if the monthly bill is already generated for the document
    private final MutableLiveData<Boolean> isMonthlyBillGenerated = new MutableLiveData<>(false);

    /**
     * Default constructor. Initializes the repository.
     * Ensures that the repository is correctly initialized (e.g., singleton or simple object).
     */
    public CreateBillViewModel() {
        this.repository = new CreateBillRepository(); // Make sure this is the correct initialization
    }

    /**
     * Constructor with dependency injection for testing purposes.
     * Allows injecting a mock repository in unit tests to test ViewModel behavior.
     *
     * @param repository The repository instance to be injected.
     */
    public CreateBillViewModel(CreateBillRepository repository) {
        this.repository = repository;
    }

    /**
     * Returns the LiveData for the bill data.
     * Observers can subscribe to this LiveData to get updated bill details when they change.
     *
     * @return LiveData containing the bill data.
     */
    public LiveData<CreateBillModel> getBillData() {
        return billData;
    }

    /**
     * Returns the LiveData for the loading state of the data.
     * Observers can subscribe to this LiveData to show a loading spinner or indicator when data is being fetched.
     *
     * @return LiveData containing the loading state.
     */
    public LiveData<Boolean> isDataLoading() {
        return isDataLoading;
    }

    /**
     * Returns the LiveData for error messages.
     * Observers can subscribe to this LiveData to show any error messages to the user when data fetching fails.
     * Was created for passing test case-4 for error handling
     *
     * @return LiveData containing the error message.
     */
    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    /**
     * Returns the LiveData that indicates if the monthly bill has been generated.
     * Observers can subscribe to this LiveData to show a dialog or message if the bill is already generated.
     *
     * @return LiveData containing the monthly bill status.
     */
    public LiveData<Boolean> isMonthlyBillGenerated() {
        return isMonthlyBillGenerated;
    }

    /**
     * Fetches the bill data for a specific document ID.
     * First, checks if the monthly bill has already been generated for the given document ID.
     * If not, it fetches the bill data from the repository and sets it in the LiveData.
     * Once the bill data is fetched successfully, it marks the monthly bill as generated.
     * If the data fetching fails, an error message is set.
     *
     * @param documentID The document ID for which to fetch the bill data.
     */
    public void fetchBillData(String documentID) {
        // Checks if the monthly bill is already generated
        if (repository.isMonthlyBillGenerated(documentID)) {
            isMonthlyBillGenerated.setValue(true);  // Mark as already generated
            return;  // Exits early as the bill is already generated
        }

        // Fetches bill data from the repository and observe the result
        repository.fetchBillData(documentID).observeForever(bill -> {
            if (bill != null) {
                // Successfully fetched bill data
                billData.setValue(bill);
                // Marks the monthly bill as generated after fetching data
                repository.setMonthlyBillGenerated(documentID);
            } else {
                // Sets error message if bill data fetching fails
                errorMessage.setValue("Failed to fetch bill data");
            }
        });
    }
}
