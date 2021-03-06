package controller;


import fxapp.Main;
import fxapp.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.AccountType;
import model.Password;
import model.UserProfile;
import javafx.collections.FXCollections;


import java.util.HashMap;



public class NewUserRegistrationController {

    @FXML private TextField nameTextField;
    @FXML private TextField idTextField;
    @FXML private TextField passwordTextField;
    @FXML private ComboBox<AccountType> accountTypeComboBoxList = new ComboBox<>();

    private Main mainApplication;

    /**
     * called automatically after load
     */
    @FXML
    private void initialize() {
        accountTypeComboBoxList.getItems().addAll(FXCollections.observableArrayList(AccountType.values()));
    }


    /**
     * Checks to see if all fields have valid entries. If so, registers the user and logs them in. If not, an alert
     * pops up explaining what went wrong.
     */
    public void confirmButtonPressed() {
        //load the logged in scene from LoggedInForm.fxml
        //make sure all the fields have something in them
        String name = nameTextField.getText();
        String id = idTextField.getText();
        String password = passwordTextField.getText();
        String hashedPass = "";
        try {
            hashedPass = Password.getSaltedHash(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AccountType accountType = accountTypeComboBoxList.getValue();
        HashMap<String, UserProfile> userProfileHashMap = Session.getInstance().getUserProfileStringHashMap();

        boolean idExists = userProfileHashMap.containsKey(id);
        if (!name.equals("") && !password.equals("")
                && !id.equals("") && accountType != null && !idExists){


            //create a UserProfile with the parameters in the fields
            UserProfile userProfile = new UserProfile(name, id, hashedPass, accountType);


            //add the UserProfile to the list of UserProfiles
            Session.getInstance().addUserProfile(userProfile);
            //Makes the UserProfile the current UserProfile
            Session.getInstance().setCurrentUser(userProfile);


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/mainMenuForm.fxml"));
            mainApplication.setWindow(loader);
            MainMenuController controller = loader.getController();
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

    /**
     * Cancels the registration process and returns to the title screen.
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
