package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.smartlivingcommunity.data.model.DirectoryDataModel;
import com.example.smartlivingcommunity.data.repository.DirectoryRepository;
import com.example.smartlivingcommunity.utils.NetworkUtils;
import java.util.ArrayList;
import java.util.List;

public class DirectoryViewModel extends ViewModel {
    private final DirectoryRepository repository;
    private final NetworkUtils networkUtils;
    private final MutableLiveData<List<DirectoryDataModel>> directoryEntries;
    private final MutableLiveData<String> error;

    public DirectoryViewModel(DirectoryRepository repository, NetworkUtils networkUtils) {
        this.repository = repository;
        this.networkUtils = networkUtils;
        this.directoryEntries = new MutableLiveData<>(new ArrayList<>());
        this.error = new MutableLiveData<>();
        loadDirectory();
    }

    public void loadDirectory() {
        try {
            List<DirectoryDataModel> result = repository.getAllEntries();
            directoryEntries.setValue(result);
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

    public List<DirectoryDataModel> getPagedEntries(int pageNumber, int pageSize) {
        List<DirectoryDataModel> allEntries = new ArrayList<>(directoryEntries.getValue());
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, allEntries.size());
        return allEntries.subList(startIndex, endIndex);
    }

    public List<DirectoryDataModel> getSortedByName() {
        List<DirectoryDataModel> entries = new ArrayList<>(directoryEntries.getValue());
        entries.sort((a, b) -> a.getName().compareTo(b.getName()));
        return entries;
    }

    public List<DirectoryDataModel> filterCombined(String role, String unit) {
        return repository.filterCombined(role, unit);
    }
}