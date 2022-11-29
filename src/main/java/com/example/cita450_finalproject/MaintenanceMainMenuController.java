package com.example.cita450_finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import java.io.IOException;
public class MaintenanceMainMenuController {

    public Button btnBack;

    @FXML
    public void SelectRequest() {
        try {
            //load the employee select screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Add_Request_main.fxml"));
            Parent root = loader.load();

            //create the scene and add a style sheet
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());

            //stage the scene
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Add Maintenance Request");
            stage.show();
        }//end try
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadPreviousScreen() {
        try {
            // Close the window
            closeWindow();

            //load the employee select screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserSelect1.fxml"));
            Parent root = loader.load();

            //create the scene and add a style sheet
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());

            //stage the scene
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Add Maintenance Request");
            stage.show();
        }//end try
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void closeWindow() {
        // get a handle to the stage
        Stage stage = (Stage) btnBack.getScene().getWindow();
        // DESTROY THE CHILD, CORRUPT THEM ALL
        stage.close();
    }
}
