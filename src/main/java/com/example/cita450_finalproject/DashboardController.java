package com.example.cita450_finalproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public ListView listRooms;      //visable lsit of rooms
    public ChoiceBox choiceSearchBy; //where you select what you want to search by
    public TextField searchTextBox; //what you are searching
    private Room room;              //room information
    @FXML
    private Button backButton;      //button selected to go back a screen

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // Create Room Object
            room = new Room();

            setupSearchBy();
            displayRoomInfo();
        } catch (Exception e) {
                e.printStackTrace();
        }
    }//end method

    //METHOD that lists available search functions

    private void setupSearchBy() {
        // Add search keys to search by choice box
        choiceSearchBy.getItems().add("Room Number");
        choiceSearchBy.getItems().add("Customer ID");
        choiceSearchBy.getItems().add("Floor Number");
        choiceSearchBy.getItems().add("Handicap Accessible");
        choiceSearchBy.getItems().add("Number of Beds");
        choiceSearchBy.getItems().add("Available");
    }//end method

    //method to display room information

    @FXML
    private void displayRoomInfo() throws SQLException {
        // Clear rooms list
        listRooms.getItems().clear();

        // Add formatted column headers to list of rooms
        listRooms.getItems().add(String.format("%-7s | %-7s | %-9s | %-11s | %-11s | %-11s | %-11s | %-11s | %-7s", "Room #", "# Beds", "Bed Size", "Bed Size", "Handicap?", "Bathtub?", "Price", "Available?", "Clean?"));

        // Key to search by in database, default empty string to get all room info
        String searchKey = "";
        String searched;

        // Check if there is a search key selected
        if (choiceSearchBy.getSelectionModel().getSelectedItem() != null) {
            // Set search key to selected items string value
            searchKey = choiceSearchBy.getSelectionModel().getSelectedItem().toString();
        }

        //check if anything was searched
        searched = searchTextBox.getText();

        if(searched != null) {
            // Pull room information
            ResultSet roomInfo = room.pullInformation(searchKey, searched);

            // While there are results from the select query
            while (roomInfo.next()) {
                // Add results to listed rooms table
                listRooms.getItems().add(
                        String.format("%-7d | ", roomInfo.getInt(1)) + // Room ID
                                String.format("%-7d | ", roomInfo.getInt(2)) + // Number of Beds
                                String.format("%-9s | ", roomInfo.getString(3)) + // Bed 1 Size
                                String.format("%-11s | ", roomInfo.getString(4)) + // Bed 2 Size
                                String.format("%-11b | ", roomInfo.getBoolean(5)) + // Is Handicap Accessible
                                String.format("%-11b | ", roomInfo.getBoolean(6)) + // Has Bathtub
                                // String.format("%-11d | ", roomInfo.getInt(7)) + // Room Service ID
                                String.format("%-11d | ", roomInfo.getInt(8)) + // Cost Per Night
                                String.format("%-11b | ", roomInfo.getBoolean(9)) + // Is Available
                                String.format("%-7b", roomInfo.getBoolean(10)) // Is Clean
                                // String.format("%-7b", roomInfo.getBoolean(11)) // Customer ID
                );//end the add
            }//end while
        }//end if
    }//end method

    //method to check a customer into a room

    @FXML
    private void checkIn() throws SQLException {
        // If nothing is selected or first item is selected show error and return.
        if (listRooms.getSelectionModel().getSelectedItem() == null || listRooms.getSelectionModel().getSelectedIndex() < 1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Please select a room");
            alert.show();

            return;
        }

        // Get selected room from list of rooms
        String selectedItem = listRooms.getSelectionModel().getSelectedItem().toString();

        // Append first three characters of selected item to a string (First three will be the room ID)
        StringBuilder roomID = new StringBuilder();
        roomID.append(selectedItem.charAt(0));
        roomID.append(selectedItem.charAt(1));
        roomID.append(selectedItem.charAt(2));

        // Attempt check in
        room.checkIn(Integer.parseInt(String.valueOf(roomID)));

        // Update rooms list
        displayRoomInfo();
        loadNewCustomerForm(String.valueOf(roomID));
    }//end method

    //method called to check someone out of a room
    @FXML
    private void checkOut() throws SQLException {
        // If nothing is selected or first item is selected show error and return.
        if (listRooms.getSelectionModel().getSelectedItem() == null || listRooms.getSelectionModel().getSelectedIndex() < 1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Please select a room");
            alert.show();

            return;
        }

        // Get selected room from list of rooms
        String selectedItem = listRooms.getSelectionModel().getSelectedItem().toString();

        // Append first three characters of selected item to a string (First three will be the room ID)
        StringBuilder roomID = new StringBuilder();
        roomID.append(selectedItem.charAt(0));
        roomID.append(selectedItem.charAt(1));
        roomID.append(selectedItem.charAt(2));
        
        // Attempt check out
        room.checkOut(Integer.parseInt(String.valueOf(roomID)));

        // Update rooms list
        displayRoomInfo();
    }//end method

    //note ^^^^ should check if the room is checked in to avoid bug

    //method to load up a new screen for adding a customer
    private void loadNewCustomerForm(String roomID) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("customer-information.fxml"));
            Parent root = loader.load();

            CustomerInformationController customerController = loader.getController();
            customerController.setRoomID(roomID);

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Enter Customer Information");
            stage.show();
        }//end try
        catch (IOException e) {
            e.printStackTrace();
        }//end catch
    }//end method

    //method to load the last screen
    @FXML
    private void LoadPrevoiusScreen()
    {
       //close the window
        closeWindow();
        try
        {   //load the employee select screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserSelect1.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Staff Login");
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }//end catch
    }//end method

    //method to close a window
    @FXML
    private void closeWindow() {
        // get a handle to the stage
        Stage stage = (Stage) backButton.getScene().getWindow();
        // DESTROY THE CHILD, CORRUPT THEM ALL
        stage.close();
    }//end method
}//end class
