package controller;

import fxapp.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Controls the Home page.
 */
public class MainMenuController {

    private Main mainApplication;
    private Main map;

    /**
     * Logs out the user and returns to the title screen.
     */
    @FXML
    public void logoutButtonPressed() {
        //load the user and password scene from loginCredentialsForm.fxml
        mainApplication.setCurrentUser(null);
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

        //mainApplication.setWindow(loader);
        //map.setWindow(loader2);

        //controller2.setMainApp(mainApplication);
    }

    /**
     * Sets the main application. This is the object that carries info needed between screens.
     * @param main the object containing cross-screen info.
     */
    public void setMainApp(Main main) {
        mainApplication = main;
    }

}
