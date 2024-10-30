package com.example.smartlivingcommunity.UI.View;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartlivingcommunity.Data.Model.Request;
import com.example.smartlivingcommunity.R;

import java.util.ArrayList;
import java.util.List;

public class ManagerRequestFragment extends Fragment {

    private RecyclerView recyclerView;
    private RequestAdapter requestAdapter;
    private List<Request> requestList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager_request, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view_requests);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize request list and adapter
        requestList = new ArrayList<>();
        requestAdapter = new RequestAdapter(requestList);
        recyclerView.setAdapter(requestAdapter);

        // Load data (you can replace this with actual data fetching logic)
        loadRequestData();

        return view;
    }

    // Dummy method to load request data
    private void loadRequestData() {
        // Here you can add code to fetch requests from a database or an API
        requestList.add(new Request("Request 1", "Pending"));
        requestList.add(new Request("Request 2", "Completed"));
        requestList.add(new Request("Request 3", "In Progress"));

        // Notify adapter about data changes
        requestAdapter.notifyDataSetChanged();
    }
}
