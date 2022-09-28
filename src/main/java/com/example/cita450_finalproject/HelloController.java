package com.example.cita450_finalproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
// Replace with only necessary imports
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private ListView listRooms;

    private DatabaseConnection db;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        test();
    }
    @FXML
    protected void test()
    {
        try {
            // Create new connection object
            db = new DatabaseConnection();

            // Execute query on object
            ResultSet rs = db.selectQuery("SELECT * FROM rooms");

            // Retrieve result set metadata (column names and such)
            ResultSetMetaData rsMetaData = rs.getMetaData();

            // Loop through and print column names
            for(int i = 1; i <= rsMetaData.getColumnCount(); i++) {
                System.out.println(rsMetaData.getColumnName(i));
            }

            // While there is something to read from result set
            while (rs.next()) {
                listRooms.getItems().add(
                        rs.getInt(1) + " " +
                        rs.getInt(2) + " " +
                        rs.getString(3) + " " +
                        rs.getString(4) + " " +
                        rs.getBoolean(5) + " " +
                        rs.getBoolean(6) + " " +
                        rs.getInt(7) + " " +
                        rs.getFloat(8) + " " +
                        rs.getBoolean(9)
                );
//                System.out.println(
//                        rs.getInt(1) + " " +
//                                rs.getInt(2) + " " +
//                                rs.getString(3) + " " +
//                                rs.getString(4) + " " +
//                                rs.getBoolean(5) + " " +
//                                rs.getBoolean(6) + " " +
//                                rs.getInt(7) + " " +
//                                rs.getFloat(8) + " " +
//                                rs.getBoolean(9)
//                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    protected void checkIn() throws SQLException {
        if(listRooms.getSelectionModel().getSelectedIndex() == -1) { return; }

        String selectedItem = listRooms.getSelectionModel().getSelectedItem().toString();

        // TODO: Strings are being parsed as their byte value not string value iunno something like that
        int roomID = selectedItem.charAt(0) + selectedItem.charAt(1) + selectedItem.charAt(2);
        ResultSet rs = db.selectQuery("SELECT is_available FROM rooms WHERE room_id = " + String.valueOf(roomID));

        // While there are results from the query
        while (rs.next()) {
            // Check if returned value is true
            if(rs.getBoolean(1)) {
                // Update Availibilty to false
                db.updateAvailability(roomID, false);
                System.out.println("Checked customer in");
            }
        }
    }

    @FXML
    protected void checkOut() {
        try {
            if(listRooms.getSelectionModel().getSelectedIndex() == -1) { return; }

            String selectedItem = listRooms.getSelectionModel().getSelectedItem().toString();

            // TODO: Find a better way to get room number
            int roomID = selectedItem.charAt(0) + selectedItem.charAt(1) + selectedItem.charAt(2);
            ResultSet rs = db.selectQuery("SELECT is_available FROM rooms WHERE room_id = " + String.valueOf(roomID));

            // While there are results from the query
            while (rs.next()) {
                // Check if returned value is true
                if(!rs.getBoolean(1)) {
                    // Update Availability to true
                    db.updateAvailability(roomID, true);
                    System.out.println("Checked customer out");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}