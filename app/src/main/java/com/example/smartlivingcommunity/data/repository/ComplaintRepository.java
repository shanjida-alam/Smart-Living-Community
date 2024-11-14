package com.example.smartlivingcommunity.data.repository;

import androidx.lifecycle.LiveData;

import com.example.smartlivingcommunity.data.model.ComplaintModel;
import com.google.android.gms.tasks.Task;

import java.util.List;

// ComplaintRepository.java
public interface ComplaintRepository {
    LiveData<Boolean> submitComplaint(ComplaintModel complaint);

    LiveData<List<ComplaintModel>> getComplaints();
}
