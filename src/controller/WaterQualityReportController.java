package controller;

import fxapp.Main;
import fxapp.SessionInfo;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.*;

import java.util.HashMap;

/**
 * Created by jackwinski on 10/24/16.
 */
public class WaterQualityReportController {
    @FXML private TextField longitudeTextField;
    @FXML private TextField latitudeTextField;
    @FXML private TextField titleTextField;
    @FXML private TextField descriptionTextField;
    @FXML private ComboBox<QuaCondition> quaConditionComboBox = new ComboBox<QuaCondition>();
    //@FXML private ComboBox<WaterCondition> waterConditionComboBox = new ComboBox<WaterCondition>();
    //@FXML private ComboBox<WaterType> waterTypeComboBox = new ComboBox<WaterType>();
    @FXML private TextField virusTextField;
    @FXML private TextField contamTextField;

    private Main mainApplication;

    @FXML
    private void initialize() {
        quaConditionComboBox.getItems().addAll(FXCollections.observableArrayList(QuaCondition.values()));
        //waterConditionComboBox.getItems().addAll(FXCollections.observableArrayList(WaterCondition.values()));
        //waterTypeComboBox.getItems().addAll(FXCollections.observableArrayList(WaterType.values()));
    }

    @FXML
    public void submitReportButtonPressed() {
        //String location = locationTextField.getText();
        String longitudeString = longitudeTextField.getText();
        String latitudeString = latitudeTextField.getText();
        Alert ruined = new Alert(Alert.AlertType.ERROR);
        String title = titleTextField.getText();
        String description = descriptionTextField.getText();
        QuaCondition quaCondition = quaConditionComboBox.getValue();
        //WaterCondition waterCondition = waterConditionComboBox.getValue();
        //WaterType waterType = waterTypeComboBox.getValue();

        String virusPPM = virusTextField.getText();
        String contPPM = contamTextField.getText();
        try{
            double longitude = Double.valueOf(longitudeString);
            double latitude = Double.valueOf(latitudeString);
            Location location = new Location(latitude, longitude, title, description, quaCondition);
            //HashMap<Integer, WaterSourceReport> sourceReportHashMap = mainApplication.getWaterSourceReportHashMap();
            HashMap<Integer, WaterQualityReport> qualityReportMap = SessionInfo.getInstance().getWaterQualityReportHashMap();

            if (!longitudeString.equals("") && !latitudeString.equals("") && !quaCondition.equals(null)) {
                //creates the new water report and puts it in the hash map
                int reportNum = SessionInfo.getInstance().reportnumber;

                WaterQualityReport qualityReport = new WaterQualityReport(reportNum, SessionInfo.getInstance().getCurrentUser(), location, quaCondition, virusPPM, contPPM);
                qualityReportMap.put(reportNum, qualityReport);
                //returns to the main menu
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("../view/mainMenuForm.fxml"));
                mainApplication.setWindow(loader);
                MainMenuController controller = loader.getController();
                controller.setMainApp(mainApplication);
                SessionInfo.getInstance().reportnumber++;
            }else {
                ruined.setHeaderText("Invalid Input. Try again.");
                ruined.showAndWait();
            }
        } catch(Exception e){
            e.printStackTrace();
            //most likely exception will be from invalid input to longitude/latitude
            ruined.setHeaderText("Invalid Input. Try again.");
            ruined.showAndWait();
        }

    }

    @FXML
    public void cancelReportButtonPressed() {
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
