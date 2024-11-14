package com.example.smartlivingcommunity.data.repository;

import com.example.smartlivingcommunity.data.model.ComplaintModel;
import com.google.android.gms.tasks.Task;

public interface ComplaintRepository {
    Task<Void> addComplaint(ComplaintModel complaint);
}