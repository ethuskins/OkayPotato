package controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import fxapp.Main;
import fxapp.Session;
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
 * Controls the map.
 */
public class MapController implements Initializable, MapComponentInitializedListener {
    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;

    private Window mainStage;

    private Main theApp;

    // --Commented out by Inspection (11/10/2016 6:30 PM):private HashMap<Integer, WaterSourceReport> reports = Session.getWaterSourceReportHashMap();

    /**
     * Initializes, adds a map listener.
     * @param url the url
     * @param rb the resourceBundle
     */
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


    /**
     * Initializes the map.
     */
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


        /* now we communicate with the model to get all the locations for markers */
        HashMap<Integer, WaterQualityReport> reports = Session.getWaterQualityReportHashMap();
        System.out.println("There are " + reports.size() + " reports.");

        for (Map.Entry<Integer, WaterQualityReport> report : reports.entrySet()){
            WaterQualityReport r = report.getValue();
            Location l = r.getLocation();
            MarkerOptions markerOptions = new MarkerOptions();
            LatLong loc = new LatLong(l.getLatitude(), l.getLongitude());

            markerOptions.position(loc)
                    .visible(Boolean.TRUE)
                    .title("Quality Report: " + l.getTitle());
            Marker marker = new Marker(markerOptions);

            map.addUIEventHandler(marker,
                    UIEventType.click,
                    (JSObject obj) -> {
                        
                        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                        infoWindowOptions.content("<h2>" + l.getTitle()
                                + "</h2><br>" + l.getDescription()
                                + "<br>Quality Condition: " + r.getQuaCondition()
                                + "<br>Location: " + l.getLatitude() + "," + l.getLongitude()
                                + "<br>Added: " + r.getDay() + "-"+ r.getMonth() + "-" +r.getYear());
                        InfoWindow window = new InfoWindow(infoWindowOptions);
                        window.open(map, marker);
                    });

            map.addMarker(marker);
        }


    }

    /**
     * Closes the menu.
     */
    @FXML
    public void onCloseMenu() {
        theApp.closeMapView();
    }

}
