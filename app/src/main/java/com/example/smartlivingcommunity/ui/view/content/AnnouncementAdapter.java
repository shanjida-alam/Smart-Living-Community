package com.example.smartlivingcommunity.ui.view.content;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.AnnouncementModel;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Adapter for displaying announcements in a RecyclerView.
 * It uses ListAdapter to efficiently manage updates to the list using DiffUtil.
 * @author Irtifa
 */
public class AnnouncementAdapter extends ListAdapter<AnnouncementModel, AnnouncementAdapter.AnnouncementViewHolder> {

    /**
     * Constructor for the AnnouncementAdapter.
     * Initializes the adapter with a DiffUtil callback for efficient list updates.
     */
    public AnnouncementAdapter() {
        super(DIFF_CALLBACK);
    }

    // DiffUtil callback to determine changes in the list efficiently
    private static final DiffUtil.ItemCallback<AnnouncementModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<AnnouncementModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull AnnouncementModel oldItem, @NonNull AnnouncementModel newItem) {
            return oldItem.getId() == newItem.getId(); // Compare by unique ID
        }

        @Override
        public boolean areContentsTheSame(@NonNull AnnouncementModel oldItem, @NonNull AnnouncementModel newItem) {
            return oldItem.equals(newItem); // Compare all contents
        }
    };

    /**
     * Creates a new ViewHolder instance when there are no existing ViewHolders to reuse.
     *
     * @param parent The parent view group.
     * @param viewType The view type of the new View.
     * @return A new AnnouncementViewHolder instance.
     */
    @NonNull
    @Override
    public AnnouncementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for an announcement item
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_announcement, parent, false);
        return new AnnouncementViewHolder(itemView);
    }

    /**
     * Binds data to the ViewHolder for the given position in the list.
     *
     * @param holder The ViewHolder to bind data to.
     * @param position The position of the item in the list.
     */
    @Override
    public void onBindViewHolder(@NonNull AnnouncementViewHolder holder, int position) {
        AnnouncementModel announcement = getItem(position); // Get the item at the current position
        holder.bind(announcement); // Bind the data to the ViewHolder
    }

    /**
     * ViewHolder class for an announcement item.
     * Holds references to the item views and binds data to them.
     */
    class AnnouncementViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewTitle;       // TextView for the announcement title
        private final TextView textViewDescription; // TextView for the announcement description
        private final TextView textViewDate;        // TextView for the announcement date

        /**
         * Constructor for the ViewHolder.
         * Initializes references to the item views.
         *
         * @param itemView The view representing a single item.
         */
        AnnouncementViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewDate = itemView.findViewById(R.id.textViewDate);
        }

        /**
         * Binds the given announcement data to the item views.
         *
         * @param announcement The announcement data to bind.
         */
        void bind(AnnouncementModel announcement) {
            textViewTitle.setText(announcement.getTitle()); // Set title
            textViewDescription.setText(announcement.getDescription()); // Set description
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            textViewDate.setText(dateFormat.format(announcement.getDate())); // Format and set date
        }
    }
}
