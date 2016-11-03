package controller;

import fxapp.Main;
import fxapp.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import model.ReportType;
import model.WaterQualityReport;

import java.util.HashMap;
import java.util.Set;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * Created by Jack Winski on 11/2/2016.
 */
public class HistoricalReportController {

    private Main mainApplication;

    @FXML private LineChart reportGraph;

    /**
     * Populates the graph
     */
    public void populate(double latitude, double longitude, int year, ReportType type) {
        final NumberAxis monthAxis = new NumberAxis();
        final NumberAxis partsAxis = new NumberAxis();
        monthAxis.setLabel("Month");
        if (type == ReportType.VIRUS) {
            partsAxis.setLabel("Virus PPM");
        } else {
            partsAxis.setLabel("Contaminant PPM");
        }
        reportGraph = new LineChart<>(monthAxis,partsAxis);
        reportGraph.setTitle("Historical Report at " + latitude + " by " + longitude + " in " + year);

        XYChart.Series series = new XYChart.Series();
        HashMap<Integer, WaterQualityReport> reportsMap = Session.getInstance().getWaterQualityReportHashMap();
        Set<Integer> keylist = reportsMap.keySet();
        ObservableList<WaterQualityReport> reportlist = FXCollections.observableArrayList();
        for (Integer x : keylist) {
            if (reportsMap.get(x).getLocation().getLatitude() == latitude && reportsMap.get(x).getLocation().getLongitude() == longitude && reportsMap.get(x).getDateTime().getYear() == year) {
                int month = reportsMap.get(x).getDateTime().getMonth().getValue();
                double ppm = 0;
                if (type == ReportType.VIRUS) {
                    ppm = Double.valueOf(reportsMap.get(x).getVirusPPM());
                } else {
                    ppm = Double.valueOf(reportsMap.get(x).getContamPPM());
                }
                series.getData().add(new XYChart.Data(month,ppm));
            }
        }
        reportGraph.getData().add(series);
    }

    /**
     * Cancels the historical report request process and returns to the title screen.
     */
    @FXML
    public void backButtonPressed() {
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
