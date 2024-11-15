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
import com.example.smartlivingcommunity.ui.view.content.DirectoryAdapter;
import com.example.smartlivingcommunity.ui.viewmodel.DirectoryViewModel;
import com.google.android.material.chip.ChipGroup;
import androidx.appcompat.widget.SearchView;

public class DirectoryFragment extends Fragment {
    private DirectoryViewModel viewModel;
    private DirectoryAdapter adapter;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private ChipGroup filterChipGroup;
    private View progressBar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_directory, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        setupViewModel();
        setupRecyclerView();
        setupSearchView();
        setupFilterChips();
    }

    private void initializeViews(View view) {
        recyclerView = view.findViewById(R.id.directoryRecyclerView);
        searchView = view.findViewById(R.id.searchView);
        filterChipGroup = view.findViewById(R.id.filterChipGroup);
        progressBar = view.findViewById(R.id.progressBar);
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(DirectoryViewModel.class);
        viewModel.getDirectoryEntries().observe(getViewLifecycleOwner(), members -> {
            adapter.submitList(members);
            progressBar.setVisibility(View.GONE);
        });
    }

    private void setupRecyclerView() {
        adapter = new DirectoryAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

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

    private void setupFilterChips() {
        filterChipGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.chipAll) {
                viewModel.loadDirectory();
            } else if (checkedId == R.id.chipResident) {
                viewModel.filterByRole("resident");
            } else if (checkedId == R.id.chipSecurity) {
                viewModel.filterByRole("security");
            }
        });
    }
}