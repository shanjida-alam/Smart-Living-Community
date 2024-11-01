package com.example.smartlivingcommunity.ui.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.SecurityLog;
import java.util.List;

/**
 * Adapter for displaying security logs in a RecyclerView.
 * This adapter binds the security log data to the RecyclerView item views.
 *
 * @author Saon
 */
public class SecurityLogAdapter extends RecyclerView.Adapter<SecurityLogAdapter.SecurityLogViewHolder> {

    private List<SecurityLog> logs;

    /**
     * Constructs a SecurityLogAdapter with the specified list of security logs.
     *
     * @param logs The list of security logs to be displayed in the RecyclerView.
     */
    public SecurityLogAdapter(List<SecurityLog> logs) {
        this.logs = logs;
    }

    @NonNull
    @Override
    public SecurityLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each security log item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.security_log_item, parent, false);
        return new SecurityLogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SecurityLogViewHolder holder, int position) {
        // Get the current security log and bind its data to the view holder
        SecurityLog log = logs.get(position);
        holder.visitorNameTextView.setText(log.getVisitorName());
        holder.entryDateTextView.setText(log.getEntryDate());
        holder.entryTimeTextView.setText(log.getEntryTime());
        holder.exitDateTextView.setText(log.getExitDate());
        holder.exitTimeTextView.setText(log.getExitTime());
    }

    @Override
    public int getItemCount() {
        // Return the total number of security logs
        return logs.size();
    }

    /**
     * ViewHolder class for holding the views for each security log item.
     */
    public static class SecurityLogViewHolder extends RecyclerView.ViewHolder {
        TextView visitorNameTextView, entryDateTextView, entryTimeTextView, exitDateTextView, exitTimeTextView;

        /**
         * Constructs a SecurityLogViewHolder and initializes the views.
         *
         * @param itemView The view for this security log item.
         */
        public SecurityLogViewHolder(@NonNull View itemView) {
            super(itemView);
            visitorNameTextView = itemView.findViewById(R.id.visitorNameTextView);
            entryDateTextView = itemView.findViewById(R.id.entryDateTextView);
            entryTimeTextView = itemView.findViewById(R.id.entryTimeTextView);
            exitDateTextView = itemView.findViewById(R.id.exitDateTextView);
            exitTimeTextView = itemView.findViewById(R.id.exitTimeTextView);
        }
    }
}
