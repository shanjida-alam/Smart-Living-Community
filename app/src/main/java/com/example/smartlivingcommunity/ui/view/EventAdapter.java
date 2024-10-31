package com.example.smartlivingcommunity.ui.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.EventModel;
import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView Adapter for displaying a list of events.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private final List<EventModel> events = new ArrayList<>();
    private final OnEventClickListener onEventClickListener;

    /**
     * Interface for handling click events on each item in the list.
     */
    public interface OnEventClickListener {
        void onEventClick(EventModel event);
    }

    /**
     * Constructs an EventAdapter with a click listener.
     *
     * @param listener Listener to handle click events on each event item.
     */
    public EventAdapter(OnEventClickListener listener) {
        this.onEventClickListener = listener;
    }

    /**
     * Updates the list of events and notifies the adapter.
     *
     * @param newEvents List of EventModel objects to display.
     */
    public void setEvents(List<EventModel> newEvents) {
        events.clear();
        events.addAll(newEvents);
        notifyDataSetChanged(); // Notifies the adapter to refresh the RecyclerView
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.litem_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        EventModel event = events.get(position);
        holder.bind(event, onEventClickListener);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    /**
     * ViewHolder class for displaying individual event items.
     */
    public static class EventViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;
        private final TextView dateTextView;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.event_title);
            dateTextView = itemView.findViewById(R.id.event_date);
        }

        /**
         * Binds event data to the view holder and sets the click listener.
         *
         * @param event The EventModel data to bind.
         * @param listener The click listener for the event.
         */
        public void bind(final EventModel event, final OnEventClickListener listener) {
            titleTextView.setText(event.getTitle());
            dateTextView.setText(event.getDate());
            itemView.setOnClickListener(v -> listener.onEventClick(event));
        }
    }
}
