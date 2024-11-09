package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.smartlivingcommunity.data.model.BulletinBoardModel;
import java.util.ArrayList;
import java.util.List;

public class BulletinBoardViewModel extends ViewModel {

    private final MutableLiveData<List<BulletinBoardModel>> bulletinBoardList = new MutableLiveData<>(new ArrayList<>());

    // Method to add a new bulletin
    public void addBulletin(BulletinBoardModel bulletin) {
        List<BulletinBoardModel> currentList = bulletinBoardList.getValue();
        if (currentList != null) {
            currentList.add(bulletin);
            bulletinBoardList.setValue(currentList);
        }
    }

    // Method to get the list of bulletins
    public LiveData<List<BulletinBoardModel>> getBulletinBoardList() {
        return bulletinBoardList;
    }
}
