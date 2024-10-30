package com.example.smartlivingcommunity.UI.View;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.smartlivingcommunity.R;


/**
 * Fragment for displaying detailed information about a service request.
 */
public class ServiceRequestDetailFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_request_detail, container, false);

        // Initialize UI elements and display request details

        return view;
    }
}
