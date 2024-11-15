package com.example.smartlivingcommunity.data.repository;

import com.example.smartlivingcommunity.data.model.DirectoryDataModel;

import java.util.List;

/**
 * @author solaimi
 * Interface representing a repository for managing directory entries in the Smart Living Community.
 */
public interface DirectoryRepository {

    /**
     * Retrieves all directory entries.
     *
     * @return A list of all entries in the directory.
     */
    List<DirectoryDataModel> getAllEntries();

    /**
     * Searches for directory entries by name.
     *
     * @param name The name to search for.
     * @return A list of matching directory entries.
     */
    List<DirectoryDataModel> searchByName(String name);

    /**
     * Searches for directory entries by unit.
     *
     * @param unit The unit to search for.
     * @return A list of matching directory entries.
     */
    List<DirectoryDataModel> searchByUnit(String unit);

    /**
     * Filters directory entries by role.
     *
     * @param role The role to filter by (e.g., "resident", "security").
     * @return A list of directory entries matching the specified role.
     */
    List<DirectoryDataModel> filterByRole(String role);

    /**
     * Retrieves details of a specific member based on their ID.
     *
     * @param memberId The unique ID of the member.
     * @return An Optional containing the member details if found, or an empty Optional otherwise.
     */
    DirectoryDataModel getMemberDetails(String memberId);

    /**
     * Filters directory entries by a combination of role and unit.
     *
     * @param role The role to filter by (e.g., "resident", "security").
     * @param unit The unit to filter by (e.g., apartment number).
     * @return A list of directory entries matching the specified criteria.
     */
    List<DirectoryDataModel> filterCombined(String role, String unit);
}

