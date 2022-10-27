package com.example.cita450_finalproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public ListView listRooms;
    public ChoiceBox choiceSearchBy;
    public TextField searchTextBox;

    private Room room;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            room = new Room();

            setupSearchBy();
            displayRoomInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupSearchBy() {
        choiceSearchBy.getItems().add("Room Number");
        choiceSearchBy.getItems().add("Customer ID");
        choiceSearchBy.getItems().add("Floor Number");
        choiceSearchBy.getItems().add("Handicap Accessible");
        choiceSearchBy.getItems().add("Number of Beds");
        choiceSearchBy.getItems().add("Available");
    }

    @FXML
    private void displayRoomInfo() throws SQLException {
        // Clear rooms list
        listRooms.getItems().clear();

        listRooms.getItems().add(String.format("%-7s | %-7s | %-9s | %-11s | %-11s | %-11s | %-11s | %-11s | %-7s", "Room #", "# Beds", "Bed Size", "Bed Size", "Handicap?", "Bathtub?", "Price", "Available?", "Clean?"));
//        System.out.printf("%-11s |  %-11s |  %-11s |  %-11s |  %-11s |  %-11s |  %-11s |  %-11s | ", "Room #", "# Beds", "Bed Size", "Bed Size", "Handicap?", "Bathtub?", "Price", "Available?");

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

        if( searched != null) {
            // Pull room information
            ResultSet roomInfo = room.pullInformation(searchKey, searched);

            // While there are results from the select query
            while (roomInfo.next()) {
                // Add results to listed rooms table
                listRooms.getItems().add(
                        String.format("%-7d | ", roomInfo.getInt(1)) +
                        String.format("%-7d | ", roomInfo.getInt(2)) +
                        String.format("%-9s | ", roomInfo.getString(3)) +
                        String.format("%-11s | ", roomInfo.getString(4)) +
                        String.format("%-11b | ", roomInfo.getBoolean(5)) +
                        String.format("%-11b | ", roomInfo.getBoolean(6)) +
//                        String.format("%-11d | ", roomInfo.getInt(7)) +
                        String.format("%-11d | ", roomInfo.getInt(8)) +
                        String.format("%-11b | ", roomInfo.getBoolean(9)) +
                        String.format("%-7b", roomInfo.getBoolean(10))
                );
            }
        }
    }

    @FXML
    private void checkIn() throws SQLException {
        // If nothing is selected return
        if (listRooms.getSelectionModel().getSelectedItem() == null) { return; }

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
    }

    @FXML
    private void checkOut() throws SQLException {
        // If nothing is selected return
        if (listRooms.getSelectionModel().getSelectedItem() == null) { return; }

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
    }

    private void loadNewCustomerForm(String roomID) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("customer-information.fxml"));
            Parent root = loader.load();

            CustomerInformationController customerController = loader.getController();
            customerController.setRoomID(roomID);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Enter Customer Information");
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void search()
    {
        //variabels
        //Drop Down Menu Choices/Search Conditions
        /*
        "Room Number"
        "Customer ID"
        "Floor"
        "Handicap Accessible"
        "Number of Beds"
        */
        String str_Searched; //what the user typed into the text box
    }
}
