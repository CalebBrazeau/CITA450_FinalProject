package com.example.cita450_finalproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import java.io.IOException;
public class MaintnessORJanitorSelectController
{
    public Button backButton;
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
    @FXML
    private void LoadPrevoiusScreen()
    {
        System.out.print("Got to LoadPrevoiusScreen Method");
        closeWindow();
        try
        {   //load the employee select screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserSelect1.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("User Select");
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }//end catch
    }
    @FXML
    private void closeWindow() {
        // get a handle to the stage
        Stage stage = (Stage) backButton.getScene().getWindow();
        // DESTROY THE CHILD, CORRUPT THEM ALL
        stage.close();
    }
}//end class

