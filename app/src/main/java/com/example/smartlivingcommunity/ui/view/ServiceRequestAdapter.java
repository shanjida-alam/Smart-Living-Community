package com.example.smartlivingcommunity.ui.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.ServiceRequest;

import java.util.List;

/**
 * Adapter for displaying a list of service requests in a RecyclerView.
 *
 * <p>This adapter binds the data from a list of ServiceRequest objects to the views in
 * the item layout for each service request.</p>
 *
 * @author Saon
 */
public class ServiceRequestAdapter extends RecyclerView.Adapter<ServiceRequestAdapter.ServiceRequestViewHolder> {
    private List<ServiceRequest> serviceRequests;

    /**
     * Constructor to initialize the service requests list.
     *
     * @param serviceRequests List of service requests to be displayed.
     */
    public ServiceRequestAdapter(List<ServiceRequest> serviceRequests) {
        this.serviceRequests = serviceRequests;
    }

    @NonNull
    @Override
    public ServiceRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout for service requests
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service_request, parent, false);
        return new ServiceRequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceRequestViewHolder holder, int position) {
        // Bind the service request data to each item view
        ServiceRequest serviceRequest = serviceRequests.get(position);
        holder.serviceTypeTextView.setText("Service Type: " + serviceRequest.getServiceType());
        holder.descriptionTextView.setText("Description: " + serviceRequest.getDescription());
        holder.statusTextView.setText("Status: " + serviceRequest.getStatus());
    }

    @Override
    public int getItemCount() {
        return serviceRequests != null ? serviceRequests.size() : 0;
    }

    public static class ServiceRequestViewHolder extends RecyclerView.ViewHolder {
        TextView serviceTypeTextView, descriptionTextView, statusTextView;

        public ServiceRequestViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceTypeTextView = itemView.findViewById(R.id.serviceTypeTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
        }
    }
}
