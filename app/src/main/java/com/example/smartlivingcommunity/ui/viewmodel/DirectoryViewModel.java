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
    private final MutableLiveData<List<DirectoryDataModel>> directoryEntries = new MutableLiveData<>(new ArrayList<>());

    public DirectoryViewModel(DirectoryRepository repository, NetworkUtils networkUtils) {
        this.repository = repository;
        this.networkUtils = networkUtils;
    }

    // Load directory entries when explicitly called
    public void loadDirectory() {
        if (networkUtils.isNetworkAvailable()) {
            directoryEntries.setValue(repository.getAllEntries());
        } else {
            // Handle no network scenario
            directoryEntries.setValue(new ArrayList<>());
        }
    }

    public LiveData<List<DirectoryDataModel>> getDirectoryEntries() {
        return directoryEntries;
    }

    public List<DirectoryDataModel> searchByName(String name) {
        return repository.searchByName(name);
    }

    public List<DirectoryDataModel> searchByUnit(String unit) {
        return repository.searchByUnit(unit);
    }

    public List<DirectoryDataModel> filterByRole(String role) {
        return repository.filterByRole(role);
    }

    public DirectoryDataModel getMemberDetails(String memberId) {
        return repository.getMemberDetails(memberId);
    }

    public List<DirectoryDataModel> filterCombined(String role, String unit) {
        return repository.filterCombined(role, unit);
    }
}
