package com.example.smartlivingcommunity;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.lifecycle.MutableLiveData;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import com.example.smartlivingcommunity.data.model.BulletinBoardModel;
import com.example.smartlivingcommunity.ui.view.BulletinBoardFragment;
import com.example.smartlivingcommunity.ui.viewmodel.BulletinBoardViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.action.ViewActions.click;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class BulletinBoardViewTest {

    @Mock
    private BulletinBoardViewModel mockViewModel;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // Set up mock data for ViewModel
        MutableLiveData<List<BulletinBoardModel>> liveData = new MutableLiveData<>();
        liveData.setValue(Arrays.asList(new BulletinBoardModel("Event 1", "Details", new Date())));
        when(mockViewModel.getBulletinBoardList()).thenReturn(liveData);

        // Launch the fragment and set the ViewModel
        FragmentScenario<BulletinBoardFragment> scenario = FragmentScenario.launchInContainer(BulletinBoardFragment.class);
        scenario.onFragment(fragment -> fragment.setViewModel(mockViewModel));
    }

    @Test
    public void addNoticeButton_ShouldOpenForm() {
        onView(withId(R.id.addNoticeButton))
                .check(matches(ViewMatchers.isDisplayed()))
                .perform(click());

        onView(withId(R.id.noticeForm))
                .check(matches(ViewMatchers.isDisplayed()));
    }
}
