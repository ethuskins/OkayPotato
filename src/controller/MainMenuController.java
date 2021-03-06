package controller;

import fxapp.Main;
import fxapp.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.AccountType;
import javafx.scene.control.Alert;

/**
 * Controls the Home page.
 */
public class MainMenuController {

    private Main mainApplication;
    // --Commented out by Inspection (11/10/2016 6:30 PM):private Main map;


    /**
     * Logs out the user and returns to the title screen.
     */
    @FXML
    public void logoutButtonPressed() {
        //load the user and password scene from loginCredentialsForm.fxml
        Session.getInstance().setCurrentUser(null);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/titleScreenForm.fxml"));
        mainApplication.setWindow(loader);
        TitleScreenController controller = loader.getController();
        controller.setMainApp(mainApplication);
    }

    /**
     * Takes user to the edit profile screen.
     */
    @FXML
    public void editProfileButtonPressed() {
        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(Main.class.getResource("../view/userProfileForm.fxml"));
        mainApplication.setWindow(loader2);
        UserProfileController controller2 = loader2.getController();
        controller2.setMainApp(mainApplication);
        controller2.populate();
        //TODO figure out NPE
    }

    /**
     * Takes the user to the Submit Water Source Report page.
     */
    @FXML
    public void submitReportButtonPressed() {
        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(Main.class.getResource("../view/waterSourceReportForm.fxml"));
        mainApplication.setWindow(loader2);
        WaterSourceReportController controller2 = loader2.getController();
        controller2.setMainApp(mainApplication);
    }

    /**
     * Takes the user to the Submit Water Quality Report page.
     */
    @FXML
    public void submitQualityReportButtonPressed() {
        Alert ruined = new Alert(Alert.AlertType.ERROR); //TODO: make
        if (Session.getInstance().getCurrentUser().getAccountType() == AccountType.USER) {
            ruined.setHeaderText("You do not have the necessary access to access this page.");
            ruined.showAndWait();
            return;
        }
        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(Main.class.getResource("../view/waterQualityReportForm.fxml"));
        mainApplication.setWindow(loader2);
        WaterQualityReportController controller2 = loader2.getController();
        controller2.setMainApp(mainApplication);
    }

    /**
     * Takes the user to the Water Source Report List page.
     */
    @FXML
    public void sourceReportListButtonPressed() {
        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(Main.class.getResource("../view/sourceReportListForm.fxml"));
        mainApplication.setWindow(loader2);
        SourceReportListController controller2 = loader2.getController();
        controller2.setMainApp(mainApplication);
    }
    /**
     * Takes the user to the Water Quality Report List page.
     */
    @FXML
    public void qualityReportListButtonPressed() {
        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(Main.class.getResource("../view/qualityReportListForm.fxml"));
        mainApplication.setWindow(loader2);
        QualityReportListController controller2 = loader2.getController();
        controller2.setMainApp(mainApplication);
    }

    /**
     * Takes the user to a google map, which is populated with pins at the locations of all the Water Source Reports.
     */
    @FXML
    public void viewReportMapButtonPressed(){
        Stage mapStage = new Stage();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/mapView.fxml"));

        try{
            BorderPane mapPane = loader.load();
            Scene mapScene = new Scene(mapPane);
            MapController controller = loader.getController();
            controller.setCallbacks(mapStage,mainApplication);
            mapStage.setScene(mapScene);
            mapStage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Takes the user to the Historical Report Selector page.
     */
    @FXML
    public void viewHistoricalReportButtonPressed() {
        Alert ruined = new Alert(Alert.AlertType.ERROR); //TODO: make
        if (!isManager()) {
            ruined.setHeaderText("You do not have the necessary access to access this page.");
            ruined.showAndWait();
            return;
        }
        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(Main.class.getResource("../view/historicalReportSelectorForm.fxml"));
        mainApplication.setWindow(loader2);
        HistoricalReportSelectorController controller2 = loader2.getController();
        controller2.setMainApp(mainApplication);
    }

    /**
     * Checks if the current user is a manager.
     *
     * @return true if the user is a manager, false otherwise
     */

    public boolean isManager() {
        if (Session.getInstance().getCurrentUser().getAccountType() != AccountType.MANAGER) {
            return false;
        }
        return true;
    }

    /**
     * Sets the main application. This is the object that carries info needed between screens.
     * @param main the object containing cross-screen info.
     */
    public void setMainApp(Main main) {
        mainApplication = main;
    }

}
