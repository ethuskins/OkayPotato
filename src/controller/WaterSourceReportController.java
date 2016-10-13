package controller;
import fxapp.Main;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.WaterCondition;
import model.WaterSourceReport;
import model.WaterType;
import java.util.HashMap;

/**
 * Created by emilyhuskins on 10/12/16.
 */
public class WaterSourceReportController {
    @FXML private TextField locationTextField;
    @FXML private ComboBox<WaterCondition> waterConditionComboBox = new ComboBox<WaterCondition>();
    @FXML private ComboBox<WaterType> waterTypeComboBox = new ComboBox<WaterType>();

    @FXML
    private void initialize() {
        waterConditionComboBox.getItems().addAll(FXCollections.observableArrayList(WaterCondition.values()));
        waterTypeComboBox.getItems().addAll(FXCollections.observableArrayList(WaterType.values()));
    }

    @FXML
    public void submitReportButtonPressed() {
        String location = locationTextField.getText();
        WaterCondition condition = waterConditionComboBox.getValue();
        WaterType type = waterTypeComboBox.getValue();

        HashMap<Integer, WaterSourceReport> sourceReportMap = mainApplication.getWaterSourceReportHashMap();

        if (!location.equals("") && !condition.equals(null) && !type.equals(null)) {
            //creates the new water report and puts it in the hash map
            sourceReportMap.put(mainApplication.reportnumber, new WaterSourceReport(mainApplication.reportnumber, mainApplication.getCurrentUser(), location, type, condition));
            //returns to the main menu
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/mainMenuForm.fxml"));
            mainApplication.setWindow(loader);
            MainMenuController controller = loader.getController();
            controller.setMainApp(mainApplication);
            mainApplication.reportnumber++;
        }else {
            Alert ruined = new Alert(Alert.AlertType.ERROR);

            ruined.setHeaderText("Please fill out all fields.");
            ruined.showAndWait();
        }


    }

    @FXML
    public void cancelReportButtonPressed() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/mainMenuForm.fxml"));
        mainApplication.setWindow(loader);
        TitleScreenController controller = loader.getController();
        controller.setMainApp(mainApplication);
    }

    private Main mainApplication;
    public void setMainApp(Main main) {
        mainApplication = main;
    }


}
