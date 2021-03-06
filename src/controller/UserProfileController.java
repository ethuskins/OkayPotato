package controller;

import fxapp.Main;
import fxapp.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import model.Password;
import model.UserProfile;

/**
 * Controller for the user Profile screen. Allows the user to edit any editable information.
 */
public class UserProfileController {

    @FXML private TextField nameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField titleTextField;
    @FXML private TextField ageTextField;

    private Main mainApplication;

    /**
     * Takes the user to the Main Menu screen
     */
    @FXML
    public void backButtonPressed() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/mainMenuForm.fxml"));
        mainApplication.setWindow(loader);
        MainMenuController controller = loader.getController();
        controller.setMainApp(mainApplication);
    }

    /**
     * Saves the changes to the users profile
     */
    @FXML
    public void saveButtonPressed() {
        String hashedPass = "";
        try {
            hashedPass = Password.getSaltedHash(passwordTextField.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Session.getInstance().getCurrentUser().setPassword(hashedPass);
        Session.getInstance().getCurrentUser().setAddress(addressTextField.getText());
        Session.getInstance().getCurrentUser().setEmailAddress(emailTextField.getText());
        Session.getInstance().getCurrentUser().setTitle(titleTextField.getText());
        Session.getInstance().getCurrentUser().setAge(Integer.parseInt(ageTextField.getText()));
        
    }

    /**
     * Populates all the text fields with the current user's info
     */
    @FXML
    public void populate() {
        UserProfile temp = Session.getInstance().getCurrentUser();
        nameTextField.setText(temp.getName());
        if (temp.getAddress() != null) {
            addressTextField.setText(temp.getAddress());
        }
        if (temp.getEmailAddress() != null) {
            emailTextField.setText(temp.getEmailAddress());
        }
        if (temp.getTitle() != null) {
            titleTextField.setText(temp.getTitle());
        }
        ageTextField.setText("" + temp.getAge());
    }

    /**
     * Sets the main application. This is the object that carries info needed between screens.
     * @param main the object containing cross-screen info.
     */
    public void setMainApp(Main main) {
        mainApplication = main;
    }
}
