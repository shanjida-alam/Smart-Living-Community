package com.example.smartlivingcommunity.ui.view;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.smartlivingcommunity.databinding.FragmentParkingRequestBinding;
import com.example.smartlivingcommunity.ui.ParkingSlotAdapter;
import com.example.smartlivingcommunity.ui.viewmodel.ParkingSlotViewModel;

public class ParkingRequestFragment extends Fragment {
    private FragmentParkingRequestBinding binding;
    private ParkingSlotViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentParkingRequestBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(ParkingSlotViewModel.class);

        // Set up RecyclerView
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ParkingSlotAdapter adapter = new ParkingSlotAdapter();
        binding.recyclerView.setAdapter(adapter);

        // Observe data
        viewModel.getParkingSlots().observe(getViewLifecycleOwner(), parkingSlots -> {
            adapter.setParkingSlots(parkingSlots);
        });

        return binding.getRoot();
    }
}
