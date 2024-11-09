package com.example.smartlivingcommunity.ui.view.bulletin;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartlivingcommunity.R;
import com.example.smartlivingcommunity.ui.viewmodel.BulletinBoardViewModel;

public class BulletinBoardView extends Fragment {

    private BulletinBoardViewModel mViewModel;

    public static BulletinBoardView newInstance() {
        return new BulletinBoardView();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bulletin_board_view, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BulletinBoardViewModel.class);
        // TODO: Use the ViewModel
    }

}