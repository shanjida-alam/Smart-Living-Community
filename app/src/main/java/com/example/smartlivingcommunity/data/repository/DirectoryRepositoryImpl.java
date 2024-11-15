// com/example/smartlivingcommunity/data/repository/DirectoryRepositoryImpl.java
package com.example.smartlivingcommunity.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.smartlivingcommunity.data.model.DirectoryDataModel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DirectoryRepositoryImpl implements DirectoryRepository {
    private final List<DirectoryDataModel> directoryData;
    private final MutableLiveData<List<DirectoryDataModel>> directoryLiveData;

    public DirectoryRepositoryImpl() {
        directoryData = new ArrayList<>();
        directoryLiveData = new MutableLiveData<>();
        // Initialize with some test data
        initializeTestData();
    }

    private void initializeTestData() {
        directoryData.add(new DirectoryDataModel("1", "John Doe", "A101", "resident"));
        directoryData.add(new DirectoryDataModel("2", "Jane Smith", "B202", "resident"));
        directoryData.add(new DirectoryDataModel("3", "Security Team", "NA", "security"));
        directoryLiveData.setValue(directoryData);
    }

    @Override
    public LiveData<List<DirectoryDataModel>> getAllEntries() {
        return directoryLiveData;
    }

    @Override
    public List<DirectoryDataModel> searchByName(String name) {
        return directoryData.stream()
                .filter(member -> member.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DirectoryDataModel> searchByUnit(String unit) {
        return directoryData.stream()
                .filter(member -> member.getUnit().equals(unit))
                .collect(Collectors.toList());
    }

    @Override
    public List<DirectoryDataModel> filterByRole(String role) {
        return directoryData.stream()
                .filter(member -> member.getRole().equals(role))
                .collect(Collectors.toList());
    }

    @Override
    public DirectoryDataModel getMemberDetails(String memberId) {
        return directoryData.stream()
                .filter(member -> member.getId().equals(memberId))
                .findFirst()
                .orElse(null);
    }
}
