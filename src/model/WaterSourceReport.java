package model;
import java.util.Calendar;

/**
 * Class controlling the water source reports.
 */
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

    /**
     * Basic constructor. Passes in the current user object to extract the name and id of the user.
     * @param reportNum the report number
     * @param user the user
     * @param location the location
     * @param type the water type
     * @param condition the water condition
     */
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

    /**
     * Getter for the year.
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Setter for the year.
     * @param year the year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Getter for the month.
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Setter for the month.
     * @param month the month
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Getter for the day.
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * Setter for the day
     * @param day the day
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Getter for the reporter name.
     * @return the reporter's name
     */
    public String getReporterName() {
        return reporterName;
    }

    /**
     * Setter for the reporter's name.
     * @param reporterName the reporter's name
     */
    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    /**
     * Getter for the reporter's ID.
     * @return the reporter's ID
     */
    public String getReporterID() {
        return reporterID;
    }

    /**
     * Setter for the reporter's ID.
     * @param reporterID the reporter's ID
     */
    public void setReporterID(String reporterID) {
        this.reporterID = reporterID;
    }

    /**
     * Getter for the water's location.
     * @return the water's location
     */
    public Location getWaterLocation() {
        return waterLocation;
    }

    /**
     * Setter for the water's location.
     * @param waterLocation the water's location
     */
    public void setWaterLocation(Location waterLocation) {
        this.waterLocation = waterLocation;
    }

    /**
     * Getter for the water's type.
     * @return the water's type
     */
    public WaterType getWaterType() {
        return waterType;
    }

    /**
     * Setter for the water's type.
     * @param waterType the water's type
     */
    public void setWaterType(WaterType waterType) {
        this.waterType = waterType;
    }

    /**
     * Getter for the water's condition.
     * @return the water's condition
     */
    public WaterCondition getWaterCondition() {
        return waterCondition;
    }

    /**
     * Setter for the water's condition.
     * @param waterCondition the water's condition
     */
    public void setWaterCondition(WaterCondition waterCondition) {
        this.waterCondition = waterCondition;
    }

    /**
     * Getter for the string location.
     * @return the string location
     */
    public String getStringLocation() {
        return stringLocation;
    }

    /**
     * Setter for the string location.
     * @param stringLocation the string location
     */
    public void setStringLocation(String stringLocation) {
        this.stringLocation = stringLocation;
    }
}
