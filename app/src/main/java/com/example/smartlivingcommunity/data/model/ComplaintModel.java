package com.example.smartlivingcommunity.data.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * ComplaintModel class representing the user's complaint information.
 * <p>
 * This class contains essential fields required for complaint registration.
 * It includes the complaint ID, unit code, user details, contact information, and the complaint description.
 * The model also provides methods for generating unique complaint IDs and accessing or modifying complaint-related data.
 * <p>
 * This class is also used for Firebase data mapping, and therefore includes no-argument constructors.
 *
 * @author Shanjida Alam
 * @version 1.0
 * @since 2024-11-15
 */
public class ComplaintModel {
    /**
     * Unique identifier for the complaint.
     */
    private String complaintId;
    /**
     * Unit code associated with the complaint.
     */
    private String unitCode;
    /**
     * User name associated with the complaint.
     */
    private String userName;
    /**
     * User role associated with the complaint.
     */
    private String userRole;
    /**
     * Phone number associated with the complaint.
     */
    private String phoneNumber;
    /**
     * Email address associated with the complaint.
     */
    private String emailAddress;
    /**
     * Description of the complaint.
     */
    private String complaintDescription;

    /**
     * No-argument constructor required for Firebase data model mapping.
     * <p>
     * This constructor generates a unique complaint ID upon object creation.
     */
    public ComplaintModel() {
        this.complaintId = generateUniqueId();
    }

    /**
     * Constructs a ComplaintModel with specified details.
     * <p>
     * This constructor allows setting the complaint's unit code, user details, contact information, and description.
     * The complaint ID is generated automatically.
     *
     * @param unitCode Unit code associated with the complaint.
     * @param userName User name associated with the complaint.
     * @param userRole User role associated with the complaint.
     * @param phoneNumber Phone number associated with the complaint.
     * @param emailAddress Email address associated with the complaint.
     * @param complaintDescription Description of the complaint.
     */
    public ComplaintModel(String unitCode, String userName, String userRole,
                          String phoneNumber, String emailAddress, String complaintDescription) {
        this.complaintId = generateUniqueId();
        this.unitCode = unitCode;
        this.userName = userName;
        this.userRole = userRole;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.complaintDescription = complaintDescription;
    }

    /**
     * Generates a unique ID for the complaint based on the current timestamp and a random suffix.
     * <p>
     * The ID follows the pattern "COMPyyyyMMddHHmmssSSS_randomSuffix", ensuring uniqueness using a timestamp and UUID.
     *
     * @return Unique ID for the complaint.
     */
    private String generateUniqueId() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault());
        String timestamp = dateFormat.format(new Date());
        String uniqueSuffix = UUID.randomUUID().toString().substring(0, 4);
        return "COMP" + timestamp + "_" + uniqueSuffix;
    }

    /**
     * Get the complaint ID.
     *
     * @return The complaint ID.
     */
    public String getComplaintId() {
        return complaintId;
    }

    /**
     * Set the complaint ID.
     * <p>
     * This method is necessary for Firebase mapping to set the complaint ID manually.
     *
     * @param complaintId The complaint ID.
     */
    public void setComplaintId(String complaintId) {
        this.complaintId = complaintId;
    }

    /**
     * Get the unit code associated with the complaint.
     *
     * @return The unit code.
     */
    public String getUnitCode() {
        return unitCode;
    }

    /**
     * Get the user name associated with the complaint.
     *
     * @return The user name.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Get the user role associated with the complaint.
     *
     * @return The user role.
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * Get the phone number associated with the complaint.
     *
     * @return The phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Get the email address associated with the complaint.
     *
     * @return The email address.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Get the complaint description.
     *
     * @return The complaint description.
     */
    public String getComplaintDescription() {
        return complaintDescription;
    }

    /**
     * Set the unit code associated with the complaint.
     *
     * @param unitCode The unit code.
     */
    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    /**
     * Set the user name associated with the complaint.
     *
     * @param userName The user name.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Set the user role associated with the complaint.
     *
     * @param userRole The user role.
     */
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    /**
     * Set the phone number associated with the complaint.
     *
     * @param phoneNumber The phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Set the email address associated with the complaint.
     *
     * @param emailAddress The email address.
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Set the complaint description.
     *
     * @param complaintDescription The complaint description.
     */
    public void setComplaintDescription(String complaintDescription) {
        this.complaintDescription = complaintDescription;
    }
}
