package com.example.smartlivingcommunity.ui.view.content;

import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.LiveData;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.SecurityLog;
import com.example.smartlivingcommunity.ui.viewmodel.SecurityLogViewModel;

import java.util.List;

/**
 * Activity that displays a list of security logs in a RecyclerView.
 * Observes changes in the data provided by the {@link SecurityLogViewModel} and
 * updates the UI accordingly.
 *
 * @author Saon
 */
public class SecurityLogActivity extends AppCompatActivity {
    private SecurityLogViewModel viewModel;
    private SecurityLogAdapter adapter;

    /**
     * Initializes the activity, sets up the RecyclerView, and observes the security log data.
     *
     * @param savedInstanceState the saved instance state bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_log);

        viewModel = new SecurityLogViewModel();
        adapter = new SecurityLogAdapter();

        // Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Observe changes in the security logs data and update the adapter
        viewModel.getLogs().observe(this, new Observer<List<SecurityLog>>() {
            @Override
            public void onChanged(List<SecurityLog> updatedLogs) {
                adapter.setSecurityLogs(updatedLogs);
            }
        });
    }
}
