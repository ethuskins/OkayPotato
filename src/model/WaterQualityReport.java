package model;

import java.util.Calendar;


    public class WaterQualityReport {
    private int reportNumber;
    private int year;
    private int month;
    private int day;
    private final String reporterName;
    private String reporterID;
    private Location waterLocation;
    private WaterCondition waterCondition;
    private QuaCondition quaCondition;
    private String stringLocation;
    private String virusPPM;
    private String contamPPM;
    private WaterType waterType;


    public WaterQualityReport(int reportNum, UserProfile user, Location location, QuaCondition quaCondition, String virPPM, String conPPM) {
        this(reportNum, user, location);
        this.quaCondition = quaCondition;
        virusPPM = virPPM;
        contamPPM = conPPM;
    }
    public WaterQualityReport(int reportNum, UserProfile user, Location location){
        reportNumber = reportNum;
        reporterName = user.getName();
        reporterID = user.getId();
        waterLocation = location;
        //this.waterType  = type;
        stringLocation = location.toString();

        Calendar timenow = Calendar.getInstance();
        month = Calendar.MONTH;
        day = Calendar.DAY_OF_MONTH;
        year = Calendar.YEAR;
    }


    /**
     * getter for the virusPPM
     * @return the virusPPM
     */
    public String getVirusPPM() { return virusPPM;}

    /**
     * setter for the virusPPM
     * @param virusPPM the virusPPM
     */
    public void setVirusPPM(String virusPPM) { this.virusPPM = virusPPM; }

    /**
     * getter for the contamPPM
     * @return the contamPPM
     */
    public String getContamPPM() { return contamPPM;}

    /**
     * setter for the contamPPM
     * @param contamPPM the contamPPM
     */
    public void setContamPPM(String contamPPM) { this.contamPPM = contamPPM; }

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
}
