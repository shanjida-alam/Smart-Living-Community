package com.example.smartlivingcommunity.data.model;

/**
 * Represents a complaint made within the Smart Living Community.
 * Each complaint has an ID, a description, and a submission date.
 *
 * @author Saon
 */
public class Complaint {
    private String complaintId;
    private String description;
    private String submissionDate;
    /**
     * Constructs a new Complaint with the specified complaint ID, description, and submission date.
     *
     * @param complaintId   the unique identifier for the complaint
     * @param description   the details of the complaint
     * @param submissionDate the date when the complaint was submitted
     */
    public Complaint(String complaintId, String description, String submissionDate) {
        this.complaintId = complaintId;
        this.description = description;
        this.submissionDate = submissionDate;
    }

    /**
     * Gets the complaint ID.
     *
     * @return the unique identifier for the complaint
     */
    public String getComplaintId() {
        return complaintId;
    }
    /**
     * Gets the description of the complaint.
     *
     * @return the details of the complaint
     */
    public String getDescription() {
        return description;
    }
    /**
     * Gets the submission date of the complaint.
     *
     * @return the date when the complaint was submitted
     */
    public String getSubmissionDate() {
        return submissionDate;
    }
}
