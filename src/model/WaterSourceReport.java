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
    private String waterLocation;
    private WaterType waterType;
    private WaterCondition waterCondition;

//Basic constructor. Passes in the current user object to extract the name and id of the user
    public WaterSourceReport(int reportNum, UserProfile user, String location, WaterType type, WaterCondition condition) {
        reportNumber = reportNum;
        dateTime = LocalDateTime.now();
        reporterName = user.getName();
        reporterID = user.getId();
        waterLocation = location;
        waterType = type;
        waterCondition = condition;
    }

    //Getters and setters
    public int getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(int reportNumber) {
        this.reportNumber = reportNumber;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getReporterName() {
        return reporterName;
    }



    public String getReporterID() {
        return reporterID;
    }

    public void setReporterID(String reporterID) {
        this.reporterID = reporterID;
    }

    public String getLocation() {
        return waterLocation;
    }

    public void setLocation(String location) {
        this.waterLocation = location;
    }

    public WaterType getWaterType() {
        return waterType;
    }

    public void setWaterType(WaterType waterType) {
        this.waterType = waterType;
    }

    public WaterCondition getWaterCondition() {
        return waterCondition;
    }

    public void setWaterCondition(WaterCondition waterCondition) {
        this.waterCondition = waterCondition;
    }
}
