package com.example.smartlivingcommunity.ui.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.Complaint;

import java.util.List;

/**
 * Adapter for displaying a list of complaints in a RecyclerView.
 * This adapter binds the complaint data to the views for each item in the list.
 *
 * <p>It uses the {@link Complaint} model to populate the data for each complaint item.</p>
 *
 * @author Saon
 */
public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ComplaintViewHolder> {
    private List<Complaint> complaints;

    /**
     * Constructs a new {@code ComplaintAdapter} with the specified list of complaints.
     *
     * @param complaints the list of complaints to be displayed
     */
    public ComplaintAdapter(List<Complaint> complaints) {
        this.complaints = complaints;
    }

    @NonNull
    @Override
    public ComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_complaint, parent, false);
        return new ComplaintViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintViewHolder holder, int position) {
        Complaint complaint = complaints.get(position);
        holder.complaintId.setText(complaint.getComplaintId());
        holder.description.setText(complaint.getDescription());
        holder.submissionDate.setText(complaint.getSubmissionDate());
    }

    @Override
    public int getItemCount() {
        return complaints.size();
    }

    /**
     * ViewHolder class for holding the views for each complaint item.
     */
    static class ComplaintViewHolder extends RecyclerView.ViewHolder {
        TextView complaintId, description, submissionDate;

        /**
         * Constructs a new {@code ComplaintViewHolder} with the specified item view.
         *
         * @param itemView the view for each complaint item
         */
        ComplaintViewHolder(View itemView) {
            super(itemView);
            complaintId = itemView.findViewById(R.id.complaintId);
            description = itemView.findViewById(R.id.description);
            submissionDate = itemView.findViewById(R.id.submissionDate);
        }
    }
}
