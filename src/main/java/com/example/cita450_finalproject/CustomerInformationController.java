package com.example.cita450_finalproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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

    // Method to add payment methods to the payment method choice box
    private void setupPaymentMethods() {
        // Add payment options to payment method choice box
        choicePaymentMethod.getItems().add("VISA");
        choicePaymentMethod.getItems().add("CASH");
    }

    // Method to collect and insert customer data into the database
    @FXML
    private void collectCustomerInfo() throws SQLException {
        // Check if all input fields are valid
        if (hasAllFieldsFilled()) {
            // Insert customer information into database
            customer.insertNewCustomer(
                    textFName.getText(),
                    textLName.getText(),
                    textPhoneNumber.getText(),
                    textEmail.getText(),
                    choicePaymentMethod.getSelectionModel().getSelectedItem().toString(),
                    Integer.parseInt(roomID));

                // Show check in confirmation box
                // Create new alert object of type confirmation
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                // Set text to show customer is checked into room
                alert.setHeaderText("Checked Customer into room " + roomID);
                // Display the alert
                alert.show();

                // Close customer-information.fxml window
                closeWindow();
                // Return to exit method
                return;
        }

        // Create new alert object of type error
        Alert alert = new Alert(Alert.AlertType.ERROR);
        // Set text to show customer is checked into room
        alert.setHeaderText("Missing or improper customer information!");
        // Display the alert
        alert.show();
}

    // Method to check if all fields are filled and valid inputs
    private boolean hasAllFieldsFilled() {
        return textFName.getText() != null
                && textLName.getText() != null
                && isValidPhoneNumber(textPhoneNumber.getText())
                && isValidEmail(textEmail.getText())
                && choicePaymentMethod.getSelectionModel().getSelectedItem() != null;
    }

    // Method to validate email input
    private boolean isValidEmail(String email) {
        // Check if passed in email is valid using regular expressions
        return Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email);
    }

    // Method to validate email input
    private boolean isValidPhoneNumber(String number) {
        // Check if passed in phone number is valid using regular expressions
        return Pattern.matches("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.\\-]\\d{3}[\\s.\\-]\\d{4}$", number);
    }

    // Method to cancel checking in a customer
    @FXML
    private void cancel() throws SQLException {
        // Cancel adding customer
        // TODO: This function is calling checkout on the Room class which means the room will be marked dirty. Dashboard is also not updated.
        customer.cancel(Integer.parseInt(roomID));

        // Close that bad bitch
        closeWindow();
    }

    // Method to close the current window
    private void closeWindow() {
        // get a handle to the stage
        Stage stage = (Stage) btnInsert.getScene().getWindow();
        // DESTROY THE CHILD, CORRUPT THEM ALL
        stage.close();
    }

    // Method to set the class' roomID variable
    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }
}
