package com.example.smartlivingcommunity.data.model;

import java.util.Objects;

/**
 * @author solaimi
 * Represents a data model for a directory entry in the Smart Living Community application.
 * This model contains information about a resident or member, such as their ID, name, unit, and role.
 */
public class DirectoryDataModel {

    // Fields representing the data attributes for a directory entry
    private String id;
    private String name;
    private String unit;
    private String role;

    /**
     * Constructor for creating a DirectoryDataModel instance.
     *
     * @param id   The unique identifier for the member.
     * @param name The name of the member.
     * @param unit The unit associated with the member (e.g., apartment number).
     * @param role The role of the member (e.g., resident, security).
     */
    public DirectoryDataModel(String id, String name, String unit, String role) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.role = role;
    }

    // Getter for the member ID
    public String getId() {
        return id;
    }

    // Setter for the member ID
    public void setId(String id) {
        this.id = id;
    }

    // Getter for the member name
    public String getName() {
        return name;
    }

    // Setter for the member name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for the member unit
    public String getUnit() {
        return unit;
    }

    // Setter for the member unit
    public void setUnit(String unit) {
        this.unit = unit;
    }

    // Getter for the member role
    public String getRole() {
        return role;
    }

    // Setter for the member role
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Compares this DirectoryDataModel instance with another object for equality.
     *
     * @param o The object to compare with this instance.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectoryDataModel model = (DirectoryDataModel) o;
        return Objects.equals(id, model.id) &&
                Objects.equals(name, model.name) &&
                Objects.equals(unit, model.unit) &&
                Objects.equals(role, model.role);
    }

    /**
     * Generates a hash code for this DirectoryDataModel instance.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, unit, role);
    }
}
