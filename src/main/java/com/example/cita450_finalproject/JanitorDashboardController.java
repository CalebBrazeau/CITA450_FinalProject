package com.example.cita450_finalproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class JanitorDashboardController implements Initializable{
    //variables
    public ListView RoomList;  //this is the table that will show up on the screen.
    public Room rooms;          //the rooms and the functions to show their infomation

    public Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try {
            rooms = new Room();
            displayRooms();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//end method

    @FXML
    private void LoadPrevoiusScreen()
    {
        System.out.print("Got to LoadPrevoiusScreen Method");
        closeWindow();
        try
        {   //load the employee select screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffLoginScreenMain.fxml"));
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
    private void displayRooms() throws SQLException
    {
        //for finding which rooms to pull up
        String searchCondition = "Clean";
        String searched = "False";

        // Clear rooms list
        RoomList.getItems().clear();

        //show the rooms room number and clean
        RoomList.getItems().add(String.format("%-7s | %-7s ", "Room #",  "Is Clean"));
        ResultSet roomInfo = rooms.pullInformation(searchCondition, searched);

        //While there are results from the select query
        while (roomInfo.next())
        {
            // Add results to listed rooms table
            RoomList.getItems().add(
                    String.format("%-7d | ", roomInfo.getInt(1)) + // Room ID
                    String.format("%-7b", roomInfo.getBoolean(10))); // Is Clean
        }//end of WHILE

    }//end of method displayRooms

    //method when button clicked
    @FXML
    private void MarkingClean() throws SQLException { //look for selected item
        String selectedItem = RoomList.getSelectionModel().getSelectedItem().toString();

        // Append first three characters of selected item to a string (First three will be the room ID)
        StringBuilder roomID = new StringBuilder();
        roomID.append(selectedItem.charAt(0));
        roomID.append(selectedItem.charAt(1));
        roomID.append(selectedItem.charAt(2));

        // Attempt mark clean
        rooms.UpdateRoomClean(Integer.parseInt(String.valueOf(roomID)));

        // Update rooms list
        displayRooms();
    }//endMethod

    @FXML
    private void closeWindow() {
        // get a handle to the stage
        Stage stage = (Stage) backButton.getScene().getWindow();
        // DESTROY THE CHILD, CORRUPT THEM ALL
        stage.close();
    }
}//end class
