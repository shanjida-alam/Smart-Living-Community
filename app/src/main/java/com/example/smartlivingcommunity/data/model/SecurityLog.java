package com.example.smartlivingcommunity.data.model;

/**
 * Represents a security log entry for a visitor, including details such as
 * visitor name, type, entry and exit times, and entry and exit dates.
 *
 * @author Saon
 */
public class SecurityLog {
    private String visitorName;
    private String visitorType;
    private String entryTime;
    private String exitTime;
    private String entryDate;
    private String exitDate;

    /**
     * Constructs a new SecurityLog instance with the specified details.
     *
     * @param visitorName the name of the visitor
     * @param visitorType the type of the visitor (e.g., guest, contractor)
     * @param entryTime the time when the visitor entered
     * @param exitTime the time when the visitor exited
     * @param entryDate the date when the visitor entered
     * @param exitDate the date when the visitor exited
     */
    public SecurityLog(String visitorName, String visitorType, String entryTime, String exitTime, String entryDate, String exitDate) {
        this.visitorName = visitorName;
        this.visitorType = visitorType;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
    }

    /**
     * Gets the visitor's name.
     *
     * @return the name of the visitor
     */
    public String getVisitorName() {
        return visitorName;
    }

    /**
     * Sets the visitor's name.
     *
     * @param visitorName the name of the visitor
     */
    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    /**
     * Gets the visitor's type.
     *
     * @return the type of the visitor (e.g., guest, contractor)
     */
    public String getVisitorType() {
        return visitorType;
    }

    /**
     * Sets the visitor's type.
     *
     * @param visitorType the type of the visitor (e.g., guest, contractor)
     */
    public void setVisitorType(String visitorType) {
        this.visitorType = visitorType;
    }

    /**
     * Gets the entry time of the visitor.
     *
     * @return the entry time of the visitor
     */
    public String getEntryTime() {
        return entryTime;
    }

    /**
     * Sets the entry time of the visitor.
     *
     * @param entryTime the entry time of the visitor
     */
    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    /**
     * Gets the exit time of the visitor.
     *
     * @return the exit time of the visitor
     */
    public String getExitTime() {
        return exitTime;
    }

    /**
     * Sets the exit time of the visitor.
     *
     * @param exitTime the exit time of the visitor
     */
    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    /**
     * Gets the entry date of the visitor.
     *
     * @return the entry date of the visitor
     */
    public String getEntryDate() {
        return entryDate;
    }

    /**
     * Sets the entry date of the visitor.
     *
     * @param entryDate the entry date of the visitor
     */
    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    /**
     * Gets the exit date of the visitor.
     *
     * @return the exit date of the visitor
     */
    public String getExitDate() {
        return exitDate;
    }

    /**
     * Sets the exit date of the visitor.
     *
     * @param exitDate the exit date of the visitor
     */
    public void setExitDate(String exitDate) {
        this.exitDate = exitDate;
    }
}
