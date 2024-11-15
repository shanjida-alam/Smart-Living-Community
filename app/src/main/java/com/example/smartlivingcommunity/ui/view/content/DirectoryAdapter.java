package com.example.smartlivingcommunity.ui.view.content;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.DirectoryDataModel;
import java.util.ArrayList;
import java.util.List;

public class DirectoryAdapter extends RecyclerView.Adapter<DirectoryAdapter.ViewHolder> {
    private List<DirectoryDataModel> directoryList = new ArrayList<>();

    public void submitList(List<DirectoryDataModel> list) {
        directoryList.clear();
        directoryList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.directory_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DirectoryDataModel model = directoryList.get(position);
        holder.name.setText(model.getName());
        holder.unit.setText(model.getUnit());
        holder.role.setText(model.getRole());
    }

    @Override
    public int getItemCount() {
        return directoryList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, unit, role;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.member_name);
            unit = itemView.findViewById(R.id.member_unit);
            role = itemView.findViewById(R.id.member_role);
        }
    }
}

