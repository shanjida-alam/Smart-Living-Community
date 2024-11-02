package com.example.smartlivingcommunity.ui.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.RegistrationRequest;

import java.util.List;

/**
 * Adapter for displaying registration requests in a RecyclerView.
 * Provides an interface for handling approval button clicks.
 *
 * @author Saon
 */
public class RegistrationRequestAdapter extends RecyclerView.Adapter<RegistrationRequestAdapter.ViewHolder> {

    private List<RegistrationRequest> requests;
    private final OnApproveClickListener onApproveClickListener;

    /**
     * Interface for handling approval button clicks.
     */
    public interface OnApproveClickListener {
        void onApproveClick(String requestId);
    }

    /**
     * Constructor for RegistrationRequestAdapter.
     *
     * @param requests               List of RegistrationRequest objects to display.
     * @param onApproveClickListener Listener for handling approve button clicks.
     */
    public RegistrationRequestAdapter(List<RegistrationRequest> requests, OnApproveClickListener onApproveClickListener) {
        this.requests = requests != null ? requests : List.of(); // Initialize to empty list if null
        this.onApproveClickListener = onApproveClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_registration_request, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RegistrationRequest request = requests.get(position);
        holder.requesterNameTextView.setText(request.getRequesterName());
        holder.roleTextView.setText(request.getRole());
        holder.nidTextView.setText(request.getNid());
        holder.approveButton.setOnClickListener(v -> onApproveClickListener.onApproveClick(request.getRequestId()));
    }

    @Override
    public int getItemCount() {
        return requests.size();
    }

    /**
     * Updates the list of registration requests and refreshes the RecyclerView.
     *
     * @param newRequests List of new RegistrationRequest objects.
     */
    public void updateRequests(List<RegistrationRequest> newRequests) {
        this.requests = newRequests != null ? newRequests : List.of();
        notifyDataSetChanged();
    }

    /**
     * ViewHolder class for holding and binding views for each registration request item.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView requesterNameTextView;
        TextView roleTextView;
        TextView nidTextView;
        Button approveButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            requesterNameTextView = itemView.findViewById(R.id.requester_name_text_view);
            roleTextView = itemView.findViewById(R.id.role_text_view);
            nidTextView = itemView.findViewById(R.id.nid_text_view);
            approveButton = itemView.findViewById(R.id.approve_button);
        }
    }
}
