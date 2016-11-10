package controller;

import fxapp.Main;
import fxapp.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import model.AccountType;
import model.WaterQualityReport;
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
        HashMap<Integer, WaterSourceReport> testermap = Session.getInstance().getWaterSourceReportHashMap();
        //HashMap<Integer, WaterQualityReport> testermap = mainApplication.getWaterQualityReportHashMap();
        Set<Integer> keylist = testermap.keySet();
        ObservableList<WaterSourceReport> reportlist = FXCollections.observableArrayList();
        for (Integer x : keylist) {
            reportlist.add(testermap.get(x));
        }
        //ObservableMap<Integer, WaterSourceReport> observableMap = FXCollections.observableMap(mainApplication.getWaterSourceReportHashMap());


        tableReportNumber.setCellValueFactory(
                new PropertyValueFactory<WaterSourceReport, Integer>("reportNumber"));
        tableDateTime.setCellValueFactory(
                new PropertyValueFactory<WaterSourceReport, Integer>("dateTime")
        );
        tableReporter.setCellValueFactory(
                new PropertyValueFactory<WaterSourceReport, String>("reporterName")
        );
        tableLocation.setCellValueFactory(
                new PropertyValueFactory<WaterSourceReport, String>("stringLocation")
        );
        tableType.setCellValueFactory(
                new PropertyValueFactory<WaterSourceReport, String>("waterType")
        );
        tableCondition.setCellValueFactory(
                new PropertyValueFactory<WaterSourceReport, String>("waterCondition")
        );
        tableReports.setItems(reportlist);
        //tableReports.getColumns(tableReportNumber, tableDateTime, tableReporter,tableLocation, tableType, tableCondition);
    }
}