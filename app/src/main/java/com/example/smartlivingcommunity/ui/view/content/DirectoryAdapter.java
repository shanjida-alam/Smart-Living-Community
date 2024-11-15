package com.example.smartlivingcommunity.ui.view.content;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.DirectoryDataModel;

public class DirectoryAdapter extends ListAdapter<DirectoryDataModel, DirectoryAdapter.ViewHolder> {

    public DirectoryAdapter() {
        super(new DirectoryDiffCallback());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_directory_member, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DirectoryDataModel member = getItem(position);
        holder.bind(member);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameText;
        private final TextView unitText;
        private final TextView roleText;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.memberName);
            unitText = itemView.findViewById(R.id.memberUnit);
            roleText = itemView.findViewById(R.id.memberRole);
        }

        void bind(DirectoryDataModel member) {
            nameText.setText(member.getName());
            unitText.setText(member.getUnit());
            roleText.setText(member.getRole());
        }
    }

    private static class DirectoryDiffCallback extends DiffUtil.ItemCallback<DirectoryDataModel> {
        @Override
        public boolean areItemsTheSame(@NonNull DirectoryDataModel oldItem, @NonNull DirectoryDataModel newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull DirectoryDataModel oldItem, @NonNull DirectoryDataModel newItem) {
            return oldItem.equals(newItem);
        }
    }
}

