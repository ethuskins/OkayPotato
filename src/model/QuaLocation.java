package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

class QuaLocation implements Serializable {

    private static final Logger LOGGER = Logger.getLogger("Location");

    static {
        LOGGER.setLevel(Level.FINER);
        try {
            FileHandler logFileHandler = new FileHandler("LogFile");
            logFileHandler.setLevel(Level.ALL);
            LOGGER.addHandler(logFileHandler);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Failed to create log file", ex);
        }
    }

    private final double longitude;
    private final double latitude;
    private final String description;
    private final String title;
    private final QuaCondition cond;

    public QuaLocation(double lat, double lg, String ti, String desc, QuaCondition cond) {
        LOGGER.entering("Location", "Constructor");
        longitude = lg;
        latitude = lat;
        description = desc;
        title = ti;
        LOGGER.exiting("Location", "Constructor");
        this.cond = cond;
    }

    /**
     * getter for longitude
     * @return longitude
     */
    public double getLongitude() { return longitude; }

    /**
     * getter for latitude
     * @return latitude
     */
    public double getLatitude() {return latitude; }

    /**
     * getter for description
     * @return description
     */
    public String getDescription() {return description;}

    /**
     * getter for title
     * @return title
     */
    public String getTitle() { return title; }

    /**
     * getter for condition
     * @return condition
     */
    public QuaCondition getCond() { return cond; }


    public void saveToText(PrintWriter pw) {
        LOGGER.setLevel(Level.FINEST);
        LOGGER.entering("Location", "saveToText");
        pw.println(longitude + "\t" + latitude + "\t" +  description + "\t" + title);
        LOGGER.exiting("Location", "saveToText");
    }

    /**
     * A way to load a location from a file.
     * @param str The string loaded from the file representing the location
     * @return The location loaded from the file.
     * @throws FileFormatException if the File's format is incorrect
     */
    public static Location makeFromFileString(String str) throws FileFormatException {
        String[] tokens = str.split("\t");


        if (tokens.length < 5) {
            throw(new FileFormatException(str));
        }

        double longit;
        double lat;
        try {
            longit = Double.parseDouble(tokens[0]);
            lat = Double.parseDouble(tokens[1]);
        } catch (NumberFormatException e) {
            throw(new FileFormatException(str));
        }
        WaterCondition cond;
        WaterType type;
        try {
            cond = WaterCondition.valueOf(tokens[4]);
            type = WaterType.valueOf(tokens[5]);
        } catch (NumberFormatException e) {
            throw(new FileFormatException(str));
        }

        return new Location(lat, longit, tokens[3], tokens[2], cond, type);
    }

    /**
     * ToString method. Returns a string in the form "33.0N, 77.4W", based off of the longitude and latitude of the
     * location
     * @return a String representation of the location.
     */
    public String toString() {
        String returnString = "";
        double checker = Math.abs(latitude);
        returnString = returnString + Double.toString(checker);
        if (latitude > 0.0) {
            returnString = returnString + "N, ";
        } else if (latitude < 0.0) {
            returnString = returnString + "S, ";
        }
        checker = Math.abs(longitude);
        returnString = returnString + Double.toString(checker);
        if (longitude > 0.0) {
            returnString = returnString + "E";
        } else if (longitude < 0.0) {
            returnString = returnString + "W";
        }
        return returnString;
    }
}
