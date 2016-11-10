package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by robertwaters on 10/7/16.
 */
public class Facade {

    private static final Facade instance = new Facade();
    public static Facade getInstance() { return instance; }

    //this is our simple model
    private final List<Location> locations = new ArrayList<>();

    private Facade() {
        //dummy make some data
        //adds markers at report locations
        /*for(Map.Entry<Integer, WaterSourceReport> report : reports.entrySet()){
            Location l = report.getValue().getLocation();
            locations.add(l);
        }*/
        /*for (int i = 0; i < 10; ++i) {
            Location l = new Location(34.0 + (i/10.0), -88.0 - (i/10.0), "Marker " + i, "<h2>Test "  + i + "</h2> <br> some data");
            locations.add(l);
        }*/
        /*System.out.println("There are " + reports.size() + " reports.");
        Location l = new Location(33.75, -84.4, "Guaranteed Marker", "<h2>Last Marker</h2><br>Guaranteed to be here.");
        locations.add(l);
        */
    }

// --Commented out by Inspection START (11/10/2016 5:15 PM):
//    /**
//     * Getter method for locations.
//     * @return locations the list of locations
//     */
//    public List<Location> getLocations() { return locations; }
// --Commented out by Inspection STOP (11/10/2016 5:15 PM)

    public void saveModelToText(File file) {
        PersistenceManager pm = new PersistenceManager(locations);
        pm.saveToText(file);
    }

    public void loadModelFromText(File file) {
        PersistenceManager pm = new PersistenceManager(locations);
        pm.loadFromText(file);
    }

    public void saveModelToBinary(File file) {
        PersistenceManager pm = new PersistenceManager(locations);
        pm.saveToBinary(file);
    }

    public void loadModelFromBinary(File file) {
        PersistenceManager pm = new PersistenceManager(locations);
        pm.loadFromBinary(file);
    }

    public void loadModelFromJson(File file) {
        PersistenceManager pm = new PersistenceManager(locations);
        pm.loadFromJsonfile(file);
    }

    public void saveModelToJson(File file) {
        PersistenceManager pm = new PersistenceManager(locations);
        pm.saveToJson(file);
    }

    /**
     * Adds a location to the locations array list.
     */
    public void addLocations() {
        locations.add(new Location(33.75, -84.4, "New Marker", "Some new data", WaterCondition.POTABLE, WaterType.BOTTLED));
    }
}
