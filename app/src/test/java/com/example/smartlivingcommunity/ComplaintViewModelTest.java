package com.example.smartlivingcommunity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

// ComplaintViewModelTest.java
@RunWith(MockitoJUnitRunner.class)
public class ComplaintViewModelTest {
    @Mock
    private ComplaintRepository repository;
    private ComplaintViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        viewModel = new ComplaintViewModel(repository);
    }

    @Test
    public void submitComplaint_withValidData_shouldSucceed() {
        // Setup
        ComplaintModel complaint = new ComplaintModel(
                 "A101", "John Doe",
                "Resident", "1234567890", "john@gmail.com", "Test complaint"
        );

        // Mock Firebase unit code verification
        MutableLiveData<Boolean> verificationResult = new MutableLiveData<>();
        verificationResult.setValue(true);
        when(repository.verifyUnitCodeInFirebase("A101", "john@gmail.com"))
                .thenReturn(verificationResult);

        // Mock complaint submission
        MutableLiveData<Boolean> submissionResult = new MutableLiveData<>();
        submissionResult.setValue(true);
        when(repository.submitComplaint(any(ComplaintModel.class)))
                .thenReturn(submissionResult);

        // Execute
        LiveData<Boolean> result = viewModel.submitComplaint(complaint);

        // Verify
        assertEquals(true, result.getValue());
    }

    @Test
    public void submitComplaint_withEmptyUnitCode_shouldFail() {
        // Setup
        ComplaintModel complaint = new ComplaintModel();
//        complaint.setComplaintId("COMP001");
        complaint.setUnitCode("");
        complaint.setUserName("John Doe");
        complaint.setUserRole("Resident");
        complaint.setPhoneNumber("1234567890");
        complaint.setEmailAddress("john@gmail.com");
        complaint.setComplaintDescription("Test complaint");

        // Execute
        LiveData<Boolean> result = viewModel.submitComplaint(complaint);

        // Verify
        assertEquals(false, result.getValue());
        verify(repository, never()).submitComplaint(any(ComplaintModel.class));
    }

    @Test
    public void submitComplaint_withEmptyDescription_shouldFail() {
        ComplaintModel complaint = new ComplaintModel(
                "A101", "John Doe",
                "Resident", "1234567890", "john@gmail.com", ""
        );

        LiveData<Boolean> result = viewModel.submitComplaint(complaint);

        assertEquals(false, result.getValue());
        verify(repository, never()).submitComplaint(any(ComplaintModel.class));
    }

    @Test
    public void submitComplaint_withInvalidPhoneNumber_shouldFail() {
        ComplaintModel complaint = new ComplaintModel(
                "A101", "John Doe",
                "Resident", "123", "john@gmail.com", "Test complaint"
        );

        LiveData<Boolean> result = viewModel.submitComplaint(complaint);

        assertEquals(false, result.getValue());
        verify(repository, never()).submitComplaint(any(ComplaintModel.class));
    }

    @Test
    public void submitComplaint_withEmptyUserName_shouldFail() {
        ComplaintModel complaint = new ComplaintModel(
                 "A101", "",
                "Resident", "1234567890","john@gmail.com", "Test complaint"
        );

        LiveData<Boolean> result = viewModel.submitComplaint(complaint);

        assertEquals(false, result.getValue());
        verify(repository, never()).submitComplaint(any(ComplaintModel.class));
    }

    @Test
    public void submitComplaint_withEmptyUserRole_shouldFail() {
        ComplaintModel complaint = new ComplaintModel(
                 "A101", "John Doe",
                "", "1234567890", "john@gmail.com","Test complaint"
        );

        LiveData<Boolean> result = viewModel.submitComplaint(complaint);

        assertEquals(false, result.getValue());
        verify(repository, never()).submitComplaint(any(ComplaintModel.class));
    }

    @Test
    public void submitComplaint_withNullComplaint_shouldFail() {
        LiveData<Boolean> result = viewModel.submitComplaint(null);
        assertEquals(false, result.getValue());
        verify(repository, never()).submitComplaint(any());
    }

    @Test
    public void submitComplaint_withEmptyEmailAddress_shouldFail() {
        ComplaintModel complaint = new ComplaintModel(
                 "A101", "John Doe",
                "Resident", "1234567890","", "Test complaint"
        );
        complaint.setEmailAddress("");

        LiveData<Boolean> result = viewModel.submitComplaint(complaint);
        assertEquals(false, result.getValue());
    }

    @Test
    public void submitComplaint_withInvalidEmailFormat_shouldFail() {
        ComplaintModel complaint = new ComplaintModel(
                 "A101", "John Doe",
                "Resident", "1234567890", "johnmail.com", "Test complaint"
        );
        complaint.setEmailAddress("invalid.email");

        LiveData<Boolean> result = viewModel.submitComplaint(complaint);
        assertEquals(false, result.getValue());
    }

    @Test
    public void submitComplaint_withDescriptionTooLong_shouldFail() {
        String longDescription = String.join("", Collections.nCopies(1001, "a")); // Creates 1001 character string
        ComplaintModel complaint = new ComplaintModel(
                 "A101", "John Doe",
                "Resident", "1234567890", "john@gmail.com", longDescription
        );

        LiveData<Boolean> result = viewModel.submitComplaint(complaint);
        assertEquals(false, result.getValue());
    }

    @Test
    public void submitComplaint_withMultipleErrors_shouldFail() {
        ComplaintModel complaint = new ComplaintModel(
                 "", "",
                "", "123", "", ""
        );

        LiveData<Boolean> result = viewModel.submitComplaint(complaint);
        assertEquals(false, result.getValue());
    }

    @Test
    public void submitComplaint_withSpecialCharactersInName_shouldSucceed() {
        ComplaintModel complaint = new ComplaintModel(
                 "A101", "John O'Doe-Smith",
                "Resident", "1234567890", "john@gmail.com", "Test complaint"
        );

        // Mock Firebase unit code verification
        MutableLiveData<Boolean> verificationResult = new MutableLiveData<>();
        verificationResult.setValue(true);
        when(repository.verifyUnitCodeInFirebase("A101", "john@gmail.com"))
                .thenReturn(verificationResult);

        // Mock complaint submission
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();
        liveData.setValue(true);
        when(repository.submitComplaint(any(ComplaintModel.class)))
                .thenReturn(liveData);

        LiveData<Boolean> result = viewModel.submitComplaint(complaint);
        assertEquals(true, result.getValue());
    }

    @Test
    public void submitComplaint_withMinimumLengthDescription_shouldSucceed() {
        ComplaintModel complaint = new ComplaintModel(
                 "A101", "John Doe",
                "Resident", "1234567890", "john@gmail.com", "Brief"
        );

        // Mock Firebase verification
        MutableLiveData<Boolean> verificationResult = new MutableLiveData<>();
        verificationResult.setValue(true);
        when(repository.verifyUnitCodeInFirebase("A101", "john@gmail.com"))
                .thenReturn(verificationResult);

        // Mock complaint submission
        MutableLiveData<Boolean> submissionResult = new MutableLiveData<>();
        submissionResult.setValue(true);
        when(repository.submitComplaint(any(ComplaintModel.class)))
                .thenReturn(submissionResult);

        LiveData<Boolean> result = viewModel.submitComplaint(complaint);
        assertEquals(true, result.getValue());
    }

    @Test
    public void submitComplaint_withWhitespaceInFields_shouldFail() {
        ComplaintModel complaint = new ComplaintModel(
                 " ", "John Doe",
                "Resident", "1234567890", "john@gmail.com", " "
        );

        LiveData<Boolean> result = viewModel.submitComplaint(complaint);
        assertEquals(false, result.getValue());
        verify(repository, never()).submitComplaint(any(ComplaintModel.class));
    }

    @Test
    public void submitComplaint_withMaximumLengthDescription_shouldSucceed() {
        String maxDescription = String.join("", Collections.nCopies(1000, "a"));
        ComplaintModel complaint = new ComplaintModel(
                 "A101", "John Doe",
                "Resident", "1234567890", "john@gmail.com", maxDescription
        );

        // Mock both the verification and submission
        MutableLiveData<Boolean> verificationResult = new MutableLiveData<>();
        verificationResult.setValue(true);
        when(repository.verifyUnitCodeInFirebase("A101", "john@gmail.com"))
                .thenReturn(verificationResult);

        MutableLiveData<Boolean> submissionResult = new MutableLiveData<>();
        submissionResult.setValue(true);
        when(repository.submitComplaint(any(ComplaintModel.class)))
                .thenReturn(submissionResult);

        LiveData<Boolean> result = viewModel.submitComplaint(complaint);
        assertEquals(true, result.getValue());
    }

    @Test
    public void submitComplaint_withDifferentUserRoles_shouldSucceed() {
        String[] validRoles = {"Admin", "Manager", "Security", "Maintenance"};

        for (String role : validRoles) {
            ComplaintModel complaint = new ComplaintModel(
                     "A101", "John Doe",
                    role, "1234567890", "john@gmail.com", "Test complaint"
            );

            MutableLiveData<Boolean> liveData = new MutableLiveData<>();
            liveData.setValue(true);
            when(repository.submitComplaint(any(ComplaintModel.class))).thenReturn(liveData);

            LiveData<Boolean> result = viewModel.submitComplaint(complaint);
            assertEquals(true, result.getValue());
        }
    }

    @Test
    public void submitComplaint_withNonExistentUnitInFirebase_shouldFail() {
        ComplaintModel complaint = new ComplaintModel(
                 "X999", "John Doe",
                "Resident", "1234567890", "john@gmail.com", "Test complaint"
        );

        when(repository.verifyUnitCodeInFirebase("X999", "john@gmail.com"))
                .thenReturn(new MutableLiveData<>(false));

        LiveData<Boolean> result = viewModel.submitComplaint(complaint);
        assertEquals(false, result.getValue());
    }

    @Test
    public void submitComplaint_withValidFirebaseUnit_shouldSucceed() {
        ComplaintModel complaint = new ComplaintModel(
                 "M-Q1NO", "John Doe",
                "Resident", "1234567890", "john@gmail.com", "Test complaint"
        );

        when(repository.verifyUnitCodeInFirebase("M-Q1NO", "john@gmail.com"))
                .thenReturn(new MutableLiveData<>(true));
        when(repository.submitComplaint(any(ComplaintModel.class)))
                .thenReturn(new MutableLiveData<>(true));

        LiveData<Boolean> result = viewModel.submitComplaint(complaint);
        assertEquals(true, result.getValue());
    }

    @Test
    public void submitComplaint_shouldGenerateUniqueId() {
        ComplaintModel complaint1 = new ComplaintModel(
                "A101", "John Doe", "Resident",
                "1234567890", "john@gmail.com", "Test"
        );

        ComplaintModel complaint2 = new ComplaintModel(
                "A101", "John Doe", "Resident",
                "1234567890", "john@gmail.com", "Test"
        );

        assertNotEquals(complaint1.getComplaintId(), complaint2.getComplaintId());
        assertTrue(complaint1.getComplaintId().startsWith("COMP"));
        assertTrue(complaint2.getComplaintId().startsWith("COMP"));
    }
}