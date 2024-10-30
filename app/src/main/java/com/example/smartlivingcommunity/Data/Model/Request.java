package com.example.smartlivingcommunity.Data.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Request {
    private String requestDescription;
    private String requestType;
    private Date timestamp; // Using Date type for better date handling

    // Constructor with all parameters
    public Request(String requestDescription, String requestType, Date timestamp) {
        this.requestDescription = requestDescription;
        this.requestType = requestType;
        this.timestamp = timestamp;
    }

    // Overloaded constructor for convenience, sets timestamp to current time
    public Request(String requestDescription, String requestType) {
        this.requestDescription = requestDescription;
        this.requestType = requestType;
        this.timestamp = new Date(); // Sets to current time
    }

    // Getters and Setters
    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        if (isValidRequestType(requestType)) {
            this.requestType = requestType;
        } else {
            throw new IllegalArgumentException("Invalid request type");
        }
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    // Method to check if request type is valid (example validation)
    private boolean isValidRequestType(String requestType) {
        // Add your validation logic here, e.g., checking against a list of valid types
        return requestType.equals("Pending") || requestType.equals("Completed") || requestType.equals("In Progress");
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return "Request{" +
                "requestDescription='" + requestDescription + '\'' +
                ", requestType='" + requestType + '\'' +
                ", timestamp='" + sdf.format(timestamp) + '\'' +
                '}';
    }
}



