package com.example.cita450_finalproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public ListView listRooms;

    private Room room;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
<<<<<<< HEAD
            room = new Room();
        } catch (Exception e) {
            e.printStackTrace();
=======
            getRoomInfo();
            insertNewCustomer();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // TODO: Probably a better name for this method
    private void getRoomInfo() throws SQLException {
        // Clear list box before adding more things
        listRooms.getItems().clear();

        // Execute query on object
        ResultSet rs = db.selectQuery("SELECT * FROM rooms");

        // Retrieve result set metadata (column names and such)
        //ResultSetMetaData rsMetaData = rs.getMetaData();

        // Loop through and print column names
        //for(int i = 1; i <= rsMetaData.getColumnCount(); i++) {
        //    System.out.println(rsMetaData.getColumnName(i));
        //}

        // While there is something to read from result set
        while (rs.next()) {
            listRooms.getItems().add(
                    rs.getInt(1) + " " +            //RoomID
                            rs.getInt(2) + " " +    //Number of beds
                            rs.getString(3) + " " + //
                            rs.getString(4) + " " +
                            rs.getBoolean(5) + " " +
                            rs.getBoolean(6) + " " +
                            rs.getInt(7) + " " +
                            rs.getFloat(8) + " " +
                            rs.getBoolean(9) + "" +
                            rs.getBoolean(10)
            );
>>>>>>> 518da8a5763f155106ad38b16e25dd4daf8d4285
        }
    }

    private void displayRoomInfo() {

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

    private void search() {

    }
}
