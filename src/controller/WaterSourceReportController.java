package controller;
import fxapp.Main;
import fxapp.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.*;

import java.util.HashMap;


public class WaterSourceReportController {
    @FXML private TextField longitudeTextField;
    @FXML private TextField latitudeTextField;
    @FXML private TextField titleTextField;
    @FXML private TextField descriptionTextField;
    @FXML private ComboBox<WaterCondition> waterConditionComboBox = new ComboBox<>();
    @FXML private ComboBox<WaterType> waterTypeComboBox = new ComboBox<>();

    private Main mainApplication;

// --Commented out by Inspection START (11/10/2016 6:30 PM):
//    @FXML
//    private void initialize() {
//        waterConditionComboBox.getItems().addAll(FXCollections.observableArrayList(WaterCondition.values()));
//        waterTypeComboBox.getItems().addAll(FXCollections.observableArrayList(WaterType.values()));
//
//    }
// --Commented out by Inspection STOP (11/10/2016 6:30 PM)

    @FXML
    public void submitReportButtonPressed() {
        //String location = locationTextField.getText();
        String longitudeString = longitudeTextField.getText();
        String latitudeString = latitudeTextField.getText();
        Alert ruined = new Alert(Alert.AlertType.ERROR);
        String title = titleTextField.getText();
        String description = descriptionTextField.getText();
        WaterCondition waterCondition = waterConditionComboBox.getValue();
        WaterType type = waterTypeComboBox.getValue();
        try{
            double longitude = Double.valueOf(longitudeString);
            double latitude = Double.valueOf(latitudeString);
            Location location = new Location(latitude, longitude, title, description, waterCondition, type );
            HashMap<Integer, WaterSourceReport> sourceReportMap = Session.getWaterSourceReportHashMap();
            //HashMap<Integer, WaterQualityReport> qualityReportMap = mainApplication.getWaterQualityReportHashMap();

            //.equals(null) is intentional. The comboBox could never have selected an option.
            //noinspection ObjectEqualsNull,ObjectEqualsNull
            if (!longitudeString.equals("") && !latitudeString.equals("") && !waterCondition.equals(null) && !type.equals(null)) {
                //creates the new water report and puts it in the hash map
                int reportNum = Session.getInstance().getWsrNumber();
                WaterSourceReport sourceReport = new WaterSourceReport(reportNum, Session.getInstance().getCurrentUser(), location, type, waterCondition);
                sourceReportMap.put(reportNum, sourceReport);

                //sending modified table back up to firebase
                //Firebase fb = Session.getInstance().getFbCurrent();
                /*Map<String, Object> fbInsert = new HashMap<>();
                for (Map.Entry<Integer, WaterSourceReport> entry : sourceReportMap.entrySet()) {
                    fbInsert.put(entry.getKey().toString(), entry.getValue());
                }
                /*try {
                    FirebaseResponse resp = fb.put(Session.getInstance().getWsrURL(), fbInsert);
                } catch (JacksonUtilityException | FirebaseException ignored) {

                }*/

                Session.getInstance().incrementWsrNumber();

                /*Map<String, Object> fbInsertNum = new HashMap<>();
                fbInsertNum.put("wsrNumber", Session.getInstance().getWsrnumber());
                fbInsertNum.put("wqrNumber", Session.getInstance().getWqrnumber());
                /*try {
                    FirebaseResponse response = fb.put(Session.getInstance().getNumURL(), fbInsertNum);
                } catch (JacksonUtilityException | FirebaseException ignored) {

                }*/
                //returns to the main menu
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("../view/mainMenuForm.fxml"));
                mainApplication.setWindow(loader);
                MainMenuController controller = loader.getController();
                controller.setMainApp(mainApplication);



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
