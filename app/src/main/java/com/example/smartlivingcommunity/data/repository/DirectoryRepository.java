package com.example.smartlivingcommunity.data.repository;

import com.example.smartlivingcommunity.data.model.DirectoryDataModel;
import java.util.List;

public interface DirectoryRepository {
    List<DirectoryDataModel> getAllEntries();
    List<DirectoryDataModel> searchByName(String name);
    List<DirectoryDataModel> searchByUnit(String unit);
    List<DirectoryDataModel> filterByRole(String role);
    DirectoryDataModel getMemberDetails(String memberId);
    List<DirectoryDataModel> filterCombined(String role, String unit);
}
