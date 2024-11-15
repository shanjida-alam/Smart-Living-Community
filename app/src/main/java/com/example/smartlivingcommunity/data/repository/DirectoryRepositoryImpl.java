package com.example.smartlivingcommunity.data.repository;

import androidx.lifecycle.MutableLiveData;
import com.example.smartlivingcommunity.data.model.DirectoryDataModel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author solaimi
 * Implementation of the DirectoryRepository interface.
 * Provides methods to interact with directory data, such as searching and filtering entries.
 */
public class DirectoryRepositoryImpl implements DirectoryRepository {

    // List to store directory data
    private final List<DirectoryDataModel> directoryData;

    // LiveData object to hold directory data changes
    private final MutableLiveData<List<DirectoryDataModel>> directoryLiveData;

    /**
     * Initializes a new instance of the DirectoryRepositoryImpl class and
     * populates initial data for testing or demonstration purposes.
     */
    public DirectoryRepositoryImpl() {
        directoryData = new ArrayList<>();
        directoryLiveData = new MutableLiveData<>();
        loadInitialData();
    }

    /**
     * Loads initial data into the directory list.
     */
    private void loadInitialData() {
        directoryData.add(new DirectoryDataModel("1", "John Doe", "A101", "resident"));
        directoryData.add(new DirectoryDataModel("2", "Jane Smith", "B202", "resident"));
        directoryData.add(new DirectoryDataModel("3", "Security Team", "NA", "security"));
        directoryLiveData.setValue(new ArrayList<>(directoryData));
    }

    /**
     * Retrieves all entries in the directory.
     *
     * @return A list of all DirectoryDataModel objects.
     */
    @Override
    public List<DirectoryDataModel> getAllEntries() {
        return new ArrayList<>(directoryData);
    }

    /**
     * Searches for directory entries by name.
     *
     * @param name The name to search for.
     * @return A list of DirectoryDataModel objects with matching names.
     */
    @Override
    public List<DirectoryDataModel> searchByName(String name) {
        return directoryData.stream()
                .filter(member -> member.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Searches for directory entries by unit.
     *
     * @param unit The unit to search for.
     * @return A list of DirectoryDataModel objects with the specified unit.
     */
    @Override
    public List<DirectoryDataModel> searchByUnit(String unit) {
        return directoryData.stream()
                .filter(member -> member.getUnit().equals(unit))
                .collect(Collectors.toList());
    }

    /**
     * Filters directory entries by role.
     *
     * @param role The role to filter by.
     * @return A list of DirectoryDataModel objects with the specified role.
     */
    @Override
    public List<DirectoryDataModel> filterByRole(String role) {
        return directoryData.stream()
                .filter(member -> member.getRole().equals(role))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves member details by ID.
     *
     * @param memberId The ID of the member to retrieve.
     * @return The DirectoryDataModel object for the member, or null if not found.
     */
    @Override
    public DirectoryDataModel getMemberDetails(String memberId) {
        return directoryData.stream()
                .filter(member -> member.getId().equals(memberId))
                .findFirst()
                .orElse(null);
    }

    /**
     * Filters directory entries by role and unit.
     *
     * @param role The role to filter by.
     * @param unit The unit to filter by.
     * @return A list of DirectoryDataModel objects that match the specified role and unit.
     */
    @Override
    public List<DirectoryDataModel> filterCombined(String role, String unit) {
        return directoryData.stream()
                .filter(member -> member.getRole().equals(role) && member.getUnit().equals(unit))
                .collect(Collectors.toList());
    }
}
