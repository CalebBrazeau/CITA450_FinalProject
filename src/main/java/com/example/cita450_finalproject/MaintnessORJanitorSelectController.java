package com.example.cita450_finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import java.io.IOException;
public class MaintnessORJanitorSelectController
{
    @FXML
    private void SelectCleaning()
    {
        //try
        try {
            //load the employee select screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("janitor-dashboard.fxml"));
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

    public void SelectMaintenance(ActionEvent actionEvent)
    {
        try {
            //load the employee select screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MaintenanceDashboard.fxml"));
            Parent root = loader.load();

            //create the scene and add a style sheet
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());

            //stage the scene
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Maintenance Dashboard");   //<<<<<----Doesn't really matter what you name it
            stage.show();
        }//end try
        catch (IOException e)
        {
            e.printStackTrace();
        }//end catch

    }
}//end class

