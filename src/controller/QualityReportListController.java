package controller;

import fxapp.Main;
import fxapp.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.WaterQualityReport;

import java.util.HashMap;
import java.util.Set;


/**
 * Controller for the report list screen. Allows the user to either login or make a new account.
 */
public class QualityReportListController {
    @FXML private TableView<WaterQualityReport> tableReports;
    @FXML private TableColumn<WaterQualityReport, Integer> tableReportNumber;
    @FXML private TableColumn<WaterQualityReport, Integer> tableDateTime;
    @FXML private TableColumn<WaterQualityReport, String> tableReporter;
    @FXML private TableColumn<WaterQualityReport, String> tableLocation;
    @FXML private TableColumn<WaterQualityReport, String> tableType;
    @FXML private TableColumn<WaterQualityReport, String> tableCondition;
    @FXML private TableColumn<WaterQualityReport, String> tableVirusPPM;
    @FXML private TableColumn<WaterQualityReport, String> tableContPPM;
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

    @FXML
    private void initialize() {
        populateTable();
    }

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
        //HashMap<Integer, WaterSourceReport> testerMap = mainApplication.getWaterSourceReportHashMap();
        HashMap<Integer, WaterQualityReport> testerMap = Session.getWaterQualityReportHashMap();
        Set<Integer> keyList = testerMap.keySet();
        ObservableList<WaterQualityReport> reportList = FXCollections.observableArrayList();
        for (Integer x : keyList) {
            reportList.add(testerMap.get(x));
        }
        //ObservableMap<Integer, WaterSourceReport> observableMap = FXCollections.observableMap(mainApplication.getWaterSourceReportHashMap());


        tableReportNumber.setCellValueFactory(new PropertyValueFactory<>("reportNumber"));
        tableDateTime.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        tableReporter.setCellValueFactory(new PropertyValueFactory<>("reporterName"));
        tableLocation.setCellValueFactory(new PropertyValueFactory<>("stringLocation"));
        //tableType.setCellValueFactory(new PropertyValueFactory<WaterQualityReport, String>("waterType"));
        tableCondition.setCellValueFactory(new PropertyValueFactory<>("quaCondition"));
        tableVirusPPM.setCellValueFactory(new PropertyValueFactory<>("virusPPM"));
        tableContPPM.setCellValueFactory(new PropertyValueFactory<>("contamPPM"));
        tableReports.setItems(reportList);
        //tableReports.getColumns(tableReportNumber, tableDateTime, tableReporter,tableLocation, tableType, tableCondition);
    }
}