package com.example.smartlivingcommunity.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.ui.viewmodel.EventViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EventView extends Fragment {

    private EventViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_view, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FloatingActionButton fab = view.findViewById(R.id.fab_add_event);
        fab.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_eventView_to_eventCreationView);
        });

        viewModel = new ViewModelProvider(this).get(EventViewModel.class);
        viewModel.getEvents().observe(getViewLifecycleOwner(), events -> {
            // Update RecyclerView with events data
        });

        return view;
    }
}
