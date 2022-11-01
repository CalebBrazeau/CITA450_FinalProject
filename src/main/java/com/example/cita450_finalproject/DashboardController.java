package com.example.cita450_finalproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Objects;
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
//                        String.format("%-11d | ", roomInfo.getInt(7)) + // Room Service ID
                                String.format("%-11d | ", roomInfo.getInt(8)) + // Cost Per Night
                                String.format("%-11b | ", roomInfo.getBoolean(9)) + // Is Available
                                String.format("%-7b", roomInfo.getBoolean(10)) // Is Clean
//                                    String.format("%-7b", roomInfo.getBoolean(11)) // Customer ID
                );
            }
        }
    }

    @FXML
    private void checkIn() throws SQLException {
        // If nothing is selected or first item is selected throw error and return.
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

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Enter Customer Information");
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
