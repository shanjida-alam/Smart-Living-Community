package com.example.smartlivingcommunity.ui.view.content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.DirectoryDataModel;
import com.example.smartlivingcommunity.data.repository.DirectoryRepositoryImpl;
import com.example.smartlivingcommunity.ui.viewmodel.DirectoryViewModel;
import com.example.smartlivingcommunity.utils.NetworkUtils;
import java.util.List;

public class DirectoryFragment extends Fragment {

    private DirectoryViewModel viewModel;
    private RecyclerView recyclerView;
    private DirectoryAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment's layout
        View view = inflater.inflate(R.layout.fragment_directory, container, false);

        // Initialize RecyclerView and set up its LayoutManager
        recyclerView = view.findViewById(R.id.directory_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Set up the adapter
        adapter = new DirectoryAdapter();
        recyclerView.setAdapter(adapter);

        // Create ViewModel and observe the data
        viewModel = new DirectoryViewModel(new DirectoryRepositoryImpl(), new NetworkUtils());
        viewModel.getDirectoryEntries().observe(getViewLifecycleOwner(), new Observer<List<DirectoryDataModel>>() {
            @Override
            public void onChanged(List<DirectoryDataModel> directoryDataModels) {
                // Update the adapter with the new data
                adapter.submitList(directoryDataModels);
            }
        });

        return view;
    }
}

