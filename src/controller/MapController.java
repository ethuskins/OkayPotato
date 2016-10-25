package controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import fxapp.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.*;
import netscape.javascript.JSObject;

import java.io.File;
import java.net.URL;
import java.util.*;

/**
 * Created by robertwaters on 10/12/16.
 */
public class MapController implements Initializable, MapComponentInitializedListener {
    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;

    private Window mainStage;

    private Main theApp;

    private HashMap<Integer, WaterSourceReport> reports = Main.getWaterSourceReportHashMap();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
    }

    /**
     * Sets the stage and app for the page.
     * @param stage the stage
     * @param app the object containing the information needed across screens.
     */
    public void setCallbacks(Window stage, Main app) {
        mainStage = stage;
        theApp = app;
    }



    @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();

        //set up the center location for the map
        LatLong center = new LatLong(33.75, -84.4);

        options.center(center)
                .zoom(9)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .mapType(MapTypeIdEnum.TERRAIN);

        map = mapView.createMap(options);


        /* now we communciate with the model to get all the locations for markers */
        List<Location> locations = new ArrayList<Location>();
        //HashMap<Integer, WaterSourceReport> reports = Main.getWaterSourceReportHashMap();
        HashMap<Integer, WaterQualityReport> reports = Main.getWaterQualityReportHashMap();
        for(Map.Entry<Integer, WaterQualityReport> report : reports.entrySet()){
            Location l = report.getValue().getLocation();
            locations.add(l);
        }
        System.out.println("There are " + reports.size() + " reports.");

        locations.add(new Location(33.70, -84.45, "Guaranteed Marker", "<br>Guaranteed to be here.", WaterCondition.WASTE, WaterType.BOTTLED));

        Facade fc = Facade.getInstance();
        //List<Location> locations = fc.getLocations();

        //for (Location l: locations) {
        for (Map.Entry<Integer, WaterQualityReport> report : reports.entrySet()){
            WaterQualityReport r = report.getValue();
            Location l = r.getLocation();
            MarkerOptions markerOptions = new MarkerOptions();
            LatLong loc = new LatLong(l.getLatitude(), l.getLongitude());

            markerOptions.position(loc)
                    .visible(Boolean.TRUE)
                    .title(l.getTitle());
            //markerOptions.label(l.getDescription());
            Marker marker = new Marker(markerOptions);

            map.addUIEventHandler(marker,
                    UIEventType.click,
                    (JSObject obj) -> {
                        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                        infoWindowOptions.content("<h2>" + l.getTitle()
                                + "</h2><br>" + l.getDescription()
                                + "<br>Water Condition: " + l.getCond().toString()
                                + "<br>Water Type: " + l.getType().toString()
                                + "<br>Quality Condition: " + r.getQuaCondition()
                                + "<br>Location: " + l.getLatitude() + "," + l.getLongitude()
                                + "<br>Added: " + r.getDateTime());
                        InfoWindow window = new InfoWindow(infoWindowOptions);
                        window.open(map, marker);
                    });

            map.addMarker(marker);
        }


    }

    /**
     * Opens the chosen text file.
     */
    @FXML
    public void onOpenTextFileMenu() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open Text File");
        File file  = fc.showOpenDialog(mainStage);
        if (file != null)
            Facade.getInstance().loadModelFromText(file);
    }

    /**
     * Opens the selected binary file.
     */
    @FXML
    public void onOpenBinaryFileMenu() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open Binary File");
        File file  = fc.showOpenDialog(mainStage);
        if (file != null)
            Facade.getInstance().loadModelFromBinary(file);
    }

    /**
     * opens the selected JSON file
     */
    @FXML
    public void onOpenJsonFileMenu() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open JSON File");
        File file  = fc.showOpenDialog(mainStage);
        if (file != null)
            Facade.getInstance().loadModelFromJson(file);
    }

    /**
     * Saves the selected text file.
     */
    @FXML
    public void onSaveTextFileMenu() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Save Text File");
        File file  = fc.showSaveDialog(mainStage);
        if (file != null)
            Facade.getInstance().saveModelToText(file);
    }

    /**
     * Saves the selected binary file.
     */
    @FXML
    public void onSaveBinaryFileMenu() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Save Binary File");
        File file  = fc.showSaveDialog(mainStage);
        if (file != null)
            Facade.getInstance().saveModelToBinary(file);
    }

    @FXML
    public void onSaveJsonMenu() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Save JSON File");
        File file  = fc.showSaveDialog(mainStage);
        if (file != null)
            Facade.getInstance().saveModelToJson(file);
    }

    /**
     * Closes the menu.
     */
    @FXML
    public void onCloseMenu() {
        theApp.closeMapView();
    }

}
