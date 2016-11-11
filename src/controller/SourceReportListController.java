package controller;

import fxapp.Main;
import fxapp.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import model.WaterSourceReport;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.HashMap;
import java.util.Set;


/**
 * Controller for the report list screen. Allows the user to either login or make a new account.
 */
public class SourceReportListController {
    @FXML private TableView<WaterSourceReport> tableReports;
    @FXML private TableColumn<WaterSourceReport, Integer> tableReportNumber;
    @FXML private TableColumn<WaterSourceReport, Integer> tableDateTime;
    @FXML private TableColumn<WaterSourceReport, String> tableReporter;
    @FXML private TableColumn<WaterSourceReport, String> tableLocation;
    @FXML private TableColumn<WaterSourceReport, String> tableType;
    @FXML private TableColumn<WaterSourceReport, String> tableCondition;
    @FXML private Button buttonPopulate;
    @FXML private Button buttonReturn;



    private Main mainApplication;

    /**
     * Sets the main application. This is the object that carries info needed between screens.
     * @param main the object containing cross-screen info.
     */
    public void setMainApp(Main main) {
        mainApplication = main;
    }

// --Commented out by Inspection START (11/10/2016 6:30 PM):
//    @FXML
//    private void initialize() {
//        populateTable();
//    }
// --Commented out by Inspection STOP (11/10/2016 6:30 PM)

    /**
     * Returns to the main menu screen.
     */
    @FXML
    public void returnButtonPressed() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/mainMenuForm.fxml"));
        mainApplication.setWindow(loader);
        MainMenuController controller = loader.getController();
        controller.setMainApp(mainApplication);
    }

    //Populates the table with Water Source Reports.
    @FXML
    private void populateTable() {
        HashMap<Integer, WaterSourceReport> testerMap = Session.getWaterSourceReportHashMap();
        //HashMap<Integer, WaterQualityReport> testerMap = mainApplication.getWaterQualityReportHashMap();
        Set<Integer> keyList = testerMap.keySet();
        ObservableList<WaterSourceReport> reportList = FXCollections.observableArrayList();
        reportList.addAll(keyList.stream().map(testerMap::get).collect(Collectors.toList()));
        //ObservableMap<Integer, WaterSourceReport> observableMap = FXCollections.observableMap(testerMap);
        //ObservableMap<Integer, WaterSourceReport> observableMap = FXCollections.observableMap(mainApplication.getWaterSourceReportHashMap());


        tableReportNumber.setCellValueFactory(
                new PropertyValueFactory<>("reportNumber"));
        tableDateTime.setCellValueFactory(
                new PropertyValueFactory<>("dateTime")
        );
        tableReporter.setCellValueFactory(
                new PropertyValueFactory<>("reporterName")
        );
        tableLocation.setCellValueFactory(
                new PropertyValueFactory<>("stringLocation")
        );
        tableType.setCellValueFactory(
                new PropertyValueFactory<>("waterType")
        );
        tableCondition.setCellValueFactory(
                new PropertyValueFactory<>("waterCondition")
        );
        tableReports.setItems(reportList);
        //tableReports.getColumns(tableReportNumber, tableDateTime, tableReporter,tableLocation, tableType, tableCondition);
    }
}