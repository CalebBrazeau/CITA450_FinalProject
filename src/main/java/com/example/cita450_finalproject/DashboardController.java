package com.example.cita450_finalproject;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public ListView listRooms;
    public ChoiceBox choiceSearchBy;

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
        choiceSearchBy.getItems().add("Customer Name");
    }

    private void displayRoomInfo() throws SQLException {
        // Clear rooms list
        listRooms.getItems().clear();

        // Key to search by in database, default empty string to get all room info
        String searchKey = "";

        // Check if there is a search key selected
        if (choiceSearchBy.getSelectionModel().getSelectedItem() != null) {
            // Set search key to selected items string value
            searchKey = choiceSearchBy.getSelectionModel().getSelectedItem().toString();
        }

        // Pull room information
        ResultSet roomInfo = room.pullInformation(searchKey);

        // While there are results from the select query
        while (roomInfo.next()) {
            // Add results to listed rooms table
            listRooms.getItems().add(
                    roomInfo.getInt(1) + " " +
                    roomInfo.getInt(2) + " " +
                    roomInfo.getString(3) + " " +
                    roomInfo.getString(4) + " " +
                    roomInfo.getBoolean(5) + " " +
                    roomInfo.getBoolean(6) + " " +
                    roomInfo.getInt(7) + " " +
                    roomInfo.getInt(8) + " " +
                    roomInfo.getBoolean(9) + " " +
                    roomInfo.getBoolean(10)
            );
        }
    }
    private void loadNewCustomerForm() {
        Parent root;
        try {
            root = FXMLLoader.load(HelloApplication.class.getResource("customer-information.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Customer Information");
            stage.setScene(new Scene(root, 600, 400));
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
