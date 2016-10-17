package fxapp;

import controller.TitleScreenController;
import controller.MapController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Facade;
import model.UserProfile;
import model.WaterSourceReport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Main class. Contains all User Profile information and current user information..
 */
public class Main extends Application {
    //Set the name of the first window the user sees
    private Stage loginScreen;
    MapController controller;


    //This is used to increment the report number when a report is generated
    public Integer reportnumber = 1;
    //we can remove this array list if we want. the hashmap should do what we want but better
    private ArrayList<UserProfile> userProfileList = new ArrayList<UserProfile>();
    private HashMap<String, UserProfile> userProfileStringHashMap = new HashMap<String, UserProfile>();
    private UserProfile currentUser;
    public HashMap<String, UserProfile> getUserProfileStringHashMap(){return userProfileStringHashMap;}

    //has a hashmap that stores the water source reports
    private HashMap<Integer, WaterSourceReport> waterSourceReportHashMap = new HashMap<Integer, WaterSourceReport>();
    public HashMap<Integer, WaterSourceReport> getWaterSourceReportHashMap() {return waterSourceReportHashMap;}


    public void addUserProfile(UserProfile userProfile){
        //we can remove the array list if we want.
        userProfileList.add(userProfile);
        userProfileStringHashMap.put(userProfile.getId(),userProfile);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        loginScreen = primaryStage;

        //a method that initializes the layout for the primary stage
        initRootLayout(loginScreen);
        primaryStage.setTitle("Okay Potato");
        primaryStage.show();
    }
    private GridPane rootLayout;

    /**
     * Initializes the layout
     * @param stage the stage of the App
     */
    private void initRootLayout(Stage stage){
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/titleScreenForm.fxml"));
            rootLayout = loader.load();
            // Give the controller access to the main app.
            //TitleScreenController is the controller for titleScreenForm.fxml
            //which is the "main" form
            TitleScreenController controller = loader.getController();
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

    public UserProfile getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserProfile currentUser) {
        this.currentUser = currentUser;
    }
    /**
     * The main method. launches the app
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * dummy method to simulate a callback from the map view
     */
    public void closeMapView() {
        Facade fc = Facade.getInstance();
        fc.addLocations();
        controller.mapInitialized();
        //mainStage.setScene(mapScene);
    }
}
