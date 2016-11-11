package controller;

import fxapp.Main;
import fxapp.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import model.*;
import javafx.collections.FXCollections;

import java.util.*;


public class HistoricalReportSelectorController {

    @FXML private ComboBox<ReportType> reportTypeComboBox = new ComboBox<>();
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
        Alert ruined = new Alert(Alert.AlertType.ERROR);
        boolean test = false;
        HashMap<Integer, WaterQualityReport> testerMap = Session.getWaterQualityReportHashMap();
        Set<Integer> keyList = testerMap.keySet();
        for (Integer x : keyList) {
            if (testerMap.get(x).getLocation().getLatitude() == lat) {
                if (testerMap.get(x).getLocation().getLongitude() == lon) {
                    if (testerMap.get(x).getYear() == year) {
                        test = true;
                    }
                }
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
            ruined.setHeaderText("There are no reports falling under those locations and that year.");
            ruined.showAndWait();
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
