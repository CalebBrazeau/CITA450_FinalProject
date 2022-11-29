package com.example.cita450_finalproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXML;

public class UserSelect1Controller
{
    public Button btn_staff;    //this is a button that you press to indicate you are staff

    //METHOD this method allows the user to select that they are staff and view the appropriate screen
   @FXML
    private void SelectStaff()
    {
        //try
        try {
            //close the current window for smooth transaction
            closeWindow();
            //load the login screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffLoginScreenMain.fxml"));
            Parent root = loader.load();

            //create the scene and add a style sheet
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());

            //stage the scene
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Staff Login");
            stage.show();

        }//end try
        catch (IOException e)
        {
            e.printStackTrace();
        }//end catch
    }//end method

    //METHOD to close the current window
    @FXML
    private void closeWindow() {
        // get a handle to the stage
        Stage stage = (Stage) btn_staff.getScene().getWindow();
        // DESTROY THE STAGE
        stage.close();
    }//end method
}//end class

