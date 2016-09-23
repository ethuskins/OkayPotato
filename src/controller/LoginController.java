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
        try {
            //load the user and password scene from userPassForm.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/userPassForm.fxml"));

            GridPane page = loader.load();
            //create the stage
            Stage userPassStage = new Stage();
            //create the scene
            Scene scene = new Scene(page);
            //set the scene
            userPassStage.setScene(scene);
            //show the stage
            userPassStage.showAndWait();

            if (isTrue("user", "pass")) {
                //go to main screen
            } else {
                //error popup
                //clear fields??
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private Main mainApplication;
    public void setMainApp(Main main) {
        mainApplication = main;
    }

    private boolean isTrue(String username, String password) {
        if (username.equals("user") && password.equals("pass")) {
            return true;
        }
        return false;
    }
}
