package controller;

import fxapp.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the title screen. Allows the user to either login or make a new account.
 */
public class LoginController {

    /**
     * Takes the user to the login screen
     */
    @FXML
    public void loginButtonPressed() {
        //load the user and password scene from userPassForm.fxml
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/userPassForm.fxml"));
        mainApplication.setWindow(loader);
        UserPassController controller = loader.getController();
        controller.setMainApp(mainApplication);
    }

    /**
     * takes the user to the registration screen
     */
    @FXML
    public void registerButtonPressed(){
        //load the new user scene from newUserRegistrationForm.fxml
        FXMLLoader loader2 = new FXMLLoader();
        //loader2.setLocation(Main.class.getResource("../view/userPassForm.fxml"));
        loader2.setLocation(Main.class.getResource("../view/newUserRegistrationForm.fxml"));
        mainApplication.setWindow(loader2);
        NewUserRegistrationController controller2 = loader2.getController();
        controller2.setMainApp(mainApplication);
    }

    private Main mainApplication;
    public void setMainApp(Main main) {
        mainApplication = main;
    }
}
