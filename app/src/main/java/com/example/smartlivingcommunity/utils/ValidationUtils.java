package com.example.smartlivingcommunity.utils;

/**
 * @author solaimi
 * Utility class for validating various inputs in the application.
 * This class contains static methods to validate search queries, unit numbers, roles, and member IDs.
 */
public class ValidationUtils {

    /**
     * Validates if the provided search query is valid.
     * A valid query is non-null, non-empty, and has at least 2 characters.
     *
     * @param query The search query to validate.
     * @return true if the query is valid, false otherwise.
     */
    public static boolean isValidSearchQuery(String query) {
        return query != null && !query.trim().isEmpty() && query.length() >= 2;
    }

    /**
     * Validates if the provided unit number is in a valid format.
     * The unit number should be in the format of one uppercase letter, an optional hyphen, followed by 1 to 3 digits (e.g., "A-123").
     *
     * @param unit The unit number to validate.
     * @return true if the unit is valid, false otherwise.
     */
    public static boolean isValidUnit(String unit) {
        return unit != null && !unit.trim().isEmpty() &&
                unit.matches("[A-Z]-?\\d{1,3}");
    }

    /**
     * Validates if the provided role is one of the predefined roles (resident, security, manager, admin).
     *
     * @param role The role to validate.
     * @return true if the role is valid, false otherwise.
     */
    public static boolean isValidRole(String role) {
        return role != null && !role.trim().isEmpty() &&
                (role.equals("resident") || role.equals("security") ||
                        role.equals("manager") || role.equals("admin"));
    }

    /**
     * Validates if the provided member ID is valid.
     * A valid member ID is non-null and non-empty.
     *
     * @param id The member ID to validate.
     * @return true if the ID is valid, false otherwise.
     */
    public static boolean isValidMemberId(String id) {
        return id != null && !id.trim().isEmpty();
    }
}

