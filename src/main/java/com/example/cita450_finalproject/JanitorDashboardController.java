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
    public Room rooms;          //the rooms and the functions to show their information
    public Button backButton;   //this button returns to a previous screen

    //Method Initialize: loads the screen up with the information that it needs
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            //make a new instance of room information
            rooms = new Room();
            //display relevant information in a table on the screen
            displayRooms();
        } //end try
        catch (Exception e) {
            e.printStackTrace();
        }//end catch
    }//end method

    //METHOD to go back to the prevoius screen. This is called when the back button is selected
    @FXML
    private void LoadPrevoiusScreen()
    {
        //clost the window that is currently open for smooth transition
        closeWindow();
        try
        {   //load the employee select screen with the style sheet attached
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffLoginScreenMain.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Staff Login");  //name of the new tab
            stage.show();
        }//end try
        catch (IOException e)
        {
            e.printStackTrace();
        }//end catch
    }//end method

    //METHOD to display which rooms currently need to be cleaned.
    @FXML
    private void displayRooms() throws SQLException
    {
        //for finding which rooms to pull up
        String searchCondition = "Clean";   //searching for information in the clean category
        String searched = "False";          //looking for rooms that are not clean

        // Clear rooms list
        RoomList.getItems().clear();

        //show the rooms room number and clean status
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

    //method when mark clean button clicked. update that room to clean.
    @FXML
    private void MarkingClean() throws SQLException
    {
        //look for selected item
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

    //method used to close the current screen. this is used when transiting to a new screen.
    @FXML
    private void closeWindow()
    {
        // get a handle to the stage
        Stage stage = (Stage) backButton.getScene().getWindow();
        // DESTROY THE STAGE
        stage.close();
    }
}//end class
