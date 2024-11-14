// DirectoryViewModel.java
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
    private final MutableLiveData<List<DirectoryDataModel>> directoryEntries = new MutableLiveData<>();

    public DirectoryViewModel(DirectoryRepository repository, NetworkUtils networkUtils) {
        this.repository = repository;
        this.networkUtils = networkUtils;
    }

    public void loadDirectory() {
        LiveData<List<DirectoryDataModel>> result = repository.getAllEntries();
        directoryEntries.setValue(result.getValue());
    }

    public LiveData<List<DirectoryDataModel>> getDirectoryEntries() {
        return directoryEntries;
    }
}