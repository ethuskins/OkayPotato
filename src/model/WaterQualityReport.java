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
    private QuaLocation waterLocation;
    private QuaCondition waterCondition;
    private String stringLocation;
    private String virusPPM;
    private String contamPPM;

    public WaterQualityReport(int reportNum, UserProfile user, QuaLocation location, QuaCondition condition, String virPPM, String conPPM) {
        reportNumber = reportNum;
        dateTime = LocalDateTime.now();
        reporterName = user.getName();
        reporterID = user.getId();
        waterLocation = location;
        waterCondition = condition;
        stringLocation = location.toString();
        virusPPM = virPPM;
        contamPPM = conPPM;
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
    public QuaLocation getLocation() {
        return waterLocation;
    }

    /**
     * Setter for location
     * @param location the new location
     */
    public void setLocation(QuaLocation location) {
        this.waterLocation = location;
    }

    /**
     * Getter for water condition
     * @return waterCondition the new waterCondition
     */
    public QuaCondition getQuaCondition() {
        return waterCondition;
    }

    /**
     * Setter for water condition
     * @param waterCondition the new waterCondition
     */
    public void setQuaCondition(QuaCondition waterCondition) {
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
