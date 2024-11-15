package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartlivingcommunity.data.model.DirectoryDataModel;
import com.example.smartlivingcommunity.data.repository.DirectoryRepository;
import com.example.smartlivingcommunity.utils.Constants;
import com.example.smartlivingcommunity.utils.NetworkUtils;
import com.example.smartlivingcommunity.utils.Resource;
import com.example.smartlivingcommunity.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

// DirectoryViewModel.java
public class DirectoryViewModel extends ViewModel {
    private final DirectoryRepository repository;
    private final NetworkUtils networkUtils;
    private final MutableLiveData<List<DirectoryDataModel>> directoryEntries;
    private final MutableLiveData<Resource<List<DirectoryDataModel>>> searchResults;
    private final MutableLiveData<String> errorMessage;
    private final List<String> searchHistory;

    public DirectoryViewModel(DirectoryRepository repository, NetworkUtils networkUtils) {
        this.repository = repository;
        this.networkUtils = networkUtils;
        this.directoryEntries = new MutableLiveData<>();
        this.searchResults = new MutableLiveData<>();
        this.errorMessage = new MutableLiveData<>();
        this.searchHistory = new ArrayList<>();
    }

    public void loadDirectory() {
        try {
            if (!networkUtils.isNetworkAvailable()) {
                errorMessage.setValue(Constants.ERROR_NETWORK);
                directoryEntries.setValue(repository.getAllEntries());
                return;
            }

            List<DirectoryDataModel> entries = repository.getAllEntries();
            if (entries != null) {
                directoryEntries.setValue(entries);
            } else {
                errorMessage.setValue("Failed to load directory");
            }
        } catch (Exception e) {
            errorMessage.setValue("Error loading directory: " + e.getMessage());
        }
    }

    public List<DirectoryDataModel> searchByName(String name) {
        try {
            if (!ValidationUtils.isValidSearchQuery(name)) {
                errorMessage.setValue(Constants.ERROR_INVALID_SEARCH);
                return new ArrayList<>();
            }
            addToSearchHistory(name);
            return repository.searchByName(name);
        } catch (Exception e) {
            errorMessage.setValue("Search error: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<DirectoryDataModel> searchByUnit(String unit) {
        try {
            if (!ValidationUtils.isValidUnit(unit)) {
                errorMessage.setValue(Constants.ERROR_INVALID_UNIT);
                return new ArrayList<>();
            }
            return repository.searchByUnit(unit);
        } catch (Exception e) {
            errorMessage.setValue("Unit search error: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<DirectoryDataModel> filterByRole(String role) {
        try {
            if (!ValidationUtils.isValidRole(role)) {
                errorMessage.setValue(Constants.ERROR_INVALID_ROLE);
                return new ArrayList<>();
            }
            return repository.filterByRole(role);
        } catch (Exception e) {
            errorMessage.setValue("Filter error: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public DirectoryDataModel getMemberDetails(String memberId) {
        try {
            if (!ValidationUtils.isValidMemberId(memberId)) {
                errorMessage.setValue(Constants.ERROR_INVALID_ID);
                return null;
            }
            return repository.getMemberDetails(memberId);
        } catch (Exception e) {
            errorMessage.setValue("Error fetching member details: " + e.getMessage());
            return null;
        }
    }

    public List<DirectoryDataModel> filterCombined(String role, String unit) {
        try {
            return repository.filterCombined(role, unit);
        } catch (Exception e) {
            errorMessage.setValue("Filter error: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private void addToSearchHistory(String query) {
        if (!searchHistory.contains(query)) {
            searchHistory.add(query);
        }
    }

    public LiveData<List<DirectoryDataModel>> getDirectoryEntries() {
        return directoryEntries;
    }

    public LiveData<Resource<List<DirectoryDataModel>>> getSearchResults() {
        return searchResults;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public List<String> getSearchHistory() {
        return new ArrayList<>(searchHistory);
    }
}