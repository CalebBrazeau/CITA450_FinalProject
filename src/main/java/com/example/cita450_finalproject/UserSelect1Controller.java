package com.example.cita450_finalproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.fxml.FXML;

public class UserSelect1Controller
{
   @FXML
    private void SelectStaff()
    {
        //try
        try {
            //load the login screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffLoginScreenMain.fxml"));
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

}//end class

