package com.example.smartlivingcommunity.data.model;

import java.util.Objects;

public class DirectoryDataModel {
    private String id;
    private String name;
    private String unit;
    private String role;

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

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setUnit(String unit) { this.unit = unit; }
    public void setRole(String role) { this.role = role; }

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

    @Override
    public int hashCode() {
        return Objects.hash(id, name, unit, role);
    }
}