package controller;

import fxapp.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
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

    /**
     * Goes back to loggedin screen
     */
    @FXML
    public void backButtonPressed() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/loggedInForm.fxml"));
        mainApplication.setWindow(loader);
        LoggedinController controller = loader.getController();
        controller.setMainApp(mainApplication);
    }

    /**
     * Saves the changes to the users profile
     */
    @FXML
    public void saveButtonPressed() {
        UserProfile temp = mainApplication.getCurrentUser();
        temp.setPassword(passwordTextField.getText());
        temp.setAddress(addressTextField.getText());
        temp.setEmailAddress(emailTextField.getText());
        temp.setTitle(titleTextField.getText());
        temp.setAge(Integer.parseInt(ageTextField.getText()));
    }

    /**
     * Populates all the text fields with the current user's info
     */
    @FXML
    public void populate() {
        UserProfile temp = mainApplication.getCurrentUser();
        nameTextField.setText(temp.getName());
        passwordTextField.setText(temp.getPassword());
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

    private Main mainApplication;
    public void setMainApp(Main main) {
        mainApplication = main;
    }
}
