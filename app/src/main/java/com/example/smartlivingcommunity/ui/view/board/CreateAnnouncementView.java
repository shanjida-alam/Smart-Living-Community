package com.example.smartlivingcommunity.ui.view.board;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.data.model.AnnouncementModel;
import com.example.smartlivingcommunity.data.repository.RoomAnnouncementRepository;
import com.example.smartlivingcommunity.databinding.FragmentCreateAnnouncementBinding;
import com.example.smartlivingcommunity.ui.viewmodel.CreateAnnouncementViewModel;
import com.example.smartlivingcommunity.ui.view.content.AnnouncementAdapter;
import com.example.smartlivingcommunity.domain.model.Result;
import com.example.smartlivingcommunity.domain.model.Result.Success;
import com.example.smartlivingcommunity.domain.model.Result.Error;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A Fragment for creating and displaying announcements in the Smart Living Community app.
 * It integrates with the CreateAnnouncementViewModel to handle business logic and updates.
 *
 * Author: Irtifa
 */
public class CreateAnnouncementView extends Fragment {
    private static final String TAG = "CreateAnnouncementView"; // Logging tag
    private CreateAnnouncementViewModel viewModel; // ViewModel instance
    private FragmentCreateAnnouncementBinding binding; // View binding for UI components
    private AnnouncementAdapter announcementAdapter; // Adapter for RecyclerView

    /**
     * Called to inflate the layout and initialize components.
     *
     * @param inflater The LayoutInflater object.
     * @param container The parent view group.
     * @param savedInstanceState Previous state information, if any.
     * @return The root view of the fragment.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout using view binding
        binding = FragmentCreateAnnouncementBinding.inflate(inflater, container, false);

        // Initialize the ViewModel with a custom factory
        viewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                if (modelClass.isAssignableFrom(CreateAnnouncementViewModel.class)) {
                    return (T) new CreateAnnouncementViewModel(new RoomAnnouncementRepository(requireContext()));
                }
                throw new IllegalArgumentException("Unknown ViewModel class");
            }
        }).get(CreateAnnouncementViewModel.class);

        setupRecyclerView(); // Setup RecyclerView for announcements
        setupObservers(); // Setup LiveData observers
        setupClickListeners(); // Setup button click listeners

        return binding.getRoot();
    }

    /**
     * Configures the RecyclerView for displaying announcements.
     */
    private void setupRecyclerView() {
        binding.recyclerViewAnnouncements.setLayoutManager(new LinearLayoutManager(requireContext())); // Set layout manager
        announcementAdapter = new AnnouncementAdapter(); // Initialize adapter
        binding.recyclerViewAnnouncements.setAdapter(announcementAdapter); // Attach adapter to RecyclerView
    }

    /**
     * Sets up LiveData observers to handle UI updates based on ViewModel changes.
     */
    private void setupObservers() {
        // Observe create result for success or error
        viewModel.getCreateResult().observe(getViewLifecycleOwner(), result -> {
            if (result instanceof Success) {
                AnnouncementModel announcement = ((Success<AnnouncementModel>) result).getData();
                Toast.makeText(requireContext(), "Announcement created: " + announcement.getTitle(), Toast.LENGTH_SHORT).show();
                requireActivity().onBackPressed(); // Navigate back
            } else if (result instanceof Error) {
                String errorMessage = ((Error<AnnouncementModel>) result).getErrorMessage();
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        // Observe loading state and update UI accordingly
        viewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            binding.buttonCreate.setEnabled(!isLoading);
        });

        // Observe announcements and update the RecyclerView adapter
        viewModel.getAnnouncements().observe(getViewLifecycleOwner(), announcements -> {
            announcementAdapter.submitList(announcements);
        });
    }

    /**
     * Sets up click listeners for the UI elements.
     */
    private void setupClickListeners() {
        binding.buttonCreate.setOnClickListener(v -> {
            String title = binding.editTextTitle.getText().toString().trim(); // Get title input
            String description = binding.editTextDescription.getText().toString().trim(); // Get description input
            String dateInput = binding.editTextDate.getText().toString().trim(); // Get date input

            // Validate inputs and create announcement if valid
            if (validateInputs(title, description, dateInput)) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    Date date = dateFormat.parse(dateInput); // Parse date input
                    viewModel.createAnnouncement(title, description, date); // Call ViewModel to create announcement
                } catch (ParseException e) {
                    Toast.makeText(requireContext(), "Invalid date format. Use dd/MM/yyyy.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Validates the user input fields.
     *
     * @param title The title input.
     * @param description The description input.
     * @param dateInput The date input.
     * @return True if all inputs are valid, otherwise false.
     */
    private boolean validateInputs(String title, String description, String dateInput) {
        if (title.isEmpty() || description.isEmpty() || dateInput.isEmpty()) {
            Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * Called when the fragment's view is destroyed.
     * Cleans up resources.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Avoid memory leaks by clearing the binding
    }
}
