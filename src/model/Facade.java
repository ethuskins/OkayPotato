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
    private List<Location> locations = new ArrayList<Location>();



    private Facade() {
        //dummy make some data
        //make it so it adds markers at report locations

        for (int i = 0; i < 10; ++i) {
            Location l = new Location(34.0 + (i/10.0), -88.0 - (i/10.0), "Marker " + i, "<h2>Test "  + i + "</h2> <br> some data");
            locations.add(l);
        }
    }

    public List<Location> getLocations() { return locations; }

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

    public void addLocations() {
        locations.add(new Location(34.043, -88.043, "New Marker", "Some new data"));
    }
}
