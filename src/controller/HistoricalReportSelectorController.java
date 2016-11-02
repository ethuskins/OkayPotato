package controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import fxapp.Main;
import fxapp.Session;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.*;
import netscape.javascript.JSObject;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.*;

/**
 * Created by Jack Winski on 11/2/2016.
 */
public class HistoricalReportSelectorController {

    @FXML private ComboBox<ReportType> reportTypeComboBox = new ComboBox<ReportType>();

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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/historicalReportForm.fxml"));
        mainApplication.setWindow(loader);
        HistoricalReportController controller = loader.getController();
        controller.setMainApp(mainApplication);
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
