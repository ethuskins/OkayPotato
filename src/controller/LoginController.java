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

//anything that happens on the loginForm.fxml scene is controlled by this class
public class LoginController {

    //do this stuff when the login button is pressed
    @FXML
    public void loginButtonPressed() {
        //load the user and password scene from userPassForm.fxml
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/userPassForm.fxml"));
        mainApplication.setWindow(loader);
        UserPassController controller = loader.getController();
        controller.setMainApp(mainApplication);
    }

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


    public void cancelButtonPressed(ActionEvent actionEvent) {

    }

    public void confirmButtonPressed(ActionEvent actionEvent) {

    }
}
