package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.smartlivingcommunity.data.model.ParkingSlot;
import com.example.smartlivingcommunity.data.repository.ParkingSlotRepository;
import java.util.List;

public class ParkingSlotViewModel extends ViewModel {
    private final MutableLiveData<List<ParkingSlot>> parkingSlots;
    private final ParkingSlotRepository repository;

    public ParkingSlotViewModel() {
        repository = new ParkingSlotRepository();
        parkingSlots = new MutableLiveData<>(repository.getParkingSlots());
    }

    public LiveData<List<ParkingSlot>> getParkingSlots() {
        return parkingSlots;
    }
}
