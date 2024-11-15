package com.example.smartlivingcommunity.ui.view.content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.BookingModel;
import com.example.smartlivingcommunity.ui.viewmodel.ParkingViewModel;
import com.example.smartlivingcommunity.utils.Resource;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class RecentBookingsFragment extends Fragment {
    private ParkingViewModel viewModel;
    private RecyclerView bookingsRecyclerView;
    private ProgressBar progressBar;
    private TextView emptyStateTextView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private BookingsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recent_bookings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        setupRecyclerView();
        initViewModel();
        setupSwipeRefresh();
        loadBookings();
    }

    private void initViews(View view) {
        bookingsRecyclerView = view.findViewById(R.id.bookingsRecyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        emptyStateTextView = view.findViewById(R.id.emptyStateTextView);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
    }

    private void setupRecyclerView() {
        adapter = new BookingsAdapter(new ArrayList<>());
        bookingsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        bookingsRecyclerView.setAdapter(adapter);
    }

    private void setupSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener(this::loadBookings);
        swipeRefreshLayout.setColorSchemeResources(R.color.custom_status_bar);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(ParkingViewModel.class);

        viewModel.getRecentBookings().observe(getViewLifecycleOwner(), resource -> {
            progressBar.setVisibility(View.GONE);
            swipeRefreshLayout.setRefreshing(false);

            if (resource.status == Resource.Status.LOADING) {
                progressBar.setVisibility(View.VISIBLE);
            } else if (resource.status == Resource.Status.SUCCESS && resource.data != null) {
                if (!resource.data.isEmpty()) {
                    adapter.updateBookings(resource.data);
                    bookingsRecyclerView.setVisibility(View.VISIBLE);
                    emptyStateTextView.setVisibility(View.GONE);
                } else {
                    bookingsRecyclerView.setVisibility(View.GONE);
                    emptyStateTextView.setVisibility(View.VISIBLE);
                }
            } else if (resource.status == Resource.Status.ERROR) {
                Snackbar.make(requireView(), resource.message, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void loadBookings() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        progressBar.setVisibility(View.VISIBLE);
        viewModel.loadRecentBookings(userId);
    }

    private static class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.BookingViewHolder> {
        private List<BookingModel> bookings;

        public BookingsAdapter(List<BookingModel> bookings) {
            this.bookings = bookings;
        }

        @NonNull
        @Override
        public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_recent_booking, parent, false);
            return new BookingViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
            BookingModel booking = bookings.get(position);
            holder.bind(booking);
        }

        @Override
        public int getItemCount() {
            return bookings.size();
        }

        public void updateBookings(List<BookingModel> newBookings) {
            this.bookings = newBookings;
            notifyDataSetChanged();
        }

        static class BookingViewHolder extends RecyclerView.ViewHolder {
            private final TextView vehicleTextView;
            private final TextView dateTextView;
            private final TextView timeSlotTextView;
            private final TextView statusTextView;
            private ImageView iconImageView;

            public BookingViewHolder(@NonNull View itemView) {
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
                int statusColor = getStatusColor(booking.getStatus());
                statusTextView.setBackgroundTintList(itemView.getContext().getColorStateList(statusColor));

                int iconResource = getVehicleIconResource(booking.getVehicle());
                iconImageView.setImageResource(iconResource);
                iconImageView.setVisibility(View.VISIBLE);
            }

            private int getStatusColor(String status) {
                switch (status.toUpperCase()) {
                    case "CONFIRMED":
                        return R.color.status_confirmed;
                    case "PENDING":
                        return R.color.status_pending;
                    case "CANCELLED":
                        return R.color.status_cancelled;
                    default:
                        return R.color.status_default;
                }
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
}