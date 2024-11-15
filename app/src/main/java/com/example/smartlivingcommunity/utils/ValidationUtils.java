package com.example.smartlivingcommunity.utils;

public class ValidationUtils {
    public static boolean isValidSearchQuery(String query) {
        return query != null && !query.trim().isEmpty() && query.length() >= 2;
    }

    public static boolean isValidUnit(String unit) {
        return unit != null && !unit.trim().isEmpty() &&
                unit.matches("[A-Z]-?\\d{1,3}");
    }

    public static boolean isValidRole(String role) {
        return role != null && !role.trim().isEmpty() &&
                (role.equals("resident") || role.equals("security") ||
                        role.equals("manager") || role.equals("admin"));
    }

    public static boolean isValidMemberId(String id) {
        return id != null && !id.trim().isEmpty();
    }
}
