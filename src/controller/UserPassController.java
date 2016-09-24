package controller;

import fxapp.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

/**
 * Created by Scott Simmons on 9/22/2016.
 */
public class UserPassController {

    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;

    @FXML
    public void signInButtonPressed() {
        //load the logged in scene from LoggedInForm.fxml
        if (usernameTextField.getText().equals("user") && passwordTextField.getText().equals("pass")){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/loggedInForm.fxml"));
            mainApplication.setWindow(loader);
        } else {
            
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
