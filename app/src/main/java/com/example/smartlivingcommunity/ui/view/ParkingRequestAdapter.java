package com.example.smartlivingcommunity.ui.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.ParkingRequest;

import java.util.List;

/**
 * Adapter for displaying parking requests in a RecyclerView.
 *
 * <p>This adapter binds the list of ParkingRequest objects to the RecyclerView items.</p>
 *
 * @author Saon
 */
public class ParkingRequestAdapter extends RecyclerView.Adapter<ParkingRequestAdapter.ParkingViewHolder> {
    private List<ParkingRequest> parkingRequests;

    /**
     * Constructor for ParkingRequestAdapter.
     *
     * @param parkingRequests The list of parking requests to display.
     */
    public ParkingRequestAdapter(List<ParkingRequest> parkingRequests) {
        this.parkingRequests = parkingRequests;
    }

    @NonNull
    @Override
    public ParkingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parking_request, parent, false);
        return new ParkingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParkingViewHolder holder, int position) {
        ParkingRequest request = parkingRequests.get(position);
        holder.residentId.setText(request.getResidentId());
        holder.slotId.setText(request.getSlotId());
        holder.slotStatus.setText(request.getSlotStatus());
    }

    @Override
    public int getItemCount() {
        return parkingRequests.size();
    }

    static class ParkingViewHolder extends RecyclerView.ViewHolder {
        TextView residentId, slotId, slotStatus;

        ParkingViewHolder(View itemView) {
            super(itemView);
            residentId = itemView.findViewById(R.id.residentId);
            slotId = itemView.findViewById(R.id.slotId);
            slotStatus = itemView.findViewById(R.id.slotStatus);
        }
    }
}
