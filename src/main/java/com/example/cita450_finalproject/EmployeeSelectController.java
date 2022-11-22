package com.example.cita450_finalproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.fxml.FXML;
public class EmployeeSelectController
{
    public Button backButton;
    @FXML
    private void SelectFrontDesk()
    {
        //try
        try {
            //load the employee select screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            Parent root = loader.load();

            //create the scene and add a style sheet
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());

            //stage the scene
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("FrontDeskView");
            stage.show();
        }//end try
        catch (IOException e)
        {
            e.printStackTrace();
        }//end catch
    }//end method
    @FXML
    private void SelectMaintClean()
    {
        //try
        try {
            //load the employee select screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MaintenanceOrJanitorSelect.fxml"));
            Parent root = loader.load();

            //create the scene and add a style sheet
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());

            //stage the scene
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Select User");
            stage.show();
        }//end try
        catch (IOException e)
        {
            e.printStackTrace();
        }//end catch
    }//end method
    @FXML
    private void closeWindow() {
        // get a handle to the stage
        Stage stage = (Stage) backButton.getScene().getWindow();
        // DESTROY THE CHILD, CORRUPT THEM ALL
        stage.close();
    }
}
