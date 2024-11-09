package com.example.smartlivingcommunity.ui.view.board;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.ui.viewmodel.CreateAnnouncementViewModel;

public class CreateAnnouncementView extends Fragment {

    private CreateAnnouncementViewModel mViewModel;

    public static CreateAnnouncementView newInstance() {
        return new CreateAnnouncementView();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_announcement, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CreateAnnouncementViewModel.class);
        // TODO: Use the ViewModel
    }

}