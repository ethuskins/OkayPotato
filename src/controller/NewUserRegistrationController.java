package controller;

import fxapp.Main;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.AccountType;
import model.UserProfile;

import javax.lang.model.type.NullType;
import java.util.HashMap;

/**
 * Created by Scott Simmons on 9/27/2016.
 */
public class NewUserRegistrationController {

    @FXML private TextField nameTextField;
    @FXML private TextField idTextField;
    @FXML private TextField passwordTextField;
    @FXML private ComboBox<AccountType> accountTypeComboBox = new ComboBox<AccountType>();

    /**
     * called automatically after load
     */
    @FXML
    private void initialize() {
        accountTypeComboBox.getItems().addAll(FXCollections.observableArrayList(AccountType.values()));
    }
    public void confirmButtonPressed() {
        //load the logged in scene from LoggedInForm.fxml
        //make sure all the fields have something in them
        String name = nameTextField.getText();
        String id = idTextField.getText();
        String password = passwordTextField.getText();
        AccountType accountType = accountTypeComboBox.getValue();
       // AccountType accountType = AccountType.USER;
        HashMap<String, UserProfile> userProfileHashMap = mainApplication.getUserProfileStringHashMap();

        boolean idExists = userProfileHashMap.containsKey(id);
        if (!name.equals("") && !password.equals("")
                && !id.equals("") && !accountType.equals(null) && !idExists){


            //create a UserProfile with the parameters in the fields
            //UserProfile userProfile = new UserProfile(nameTextField.getText(), idTextField.getText(),
            //        passwordTextField.getText(), accountTypeComboBox.getSelectionModel().getSelectedItem());
            UserProfile userProfile = new UserProfile(name, id, password, accountType);


            //add the UserProfile to the list of UserProfiles
            mainApplication.addUserProfile(userProfile);

            FXMLLoader loader = new FXMLLoader();
            //should creating a new user profile lead to the logged in form?
            loader.setLocation(Main.class.getResource("../view/loggedInForm.fxml"));
            mainApplication.setWindow(loader);
            LoggedinController controller = loader.getController();
            controller.setMainApp(mainApplication);
        } else {
            Alert ruined = new Alert(AlertType.ERROR);
            if(idExists){
                ruined.setHeaderText("User ID already Exists");
            }else{
                ruined.setHeaderText("Please fill out all fields.");
            }

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
