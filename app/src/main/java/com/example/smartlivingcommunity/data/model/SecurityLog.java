package com.example.smartlivingcommunity.data.model;

/**
 * Model class representing a security log entry.
 *
 * <p>This class holds information about a visitor's entry and exit times, along with the
 * entry and exit dates, which can be used to track visitor movements within the community.</p>
 *
 * @author Saon
 */
public class SecurityLog {
    private String visitorName;
    private String entryDate;
    private String exitDate;
    private String entryTime;
    private String exitTime;

    /**
     * Constructs a new SecurityLog entry with the specified details.
     *
     * @param visitorName the name of the visitor
     * @param entryDate   the date of entry
     * @param exitDate    the date of exit
     * @param entryTime   the time of entry
     * @param exitTime    the time of exit
     */
    public SecurityLog(String visitorName, String entryDate, String exitDate, String entryTime, String exitTime) {
        this.visitorName = visitorName;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
    }

    // Getters and Setters

    /**
     * Returns the visitor's name.
     *
     * @return visitorName
     */
    public String getVisitorName() { return visitorName; }

    /**
     * Sets the visitor's name.
     *
     * @param visitorName the visitor's name
     */
    public void setVisitorName(String visitorName) { this.visitorName = visitorName; }

    /**
     * Returns the date of entry.
     *
     * @return entryDate
     */
    public String getEntryDate() { return entryDate; }

    /**
     * Sets the date of entry.
     *
     * @param entryDate the entry date
     */
    public void setEntryDate(String entryDate) { this.entryDate = entryDate; }

    /**
     * Returns the date of exit.
     *
     * @return exitDate
     */
    public String getExitDate() { return exitDate; }

    /**
     * Sets the date of exit.
     *
     * @param exitDate the exit date
     */
    public void setExitDate(String exitDate) { this.exitDate = exitDate; }

    /**
     * Returns the entry time.
     *
     * @return entryTime
     */
    public String getEntryTime() { return entryTime; }

    /**
     * Sets the entry time.
     *
     * @param entryTime the entry time
     */
    public void setEntryTime(String entryTime) { this.entryTime = entryTime; }

    /**
     * Returns the exit time.
     *
     * @return exitTime
     */
    public String getExitTime() { return exitTime; }

    /**
     * Sets the exit time.
     *
     * @param exitTime the exit time
     */
    public void setExitTime(String exitTime) { this.exitTime = exitTime; }
}
