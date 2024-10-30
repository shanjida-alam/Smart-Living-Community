package com.example.smartlivingcommunity.UI.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartlivingcommunity.Data.Model.Request;
import com.example.smartlivingcommunity.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {

    private List<Request> requestList;

    public RequestAdapter(List<Request> requestList) {
        this.requestList = requestList;
    }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_request, parent, false);
        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
        Request request = requestList.get(position);
        holder.bind(request);
    }

    @Override
    public int getItemCount() {
        return requestList.size();
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder {
        private TextView requestTitle; // Change this to requestDescription if needed
        private TextView requestType;
        private TextView requestTimestamp;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            requestTitle = itemView.findViewById(R.id.text_request_description); // Update with actual TextView ID
            requestType = itemView.findViewById(R.id.text_request_type); // Ensure this ID matches your layout
            requestTimestamp = itemView.findViewById(R.id.text_request_timestamp); // New TextView for timestamp
        }

        public void bind(Request request) {
            requestTitle.setText(request.getRequestDescription()); // Display request description
            requestType.setText(request.getRequestType()); // Display request type

            // Format timestamp to a readable string
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            requestTimestamp.setText(sdf.format(request.getTimestamp())); // Display formatted timestamp
        }
    }
}

