package controller;

import fxapp.Main;
import fxapp.Session;
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
 * Controls the historical report.
 */
public class HistoricalReportController {

    private Main mainApplication;

    @FXML private LineChart<Double, Double> reportGraph;
    @FXML private NumberAxis monthAxis;
    @FXML private NumberAxis partsAxis;

    /**
     * Populates the graph.
     *
     * @param latitude the latitude
     * @param longitude the longitude
     * @param year the year
     * @param type the type
     */
    public void populate(double latitude, double longitude, int year, ReportType type) {
        monthAxis.setLabel("Month");
        if (type == ReportType.VIRUS) {
            partsAxis.setLabel("Virus PPM");
        } else {
            partsAxis.setLabel("Contaminant PPM");
        }

        reportGraph.setTitle("Historical Report at " + latitude + " by " + longitude + " in " + year);

        XYChart.Series<Double, Double> series = new XYChart.Series<>();
        HashMap<Integer, WaterQualityReport> reportsMap = Session.getWaterQualityReportHashMap();
        Set<Integer> keyList = reportsMap.keySet();
        double[][] reports = new double[12][2];
        for (Integer x : keyList) {
            if (reportsMap.get(x).getLocation().getLatitude() == latitude && reportsMap.get(x).getLocation().getLongitude() == longitude && reportsMap.get(x).getYear() == year) {
                int month = reportsMap.get(x).getMonth();
                double ppm;
                if (type == ReportType.VIRUS) {
                    ppm = Double.valueOf(reportsMap.get(x).getVirusPPM());
                } else {
                    ppm = Double.valueOf(reportsMap.get(x).getContamPPM());
                }
                reports[month][0] += ppm;
                reports[month][1] ++;
            }
        }
        for (int i = 0; i < 12; i++) {
            if (reports[i][1] != 0) {
                double temp = reports[i][0]/reports[i][1];
                series.getData().add(new XYChart.Data(i,temp));
            }
        }
        reportGraph.getData().add(series);
    }

    /**
     * Cancels the historical report request process and returns to the title screen.
     *
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
