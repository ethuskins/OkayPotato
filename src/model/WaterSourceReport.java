package model;
import java.util.Calendar;


public class WaterSourceReport {
     private int year;
     private int month;
     private int day;
     private String reporterName;




     private String reporterID;
     private final int reportNumber;
     private Location waterLocation;
     private WaterType waterType;
     private WaterCondition waterCondition;
     private String stringLocation;

    //Basic constructor. Passes in the current user object to extract the name and id of the user
    public WaterSourceReport(int reportNum, UserProfile user, Location location, WaterType type, WaterCondition condition) {
        reportNumber = reportNum;

        String reporterName = user.getName();
        String reporterID = user.getId();
        waterLocation = location;
        WaterType waterType = type;
        WaterCondition waterCondition = condition;
        String stringLocation = location.toString();

        Calendar timenow = Calendar.getInstance();
        int month = Calendar.MONTH;
        int day = Calendar.DAY_OF_MONTH;
        int year = Calendar.YEAR;
    }

    /**
     * getter for the report number
     * @return The report number
     */
    public int getReportNumber() {
        return reportNumber;
    }


    /**
     * Setter for location
     * @param location the new location
     */
    public void setLocation(Location location) {
        this.waterLocation = location;
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getReporterID() {
        return reporterID;
    }

    public void setReporterID(String reporterID) {
        this.reporterID = reporterID;
    }

    public Location getWaterLocation() {
        return waterLocation;
    }

    public void setWaterLocation(Location waterLocation) {
        this.waterLocation = waterLocation;
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

    public String getStringLocation() {
        return stringLocation;
    }

    public void setStringLocation(String stringLocation) {
        this.stringLocation = stringLocation;
    }
}
