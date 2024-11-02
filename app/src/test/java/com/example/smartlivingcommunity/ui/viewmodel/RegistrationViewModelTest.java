package com.example.smartlivingcommunity.ui.viewmodel;

import static org.mockito.Mockito.*;

import com.example.smartlivingcommunity.data.model.RegistrationModel;
import com.example.smartlivingcommunity.data.repository.RegistrationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RegistrationViewModelTest {
    private RegistrationViewModel viewModel;

    @Mock
    private RegistrationRepository mockRepository;

    // Initializes the mocks and the view model with a mocked repository before each test runs
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        viewModel = new RegistrationViewModel(mockRepository);
    }

    // Test case: checks if "onEmailExists" callback is invoked when the email already exists
    @Test
    public void testRegisterResident_emailExists_invokesOnEmailExists() {
        RegistrationModel model = new RegistrationModel();  // Empty registration model
        String additionalUnitCode = "R-1234";

        // Mocks for the success, emailExists, and failure callbacks
        Runnable onSuccess = mock(Runnable.class);
        Runnable onEmailExists = mock(Runnable.class);
        Runnable onFailure = mock(Runnable.class);

        // Simulate the "email exists" scenario
        doAnswer(invocation -> {
            Runnable emailExistsCallback = invocation.getArgument(1);  // Retrieve the onEmailExists callback
            emailExistsCallback.run();  // Trigger the onEmailExists callback
            return null;
        }).when(mockRepository).checkEmailExists(eq(model.getEmail()), any(), any());

        // Call the method being tested
        viewModel.registerResident(model, additionalUnitCode, onSuccess, onEmailExists, onFailure);

        // Verify that only the onEmailExists callback was triggered, and not onSuccess or onFailure
        verify(onEmailExists).run();
        verify(onSuccess, never()).run();
        verify(onFailure, never()).run();
    }

    // Test case: checks if "onSuccess" callback is invoked when email does not exist and data storage succeeds
    @Test
    public void testRegisterResident_emailDoesNotExist_invokesOnSuccess() {
        RegistrationModel model = new RegistrationModel();
        String additionalUnitCode = "R-1234";

        // Mocks for the success, emailExists, and failure callbacks
        Runnable onSuccess = mock(Runnable.class);
        Runnable onEmailExists = mock(Runnable.class);
        Runnable onFailure = mock(Runnable.class);

        // Simulate the "email does not exist" scenario
        doAnswer(invocation -> {
            Runnable onSuccessCallback = invocation.getArgument(2);  // Retrieve the onSuccess callback
            onSuccessCallback.run();  // Trigger the onSuccess callback
            return null;
        }).when(mockRepository).checkEmailExists(eq(model.getEmail()), any(), any());

        // Simulate successful data storage
        doAnswer(invocation -> {
            Runnable storeSuccessCallback = invocation.getArgument(2);  // Retrieve the onSuccess callback for storage
            storeSuccessCallback.run();  // Trigger the onSuccess callback
            return null;
        }).when(mockRepository).storeRegistrationData(eq(model), eq(additionalUnitCode), any(), any());

        // Call the method being tested
        viewModel.registerResident(model, additionalUnitCode, onSuccess, onEmailExists, onFailure);

        // Verify that only the onSuccess callback was triggered, and not onEmailExists or onFailure
        verify(onSuccess).run();
        verify(onEmailExists, never()).run();
        verify(onFailure, never()).run();
    }

    // Test case: checks if "onFailure" callback is invoked when data storage fails
    @Test
    public void testRegisterResident_storeFails_invokesOnFailure() {
        RegistrationModel model = new RegistrationModel();
        String additionalUnitCode = "R-1234";

        // Mocks for the success, emailExists, and failure callbacks
        Runnable onSuccess = mock(Runnable.class);
        Runnable onEmailExists = mock(Runnable.class);
        Runnable onFailure = mock(Runnable.class);

        // Simulate the "email does not exist" scenario
        doAnswer(invocation -> {
            Runnable onSuccessCallback = invocation.getArgument(2);  // Retrieve the onSuccess callback
            onSuccessCallback.run();  // Trigger the onSuccess callback
            return null;
        }).when(mockRepository).checkEmailExists(eq(model.getEmail()), any(), any());

        // Simulate data storage failure
        doAnswer(invocation -> {
            Runnable storeFailureCallback = invocation.getArgument(3);  // Retrieve the onFailure callback for storage
            storeFailureCallback.run();  // Trigger the onFailure callback
            return null;
        }).when(mockRepository).storeRegistrationData(eq(model), eq(additionalUnitCode), any(), any());

        // Call the method being tested
        viewModel.registerResident(model, additionalUnitCode, onSuccess, onEmailExists, onFailure);

        // Verify that only the onFailure callback was triggered, and not onSuccess or onEmailExists
        verify(onFailure).run();
        verify(onSuccess, never()).run();
        verify(onEmailExists, never()).run();
    }
}
