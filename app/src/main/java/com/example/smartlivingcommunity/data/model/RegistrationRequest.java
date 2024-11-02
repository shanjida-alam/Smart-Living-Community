package com.example.smartlivingcommunity.data.model;

/**
 * Model class representing a registration request within the community.
 * This class stores details about a resident's registration request, including
 * a unique request ID, the requester's name, their role, and their National ID (NID).
 *
 * @author Saon
 */
public class RegistrationRequest {

    private String requestId;
    private String requesterName;
    private String role;
    private String nid;

    /**
     * Constructs a RegistrationRequest with the specified details.
     *
     * @param requestId     Unique identifier for the registration request.
     * @param requesterName Name of the individual submitting the request.
     * @param role          Role of the requester within the community (e.g., Resident, Manager).
     * @param nid           National ID (NID) of the requester.
     */
    public RegistrationRequest(String requestId, String requesterName, String role, String nid) {
        this.requestId = requestId;
        this.requesterName = requesterName;
        this.role = role;
        this.nid = nid;
    }

    /**
     * Returns the unique ID of the registration request.
     *
     * @return A String representing the request ID.
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Returns the name of the requester who submitted the registration request.
     *
     * @return A String representing the requester's name.
     */
    public String getRequesterName() {
        return requesterName;
    }

    /**
     * Returns the role of the requester in the community.
     *
     * @return A String representing the requester's role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Returns the National ID (NID) of the requester.
     *
     * @return A String representing the requester's National ID.
     */
    public String getNid() {
        return nid;
    }
}
