package com.example.smartlivingcommunity.ui;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartlivingcommunity.databinding.ItemParkingSlotBinding;
import com.example.smartlivingcommunity.data.model.ParkingSlot;
import java.util.ArrayList;
import java.util.List;

public class ParkingSlotAdapter extends RecyclerView.Adapter<ParkingSlotAdapter.ViewHolder> {
    private List<ParkingSlot> parkingSlots = new ArrayList<>();

    public void setParkingSlots(List<ParkingSlot> parkingSlots) {
        this.parkingSlots = parkingSlots;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemParkingSlotBinding binding = ItemParkingSlotBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ParkingSlot slot = parkingSlots.get(position);
        holder.binding.textViewSlotID.setText(slot.getSlotID());
        holder.binding.textViewStatus.setText(slot.getStatus());
    }

    @Override
    public int getItemCount() {
        return parkingSlots.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemParkingSlotBinding binding;
        public ViewHolder(ItemParkingSlotBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
