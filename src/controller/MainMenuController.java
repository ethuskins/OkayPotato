package controller;

import fxapp.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

/**
 * Controls the Home page.
 */
public class MainMenuController {

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

    //do this stuff when the edit profile button is pressed
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

    @FXML
    public void submitReportButtonPressed() {
        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(Main.class.getResource("../view/waterSourceReportForm.fxml"));
        mainApplication.setWindow(loader2);
        WaterSourceReportController controller2 = loader2.getController();
        controller2.setMainApp(mainApplication);
    }

    private Main mainApplication;

    public void setMainApp(Main main) {
        mainApplication = main;
    }

}
