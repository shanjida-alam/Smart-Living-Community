package com.example.smartlivingcommunity.ui.view.content;

import android.content.Context;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

/**
 * A handler class for managing bottom navigation actions within the Smart Living Community application.
 * This class implements {@link NavigationView.OnNavigationItemSelectedListener} to handle item
 * selection events in the bottom navigation menu and to perform fragment transactions.
 */
public class BottomNavHandler implements NavigationView.OnNavigationItemSelectedListener {

    /** FragmentManager instance for managing fragment transactions */
    private final FragmentManager fragmentManager;

    /** Context instance to allow interaction with other components */
    private final Context context;

    /**
     * Constructs a BottomNavHandler.
     *
     * @param context the context in which this handler is used
     * @param fragmentManager the FragmentManager used to handle fragment transactions
     */
    public BottomNavHandler(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    /**
     * Called when an item in the navigation menu is selected.
     *
     * @param item the selected menu item
     * @return true if the event was handled, false otherwise
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}