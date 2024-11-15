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
        viewModel.getDirectoryEntries().removeObserver(directoryObserver);
    }

    // Test Case 1: Test loading directory
    @Test
    public void testLoadDirectory() {
        // Arrange
        List<DirectoryDataModel> mockResult = createTestData();
        when(mockRepository.getAllEntries()).thenReturn(mockResult);
        when(networkUtils.isNetworkAvailable()).thenReturn(true); // Assuming network is available

        // Act
        viewModel.getDirectoryEntries().observeForever(directoryObserver);
        viewModel.loadDirectory();

        // Assert
        verify(directoryObserver).onChanged(mockResult); // Ensure observer was notified with the correct data
    }

    // Test Case 2: Test search by name with valid input
    @Test
    public void testSearchByName() {
        // Arrange
        String searchQuery = "John"; // Valid input: "John"
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

    // Test Case 3: Test search by unit with valid input
    @Test
    public void testSearchByUnit() {
        // Arrange
        String unitNumber = "A101"; // Valid input: "A101"
        List<DirectoryDataModel> expectedResults = filterByUnit(testData, unitNumber);
        when(mockRepository.searchByUnit(unitNumber)).thenReturn(expectedResults);

        // Act
        List<DirectoryDataModel> results = viewModel.searchByUnit(unitNumber);

        // Assert
        assertNotNull("Unit search results should not be null", results);
        assertTrue("Results should match unit number",
                results.stream().allMatch(member -> member.getUnit().equals(unitNumber)));
    }

    // Test Case 4: Test filter by role with valid input
    @Test
    public void testFilterByRole() {
        // Arrange
        String role = "resident"; // Valid input: "resident"
        List<DirectoryDataModel> expectedResults = filterByRole(testData, role);
        when(mockRepository.filterByRole(role)).thenReturn(expectedResults);

        // Act
        List<DirectoryDataModel> results = viewModel.filterByRole(role);

        // Assert
        assertNotNull("Role filter results should not be null", results);
        assertTrue("Results should match role",
                results.stream().allMatch(member -> member.getRole().equals(role)));
    }

    // Test Case 5: Test viewing member details with valid input
    @Test
    public void testViewMemberDetails() {
        // Arrange
        String memberId = "1"; // Valid input: "1"
        DirectoryDataModel expectedMember = testData.get(0);
        when(mockRepository.getMemberDetails(memberId)).thenReturn(expectedMember);

        // Act
        DirectoryDataModel result = viewModel.getMemberDetails(memberId);

        // Assert
        assertNotNull("Member details should not be null", result);
        assertEquals("Member details should match", expectedMember.getId(), result.getId());
    }

    // Test Case 6: Test empty search results for non-existent query
    @Test
    public void testEmptySearchResults() {
        String searchQuery = "NonExistent"; // Valid input: "NonExistent"
        when(mockRepository.searchByName(searchQuery)).thenReturn(new ArrayList<>());

        List<DirectoryDataModel> results = viewModel.searchByName(searchQuery);

        assertTrue(results.isEmpty());
    }

    // Test Case 7: Test combined filter by role and unit with valid input
    @Test
    public void testCombinedFilters() {
        String role = "resident"; // Valid input: "resident"
        String unit = "A101"; // Valid input: "A101"
        List<DirectoryDataModel> filteredList = filterCombined(testData, role, unit);
        when(mockRepository.filterCombined(role, unit)).thenReturn(filteredList);

        List<DirectoryDataModel> results = viewModel.filterCombined(role, unit);

        assertTrue(results.stream().allMatch(member ->
                member.getRole().equals(role) && member.getUnit().equals(unit)));
    }

    // Test Case 8: Test search with special characters in the query
    @Test
    public void testSearchWithSpecialCharacters() {
        String searchQuery = "John@Doe"; // Valid input: "John@Doe"
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
