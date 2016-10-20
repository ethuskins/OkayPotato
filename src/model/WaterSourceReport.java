package model;
import java.time.LocalDateTime;

/**
 * Created by emilyhuskins on 10/12/16.
 */
public class WaterSourceReport {
    private int reportNumber;
    private LocalDateTime dateTime;
    private String reporterName;
    private String reporterID;
    private Location waterLocation;
    private WaterType waterType;
    private WaterCondition waterCondition;
    private String stringLocation;

//Basic constructor. Passes in the current user object to extract the name and id of the user
    public WaterSourceReport(int reportNum, UserProfile user, Location location, WaterType type, WaterCondition condition) {
        reportNumber = reportNum;
        dateTime = LocalDateTime.now();
        reporterName = user.getName();
        reporterID = user.getId();
        waterLocation = location;
        waterType = type;
        waterCondition = condition;
        stringLocation = location.toString();
    }

    /**
     * getter for the report number
     * @return The report number
     */
    public int getReportNumber() {
        return reportNumber;
    }

    /**
     * Setter for the report number
     * @param reportNumber the report number
     */
    public void setReportNumber(int reportNumber) {
        this.reportNumber = reportNumber;
    }

    /**
     * Getter for the dateTime attribute
     * @return dateTime the dateTime attribute.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Setter for the dateTime attribute
     * @param dateTime the new dateTime attribute.
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * getter method for reporterName
     * @return the reporterName
     */
    public String getReporterName() {
        return reporterName;
    }

    /**
     * Getter for reporterId
     * @return the reporterId
     */
    public String getReporterID() {
        return reporterID;
    }

    /**
     * Setter for ReporterId
     * @param reporterID the new reporterId
     */
    public void setReporterID(String reporterID) {
        this.reporterID = reporterID;
    }

    /**
     * Getter for the waterLocation
     * @return the waterLocation
     */
    public Location getLocation() {
        return waterLocation;
    }

    /**
     * Setter for location
     * @param location the new location
     */
    public void setLocation(Location location) {
        this.waterLocation = location;
    }

    /**
     * Getter for waterType
     * @return the water Type
     */
    public WaterType getWaterType() {
        return waterType;
    }

    /**
     * Setter for water type
     * @param waterType the new water type
     */
    public void setWaterType(WaterType waterType) {
        this.waterType = waterType;
    }

    /**
     * Getter for water condition
     * @return waterCondition the new waterCondition
     */
    public WaterCondition getWaterCondition() {
        return waterCondition;
    }

    /**
     * Setter for water condition
     * @param waterCondition the new waterCondition
     */
    public void setWaterCondition(WaterCondition waterCondition) {
        this.waterCondition = waterCondition;
    }

    /**
     * Getter for string location
     * @return the string location
     */
    public String getStringLocation() {
        return stringLocation;
    }

    /**
     * Setter for the stringLocation
     * @param stringLocation The new stringLocation
     */
    public void setStringLocation(String stringLocation) {
        this.stringLocation = stringLocation;
    }
}
