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

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ComplaintViewHolder> {
    private List<Complaint> complaints;

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

    static class ComplaintViewHolder extends RecyclerView.ViewHolder {
        TextView complaintId, description, submissionDate;

        ComplaintViewHolder(View itemView) {
            super(itemView);
            complaintId = itemView.findViewById(R.id.complaintId);
            description = itemView.findViewById(R.id.description);
            submissionDate = itemView.findViewById(R.id.submissionDate);
        }
    }
}
