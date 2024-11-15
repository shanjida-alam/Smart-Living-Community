package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartlivingcommunity.data.model.ComplaintModel;
import com.example.smartlivingcommunity.data.repository.ComplaintRepository;
import com.example.smartlivingcommunity.data.repository.ComplaintRepositoryImplementation;

/**
 * The ViewModel class for managing complaint-related data and operations in the Smart Living Community app.
 * <p>
 * This class serves as a bridge between the UI layer and the data layer (repository), handling business logic
 * and ensuring separation of concerns. It is responsible for validating and submitting complaints
 * and verifying user-specific data, such as unit code ownership.
 */
public class ComplaintViewModel extends ViewModel {

    /**
     * Repository instance for interacting with complaint data.
     */
    private final ComplaintRepository repository;

    /**
     * Default constructor for creating a new instance of {@code ComplaintViewModel}.
     * <p>
     * Initializes the ViewModel with a default implementation of the {@link ComplaintRepository}.
     */
    public ComplaintViewModel() {
        this.repository = new ComplaintRepositoryImplementation();
    }

    /**
     * Overloaded constructor for creating a new instance of {@code ComplaintViewModel} with a custom repository.
     * <p>
     * This constructor is primarily used for dependency injection, such as during unit testing.
     *
     * @param repository The custom repository to be used for interacting with complaint data.
     */
    public ComplaintViewModel(ComplaintRepository repository) {
        this.repository = repository;
    }

    /**
     * Submits a complaint to the repository after validating its contents and verifying ownership if necessary.
     * <p>
     * - If the complaint's user role is "Resident," unit code ownership is verified in Firebase.
     * - If valid, the complaint is submitted to the repository for storage.
     *
     * @param complaint The {@link ComplaintModel} object containing complaint details to be submitted.
     * @return A {@link LiveData} object that emits {@code true} if the complaint submission succeeds, or {@code false} otherwise.
     */
    public LiveData<Boolean> submitComplaint(ComplaintModel complaint) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();

        // Validate the complaint data
        if (!isValidComplaint(complaint)) {
            result.setValue(false);
            return result;
        }

        // Handle unit code verification for "Resident" role
        if ("Resident".equals(complaint.getUserRole())) {
            MutableLiveData<Boolean> finalResult = new MutableLiveData<>();

            // Verify unit code ownership in Firebase
            repository.verifyUnitCodeInFirebase(complaint.getUnitCode(), complaint.getEmailAddress())
                    .observeForever(isVerified -> {
                        if (Boolean.TRUE.equals(isVerified)) {
                            // If verified, submit the complaint
                            repository.submitComplaint(complaint).observeForever(finalResult::setValue);
                        } else {
                            finalResult.setValue(false);
                        }
                    });

            return finalResult;
        } else {
            // For non-resident roles, directly submit the complaint
            return repository.submitComplaint(complaint);
        }
    }

    /**
     * Validates the contents of a {@link ComplaintModel}.
     * <p>
     * Ensures that all mandatory fields are non-null, adhere to specific format constraints, and fall within acceptable limits.
     *
     * @param complaint The {@link ComplaintModel} to validate.
     * @return {@code true} if the complaint is valid, {@code false} otherwise.
     */
    private boolean isValidComplaint(ComplaintModel complaint) {
        if (complaint == null) return false;

        // Extract fields from the complaint object
        String unitCode = complaint.getUnitCode();
        String userName = complaint.getUserName();
        String userRole = complaint.getUserRole();
        String phoneNumber = complaint.getPhoneNumber();
        String email = complaint.getEmailAddress();
        String description = complaint.getComplaintDescription();

        // Validate fields
        return !(unitCode == null || unitCode.trim().isEmpty() || // Unit code cannot be empty
                userName == null || userName.trim().isEmpty() ||  // User name cannot be empty
                userRole == null || userRole.trim().isEmpty() ||  // User role cannot be empty
                phoneNumber == null || !phoneNumber.matches("\\d{11}") || // Phone number must be 11 digits
                email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$") || // Email must be valid
                description == null || description.trim().isEmpty() || // Description cannot be empty
                description.length() > 1000); // Description must not exceed 1000 characters
    }
}
