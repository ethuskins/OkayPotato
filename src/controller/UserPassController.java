package controller;

import fxapp.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

/**
 * Created by Scott Simmons on 9/22/2016.
 */
public class UserPassController {

    @FXML
    public void signInButtonPressed() {
        //load the user and password scene from userPassForm.fxml
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/loggedInForm.fxml"));
        mainApplication.setWindow(loader);
    }

    private Main mainApplication;
    public void setMainApp(Main main) {
        mainApplication = main;
    }
}
