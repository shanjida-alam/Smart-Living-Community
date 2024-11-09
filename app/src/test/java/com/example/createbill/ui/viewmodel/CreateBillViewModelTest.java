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
        // Input: Water = 100, Gas = 150, Electricity = 200, WiFi = 50, ServiceFee = 200
        CreateBillModel model = new CreateBillModel();
        model.setWaterBill(100);
        model.setGasBill(150);
        model.setElectricityBill(200);
        model.setWifiBill(50);
        model.setServiceFee(200);

        // Execute
        double expectedTotalBill = 100 + 150 + 200 + 50 + 200;
        model.calculateTotalBill();

        // Expected Output: Total bill = 700
        assertEquals(expectedTotalBill, model.getTotalBill(), 0.01);
    }
}


