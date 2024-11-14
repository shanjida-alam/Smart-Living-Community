// DirectoryRepository.java
package com.example.smartlivingcommunity.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartlivingcommunity.data.model.DirectoryDataModel;

import java.util.List;

public class DirectoryRepository {
    private final MutableLiveData<List<DirectoryDataModel>> directoryEntries = new MutableLiveData<>();

    public LiveData<List<DirectoryDataModel>> getAllEntries() {
        // In real implementation, this would fetch from database/network
        return directoryEntries;
    }

    public void loadEntries(List<DirectoryDataModel> entries) {
        directoryEntries.setValue(entries);
    }
}