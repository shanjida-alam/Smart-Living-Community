package com.example.smartlivingcommunity.ui.view.content;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.ServiceRequestModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.List;

public class ServiceRequestFragment extends Fragment {

    private EditText editTextDescription;
    private Spinner spinnerRequestType;
    private EditText editTextUrgency;
    private Button buttonSubmit;
    private FirebaseFirestore firestore;

    private static final String TAG = "ServiceRequestFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_request, container, false);
        initViews(view);
        setupSpinner();
        setupFirestore();
        setupSubmitButton();
        return view;
    }

    private void initViews(View view) {
        editTextDescription = view.findViewById(R.id.editTextDescription);
        spinnerRequestType = view.findViewById(R.id.spinnerRequestType);
        editTextUrgency = view.findViewById(R.id.editTextUrgency);
        buttonSubmit = view.findViewById(R.id.buttonSubmit);
    }

    private void setupSpinner() {
        List<String> requestTypes = Arrays.asList("Cleaning", "Maintenance", "Repair", "Other");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, requestTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRequestType.setAdapter(adapter);
    }

    private void setupFirestore() {
        firestore = FirebaseFirestore.getInstance();
    }

    private void setupSubmitButton() {
        buttonSubmit.setOnClickListener(v -> submitRequest());
    }

    private void submitRequest() {
        String description = editTextDescription.getText().toString().trim();
        String requestType = spinnerRequestType.getSelectedItem().toString();
        String urgency = editTextUrgency.getText().toString().trim();

        if (description.isEmpty() || urgency.isEmpty()) {
            Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        ServiceRequestModel request = new ServiceRequestModel(requestType, description, urgency);
        firestore.collection("serviceRequests").add(request)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(getContext(), "Request submitted successfully!", Toast.LENGTH_SHORT).show();
                    clearFields();
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Error adding document", e);
                    Toast.makeText(getContext(), "Failed to submit request. Please try again.", Toast.LENGTH_SHORT).show();
                });
    }

    private void clearFields() {
        editTextDescription.setText("");
        spinnerRequestType.setSelection(0); // Reset to first item
        editTextUrgency.setText("");
    }
}
