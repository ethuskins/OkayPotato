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
 * The login screen. Prompts the user to enter their username and password to login.
 */
public class LoginCredentialsController {

    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;

    private Main mainApplication;

    /**
     * Checks to see whether a valid username/password combination is entered. If so, logs the user in. If not, shows
     * an error.
     */
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
            mainApplication.setCurrentUser(userProfile);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/mainMenuForm.fxml"));
            mainApplication.setWindow(loader);
            MainMenuController controller = loader.getController();
            controller.setMainApp(mainApplication);
        } else {
            Alert ruined = new Alert(AlertType.ERROR);
            ruined.setHeaderText("Incorrect Username/Password");
            ruined.showAndWait();
        }
    }

    /**
     * Cancels the login process and proceeds back to the title screen.
     */
    @FXML
    public void cancelButtonPressed() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/titleScreenForm.fxml"));
        mainApplication.setWindow(loader);
        TitleScreenController controller = loader.getController();
        controller.setMainApp(mainApplication);
    }

    /**
     * Sets the main application. This is the object that carries info needed between screens.
     * @param main the object containing cross-screen info.
     */
    public void setMainApp(Main main) {
        mainApplication = main;
    }
}
