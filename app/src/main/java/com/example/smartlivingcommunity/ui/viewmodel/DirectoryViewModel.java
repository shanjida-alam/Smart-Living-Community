package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.smartlivingcommunity.data.model.DirectoryDataModel;
import com.example.smartlivingcommunity.data.repository.DirectoryRepository;
import com.example.smartlivingcommunity.utils.NetworkUtils;
import java.util.List;

public class DirectoryViewModel extends ViewModel {
    private final DirectoryRepository repository;
    private final NetworkUtils networkUtils;
    private final MutableLiveData<List<DirectoryDataModel>> directoryEntries;
    private final MutableLiveData<String> error;

    public DirectoryViewModel(DirectoryRepository repository, NetworkUtils networkUtils) {
        this.repository = repository;
        this.networkUtils = networkUtils;
        this.directoryEntries = new MutableLiveData<>();
        this.error = new MutableLiveData<>();
    }

    public void loadDirectory() {
        try {
            LiveData<List<DirectoryDataModel>> result = repository.getAllEntries();
            directoryEntries.setValue(result.getValue());
        } catch (Exception e) {
            error.setValue("Failed to load directory: " + e.getMessage());
        }
    }

    public LiveData<List<DirectoryDataModel>> getDirectoryEntries() {
        return directoryEntries;
    }

    public List<DirectoryDataModel> searchByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Search name cannot be empty");
        }
        return repository.searchByName(name);
    }

    public List<DirectoryDataModel> searchByUnit(String unit) {
        if (unit == null || unit.trim().isEmpty()) {
            throw new IllegalArgumentException("Unit number cannot be empty");
        }
        return repository.searchByUnit(unit);
    }

    public List<DirectoryDataModel> filterByRole(String role) {
        if (role == null || role.trim().isEmpty()) {
            throw new IllegalArgumentException("Role cannot be empty");
        }
        return repository.filterByRole(role);
    }

    public DirectoryDataModel getMemberDetails(String memberId) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be empty");
        }
        return repository.getMemberDetails(memberId);
    }

    public LiveData<String> getError() {
        return error;
    }
}