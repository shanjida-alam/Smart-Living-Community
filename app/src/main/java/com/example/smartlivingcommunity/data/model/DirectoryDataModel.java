// com/example/smartlivingcommunity/data/model/DirectoryDataModel.java
package com.example.smartlivingcommunity.data.model;

import androidx.annotation.NonNull;

public class DirectoryDataModel {
    private final String id;
    private final String name;
    private final String unit;
    private final String role;
    private final String contactInfo;
    private boolean allowContactAccess;

    public DirectoryDataModel(String id, String name, String unit, String role) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.role = role;
        this.contactInfo = "";
        this.allowContactAccess = true;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getUnit() { return unit; }
    public String getRole() { return role; }
    public String getContactInfo() { return contactInfo; }
    public boolean isAllowContactAccess() { return allowContactAccess; }

    // Setters
    public void setAllowContactAccess(boolean allowContactAccess) {
        this.allowContactAccess = allowContactAccess;
    }

    @NonNull
    @Override
    public String toString() {
        return "DirectoryDataModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectoryDataModel that = (DirectoryDataModel) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}