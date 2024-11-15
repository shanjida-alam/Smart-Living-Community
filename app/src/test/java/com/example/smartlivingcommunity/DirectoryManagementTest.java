package com.example.smartlivingcommunity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.example.smartlivingcommunity.data.model.DirectoryDataModel;
import com.example.smartlivingcommunity.data.repository.DirectoryRepository;
import com.example.smartlivingcommunity.ui.viewmodel.DirectoryViewModel;
import com.example.smartlivingcommunity.utils.NetworkUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DirectoryManagementTest implements AutoCloseable {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private DirectoryRepository mockRepository;

    @Mock
    private NetworkUtils networkUtils;

    @Mock
    private Observer<List<DirectoryDataModel>> directoryObserver;

    private DirectoryViewModel viewModel;
    private List<DirectoryDataModel> testData;
    private AutoCloseable mockCloseable;

    @Before
    public void setUp() {
        mockCloseable = MockitoAnnotations.openMocks(this);
        viewModel = new DirectoryViewModel(mockRepository, networkUtils);
        testData = createTestData();
    }

    @After
    public void tearDown() throws Exception {
        mockCloseable.close();
        if (directoryObserver != null) {
            viewModel.getDirectoryEntries().removeObserver(directoryObserver);
        }
    }

    @Test
    public void testLoadDirectory() {
        // Arrange
        List<DirectoryDataModel> mockResult = createTestData();
        when(mockRepository.getAllEntries()).thenReturn(mockResult);

        // Act
        viewModel.loadDirectory();
        List<DirectoryDataModel> result = viewModel.getDirectoryEntries().getValue();

        // Assert
        assertNotNull("Directory entries should not be null", result);
        assertEquals("Directory should contain all entries", mockResult.size(), result.size());
    }

    @Test
    public void testSearchByName() {
        // Arrange
        String searchQuery = "John";
        List<DirectoryDataModel> expectedResults = filterByName(testData, searchQuery);
        when(mockRepository.searchByName(searchQuery)).thenReturn(expectedResults);

        // Act
        List<DirectoryDataModel> results = viewModel.searchByName(searchQuery);

        // Assert
        assertNotNull("Search results should not be null", results);
        assertTrue("Results should contain search query",
                results.stream().allMatch(member ->
                        member.getName().toLowerCase().contains(searchQuery.toLowerCase())));
    }

    @Test
    public void testSearchByUnit() {
        // Arrange
        String unitNumber = "A101";
        List<DirectoryDataModel> expectedResults = filterByUnit(testData, unitNumber);
        when(mockRepository.searchByUnit(unitNumber)).thenReturn(expectedResults);

        // Act
        List<DirectoryDataModel> results = viewModel.searchByUnit(unitNumber);

        // Assert
        assertNotNull("Unit search results should not be null", results);
        assertTrue("Results should match unit number",
                results.stream().allMatch(member -> member.getUnit().equals(unitNumber)));
    }

    @Test
    public void testFilterByRole() {
        // Arrange
        String role = "resident";
        List<DirectoryDataModel> expectedResults = filterByRole(testData, role);
        when(mockRepository.filterByRole(role)).thenReturn(expectedResults);

        // Act
        List<DirectoryDataModel> results = viewModel.filterByRole(role);

        // Assert
        assertNotNull("Role filter results should not be null", results);
        assertTrue("Results should match role",
                results.stream().allMatch(member -> member.getRole().equals(role)));
    }

    @Test
    public void testViewMemberDetails() {
        // Arrange
        String memberId = "1";
        DirectoryDataModel expectedMember = testData.get(0);
        when(mockRepository.getMemberDetails(memberId)).thenReturn(expectedMember);

        // Act
        DirectoryDataModel result = viewModel.getMemberDetails(memberId);

        // Assert
        assertNotNull("Member details should not be null", result);
        assertEquals("Member details should match", expectedMember.getId(), result.getId());
    }

    @Test
    public void testEmptySearchResults() {
        String searchQuery = "NonExistent";
        when(mockRepository.searchByName(searchQuery)).thenReturn(new ArrayList<>());

        List<DirectoryDataModel> results = viewModel.searchByName(searchQuery);

        assertTrue(results.isEmpty());
    }

    @Test
    public void testCombinedFilters() {
        String role = "resident";
        String unit = "A101";
        List<DirectoryDataModel> filteredList = filterCombined(testData, role, unit);
        when(mockRepository.filterCombined(role, unit)).thenReturn(filteredList);

        List<DirectoryDataModel> results = viewModel.filterCombined(role, unit);

        assertTrue(results.stream().allMatch(member ->
                member.getRole().equals(role) && member.getUnit().equals(unit)));
    }

    @Test
    public void testSearchWithSpecialCharacters() {
        String searchQuery = "John@Doe";
        when(mockRepository.searchByName(searchQuery)).thenReturn(new ArrayList<>());

        List<DirectoryDataModel> results = viewModel.searchByName(searchQuery);

        assertTrue(results.isEmpty());
    }

    private List<DirectoryDataModel> createTestData() {
        List<DirectoryDataModel> data = new ArrayList<>();
        data.add(new DirectoryDataModel("1", "John Doe", "A101", "resident"));
        data.add(new DirectoryDataModel("2", "Jane Smith", "B202", "resident"));
        data.add(new DirectoryDataModel("3", "Security Team", "NA", "security"));
        return data;
    }

    private List<DirectoryDataModel> filterByName(List<DirectoryDataModel> data, String name) {
        return data.stream()
                .filter(member -> member.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    private List<DirectoryDataModel> filterByUnit(List<DirectoryDataModel> data, String unit) {
        return data.stream()
                .filter(member -> member.getUnit().equals(unit))
                .collect(Collectors.toList());
    }

    private List<DirectoryDataModel> filterByRole(List<DirectoryDataModel> data, String role) {
        return data.stream()
                .filter(member -> member.getRole().equals(role))
                .collect(Collectors.toList());
    }

    private List<DirectoryDataModel> filterCombined(List<DirectoryDataModel> data, String role, String unit) {
        return data.stream()
                .filter(member -> member.getRole().equals(role) && member.getUnit().equals(unit))
                .collect(Collectors.toList());
    }

    @Override
    public void close() throws Exception {
        tearDown();
    }
}