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

/**
 * @author solaimi
 * DirectoryViewModel is responsible for handling the logic related to the directory.
 * It manages the retrieval and filtering of directory entries, and the search functionality
 * by name, unit, and role. It also provides error handling and network availability checks.
 */
public class DirectoryViewModel extends ViewModel {

    // Dependencies
    private final DirectoryRepository repository;           // Repository to fetch directory data
    private final NetworkUtils networkUtils;                // Utility for checking network availability

    // Mutable LiveData for managing directory entries, search results, and error messages
    private final MutableLiveData<List<DirectoryDataModel>> directoryEntries;
    private final MutableLiveData<Resource<List<DirectoryDataModel>>> searchResults;
    private final MutableLiveData<String> errorMessage;

    // List to store the search history
    private final List<String> searchHistory;

    // Directory repository instance (not used)
    private final DirectoryRepository directoryRepository;

    /**
     * Constructor for DirectoryViewModel.
     *
     * @param repository The repository responsible for fetching directory data.
     * @param networkUtils The utility to check network availability.
     */
    public DirectoryViewModel(DirectoryRepository repository, NetworkUtils networkUtils) {
        this.repository = repository;
        this.networkUtils = networkUtils;
        this.directoryEntries = new MutableLiveData<>();
        this.searchResults = new MutableLiveData<>();
        this.errorMessage = new MutableLiveData<>();
        this.searchHistory = new ArrayList<>();
        this.directoryRepository = null;  // Not used in the current implementation
    }

    /**
     * Loads the directory entries either from the network or local storage.
     * Displays an error message if loading fails.
     */
    public void loadDirectory() {
        try {
            if (!networkUtils.isNetworkAvailable()) {
                errorMessage.setValue(Constants.ERROR_NETWORK); // Error if no network is available
                directoryEntries.setValue(repository.getAllEntries()); // Fallback to local entries
                return;
            }

            // Fetch entries from the repository
            List<DirectoryDataModel> entries = repository.getAllEntries();
            if (entries != null) {
                directoryEntries.setValue(entries); // Set the directory entries
            } else {
                errorMessage.setValue("Failed to load directory"); // Error message if fetching fails
            }
        } catch (Exception e) {
            // Catch any exceptions and show the error message
            errorMessage.setValue("Error loading directory: " + e.getMessage());
        }
    }

    /**
     * Searches the directory by name. Validates the search query before executing the search.
     *
     * @param name The name to search for in the directory.
     * @return A list of directory entries matching the search query.
     */
    public List<DirectoryDataModel> searchByName(String name) {
        try {
            if (!ValidationUtils.isValidSearchQuery(name)) {
                errorMessage.setValue(Constants.ERROR_INVALID_SEARCH); // Invalid search query error
                return new ArrayList<>();
            }
            addToSearchHistory(name); // Add the valid query to search history
            return repository.searchByName(name); // Return search results from repository
        } catch (Exception e) {
            errorMessage.setValue("Search error: " + e.getMessage()); // Error message on exception
            return new ArrayList<>();
        }
    }

    /**
     * Searches the directory by unit. Validates the unit before executing the search.
     *
     * @param unit The unit to search for in the directory.
     * @return A list of directory entries matching the unit.
     */
    public List<DirectoryDataModel> searchByUnit(String unit) {
        try {
            if (!ValidationUtils.isValidUnit(unit)) {
                errorMessage.setValue(Constants.ERROR_INVALID_UNIT); // Invalid unit error
                return new ArrayList<>();
            }
            return repository.searchByUnit(unit); // Return search results by unit
        } catch (Exception e) {
            errorMessage.setValue("Unit search error: " + e.getMessage()); // Error message on exception
            return new ArrayList<>();
        }
    }

    /**
     * Filters the directory entries by role. Validates the role before executing the filter.
     *
     * @param role The role to filter by in the directory.
     * @return A list of directory entries matching the role.
     */
    public List<DirectoryDataModel> filterByRole(String role) {
        try {
            if (!ValidationUtils.isValidRole(role)) {
                errorMessage.setValue(Constants.ERROR_INVALID_ROLE); // Invalid role error
                return new ArrayList<>();
            }
            return repository.filterByRole(role); // Return filtered directory entries by role
        } catch (Exception e) {
            errorMessage.setValue("Filter error: " + e.getMessage()); // Error message on exception
            return new ArrayList<>();
        }
    }

    /**
     * Fetches the details of a directory member by their ID. Validates the member ID before retrieving the details.
     *
     * @param memberId The ID of the member whose details are to be fetched.
     * @return The directory entry of the member, or null if the member ID is invalid.
     */
    public DirectoryDataModel getMemberDetails(String memberId) {
        try {
            if (!ValidationUtils.isValidMemberId(memberId)) {
                errorMessage.setValue(Constants.ERROR_INVALID_ID); // Invalid member ID error
                return null;
            }
            return repository.getMemberDetails(memberId); // Return member details
        } catch (Exception e) {
            errorMessage.setValue("Error fetching member details: " + e.getMessage()); // Error message on exception
            return null;
        }
    }

    /**
     * Filters the directory entries by both role and unit.
     *
     * @param role The role to filter by.
     * @param unit The unit to filter by.
     * @return A list of directory entries matching both criteria.
     */
    public List<DirectoryDataModel> filterCombined(String role, String unit) {
        try {
            return repository.filterCombined(role, unit); // Return filtered directory entries by both role and unit
        } catch (Exception e) {
            errorMessage.setValue("Filter error: " + e.getMessage()); // Error message on exception
            return new ArrayList<>();
        }
    }

    /**
     * Adds a search query to the search history if it is not already present.
     *
     * @param query The query to be added to the search history.
     */
    private void addToSearchHistory(String query) {
        if (!searchHistory.contains(query)) {
            searchHistory.add(query); // Add query to search history
        }
    }

    // Getters for LiveData and search history

    public LiveData<List<DirectoryDataModel>> getDirectoryEntries() {
        return directoryEntries; // Get live data for directory entries
    }

    public LiveData<Resource<List<DirectoryDataModel>>> getSearchResults() {
        return searchResults; // Get live data for search results
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage; // Get live data for error messages
    }

    public List<String> getSearchHistory() {
        return new ArrayList<>(searchHistory); // Return a copy of the search history
    }
}
