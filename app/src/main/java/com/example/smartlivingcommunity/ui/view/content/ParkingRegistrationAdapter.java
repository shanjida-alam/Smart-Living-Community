package com.example.smartlivingcommunity.ui.view.content;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.ParkingRegistrationModel;

import java.util.List;

public class ParkingRegistrationAdapter extends RecyclerView.Adapter<ParkingRegistrationAdapter.ViewHolder> {
    private List<ParkingRegistrationModel> registrations;

    public ParkingRegistrationAdapter(List<ParkingRegistrationModel> registrations) {
        this.registrations = registrations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_parking_registration, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ParkingRegistrationModel registration = registrations.get(position);

        holder.vehicleTypeText.setText("Vehicle Type: " + registration.getVehicleType());
        holder.vehicleNumberText.setText("Vehicle Number: " + registration.getVehicleNumber());
        holder.statusText.setText("Status: " + registration.getParkingStatus());
        holder.dateText.setText("Requested on: " + registration.getRequestDate());

        // Set status text color based on status
        int color;
        switch (registration.getParkingStatus()) {
            case "APPROVED":
                color = holder.itemView.getContext().getResources().getColor(R.color.status_approved);
                break;
            case "REJECTED":
                color = holder.itemView.getContext().getResources().getColor(R.color.status_rejected);
                break;
            default: // PENDING
                color = holder.itemView.getContext().getResources().getColor(R.color.status_pending);
                break;
        }
        holder.statusText.setTextColor(color);

    }

    @Override
    public int getItemCount() {
        return registrations != null ? registrations.size() : 0;
    }

    public void updateRegistrations(List<ParkingRegistrationModel> newRegistrations) {
        this.registrations = newRegistrations;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView vehicleTypeText;
        final TextView vehicleNumberText;
        final TextView statusText;
        final TextView dateText;

        ViewHolder(View view) {
            super(view);
            vehicleTypeText = view.findViewById(R.id.vehicleTypeText);
            vehicleNumberText = view.findViewById(R.id.vehicleNumberText);
            statusText = view.findViewById(R.id.statusText);
            dateText = view.findViewById(R.id.dateText);
        }
    }
}