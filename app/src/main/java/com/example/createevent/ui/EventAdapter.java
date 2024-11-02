package com.example.createevent.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.createevent.R;
import com.example.createevent.data.model.EventDataModel;
import com.example.createevent.ui.view.EventDetailsView;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private Context context;
    private List<EventDataModel> eventList;

    // Constructor to initialize context and event list
    public EventAdapter(Context context, List<EventDataModel> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        EventDataModel eventData = eventList.get(position);

        // Set text views based on event data
        holder.titleView.setText(eventData.getEventTitle());
        holder.descriptionView.setText(eventData.getEventDesc());
        holder.timeView.setText(eventData.dataTime());
        holder.locationView.setText(eventData.getEventLocation());

        // Set click listener for card
        holder.cardView.setOnClickListener(view -> {
            Intent intent = new Intent(context, EventDetailsView.class);
            intent.putExtra("Title", eventData.getEventTitle());
            intent.putExtra("Description", eventData.getEventDesc());
            intent.putExtra("Time", eventData.dataTime());
            intent.putExtra("Location", eventData.getEventLocation());
            intent.putExtra("Key", eventData.getKey());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    // Method to update event list and notify adapter
    public void updateEventList(ArrayList<EventDataModel> updatedList) {
        this.eventList = updatedList;
        notifyDataSetChanged();
    }

    public void searchEventDataList(ArrayList<EventDataModel> searchList) {

    }

    // ViewHolder for event items
    static class EventViewHolder extends RecyclerView.ViewHolder {

        TextView titleView, descriptionView, timeView, locationView;
        CardView cardView;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.recTitle);
            descriptionView = itemView.findViewById(R.id.recDesc);
            timeView = itemView.findViewById(R.id.recTime);
            locationView = itemView.findViewById(R.id.recLocation);
            cardView = itemView.findViewById(R.id.recCard);
        }
    }
}
