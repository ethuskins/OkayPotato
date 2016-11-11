package model;
import java.util.Calendar;


public class WaterSourceReport {
    private final int reportNumber;
    private Location waterLocation;

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


}
