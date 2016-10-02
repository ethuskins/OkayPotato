package controller;

import fxapp.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.UserProfile;

import java.util.HashMap;

/**
 * Created by Scott Simmons on 9/22/2016.
 */
public class UserPassController {

    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;

    @FXML
    public void signInButtonPressed() {
        //load the logged in scene from LoggedInForm.fxml
        HashMap<String, UserProfile> userProfileHashMap = mainApplication.getUserProfileStringHashMap();
        String userName = usernameTextField.getText();
        String pass = null;
        UserProfile userProfile = userProfileHashMap.get(userName);
        if (userProfile != null) {
            pass = userProfile.getPassword();
        }
        //checks if entered info matches an entry in the hashmap
        if (userProfile != null && passwordTextField.getText().equals(pass)){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/loggedInForm.fxml"));
            mainApplication.setWindow(loader);
            LoggedinController controller = loader.getController();
            controller.setMainApp(mainApplication);
        } else {
            Alert ruined = new Alert(AlertType.ERROR);
            ruined.setHeaderText("Incorrect Username/Password");
            ruined.showAndWait();
        }
    }

    @FXML
    public void cancelButtonPressed() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/loginForm.fxml"));
        mainApplication.setWindow(loader);
        LoginController controller = loader.getController();
        controller.setMainApp(mainApplication);
    }

    private Main mainApplication;
    public void setMainApp(Main main) {
        mainApplication = main;
    }
}
