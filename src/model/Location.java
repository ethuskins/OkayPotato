package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by robertwaters on 10/7/16.
 * Just a class to hold some data we might want to display on the map
 */
public class Location implements Serializable {

    private static Logger LOGGER = Logger.getLogger("Location");
    private static FileHandler logFileHandler;
    static {
        LOGGER.setLevel(Level.FINER);
        try {
            logFileHandler = new FileHandler("LogFile");
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
    private final WaterCondition cond;
    private final WaterType type;

    public Location(double lat, double lg, String ti, String desc, WaterCondition cond, WaterType type) {
        LOGGER.entering("Location", "Constructor");
        longitude = lg;
        latitude = lat;
        description = desc;
        title = ti;
        LOGGER.exiting("Location", "Constructor");
        this.cond = cond;
        this.type = type;
    }

    public double getLongitude() { return longitude; }
    public double getLatitude() {return latitude; }
    public String getDescription() {return description;}
    public String getTitle() { return title; }
    public WaterCondition getCond() { return cond; }
    public WaterType getType() { return type; }


    public void saveToText(PrintWriter pw) {
        LOGGER.setLevel(Level.FINEST);
        LOGGER.entering("Location", "saveToText");
        pw.println(longitude + "\t" + latitude + "\t" +  description + "\t" + title);
        LOGGER.exiting("Location", "saveToText");
    }

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
}
