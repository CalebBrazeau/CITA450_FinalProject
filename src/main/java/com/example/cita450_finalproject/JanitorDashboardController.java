package com.example.cita450_finalproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class JanitorDashboardController implements Initializable{
    //variables
    public ListView RoomList;  //this is the table that will show up on the screen.
    public Room rooms;          //the rooms and the functions to show their infomation

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            //create rooms to search through
            rooms = new Room();
            displayRooms();
        }//end try X_X
       catch (Exception e)
       {
            e.printStackTrace();
        }//end catch
    }//end method

    private void displayRooms() throws SQLException
    {


        //for finding which rooms to pull up
        String searchCondition = "Clean";
        String searched = "False";

        //RoomList.getItems().add(String.format("%-7s | %-7s ", "Room #",  "Is Clean"));

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


}//end class
