package fxapp;

import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.net.URL;

import java.io.IOException;

public class Main extends Application {
    //Set the name of the first window the user sees
    private Stage loginScreen;

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
            controller.setOwner(this);
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
            loginScreen.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
