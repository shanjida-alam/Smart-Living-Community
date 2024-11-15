package com.example.smartlivingcommunity.ui.view.content;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.ResidentParkingSlot;

import java.util.List;

/**
 * Adapter for displaying parking slots in a RecyclerView.
 *
 * <p>This adapter binds the list of ParkingSlot objects to the RecyclerView items.</p>
 *
 * @author Saon
 */
public class ParkingSlotAdapter extends RecyclerView.Adapter<ParkingSlotAdapter.ParkingSlotViewHolder> {
    private List<ResidentParkingSlot> parkingSlots;

    /**
     * Constructor to initialize the parking slots list.
     *
     * @param parkingSlots The list of parking slots to display.
     */
    public ParkingSlotAdapter(List<ResidentParkingSlot> parkingSlots) {
        this.parkingSlots = parkingSlots;
    }

    @NonNull
    @Override
    public ParkingSlotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout for parking slots
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parking_slot, parent, false);
        return new ParkingSlotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParkingSlotViewHolder holder, int position) {
        // Bind the parking slot data to each item view
        ResidentParkingSlot slot = parkingSlots.get(position);
        holder.slotIdTextView.setText("Slot ID: " + slot.getSlotID());
        holder.statusTextView.setText("Status: " + slot.getStatus());
    }

    @Override
    public int getItemCount() {
        return parkingSlots != null ? parkingSlots.size() : 0;
    }

    /**
     * Method to update parking slot data and refresh the list.
     *
     * @param parkingSlots The new list of parking slots to display.
     */
    public void setParkingSlots(List<ResidentParkingSlot> parkingSlots) {
        this.parkingSlots = parkingSlots;
        notifyDataSetChanged();
    }

    // ViewHolder for ParkingSlot items
    public static class ParkingSlotViewHolder extends RecyclerView.ViewHolder {
        TextView slotIdTextView, statusTextView;

        public ParkingSlotViewHolder(@NonNull View itemView) {
            super(itemView);
            slotIdTextView = itemView.findViewById(R.id.slotIdTextView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
        }
    }
}