package com.example.smartlivingcommunity.ui.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.ParkingList;
import java.util.List;

/**
 * Adapter for displaying a list of parking slots in a RecyclerView.
 * This adapter binds the ParkingList data to the views in the item layout.
 *
 * @author Saon
 */
public class ParkingListAdapter extends RecyclerView.Adapter<ParkingListAdapter.ParkingListViewHolder> {

    private List<ParkingList> parkingList;

    /**
     * Constructor for ParkingListAdapter.
     *
     * @param parkingList List of ParkingList objects to display.
     */
    public ParkingListAdapter(List<ParkingList> parkingList) {
        this.parkingList = parkingList;
    }

    @NonNull
    @Override
    public ParkingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_parking_list, parent, false);
        return new ParkingListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParkingListViewHolder holder, int position) {
        ParkingList parking = parkingList.get(position);
        holder.slotIdTextView.setText("Slot ID: " + parking.getSlotID());
        holder.vehicleIdTextView.setText("Vehicle ID: " + parking.getVehicleID());
        holder.statusTextView.setText("Status: " + parking.getStatus());
        holder.residentIdTextView.setText("Resident ID: " + parking.getResidentID());
    }

    @Override
    public int getItemCount() {
        return parkingList.size();
    }

    /**
     * ViewHolder class for holding the views for each parking slot item.
     * This class is responsible for initializing the views that will be displayed
     * for each item in the RecyclerView.
     */
    static class ParkingListViewHolder extends RecyclerView.ViewHolder {
        TextView slotIdTextView, vehicleIdTextView, statusTextView, residentIdTextView;

        /**
         * Constructor for ParkingListViewHolder.
         *
         * @param itemView The view of the item layout for parking slots.
         */
        ParkingListViewHolder(View itemView) {
            super(itemView);
            slotIdTextView = itemView.findViewById(R.id.slotIdTextView);
            vehicleIdTextView = itemView.findViewById(R.id.vehicleIdTextView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
            residentIdTextView = itemView.findViewById(R.id.residentIdTextView);
        }
    }
}
