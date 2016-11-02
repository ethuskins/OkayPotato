package controller;

import fxapp.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

/**
 * Created by Jack Winski on 11/2/2016.
 */
public class HistoricalReportController {

    private Main mainApplication;


    /**
     * Cancels the historical report request process and returns to the title screen.
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
     * Sets the main application. This is the object that carries info needed between screens.
     * @param main the object containing cross-screen info.
     */
    public void setMainApp(Main main) {
        mainApplication = main;
    }
}
