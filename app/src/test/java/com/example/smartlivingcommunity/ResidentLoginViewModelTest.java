package com.example.smartlivingcommunity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.example.smartlivingcommunity.data.model.ResidentLoginModel;
import com.example.smartlivingcommunity.ui.viewmodel.ResidentLoginViewModel;
import com.example.smartlivingcommunity.utils.Resource;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ResidentLoginViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private FirebaseFirestore mockDb;
    @Mock
    private CollectionReference mockCollectionRef;
    @Mock
    private Query mockQuery;
    @Mock
    private Task<QuerySnapshot> mockTask;
    @Mock
    private QuerySnapshot mockQuerySnapshot;
    @Mock
    private DocumentSnapshot mockDocumentSnapshot;
    @Mock
    private Observer<Resource<Boolean>> mockObserver;

    private ResidentLoginViewModel viewModel;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // Setup mock chain
        when(mockDb.collection("residents")).thenReturn(mockCollectionRef);
        when(mockCollectionRef.whereEqualTo(anyString(), anyString())).thenReturn(mockQuery);
        when(mockQuery.get()).thenReturn(mockTask);

        // Initialize ViewModel with mocked FirebaseFirestore
        viewModel = new ResidentLoginViewModel() {
            @Override
            protected FirebaseFirestore getFirebaseInstance() {
                return mockDb;
            }
        };

        viewModel.getLoginResult().observeForever(mockObserver);
    }

    @Test
    public void login_WithValidCredentials_ShouldReturnSuccess() {
        // Arrange
        String email = "jubaer.stu2019@juniv.edu";
        String password = "Jubaer@1234";

        ResidentLoginModel mockUser = new ResidentLoginModel();
        mockUser.setEmail(email);
        mockUser.setPassword(password);

        List<DocumentSnapshot> documents = new ArrayList<>();
        documents.add(mockDocumentSnapshot);

        when(mockTask.isSuccessful()).thenReturn(true);
        when(mockTask.getResult()).thenReturn(mockQuerySnapshot);
        when(mockQuerySnapshot.isEmpty()).thenReturn(false);
        when(mockQuerySnapshot.getDocuments()).thenReturn(documents);
        when(mockDocumentSnapshot.toObject(ResidentLoginModel.class)).thenReturn(mockUser);

        // Act
        viewModel.login(email, password);

        // Complete the task
        mockTask.addOnCompleteListener(task -> {});

        // Assert
        verify(mockObserver).onChanged(Resource.loading(null));
        verify(mockObserver).onChanged(Resource.success(true));
    }

    @Test
    public void login_WithNonexistentUser_ShouldReturnError() {
        // Arrange
        String email = "nonexistent@example.com";
        String password = "password123";

        when(mockTask.isSuccessful()).thenReturn(true);
        when(mockTask.getResult()).thenReturn(mockQuerySnapshot);
        when(mockQuerySnapshot.isEmpty()).thenReturn(true);

        // Act
        viewModel.login(email, password);

        // Complete the task
        mockTask.addOnCompleteListener(task -> {});

        // Assert
        verify(mockObserver).onChanged(Resource.loading(null));
        verify(mockObserver).onChanged(Resource.error("User not found", false));
    }

    @Test
    public void login_WithInvalidPassword_ShouldReturnError() {
        // Arrange
        String email = "test@example.com";
        String password = "wrongpassword";

        ResidentLoginModel mockUser = new ResidentLoginModel();
        mockUser.setEmail(email);
        mockUser.setPassword("correctpassword");

        List<DocumentSnapshot> documents = new ArrayList<>();
        documents.add(mockDocumentSnapshot);

        when(mockTask.isSuccessful()).thenReturn(true);
        when(mockTask.getResult()).thenReturn(mockQuerySnapshot);
        when(mockQuerySnapshot.isEmpty()).thenReturn(false);
        when(mockQuerySnapshot.getDocuments()).thenReturn(documents);
        when(mockDocumentSnapshot.toObject(ResidentLoginModel.class)).thenReturn(mockUser);

        // Act
        viewModel.login(email, password);

        // Complete the task
        mockTask.addOnCompleteListener(task -> {});

        // Assert
        verify(mockObserver).onChanged(Resource.loading(null));
        verify(mockObserver).onChanged(Resource.error("Invalid password", false));
    }

    @Test
    public void login_WithFirebaseError_ShouldReturnError() {
        // Arrange
        String email = "test@example.com";
        String password = "password123";
        String errorMessage = "Network error";

        Exception mockException = mock(Exception.class);
        when(mockException.getMessage()).thenReturn(errorMessage);

        when(mockTask.isSuccessful()).thenReturn(false);
        when(mockTask.getException()).thenReturn(mockException);

        // Act
        viewModel.login(email, password);

        // Complete the task
        mockTask.addOnCompleteListener(task -> {});

        // Assert
        verify(mockObserver).onChanged(Resource.loading(null));
        verify(mockObserver).onChanged(Resource.error(errorMessage, false));
    }
}