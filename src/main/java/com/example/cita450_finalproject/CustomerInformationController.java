package com.example.cita450_finalproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerInformationController implements Initializable {

    public TextField textFName;
    public TextField textLName;
    public TextField textPhoneNumber;
    public TextField textEmail;
    public ChoiceBox choicePaymentMethod;
    public Button btnInsert;

    Customer customer;
    String roomID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            customer = new Customer();
            setupPaymentMethods();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void setupPaymentMethods() {
        choicePaymentMethod.getItems().add("VISA");
        choicePaymentMethod.getItems().add("CASH");
    }

    @FXML
    private void collectCustomerInfo() throws SQLException {
        customer.insertNewCustomer(
                textFName.getText(),
                textLName.getText(),
                textPhoneNumber.getText(),
                textEmail.getText(),
                choicePaymentMethod.getSelectionModel().getSelectedItem().toString(),
                Integer.parseInt(roomID)
        );

        // Show check in confirmation box
        // Create new alert object of type confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        // Set text to show customer is checked into room
        alert.setHeaderText("Checked in Customer to room " + roomID);
        // Display the alert
        alert.show();

        // Close customer-information.fxml window
        closeWindow();
    }

    private void closeWindow() {
        // get a handle to the stage
        Stage stage = (Stage) btnInsert.getScene().getWindow();
        // DESTROY THE CHILD, CORRUPT THEM ALL
        stage.close();
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }
}
