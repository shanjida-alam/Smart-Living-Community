package com.example.smartlivingcommunity;

import static org.junit.Assert.*;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.example.smartlivingcommunity.data.model.ParkingSlot;
import com.example.smartlivingcommunity.data.model.ServiceRequest;
import com.example.smartlivingcommunity.ui.viewmodel.ResidentDashboardViewModel;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ResidentDashboardViewModelTest {

    private ResidentDashboardViewModel viewModel;

    // This rule ensures that LiveData updates happen immediately.
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        viewModel = new ResidentDashboardViewModel();
    }

    @Test
    public void testGetParkingSlots_Success() throws InterruptedException {
        // Arrange
        LiveData<List<ParkingSlot>> liveData = viewModel.getParkingSlots();

        // Act and Assert
        List<ParkingSlot> parkingSlots = getValue(liveData);
        assertNotNull("Parking slots should not be null", parkingSlots);
        assertTrue("Parking slots should contain items", parkingSlots.size() > 0);
        assertEquals("First parking slot should have ID 'A1'", "A1", parkingSlots.get(0).getSlotID());
    }

    private <T> T getValue(final LiveData<T> liveData) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final Object[] data = new Object[1];
        Observer<T> observer = new Observer<T>() {
            @Override
            public void onChanged(T o) {
                data[0] = o;
                latch.countDown();
                liveData.removeObserver(this);
            }
        };
        liveData.observeForever(observer);
        latch.await(2, TimeUnit.SECONDS);
        return (T) data[0];
    }
}
