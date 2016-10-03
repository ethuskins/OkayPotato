package controller;

import fxapp.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

/**
 * Controls the Home page.
 */
public class LoggedinController {

    /**
     * Logs out the user and returns to the title screen.
     */
    @FXML
    public void logoutButtonPressed() {
        //load the user and password scene from userPassForm.fxml
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/loginForm.fxml"));
        mainApplication.setWindow(loader);
        LoginController controller = loader.getController();
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
        //TODO figure out NPE
    }

    private Main mainApplication;

    public void setMainApp(Main main) {
        mainApplication = main;
    }

}
