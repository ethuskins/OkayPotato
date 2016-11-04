package controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import fxapp.Main;
import fxapp.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.*;
import netscape.javascript.JSObject;

import java.awt.*;
import java.awt.TextField;
import java.io.File;
import java.net.URL;
import java.util.*;

/**
 * Created by Jack Winski on 11/2/2016.
 */
public class HistoricalReportSelectorController {

    @FXML private ComboBox<ReportType> reportTypeComboBox = new ComboBox<ReportType>();
    @FXML private javafx.scene.control.TextField longitudeTextField;
    @FXML private javafx.scene.control.TextField latitudeTextField;
    @FXML private javafx.scene.control.TextField yearTextField;

    private Main mainApplication;

    /**
     * called automatically after load
     */
    @FXML
    private void initialize() {
        reportTypeComboBox.getItems().addAll(FXCollections.observableArrayList(ReportType.values()));
    }

    /**
     * Takes in the information selected and goes to the historical report screen.
     */
    @FXML
    public void viewButtonPressed() {
        double lat = Double.valueOf(latitudeTextField.getText());
        double lon = Double.valueOf(longitudeTextField.getText());
        int year = Integer.valueOf(yearTextField.getText());
        boolean test = false;
        HashMap<Integer, WaterQualityReport> testermap = Session.getInstance().getWaterQualityReportHashMap();
        Set<Integer> keylist = testermap.keySet();
        ObservableList<WaterQualityReport> reportlist = FXCollections.observableArrayList();
        for (Integer x : keylist) {
            if (testermap.get(x).getLocation().getLatitude() == lat && testermap.get(x).getLocation().getLongitude() == lon && testermap.get(x).getYear() == year) {
                test = true;
            }
        }
        if (test) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/historicalReportForm.fxml"));
            mainApplication.setWindow(loader);
            HistoricalReportController controller = loader.getController();
            controller.populate(lat,lon,year,reportTypeComboBox.getValue());
            controller.setMainApp(mainApplication);
        } else {
            //Put a pop up here
        }
    }

    /**
     * Cancels the historical report request process and returns to the title screen.
     */
    @FXML
    public void cancelButtonPressed() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/mainMenuForm.fxml"));
        mainApplication.setWindow(loader);
        MainMenuController controller = loader.getController();
        controller.setMainApp(mainApplication);
    }

    /**
     * Sets the main application. This is the object that carries info needed between screens.
     * @param main the object containing cross-screen info.
     */
    public void setMainApp(Main main) {
        mainApplication = main;
    }
}
