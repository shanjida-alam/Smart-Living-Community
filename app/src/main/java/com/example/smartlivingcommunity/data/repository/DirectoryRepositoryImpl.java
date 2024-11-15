package com.example.smartlivingcommunity.data.repository;

import com.example.smartlivingcommunity.data.model.DirectoryDataModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DirectoryRepositoryImpl implements DirectoryRepository {
    private final List<DirectoryDataModel> directoryData;

    public DirectoryRepositoryImpl() {
        directoryData = new ArrayList<>();
        // Initialize with some test data
        initializeTestData();
    }

    private void initializeTestData() {
        directoryData.add(new DirectoryDataModel("1", "John Doe", "A101", "resident"));
        directoryData.add(new DirectoryDataModel("2", "Jane Smith", "B202", "resident"));
        directoryData.add(new DirectoryDataModel("3", "Security Team", "NA", "security"));
    }

    @Override
    public List<DirectoryDataModel> getAllEntries() {
        return new ArrayList<>(directoryData);
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

    @Override
    public List<DirectoryDataModel> getPagedEntries(int pageNumber, int pageSize) {
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, directoryData.size());
        return new ArrayList<>(directoryData.subList(startIndex, endIndex));
    }

    @Override
    public List<DirectoryDataModel> getSortedByName() {
        List<DirectoryDataModel> sortedData = new ArrayList<>(directoryData);
        sortedData.sort(Comparator.comparing(DirectoryDataModel::getName));
        return sortedData;
    }

    @Override
    public List<DirectoryDataModel> filterCombined(String role, String unit) {
        return directoryData.stream()
                .filter(member -> member.getRole().equals(role) && member.getUnit().equals(unit))
                .collect(Collectors.toList());
    }
}