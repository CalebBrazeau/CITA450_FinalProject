package com.example.cita450_finalproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerInformationController implements Initializable {

    public TextField textFName;
    public TextField textLName;
    public TextField textPhoneNumber;
    public TextField textEmail;
    public ChoiceBox choicePaymentMethod;

    private DatabaseConnection db;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            db = new DatabaseConnection();
            setupPaymentMethods();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    private void setupPaymentMethods() {
        choicePaymentMethod.getItems().add("VISA");
        choicePaymentMethod.getItems().add("CASH");
    }

    @FXML
    private void collectCustomerInfo() throws SQLException {
        /* TODO: Check if customer exists
            Assign them to a room
            If not, create new customer
            Assign them to a room
         */
        // Query to get the number of rows
        String query = "SELECT COUNT(customer_billing_address_ID) FROM customers";

        // Run query in db object
        ResultSet rs = db.selectQuery(query);

        // If there are results from the query
        if (rs.next()) {
            // Add one to returned results to get the next billing address ID
            int billingAddressID = rs.getInt(1) + 1;

            // Insert customer using db object
            db.insertCustomer(textFName.getText(), textLName.getText(), textPhoneNumber.getText(), textEmail.getText(), billingAddressID, choicePaymentMethod.getSelectionModel().getSelectedItem().toString());
        }
    }
}
