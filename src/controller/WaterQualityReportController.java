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


public class WaterQualityReportController {
    @FXML private TextField longitudeTextField;
    @FXML private TextField latitudeTextField;
    @FXML private TextField titleTextField;
    @FXML private TextField descriptionTextField;
    @FXML private ComboBox<QuaCondition> quaConditionComboBox = new ComboBox<>();
    //@FXML private ComboBox<WaterCondition> waterConditionComboBox = new ComboBox<WaterCondition>();
    //@FXML private ComboBox<WaterType> waterTypeComboBox = new ComboBox<WaterType>();
    @FXML private TextField virusTextField;
    @FXML private TextField contamTextField;

    private Main mainApplication;

// --Commented out by Inspection START (11/10/2016 6:30 PM):
//    @FXML
//    private void initialize() {
//        quaConditionComboBox.getItems().addAll(FXCollections.observableArrayList(QuaCondition.values()));
//        //waterConditionComboBox.getItems().addAll(FXCollections.observableArrayList(WaterCondition.values()));
//        //waterTypeComboBox.getItems().addAll(FXCollections.observableArrayList(WaterType.values()));
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
            HashMap<Integer, WaterQualityReport> qualityReportMap = Session.getWaterQualityReportHashMap();

            if (!longitudeString.equals("") && !latitudeString.equals("") && quaCondition != null) {
                //creates the new water report and puts it in the hash map
                int reportNum = Session.getInstance().getWqrNumber();

                WaterQualityReport qualityReport = new WaterQualityReport(reportNum, Session.getInstance().getCurrentUser(), location, quaCondition, virusPPM, contPPM);
                qualityReportMap.put(reportNum, qualityReport);

                //sending modified table back up to firebase
                //Firebase fb = Session.getInstance().getFbCurrent();
                /*Map<String, Object> fbInsert = new HashMap<>();
                for (Map.Entry<Integer, WaterQualityReport> entry : qualityReportMap.entrySet()) {
                    fbInsert.put(entry.getKey().toString(), entry.getValue());
                }
                /*try {
                    FirebaseResponse resp = fb.put(Session.getInstance().getWqrURL(), fbInsert);
                } catch (JacksonUtilityException ignored) {

                } catch (FirebaseException fbex) {
                    System.out.println("FB exception in wqrController");
                }*/


                Session.getInstance().incrementWqrNumber();




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
