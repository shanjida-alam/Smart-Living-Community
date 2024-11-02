package com.example.smartlivingcommunity.ui.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.EventRequest;

import java.util.List;

/**
 * Adapter for displaying event requests in a RecyclerView.
 * Provides an interface for handling approval button clicks.
 *
 * @author Saon
 */
public class EventRequestAdapter extends RecyclerView.Adapter<EventRequestAdapter.ViewHolder> {

    private List<EventRequest> events;
    private final OnApproveClickListener onApproveClickListener;

    /**
     * Interface for handling approval button clicks.
     */
    public interface OnApproveClickListener {
        void onApproveClick(String requestId);
    }

    /**
     * Constructor for EventRequestAdapter.
     *
     * @param events               List of EventRequest objects to display.
     * @param onApproveClickListener Listener for handling approve button clicks.
     */
    public EventRequestAdapter(List<EventRequest> events, OnApproveClickListener onApproveClickListener) {
        this.events = events != null ? events : List.of(); // Handle potential null list
        this.onApproveClickListener = onApproveClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event_request, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EventRequest event = events.get(position);
        holder.eventNameTextView.setText(event.getEventName());
        holder.requestDateTextView.setText(event.getRequestDate());
        holder.approveButton.setOnClickListener(v -> onApproveClickListener.onApproveClick(event.getRequestId()));
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    /**
     * Updates the event list and refreshes the RecyclerView.
     *
     * @param newEvents List of new EventRequest objects.
     */
    public void updateEvents(List<EventRequest> newEvents) {
        this.events = newEvents != null ? newEvents : List.of();
        notifyDataSetChanged();
    }

    /**
     * ViewHolder class for holding and binding views for each event request item.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView eventNameTextView;
        TextView requestDateTextView;
        Button approveButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventNameTextView = itemView.findViewById(R.id.event_name_text_view);
            requestDateTextView = itemView.findViewById(R.id.request_date_text_view);
            approveButton = itemView.findViewById(R.id.approve_button);
        }
    }
}
