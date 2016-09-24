package controller;

import fxapp.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

//anything that happens on the loginForm.fxml scene is controlled by this class
public class LoginController {

    //do this stuff when the login button is pressed
    @FXML
    public void loginButtonPressed() {
        //load the user and password scene from userPassForm.fxml
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/userPassForm.fxml"));
        mainApplication.setWindow(loader);
    }

    private Main mainApplication;
    public void setMainApp(Main main) {
        mainApplication = main;
    }

}
