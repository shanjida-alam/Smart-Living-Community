/**
 * Test suite for Directory Management feature focusing on resident access
 * Implements MVVM architecture testing with Repository pattern
 *
 * @author YourName
 */
package com.example.smartlivingcommunity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;


import com.example.smartlivingcommunity.data.model.DirectoryDataModel;
import com.example.smartlivingcommunity.data.repository.DirectoryRepository;
import com.example.smartlivingcommunity.ui.viewmodel.DirectoryViewModel;
import com.example.smartlivingcommunity.utils.NetworkUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;


public class DirectoryManagementTest {

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

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        viewModel = new DirectoryViewModel(mockRepository, networkUtils);
        testData = createTestData();
    }

    /**
     * Test Case 1: Basic Directory Access (DIR_RES_001)
     * Verifies that residents can access and view the directory
     */
    @Test
    public void testBasicDirectoryAccess() {
        // Arrange
        MutableLiveData<List<DirectoryDataModel>> mockResult = new MutableLiveData<>();
        mockResult.setValue(testData);
        when(mockRepository.getAllEntries()).thenReturn(mockResult);

        // Act
        viewModel.loadDirectory();
        viewModel.getDirectoryEntries().observeForever(directoryObserver);

        // Assert
        verify(directoryObserver).onChanged(testData);
        assertEquals("Directory should contain all entries", testData.size(),
                viewModel.getDirectoryEntries().getValue().size());
    }
}

/**
 * Test Case 2: Basic Search Functionality (DIR_RES_002)
 * Verifies that residents can search directory using name
 */
@Test
public void testBasicSearch() {
    // Arrange
    String searchQuery = "John";
    List<DirectoryDataModel> filteredList = filterByName(testData, searchQuery);
    MutableLiveData<List<DirectoryDataModel>> mockResult = new MutableLiveData<>();
    mockResult.setValue(filteredList);
    when(mockRepository.searchDirectory(searchQuery)).thenReturn(mockResult);

    // Act
    viewModel.setSearchQuery(searchQuery);
    viewModel.getSearchResults().observeForever(directoryObserver);

    // Assert
    verify(directoryObserver).onChanged(filteredList);
    assertTrue("Search results should contain query string",
            viewModel.getSearchResults().getValue().stream()
                    .anyMatch(entry -> entry.getName().contains(searchQuery)));
}
/**
 * Test Case 3: View Member Details (DIR_RES_004)
 * Verifies that residents can view permitted member details
 */
@Test
public void testViewMemberDetails() {
    // Arrange
    String memberId = "123";
    DirectoryDataModel member = testData.get(0);
    MutableLiveData<DirectoryDataModel> mockResult = new MutableLiveData<>();
    mockResult.setValue(member);
    when(mockRepository.getMemberDetails(memberId)).thenReturn(mockResult);

    // Act
    viewModel.loadMemberDetails(memberId);

    // Assert
    assertEquals("Member details should match", member,
            viewModel.getMemberDetails().getValue());
}

/**
 * Test Case 4: Contact Information Privacy (DIR_RES_005)
 * Verifies privacy settings for contact information
 */
@Test
public void testContactPrivacy() {
    // Arrange
    DirectoryDataModel member = createMemberWithPrivacySettings(false);

    // Act
    boolean canAccessContact = viewModel.canAccessContactInfo(member);

    // Assert
    assertFalse("Should not allow access to private contact info", canAccessContact);
}

/**
 * Test Case 5: Offline Access (DIR_RES_007)
 * Verifies directory behavior in offline mode
 */
@Test
public void testOfflineAccess() {
    // Arrange
    when(networkUtils.isNetworkAvailable()).thenReturn(false);
    when(mockRepository.getCachedEntries()).thenReturn(testData);

    // Act
    viewModel.loadDirectory();

    // Assert
    assertNotNull("Should load cached data when offline",
            viewModel.getDirectoryEntries().getValue());
    assertTrue("Should indicate offline mode", viewModel.isOfflineMode().getValue());
}

/**
 * Test Case 6: Search with Filters (DIR_RES_003)
 * Verifies filtered search functionality
 */
@Test
public void testFilteredSearch() {
    // Arrange
    String role = "security";
    String block = "A";
    MutableLiveData<List<DirectoryDataModel>> mockResult = new MutableLiveData<>();
    List<DirectoryDataModel> filteredList = filterByRoleAndBlock(testData, role, block);
    mockResult.setValue(filteredList);
    when(mockRepository.searchWithFilters(role, block)).thenReturn(mockResult);

    // Act
    viewModel.applyFilters(role, block);

    // Assert
    assertEquals("Should return filtered results", filteredList.size(),
            viewModel.getSearchResults().getValue().size());
}

/**
 * Test Case 7: Search Performance (DIR_RES_018)
 * Verifies search response times meet requirements
 */
@Test
public void testSearchPerformance() {
    // Arrange
    String searchQuery = "John";
    long startTime = System.currentTimeMillis();

    // Act
    viewModel.setSearchQuery(searchQuery);

    // Assert
    long endTime = System.currentTimeMillis();
    assertTrue("Search should complete within 2 seconds",
            (endTime - startTime) <= 2000);
}

/**
 * Test Case 8: Error State Recovery (DIR_RES_020)
 * Verifies recovery from error states
 */
@Test
public void testErrorRecovery() {
    // Arrange
    when(mockRepository.getAllEntries())
            .thenThrow(new NetworkException("Network error"))
            .thenReturn(new MutableLiveData<>(testData));

    // Act & Assert
    // First attempt - should fail
    viewModel.loadDirectory();
    assertTrue(viewModel.getError().getValue().contains("Network error"));

    // Second attempt - should recover
    viewModel.retryLastOperation();
    assertNull(viewModel.getError().getValue());
    assertNotNull(viewModel.getDirectoryEntries().getValue());
}

/**
 * Test Case 9: Invalid Search Handling (DIR_RES_011)
 * Verifies handling of invalid search inputs
 */
@Test
public void testInvalidSearchHandling() {
    // Arrange
    String invalidQuery = "";

    // Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        viewModel.setSearchQuery(invalidQuery);
    });
    assertTrue(exception.getMessage().contains("Invalid search query"));
}

/**
 * Test Case 10: Directory Refresh (DIR_RES_006)
 * Verifies manual refresh functionality
 */
@Test
public void testDirectoryRefresh() {
    // Arrange
    MutableLiveData<List<DirectoryDataModel>> mockResult = new MutableLiveData<>();
    List<DirectoryDataModel> updatedData = createUpdatedTestData();
    mockResult.setValue(updatedData);
    when(mockRepository.refreshDirectory()).thenReturn(mockResult);

    // Act
    viewModel.refreshDirectory();

    // Assert
    assertEquals("Directory should contain updated entries", updatedData.size(),
            viewModel.getDirectoryEntries().getValue().size());
}

// Helper Methods
private List<DirectoryDataModel> createTestData() {
    List<DirectoryDataModel> data = new ArrayList<>();
    data.add(new DirectoryDataModel("John Doe", "A101", "resident"));
    data.add(new DirectoryDataModel("Jane Smith", "B202", "resident"));
    data.add(new DirectoryDataModel("Security Team", "A-Block", "security"));
    return data;
}

private List<DirectoryDataModel> filterByName(List<DirectoryDataModel> data, String name) {
    return data.stream()
            .filter(member -> member.getName().toLowerCase().contains(name.toLowerCase()))
            .collect(Collectors.toList());
}

private List<DirectoryDataModel> filterByRoleAndBlock(
        List<DirectoryDataModel> data, String role, String block) {
    return data.stream()
            .filter(member -> member.getRole().equals(role)
                    && member.getUnit().startsWith(block))
            .collect(Collectors.toList());
}

private DirectoryDataModel createMemberWithPrivacySettings(boolean allowContact) {
    DirectoryDataModel member = new DirectoryDataModel("Test User", "A101", "resident");
    member.setAllowContactAccess(allowContact);
    return member;
}

private List<DirectoryDataModel> createUpdatedTestData() {
    List<DirectoryDataModel> data = createTestData();
    data.add(new DirectoryDataModel("New Member", "C303", "resident"));
    return data;
}
}

