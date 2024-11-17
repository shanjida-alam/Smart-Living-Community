package com.example.smartlivingcommunity.ui.view.content;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.BookingModel;

import java.util.List;

public class RecentBookingsAdapter extends RecyclerView.Adapter<RecentBookingsAdapter.ViewHolder> {
    private List<BookingModel> bookings;

    public RecentBookingsAdapter(List<BookingModel> bookings) {
        this.bookings = bookings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recent_booking, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookingModel booking = bookings.get(position);
        holder.bind(booking);
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    public void updateBookings(List<BookingModel> newBookings) {
        bookings = newBookings;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView vehicleTextView;
        private TextView dateTextView;
        private TextView timeSlotTextView;
        private TextView statusTextView;
        private ImageView iconImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vehicleTextView = itemView.findViewById(R.id.vehicleTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            timeSlotTextView = itemView.findViewById(R.id.timeSlotTextView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
            iconImageView = itemView.findViewById(R.id.vehicleIconImageView);
        }

        public void bind(BookingModel booking) {
            vehicleTextView.setText(booking.getVehicle());
            dateTextView.setText(booking.getDate());
            timeSlotTextView.setText(booking.getTimeSlot());
            statusTextView.setText(booking.getStatus());
            // Use a map to handle vehicle icons more flexibly
            int iconResource = getVehicleIconResource(booking.getVehicle());
            iconImageView.setImageResource(iconResource);
            iconImageView.setVisibility(View.VISIBLE);
        }

        // Helper method to get the appropriate icon based on vehicle type
        private int getVehicleIconResource(String vehicleType) {
            switch (vehicleType.toLowerCase()) {
                case "car":
                    return R.drawable.parking;
                case "motorcycle":
                    return R.drawable.ic_bike;
                default:
                    return R.drawable.parking; // Default icon if no match
            }
        }
    }
}