package com.example.smartlivingcommunity.ui.view.board;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import java.util.Date;

import com.example.smartlivingcommunity.databinding.FragmentCreateAnnouncementBinding;
import com.example.smartlivingcommunity.ui.viewmodel.CreateAnnouncementViewModel;

public class CreateAnnouncementView extends Fragment {
    private CreateAnnouncementViewModel viewModel;
    private FragmentCreateAnnouncementBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreateAnnouncementBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(CreateAnnouncementViewModel.class);

        setupObservers();
        setupClickListeners();

        return binding.getRoot();
    }

    private void setupObservers() {
        viewModel.getCreateResult().observe(getViewLifecycleOwner(), result -> {
            if (result.isSuccess()) {
                Toast.makeText(requireContext(), "Announcement created successfully", Toast.LENGTH_SHORT).show();
                requireActivity().onBackPressed();
            } else {
                Toast.makeText(requireContext(), result.getError(), Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            binding.buttonCreate.setEnabled(!isLoading);
        });
    }

    private void setupClickListeners() {
        binding.buttonCreate.setOnClickListener(v -> {
            String title = binding.editTextTitle.getText().toString();
            String description = binding.editTextDescription.getText().toString();
            Date date = new Date();

            viewModel.createAnnouncement(title, description, date);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}