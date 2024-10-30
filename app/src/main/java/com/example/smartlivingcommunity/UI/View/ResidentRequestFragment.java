package com.example.smartlivingcommunity.UI.View;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartlivingcommunity.Data.Model.ServiceRequest;
import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.UI.ViewModel.ResidentRequestViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Fragment for residents to submit and track service requests.
 */
public class ResidentRequestFragment extends Fragment {
    private EditText requestDescription;
    private EditText requestType;
    private TextView timestamp;
    private Button submitRequestButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resident_request, container, false);

        requestDescription = view.findViewById(R.id.requestDescription);
        requestType = view.findViewById(R.id.requestType);
        timestamp = view.findViewById(R.id.timestamp);
        submitRequestButton = view.findViewById(R.id.submitRequestButton);

        submitRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ResidentRequestFragment", "Submit button clicked");
                submitRequest();
            }
        });

        return view;
    }

    private void submitRequest() {
        String description = requestDescription.getText().toString();
        String type = requestType.getText().toString();

        if (!description.isEmpty() && !type.isEmpty()) {
            // Generate timestamp
            String currentTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
            timestamp.setText("Timestamp: " + currentTimestamp);

            // Handle request submission (e.g., save to database)
            Toast.makeText(getActivity(), "Request submitted!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Please enter both a request description and type.", Toast.LENGTH_SHORT).show();
        }
    }
}


