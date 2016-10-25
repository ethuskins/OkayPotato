package model;

import java.time.LocalDateTime;

/**
 * Created by Jack Winski on 10/24/2016.
 */
    public class WaterQualityReport {
    private int reportNumber;
    private LocalDateTime dateTime;
    private String reporterName;
    private String reporterID;
    private Location waterLocation;
    private WaterCondition waterCondition;
    private QuaCondition quaCondition;
    private String stringLocation;
    private String virusPPM;
    private String contamPPM;
    private WaterType waterType;


    public WaterQualityReport(int reportNum, UserProfile user, Location location, WaterType type, WaterCondition waterCondition, QuaCondition quaCondition, String virPPM, String conPPM) {
        this(reportNum, user, location, type, waterCondition);
        this.quaCondition = quaCondition;
        virusPPM = virPPM;
        contamPPM = conPPM;
    }
    public WaterQualityReport(int reportNum, UserProfile user, Location location, WaterType type, WaterCondition waterCondition){
        this.waterCondition = waterCondition;
        reportNumber = reportNum;
        reporterName = user.getName();
        reporterID = user.getId();
        dateTime = LocalDateTime.now();
        waterLocation = location;
        this.waterType  = type;
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
     * getter for the water type
     * @return The water type
     */
    public WaterType getWaterType() {
        return waterType;
    }
    /**
     * Setter for the water type
     * @param waterType the water type
     */
    public void setWaterType(WaterType waterType) {
        this.waterType = waterType;
    }
    /**
     * getter for the water condition
     * @return The water condition
     */
    public WaterCondition getWaterCondition() {
        return waterCondition;
    }
    /**
     * Setter for the water condition
     * @param waterCondition the water condition
     */
    public void setWaterCondition(WaterCondition waterCondition) {
        this.waterCondition = waterCondition;
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
     * Getter for water condition
     * @return waterCondition the new waterCondition
     */
    public QuaCondition getQuaCondition() {
        return quaCondition;
    }

    /**
     * Setter for water condition
     * @param waterCondition the new waterCondition
     */
    public void setQuaCondition(QuaCondition waterCondition) {
        this.quaCondition = quaCondition;
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
