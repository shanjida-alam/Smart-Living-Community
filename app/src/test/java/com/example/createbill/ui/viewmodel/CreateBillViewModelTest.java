package com.example.createbill.ui.viewmodel;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.example.createbill.data.model.CreateBillModel;
import com.example.createbill.ui.viewmodel.CreateBillViewModel;
import com.example.createbill.data.repository.CreateBillRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CreateBillViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    private CreateBillRepository repository;

    private CreateBillViewModel viewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        viewModel = new CreateBillViewModel(repository);
    }

    // Test case 1: Fetch Bill Data with Low Urgency
    @Test
    public void fetchBillData_withLowUrgency_setsCorrectServiceFeeAndData() {
        // Input: Document ID for low urgency
        String documentID = "fRNhJyfSD5mVkBK4RMaa";
        CreateBillModel expectedModel = new CreateBillModel(700, 600, 5000, 500, 100); // demo data
        MutableLiveData<CreateBillModel> liveData = new MutableLiveData<>(expectedModel);

        // Mock repository to return data with low urgency fee
        when(repository.fetchBillData(documentID)).thenReturn(liveData);

        // Execute
        viewModel.fetchBillData(documentID);
        CreateBillModel result = viewModel.getBillData().getValue();

        // Expected Output: Service fee = 100
        assertNotNull(result);
        assertEquals(100, result.getServiceFee(), 0.01);
        assertEquals(700, result.getWaterBill(), 0.01);
        assertEquals(600, result.getGasBill(), 0.01);
        assertEquals(5000, result.getElectricityBill(), 0.01);
    }

    // Test case 2: Fetch Bill Data with Medium Urgency
    @Test
    public void fetchBillData_withMediumUrgency_setsCorrectServiceFeeAndData() {
        // Input: Document ID for medium urgency
        String documentID = "khHifFLgZOkJGYWupJ5r";
        CreateBillModel expectedModel = new CreateBillModel(700, 600, 5000, 500, 200); // demo data
        MutableLiveData<CreateBillModel> liveData = new MutableLiveData<>(expectedModel);

        // Mock repository to return data with medium urgency fee
        when(repository.fetchBillData(documentID)).thenReturn(liveData);

        // Execute
        viewModel.fetchBillData(documentID);
        CreateBillModel result = viewModel.getBillData().getValue();

        // Expected Output: Service fee = 200
        assertNotNull(result);
        assertEquals(200, result.getServiceFee(), 0.01);
    }

    // Test case 3: Fetch Bill Data with High Urgency
    @Test
    public void fetchBillData_withHighUrgency_setsCorrectServiceFeeAndData() {
        // Input: Document ID for high urgency
        String documentID = "M5cCuHx0mbESQl2Yoq5U";
        CreateBillModel expectedModel = new CreateBillModel(700, 600, 5000, 500, 300); // demo data
        MutableLiveData<CreateBillModel> liveData = new MutableLiveData<>(expectedModel);

        // Mock repository to return data with high urgency fee
        when(repository.fetchBillData(documentID)).thenReturn(liveData);

        // Execute
        viewModel.fetchBillData(documentID);
        CreateBillModel result = viewModel.getBillData().getValue();

        // Expected Output: Service fee = 300
        assertNotNull(result);
        assertEquals(300, result.getServiceFee(), 0.01);
    }

    // Test case 4: Handle Error in Fetching Bill Data
    @Test
    public void fetchBillData_whenFailure_setsErrorMessage() {
        // Input: Document ID for failed fetch
        String documentID = "invalidDocumentID123";
        MutableLiveData<CreateBillModel> liveData = new MutableLiveData<>(null);

        // Mock repository to simulate a failure
        when(repository.fetchBillData(documentID)).thenReturn(liveData);

        // Execute
        viewModel.fetchBillData(documentID);
        String errorMessage = viewModel.getErrorMessage().getValue();

        // Expected Output: Error message for failure
        assertEquals("Failed to fetch bill data", errorMessage);
    }

    // Test case 5: Calculate Total Bill
    @Test
    public void calculateTotalBill_correctlyCalculatesSum() {
        String documentID = "uenv5PSMx34C43jHEefc";

        // Mocking expected fetched data for the document
        double waterBill = 700;
        double gasBill = 600;
        double electricityBill = 5000;
        double wifiBill = 500;
        double serviceFee = 300;
        CreateBillModel expectedModel = new CreateBillModel(waterBill, gasBill, electricityBill, wifiBill, serviceFee);
        MutableLiveData<CreateBillModel> liveData = new MutableLiveData<>(expectedModel);

        // Mock the repository to return the above data when fetchBillData is called
        when(repository.fetchBillData(documentID)).thenReturn(liveData);

        // Execute: Fetch the bill data using the ViewModel
        viewModel.fetchBillData(documentID);

        // Wait for the fetched data to be available in the ViewModel
        CreateBillModel result = viewModel.getBillData().getValue();

        // Calculate the expected total bill based on fetched data
        double expectedTotalBill = waterBill + gasBill + electricityBill + wifiBill + serviceFee;

        // Assert that the total bill in the fetched model is correct
        assertNotNull(result);
        result.calculateTotalBill(); // Ensure total calculation is triggered
        assertEquals(expectedTotalBill, result.getTotalBill(), 0.01);
    }


}


