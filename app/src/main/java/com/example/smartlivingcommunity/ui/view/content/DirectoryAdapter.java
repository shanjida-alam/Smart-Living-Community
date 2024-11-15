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

/**
 * @author solaimi
 * Adapter class for displaying a list of DirectoryDataModel items in a RecyclerView.
 * This adapter uses ListAdapter for efficient updates and handling of data changes.
 */
public class DirectoryAdapter extends ListAdapter<DirectoryDataModel, DirectoryAdapter.ViewHolder> {

    /**
     * Constructs a new DirectoryAdapter instance.
     */
    public DirectoryAdapter() {
        super(new DirectoryDiffCallback());
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View for the provided data type.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_directory_member, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Called by RecyclerView to display data at the specified position.
     * This method updates the contents of the ViewHolder to reflect the item at the given position.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the item
     *                 at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DirectoryDataModel member = getItem(position);
        holder.bind(member);
    }

    /**
     * ViewHolder class for the DirectoryAdapter.
     * Holds references to the views for each data item and binds data to the views.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameText;
        private final TextView unitText;
        private final TextView roleText;

        /**
         * Constructs a new ViewHolder instance.
         *
         * @param itemView The view associated with this ViewHolder.
         */
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.memberName);
            unitText = itemView.findViewById(R.id.memberUnit);
            roleText = itemView.findViewById(R.id.memberRole);
        }

        /**
         * Binds a DirectoryDataModel object to the ViewHolder.
         *
         * @param member The DirectoryDataModel object to bind.
         */
        void bind(DirectoryDataModel member) {
            nameText.setText(member.getName());
            unitText.setText(member.getUnit());
            roleText.setText(member.getRole());
        }
    }

    /**
     * DiffUtil.ItemCallback implementation for comparing DirectoryDataModel objects.
     * Optimizes updates by determining if items or their content have changed.
     */
    private static class DirectoryDiffCallback extends DiffUtil.ItemCallback<DirectoryDataModel> {

        /**
         * Checks if two items represent the same entity based on their IDs.
         *
         * @param oldItem The old item.
         * @param newItem The new item.
         * @return True if items represent the same entity, false otherwise.
         */
        @Override
        public boolean areItemsTheSame(@NonNull DirectoryDataModel oldItem, @NonNull DirectoryDataModel newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        /**
         * Checks if the contents of two items are the same.
         *
         * @param oldItem The old item.
         * @param newItem The new item.
         * @return True if contents of both items are the same, false otherwise.
         */
        @Override
        public boolean areContentsTheSame(@NonNull DirectoryDataModel oldItem, @NonNull DirectoryDataModel newItem) {
            return oldItem.equals(newItem);
        }
    }
}
