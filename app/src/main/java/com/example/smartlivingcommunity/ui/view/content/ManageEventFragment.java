package com.example.smartlivingcommunity.ui.view.content;

import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.ui.view.board.CreateAnnouncementView;

/**
 * A fragment for managing events in the Smart Living Community app.
 * Provides functionality to navigate to the Create Announcement screen.
 *
 * Author: Irtifa & Tanvir
 */
public class ManageEventFragment extends Fragment {

    /**
     * Called to inflate the layout and initialize components for this fragment.
     *
     * @param inflater The LayoutInflater object.
     * @param container The parent view group.
     * @param savedInstanceState Previous state information, if any.
     * @return The root view of the fragment.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manage_event, container, false);

        // Get reference to the "Create Announcement" button
        Button buttonCreateAnnouncement = view.findViewById(R.id.buttonCreateAnnouncement);

        // Set click listener on the button to handle navigation
        buttonCreateAnnouncement.setOnClickListener(v -> {
            Log.d("ManageEventFragment", "Create Announcement button clicked"); // Log the button click
            try {
                // Navigate to CreateAnnouncementView fragment
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new CreateAnnouncementView()); // Replace the current fragment
                transaction.addToBackStack(null); // Add transaction to the back stack for navigation
                transaction.commit(); // Commit the transaction
                Log.d("ManageEventFragment", "Fragment transaction committed"); // Log successful transaction
            } catch (Exception e) {
                Log.e("ManageEventFragment", "Error during fragment transaction", e); // Log any exceptions
            }
        });

        return view; // Return the root view
    }
}
