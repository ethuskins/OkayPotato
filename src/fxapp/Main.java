package fxapp;

import controller.LoginController;
import controller.NewUserRegistrationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.UserProfile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main extends Application {
    //Set the name of the first window the user sees
    private Stage loginScreen;
    //we can remove this array list if we want. the hashmap should do what we want but better
    private ArrayList<UserProfile> userProfileList = new ArrayList<UserProfile>();
    private HashMap<String, UserProfile> userProfileStringHashMap = new HashMap<String, UserProfile>();
    public HashMap<String, UserProfile> getUserProfileStringHashMap(){return userProfileStringHashMap;}
    public void addUserProfile(UserProfile userProfile){
        //we can remove the array list if we want.
        userProfileList.add(userProfile);
        userProfileStringHashMap.put(userProfile.getId(),userProfile);
    }
    //This (start) is where the application goes first
    @Override
    public void start(Stage primaryStage) throws Exception{
        loginScreen = primaryStage;

        //a method that initializes the layout for the primary stage
        initRootLayout(loginScreen);
        primaryStage.setTitle("Okay Potato");
        primaryStage.show();
    }
    private GridPane rootLayout;

    //here we initialize the layout
    private void initRootLayout(Stage stage){
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/loginForm.fxml"));
            rootLayout = loader.load();
            // Give the controller access to the main app.
            //LoginController is the controller for loginForm.fxml
            //which is the "main" form
            LoginController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Set the Main App title
        loginScreen.setTitle("Okay Potato");

        // Show the scene containing the root layout.
        Scene scene = new Scene(rootLayout);
        loginScreen.setScene(scene);
        loginScreen.show();
    }

    public void setWindow(FXMLLoader input) {
        try {
            GridPane page = input.load();
            //create the scene
            Scene scene = new Scene(page);
            //set the scene
            loginScreen.setScene(scene);
            //show the stage
            loginScreen.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
