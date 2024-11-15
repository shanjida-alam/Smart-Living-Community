package com.example.smartlivingcommunity.data.model;

public class DirectoryDataModel {
    private final String id;
    private final String name;
    private final String unit;
    private final String role;

    public DirectoryDataModel(String id, String name, String unit, String role) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.role = role;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getUnit() { return unit; }
    public String getRole() { return role; }

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
