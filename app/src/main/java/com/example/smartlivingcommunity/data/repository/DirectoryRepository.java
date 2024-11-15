package com.example.smartlivingcommunity.data.repository;

import androidx.lifecycle.LiveData;
import com.example.smartlivingcommunity.data.model.DirectoryDataModel;
import java.util.List;

public interface DirectoryRepository {
    LiveData<List<DirectoryDataModel>> getAllEntries();
    List<DirectoryDataModel> searchByName(String name);
    List<DirectoryDataModel> searchByUnit(String unit);
    List<DirectoryDataModel> filterByRole(String role);
    DirectoryDataModel getMemberDetails(String memberId);
}