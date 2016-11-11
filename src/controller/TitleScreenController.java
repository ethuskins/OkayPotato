package controller;

import fxapp.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

/**
 * Controller for the title screen. Allows the user to either login or make a new account.
 */
public class TitleScreenController {

    private Main mainApplication;

    /**
     * Takes the user to the login screen
     */
    @FXML
    public void loginButtonPressed() {
        //load the user and password scene from loginCredentialsForm.fxml
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/loginCredentialsForm.fxml"));
        mainApplication.setWindow(loader);
        LoginCredentialsController controller = loader.getController();
        controller.setMainApp(mainApplication);
    }

    /**
     * Takes the user to the registration screen.
     */
    @FXML
    public void registerButtonPressed(){
        //load the new user scene from newUserRegistrationForm.fxml
        FXMLLoader loader2 = new FXMLLoader();
        //loader2.setLocation(Main.class.getResource("../view/loginCredentialsForm.fxml"));
        loader2.setLocation(Main.class.getResource("../view/newUserRegistrationForm.fxml"));
        mainApplication.setWindow(loader2);
        NewUserRegistrationController controller2 = loader2.getController();
        controller2.setMainApp(mainApplication);
    }

    /**
     * Sets the main application. This is the object that carries info needed between screens.
     * @param main the object containing cross-screen info.
     */
    public void setMainApp(Main main) {
        mainApplication = main;
    }
}
