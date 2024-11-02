package com.example.smartlivingcommunity.ui.view.content;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.ResidentEvent;

import java.util.List;

/**
 * Adapter for displaying a list of events in a RecyclerView.
 * This adapter binds the event data to the views for each item in the list.
 *
 * <p>It uses the {@link ResidentEvent} model to populate the data for each event item.</p>
 *
 * @author Saon
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<ResidentEvent> eventList;

    /**
     * Constructs a new {@code EventAdapter} with the specified list of events.
     *
     * @param eventList the list of events to be displayed
     */
    public EventAdapter(List<ResidentEvent> eventList) {
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        ResidentEvent event = eventList.get(position);
        holder.eventName.setText(event.getEventName());
        holder.eventDate.setText(event.getDate());
        holder.eventLocation.setText(event.getLocation());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    /**
     * ViewHolder class for holding the views for each event item.
     */
    public static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView eventName, eventDate, eventLocation;
        Button registerButton;

        /**
         * Constructs a new {@code EventViewHolder} with the specified item view.
         *
         * @param itemView the view for each event item
         */
        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventName = itemView.findViewById(R.id.eventName);
            eventDate = itemView.findViewById(R.id.eventDate);
            eventLocation = itemView.findViewById(R.id.eventLocation);
            registerButton = itemView.findViewById(R.id.registerButton);
        }
    }
}
