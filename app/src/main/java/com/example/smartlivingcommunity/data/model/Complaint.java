package com.example.smartlivingcommunity.data.model;
public class Complaint {
    private String complaintId;
    private String description;
    private String submissionDate;

    public Complaint(String complaintId, String description, String submissionDate) {
        this.complaintId = complaintId;
        this.description = description;
        this.submissionDate = submissionDate;
    }

    public String getComplaintId() { return complaintId; }
    public String getDescription() { return description; }
    public String getSubmissionDate() { return submissionDate; }
}
