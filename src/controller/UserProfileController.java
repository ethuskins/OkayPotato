package controller;

import fxapp.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

/**
 * Controller for the user Profile screen. Allows the user to edit any editable information.
 */
public class UserProfileController {
    @FXML
    public void cancelButtonPressed() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/loggedInForm.fxml"));
        mainApplication.setWindow(loader);
        LoggedinController controller = loader.getController();
        controller.setMainApp(mainApplication);
    }

    @FXML
    public void saveButtonPressed() {

    }

    private Main mainApplication;
    public void setMainApp(Main main) {
        mainApplication = main;
    }
}
