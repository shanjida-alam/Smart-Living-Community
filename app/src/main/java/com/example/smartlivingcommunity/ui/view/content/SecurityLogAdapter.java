package com.example.smartlivingcommunity.ui.view.content;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.SecurityLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter class for displaying security logs in a RecyclerView.
 * This adapter binds {@link SecurityLog} data to item views in the list.
 *
 * @author Saon
 */
public class SecurityLogAdapter extends RecyclerView.Adapter<SecurityLogAdapter.SecurityLogViewHolder> {
    private List<SecurityLog> securityLogs = new ArrayList<>();

    /**
     * Sets the list of security logs to be displayed in the RecyclerView.
     * Notifies the adapter of data changes to refresh the list.
     *
     * @param logs the list of {@link SecurityLog} objects to display
     */
    public void setSecurityLogs(List<SecurityLog> logs) {
        this.securityLogs = logs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SecurityLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_security_log, parent, false);
        return new SecurityLogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SecurityLogViewHolder holder, int position) {
        SecurityLog log = securityLogs.get(position);
        holder.bind(log);
    }

    @Override
    public int getItemCount() {
        return securityLogs.size();
    }

    /**
     * ViewHolder class for the security log item view. Binds the data from
     * {@link SecurityLog} to the individual views in the layout.
     */
    static class SecurityLogViewHolder extends RecyclerView.ViewHolder {
        private final TextView visitorNameText;
        private final TextView visitorTypeText;
        private final TextView entryTimeText;
        private final TextView exitTimeText;
        private final TextView entryDateText;
        private final TextView exitDateText;

        /**
         * Constructs a SecurityLogViewHolder instance and initializes the views for the security log item.
         *
         * @param itemView the view representing a single security log item
         */
        public SecurityLogViewHolder(@NonNull View itemView) {
            super(itemView);
            visitorNameText = itemView.findViewById(R.id.visitorNameText);
            visitorTypeText = itemView.findViewById(R.id.visitorTypeText);
            entryTimeText = itemView.findViewById(R.id.entryTimeText);
            exitTimeText = itemView.findViewById(R.id.exitTimeText);
            entryDateText = itemView.findViewById(R.id.entryDateText);
            exitDateText = itemView.findViewById(R.id.exitDateText);
        }

        /**
         * Binds the {@link SecurityLog} data to the views.
         *
         * @param log the security log data to bind
         */
        public void bind(SecurityLog log) {
            visitorNameText.setText(log.getVisitorName());
            visitorTypeText.setText(log.getVisitorType());
            entryTimeText.setText("Entry: " + log.getEntryTime());
            exitTimeText.setText("Exit: " + log.getExitTime());
            entryDateText.setText("Date In: " + log.getEntryDate());
            exitDateText.setText("Date Out: " + log.getExitDate());
        }
    }
}
