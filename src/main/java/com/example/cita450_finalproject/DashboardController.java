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
            room = new Room();
        } catch (Exception e) {
            e.printStackTrace();
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
