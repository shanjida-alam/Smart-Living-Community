package com.example.smartlivingcommunity.ui.view.content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.ui.viewmodel.DirectoryViewModel;
import com.google.android.material.chip.ChipGroup;
import androidx.appcompat.widget.SearchView;

/**
 * @author solaimi
 * DirectoryFragment displays a list of directory entries using a RecyclerView.
 * It supports search and filtering functionalities using a SearchView and ChipGroup.
 */
public class DirectoryFragment extends Fragment {
    private DirectoryViewModel viewModel;
    private DirectoryAdapter adapter;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private ChipGroup filterChipGroup;
    private View progressBar;

    /**
     * Inflates the fragment layout and returns the root view.
     *
     * @param inflater  The LayoutInflater object that can be used to inflate views in the fragment.
     * @param container The parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     * @return The root View for the fragment's layout.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_directory, container, false);
    }

    /**
     * Called immediately after onCreateView(). Initializes views, sets up the ViewModel, RecyclerView,
     * and handles search and filter events.
     *
     * @param view               The View returned by onCreateView().
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        setupViewModel();
        setupRecyclerView();
        setupSearchView();
        setupFilterChips();
    }

    /**
     * Initializes all views used in the fragment.
     *
     * @param view The root view of the fragment.
     */
    private void initializeViews(View view) {
        recyclerView = view.findViewById(R.id.directoryRecyclerView);
        searchView = view.findViewById(R.id.searchView);
        filterChipGroup = view.findViewById(R.id.filterChipGroup);
        progressBar = view.findViewById(R.id.progressBar);
    }

    /**
     * Sets up the ViewModel and observes data changes for directory entries.
     * Updates the adapter when data changes and hides the progress bar.
     */
    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(DirectoryViewModel.class);
        viewModel.getDirectoryEntries().observe(getViewLifecycleOwner(), members -> {
            adapter.submitList(members);
            progressBar.setVisibility(View.GONE);
        });
    }

    /**
     * Configures the RecyclerView with a DirectoryAdapter and a LinearLayoutManager.
     */
    private void setupRecyclerView() {
        adapter = new DirectoryAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    /**
     * Sets up the SearchView to listen for query input and filter directory entries by name.
     */
    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                viewModel.searchByName(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() >= 2) {
                    viewModel.searchByName(newText);
                }
                return true;
            }
        });
    }

    /**
     * Configures the ChipGroup to filter directory entries based on selected chips (role-based filtering).
     */
    private void setupFilterChips() {
        filterChipGroup.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (checkedIds.contains(R.id.chipAll)) {
                viewModel.loadDirectory();
            } else if (checkedIds.contains(R.id.chipResident)) {
                viewModel.filterByRole("resident");
            } else if (checkedIds.contains(R.id.chipSecurity)) {
                viewModel.filterByRole("security");
            }
        });
    }
}
