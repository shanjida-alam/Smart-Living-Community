package com.example.smartlivingcommunity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartlivingcommunity.data.model.ComplaintModel;
import com.example.smartlivingcommunity.data.repository.ComplaintRepository;
import com.example.smartlivingcommunity.ui.viewmodel.ComplaintViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

/**
 * Unit tests for the ComplaintViewModel class.
 * This test class validates various functionalities of the ComplaintViewModel.
 * Each test case checks the behavior of the ViewModel under specific conditions.
 *
 * @author Shanjida Alam
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class ComplaintViewModelTest {
    /**
     * Mock repository used for ComplaintViewModel.
     */
    @Mock
    private ComplaintRepository repository;
    /**
     * Instance of the ViewModel being tested.
     */
    private ComplaintViewModel viewModel;
    /**
     * Rule to allow instant execution of background tasks in LiveData.
     */
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    /**
     * Sets up the test environment.
     * Initializes mocks and creates an instance of the ViewModel before each test.
     */
    @Before
    public void setup() {
        /**
         * Initialize the mocks and view model
         */
        MockitoAnnotations.openMocks(this);
        /**
         * Create a new instance of the view model with the mock repository
         */
        viewModel = new ComplaintViewModel(repository);
    }

    /**
     * Tests the submitComplaint method with valid data.
     * Verifies that the complaint is successfully submitted and returns true.
     */
    @Test
    public void submitComplaint_withValidData_shouldSucceed() {
        /**
         * Create a valid complaint test case
         */
        ComplaintModel complaint = new ComplaintModel(
                 "A101", "John Doe",
                "Resident", "12345678905", "john@gmail.com", "Test complaint"
        );

        /**
         * Mock Firebase unit code verification
         */
        MutableLiveData<Boolean> verificationResult = new MutableLiveData<>();
        /**
         * Set the verification result to true
         */
        verificationResult.setValue(true);
        when(repository.verifyUnitCodeInFirebase("A101", "john@gmail.com"))
                .thenReturn(verificationResult);

        /**
         * Mock complaint submission
         */
        MutableLiveData<Boolean> submissionResult = new MutableLiveData<>();
        /**
         * Set the submission result to true
         */
        submissionResult.setValue(true);
        when(repository.submitComplaint(any(ComplaintModel.class)))
                .thenReturn(submissionResult);

        // Execute
        /**
         * Call the submitComplaint method
         */
        LiveData<Boolean> result = viewModel.submitComplaint(complaint);

        // Verify
        /**
         * Assert that the result is true
         */
        assertEquals(true, result.getValue());
    }

    /**
     * Tests the submitComplaint method with empty unit code.
     * Verifies that the submission fails and the repository is not called.
     */
    @Test
    public void submitComplaint_withEmptyUnitCode_shouldFail() {
        /**
         * Create an invalid complaint test case
         */
        ComplaintModel complaint = new ComplaintModel();
        /**
         * Set the unit code to an empty string
         */
        complaint.setUnitCode("");
        /**
         * Set the other fields to valid values
         */
        complaint.setUserName("John Doe");
        complaint.setUserRole("Resident");
        complaint.setPhoneNumber("1234567890");
        complaint.setEmailAddress("john@gmail.com");
        complaint.setComplaintDescription("Test complaint");

        // Execute
        /**
         * Call the submitComplaint method
         */
        LiveData<Boolean> result = viewModel.submitComplaint(complaint);

        // Verify
        /**
         * Assert that the result is false
         */
        assertEquals(false, result.getValue());
        verify(repository, never()).submitComplaint(any(ComplaintModel.class));
    }

    /**
     * Tests the submitComplaint method with empty description.
     * Verifies that the submission fails and the repository is not called.
     */
    @Test
    public void submitComplaint_withEmptyDescription_shouldFail() {
        /**
         * Create an invalid complaint test case
         */
        ComplaintModel complaint = new ComplaintModel(
                "A101", "John Doe",
                "Resident", "1234567890", "john@gmail.com", ""
        );

        /**
         * Call the submitComplaint method
         */
        LiveData<Boolean> result = viewModel.submitComplaint(complaint);
        /**
         * Assert that the result is false
         */
        assertEquals(false, result.getValue());
        /**
         * Verify that the repository was not called
         */
        verify(repository, never()).submitComplaint(any(ComplaintModel.class));
    }

    /**
     * Tests the submitComplaint method with invalid phone number.
     * Verifies that the submission fails and the repository is not called.
     */
    @Test
    public void submitComplaint_withInvalidPhoneNumber_shouldFail() {
        /**
         * Create an invalid complaint test case
         */
        ComplaintModel complaint = new ComplaintModel(
                "A101", "John Doe",
                "Resident", "123", "john@gmail.com", "Test complaint"
        );

        /**
         * Call the submitComplaint method
         */
        LiveData<Boolean> result = viewModel.submitComplaint(complaint);

        /**
         * Assert that the result is false
         */
        assertEquals(false, result.getValue());
        /**
         * Verify that the repository was not called
         */
        verify(repository, never()).submitComplaint(any(ComplaintModel.class));
    }

    /**
     * Tests the submitComplaint method with empty user name.
     * Verifies that the submission fails and the repository is not called.
     */
    @Test
    public void submitComplaint_withEmptyUserName_shouldFail() {
        /**
         * Create an invalid complaint test case
         */
        ComplaintModel complaint = new ComplaintModel(
                 "A101", "",
                "Resident", "1234567890","john@gmail.com", "Test complaint"
        );

        /**
         * Call the submitComplaint method
         */
        LiveData<Boolean> result = viewModel.submitComplaint(complaint);

        /**
         * Assert that the result is false
         */
        assertEquals(false, result.getValue());
        /**
         * Verify that the repository was not called
         */
        verify(repository, never()).submitComplaint(any(ComplaintModel.class));
    }

    /**
     * Tests the submitComplaint method with empty user role.
     * Verifies that the submission fails and the repository is not called.
     */
    @Test
    public void submitComplaint_withEmptyUserRole_shouldFail() {
        /**
         * Create an invalid complaint test case
         */
        ComplaintModel complaint = new ComplaintModel(
                 "A101", "John Doe",
                "", "1234567890", "john@gmail.com","Test complaint"
        );

        /**
         * Call the submitComplaint method
         */
        LiveData<Boolean> result = viewModel.submitComplaint(complaint);

        /**
         * Assert that the result is false
         */
        assertEquals(false, result.getValue());
        /**
         * Verify that the repository was not called
         */
        verify(repository, never()).submitComplaint(any(ComplaintModel.class));
    }

    /**
     * Test the submitComplaint method with invalid data (null complaint)
     */
    @Test
    public void submitComplaint_withNullComplaint_shouldFail() {
        /**
         * Call the submitComplaint method
         */
        LiveData<Boolean> result = viewModel.submitComplaint(null);
        /**
         * Assert that the result is false
         */
        assertEquals(false, result.getValue());
        /**
         * Verify that the repository was not called
         */
        verify(repository, never()).submitComplaint(any());
    }

    /**
     * Test the submitComplaint method with invalid data (empty email address)
     */
    @Test
    public void submitComplaint_withEmptyEmailAddress_shouldFail() {
        /**
         * Create an invalid complaint test case
         */
        ComplaintModel complaint = new ComplaintModel(
                 "A101", "John Doe",
                "Resident", "1234567890","", "Test complaint"
        );
        complaint.setEmailAddress("");

        /**
         * Call the submitComplaint method
         */
        LiveData<Boolean> result = viewModel.submitComplaint(complaint);
        /**
         * Assert that the result is false
         */
        assertEquals(false, result.getValue());
    }

    /**
     * Tests the submitComplaint method with invalid email format.
     * Verifies that the submission fails.
     */
    @Test
    public void submitComplaint_withInvalidEmailFormat_shouldFail() {
        /**
         * Create an invalid complaint test case
         */
        ComplaintModel complaint = new ComplaintModel(
                 "A101", "John Doe",
                "Resident", "1234567890", "johnmail.com", "Test complaint"
        );
        complaint.setEmailAddress("invalid.email");

        /**
         * Call the submitComplaint method
         */
        LiveData<Boolean> result = viewModel.submitComplaint(complaint);
        /**
         * Assert that the result is false
         */
        assertEquals(false, result.getValue());
    }

    /**
     * Tests the submitComplaint method with a description that exceeds the maximum allowed length.
     * Verifies that the submission fails.
     */
    @Test
    public void submitComplaint_withDescriptionTooLong_shouldFail() {
        /**
         * Creates 1001 character string
         */
        String longDescription = String.join("", Collections.nCopies(1001, "a"));
        /**
         * Create an invalid complaint test case
         */
        ComplaintModel complaint = new ComplaintModel(
                 "A101", "John Doe",
                "Resident", "1234567890", "john@gmail.com", longDescription
        );

        /**
         * Call the submitComplaint method
         */
        LiveData<Boolean> result = viewModel.submitComplaint(complaint);
        /**
         * Assert that the result is false
         */
        assertEquals(false, result.getValue());
    }

    /**
     * Test the submitComplaint method with multiple errors
     */
    @Test
    public void submitComplaint_withMultipleErrors_shouldFail() {
        /**
         * Create an invalid complaint test case
         */
        ComplaintModel complaint = new ComplaintModel(
                 "", "",
                "", "123", "", ""
        );

        /**
         * Call the submitComplaint method
         */
        LiveData<Boolean> result = viewModel.submitComplaint(complaint);
        /**
         * Assert that the result is false
         */
        assertEquals(false, result.getValue());
    }

    /**
     * Test the submitComplaint method with special characters in the name
     */
    @Test
    public void submitComplaint_withSpecialCharactersInName_shouldSucceed() {
        /**
         * Create an invalid complaint test case
         */
        ComplaintModel complaint = new ComplaintModel(
                 "A101", "John O'Doe-Smith",
                "Resident", "12345678905", "john@gmail.com", "Test complaint"
        );

        // Mock Firebase unit code verification
        /**
         * Create a mock MutableLiveData
         */
        MutableLiveData<Boolean> verificationResult = new MutableLiveData<>();
        /**
         * Set the verification result to true
         */
        verificationResult.setValue(true);
        /**
         * Mock the verification method
         */
        when(repository.verifyUnitCodeInFirebase("A101", "john@gmail.com"))
                .thenReturn(verificationResult);

        // Mock complaint submission
        /**
         * Create a mock MutableLiveData
         */
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();
        /**
         * Set the submission result to true
         */
        liveData.setValue(true);
        /**
         * Mock the submission method
         */
        when(repository.submitComplaint(any(ComplaintModel.class)))
                .thenReturn(liveData);

        // Execute
        /**
         * Call the submitComplaint method
         */
        LiveData<Boolean> result = viewModel.submitComplaint(complaint);
        /**
         * Assert that the result is true
         */
        assertEquals(true, result.getValue());
    }

    /**
     * Test the submitComplaint method with a minimum length description
     */
    @Test
    public void submitComplaint_withMinimumLengthDescription_shouldSucceed() {
        /**
         * Create an invalid complaint test case
         */
        ComplaintModel complaint = new ComplaintModel(
                 "A101", "John Doe",
                "Resident", "12345678905", "john@gmail.com", "Brief"
        );

        // Mock Firebase verification
        /**
         * Create a mock MutableLiveData
         */
        MutableLiveData<Boolean> verificationResult = new MutableLiveData<>();
        /**
         * Set the verification result to true
         */
        verificationResult.setValue(true);
        /**
         * Mock the verification method
         */
        when(repository.verifyUnitCodeInFirebase("A101", "john@gmail.com"))
                .thenReturn(verificationResult);

        // Mock complaint submission
        /**
         * Create a mock MutableLiveData
         */
        MutableLiveData<Boolean> submissionResult = new MutableLiveData<>();
        /**
         * Set the submission result to true
         */
        submissionResult.setValue(true);
        /**
         * Mock the submission method
         */
        when(repository.submitComplaint(any(ComplaintModel.class)))
                .thenReturn(submissionResult);

        // Execute
        /**
         * Call the submitComplaint method
         */
        LiveData<Boolean> result = viewModel.submitComplaint(complaint);
        /**
         * Assert that the result is true
         */
        assertEquals(true, result.getValue());
    }

    /**
     * Test the submitComplaint method with whitespace in the fields
     */
    @Test
    public void submitComplaint_withWhitespaceInFields_shouldFail() {
        /**
         * Create an invalid complaint test case
         */
        ComplaintModel complaint = new ComplaintModel(
                 " ", "John Doe",
                "Resident", "1234567890", "john@gmail.com", " "
        );

        /**
         * Call the submitComplaint method
         */
        LiveData<Boolean> result = viewModel.submitComplaint(complaint);
        /**
         * Assert that the result is false
         */
        assertEquals(false, result.getValue());
        /**
         * Verify that the repository was not called
         */
        verify(repository, never()).submitComplaint(any(ComplaintModel.class));
    }

    /**
     * Test the submitComplaint method with a maximum length description
     */
    @Test
    public void submitComplaint_withMaximumLengthDescription_shouldSucceed() {
        /**
         * Creates 1000 character string
         */
        String maxDescription = String.join("", Collections.nCopies(1000, "a"));
        /**
         * Create an invalid complaint test case
         */
        ComplaintModel complaint = new ComplaintModel(
                 "A101", "John Doe",
                "Resident", "12345678905", "john@gmail.com", maxDescription
        );

        // Mock both the verification and submission
        /**
         * Create a mock MutableLiveData
         */
        MutableLiveData<Boolean> verificationResult = new MutableLiveData<>();
        /**
         * Set the verification result to true
         */
        verificationResult.setValue(true);
        /**
         * Mock the verification method
         */
        when(repository.verifyUnitCodeInFirebase("A101", "john@gmail.com"))
                .thenReturn(verificationResult);

        /**
         * Create a mock MutableLiveData
         */
        MutableLiveData<Boolean> submissionResult = new MutableLiveData<>();
        submissionResult.setValue(true);
        /**
         * Mock the submission method
         */
        when(repository.submitComplaint(any(ComplaintModel.class)))
                .thenReturn(submissionResult);

        /**
         * Call the submitComplaint method
         */
        LiveData<Boolean> result = viewModel.submitComplaint(complaint);
        /**
         * Assert that the result is true
         */
        assertEquals(true, result.getValue());
    }

    /**
     * Tests the submitComplaint method with valid data and different user roles.
     * Verifies that the submission succeeds for all valid roles.
     */
    @Test
    public void submitComplaint_withDifferentUserRoles_shouldSucceed() {
        /**
         * Create an array for valid roles
         */
        String[] validRoles = {"Admin", "Manager", "Security", "Maintenance"};

        /**
         * Loop through valid roles
         */
        for (String role : validRoles) {
            ComplaintModel complaint = new ComplaintModel(
                     "A101", "John Doe",
                    role, "12345678905", "john@gmail.com", "Test complaint"
            );

            /**
             * Create a mock MutableLiveData
             */
            MutableLiveData<Boolean> liveData = new MutableLiveData<>();
            /**
             * Set the submission result to true
             */
            liveData.setValue(true);
            /**
             * Mock the submission method
             */
            when(repository.submitComplaint(any(ComplaintModel.class))).thenReturn(liveData);

            /**
             * Call the submitComplaint method
             */
            LiveData<Boolean> result = viewModel.submitComplaint(complaint);
            /**
             * Assert that the result is true
             */
            assertEquals(true, result.getValue());
        }
    }

    /**
     * Test the submitComplaint method with a non-existent unit in Firebase
     */
    @Test
    public void submitComplaint_withNonExistentUnitInFirebase_shouldFail() {
        /**
         * Create an invalid complaint test case
         */
        ComplaintModel complaint = new ComplaintModel(
                "X999", "John Doe",
                "Resident", "12345678901", "john@gmail.com", "Test complaint"
        );

        /**
         * Create a mock MutableLiveData
         */
        MutableLiveData<Boolean> verificationResult = new MutableLiveData<>();
        /**
         * Set the verification result to false
         */
        verificationResult.setValue(false);
        /**
         * Mock the verification method
         */
        when(repository.verifyUnitCodeInFirebase("X999", "john@gmail.com"))
                .thenReturn(verificationResult);

        /**
         * Call the submitComplaint method
         */
        LiveData<Boolean> result = viewModel.submitComplaint(complaint);
        /**
         * Assert that the result is false
         */
        assertEquals(false, result.getValue());
        /**
         * Verify that the repository was not called
         */
        verify(repository).verifyUnitCodeInFirebase("X999", "john@gmail.com");
    }

    /**
     * Test the submitComplaint method with a valid unit in Firebase
     */
    @Test
    public void submitComplaint_withValidFirebaseUnit_shouldSucceed() {
        /**
         * Create an valid complaint test case
         */
        ComplaintModel complaint = new ComplaintModel(
                 "M-Q1NO", "John Doe",
                "Resident", "12345678905", "john@gmail.com", "Test complaint"
        );

        /**
         * Create a mock MutableLiveData
         */
        when(repository.verifyUnitCodeInFirebase("M-Q1NO", "john@gmail.com"))
                .thenReturn(new MutableLiveData<>(true));
        /**
         * Mock the submission method
         */
        when(repository.submitComplaint(any(ComplaintModel.class)))
                .thenReturn(new MutableLiveData<>(true));

        /**
         * Call the submitComplaint method
         */
        LiveData<Boolean> result = viewModel.submitComplaint(complaint);
        /**
         * Assert that the result is true
         */
        assertEquals(true, result.getValue());
    }

    /**
     * Tests the submitComplaint method with valid data and ensures the generated complaint ID is unique.
     */
    @Test
    public void submitComplaint_shouldGenerateUniqueId() {
        /**
         * Create an valid complaint test case for generating unique complaint ID
         */
        ComplaintModel complaint1 = new ComplaintModel(
                "A101", "John Doe", "Resident",
                "1234567890", "john@gmail.com", "Test"
        );

        /**
         * Create an valid complaint test case for generating unique complaint ID
         */
        ComplaintModel complaint2 = new ComplaintModel(
                "A101", "John Doe", "Resident",
                "1234567890", "john@gmail.com", "Test"
        );

        /**
         * Assert that the complaint IDs are not equal
         */
        assertNotEquals(complaint1.getComplaintId(), complaint2.getComplaintId());
        /**
         * Assert that the complaint IDs start with "COMP"
         */
        assertTrue(complaint1.getComplaintId().startsWith("COMP"));
        /**
         * Assert that the complaint IDs start with "COMP"
         */
        assertTrue(complaint2.getComplaintId().startsWith("COMP"));
    }
}