package com.example.smartlivingcommunity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.smartlivingcommunity.data.model.ResidentLoginModel;
import com.example.smartlivingcommunity.ui.viewmodel.ResidentLoginViewModel;
import com.example.smartlivingcommunity.utils.Resource;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

@RunWith(AndroidJUnit4.class)
public class ResidentLoginViewModelInstrumentedTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    private ResidentLoginViewModel viewModel;

    @Mock
    private FirebaseFirestore mockFirestore;

    @Mock
    private CollectionReference mockCollectionReference;

    @Mock
    private Query mockQuery;

    @Mock
    private Task<QuerySnapshot> mockTask;

    @Mock
    private QuerySnapshot mockQuerySnapshot;

    @Mock
    private DocumentSnapshot mockDocumentSnapshot;

    @Mock
    private Observer<Resource<Boolean>> observer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // Injecting the mocked Firestore instance
        viewModel = new ResidentLoginViewModel(mockFirestore);
        viewModel.getLoginResult().observeForever(observer);
    }

    @Test
    public void login_withValidCredentials_shouldReturnSuccess() {
        String email = "jubaer.stu2019@juniv.edu";
        String password = "Jubaer@1234";

        ResidentLoginModel mockUser = new ResidentLoginModel();
        mockUser.setEmail(email);
        mockUser.setPassword(password);

        // Mock the result of the query snapshot
        when(mockQuerySnapshot.isEmpty()).thenReturn(false);
        when(mockQuerySnapshot.getDocuments()).thenReturn(Collections.singletonList(mockDocumentSnapshot));
        when(mockDocumentSnapshot.toObject(ResidentLoginModel.class)).thenReturn(mockUser);

        // Perform the login action
        viewModel.login(email, password);

        // Capture and verify the emitted values from LiveData
        ArgumentCaptor<Resource<Boolean>> captor = ArgumentCaptor.forClass(Resource.class);
        verify(observer, times(2)).onChanged(captor.capture());

        Resource<Boolean> loadingState = captor.getAllValues().get(0);
        Resource<Boolean> successState = captor.getAllValues().get(1);

        assertEquals(Resource.Status.LOADING, loadingState.status);
        assertEquals(Resource.Status.SUCCESS, successState.status);
        assertEquals(true, successState.data);
    }

    // Additional tests for invalid password, user not found, etc., can be added here
}
