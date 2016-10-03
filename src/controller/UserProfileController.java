package controller;

import fxapp.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

/**
 * Created by Forrest on 10/3/2016.
 */
public class UserProfileController {

    @FXML
    public void backButtonPressed() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/loggedInForm.fxml"));
        mainApplication.setWindow(loader);
        LoginController controller = loader.getController();
        controller.setMainApp(mainApplication);
    }

    private Main mainApplication;
    public void setMainApp(Main main) {
        mainApplication = main;
    }
}
