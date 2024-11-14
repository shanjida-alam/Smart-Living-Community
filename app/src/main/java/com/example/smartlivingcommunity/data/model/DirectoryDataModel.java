// DirectoryDataModel.java
package com.example.smartlivingcommunity.data.model;

public class DirectoryDataModel {
    private String id;
    private String name;
    private String unit;
    private String role;
    private String contact;

    public DirectoryDataModel(String id, String name, String unit, String role) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.role = role;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
