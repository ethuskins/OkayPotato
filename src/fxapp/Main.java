package fxapp;

import controller.TitleScreenController;
import controller.MapController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The Main class. Contains all User Profile information and current user information..
 */
public class Main extends Application {
    //Set the name of the first window the user sees
    private Stage loginScreen;
    private final MapController controller;

    public Main() {
        controller = null;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        loginScreen = primaryStage;

        //a method that initializes the layout for the primary stage
        initRootLayout();
        primaryStage.setTitle("Okay Potato");
        primaryStage.show();
    }
    private GridPane rootLayout;

    /**
     * Initializes the layout
     *
     */
    private void initRootLayout(){
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

    /**
     * Sets the window for the main application
     * @param input the input object used to load the page.
     */
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

    /**
     * The main method. launches the app
     * @param args the arguments of the main method
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * dummy method to simulate a callback from the map view
     */
    public void closeMapView() {
        if (controller != null) {
            controller.mapInitialized();
        }
    }
}
