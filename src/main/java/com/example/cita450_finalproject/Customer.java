package com.example.cita450_finalproject;

import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {
    DatabaseConnection dbConnection;
    Room room;

    public Customer() {
        try {
            // Create new DatabaseConnection object
            dbConnection = new DatabaseConnection();
            // Create new Room object
            room = new Room();
        } catch (Exception e) {
            // Uh oh
            e.printStackTrace();
        }
    }

    // Method to get customers ID using full name, phone number, and email
    public int getCustomerID(String customerFName, String customerLName, String customerPhone, String customerEmail) throws SQLException {
        // Get and store customer ID from database
        ResultSet rs = dbConnection.selectQuery("SELECT customer_id FROM customers WHERE customer_fname = '" + customerFName +
                "' AND customer_lname = '" + customerLName +
                "' AND customer_phone = '" + customerPhone +
                "' AND customer_email = '" + customerEmail + "'");
        // Check if there is returned data
        if (rs.next()) {
            // Return customer ID
            return rs.getInt(1);
        }
        // Return 0 if no customer ID is found
        return 0;
    }

    public void insertNewCustomer(String customerFName, String customerLName, String customerPhone, String customerEmail, String customerPaymentMethod, int roomID) throws SQLException {
        // Check if customer exists by attempting to get the customer info
        int customerID = getCustomerID(customerFName, customerLName, customerPhone, customerEmail);

        // If returned customer ID is greater than zero, a customer with that information already exists.
        if (customerID > 0) {
            // Assign Customer to Room
            dbConnection.updateCustomerID(roomID, customerID);
            return;
        }

        // Get number of billing address to create a new unique ID
        String query = "SELECT COUNT(customer_billing_address_ID) FROM customers";

        // Run query in db object
        ResultSet rs = dbConnection.selectQuery(query);

        // If there are results from the query
        if (rs.next()) {
            // Add one to returned results to get the next billing address ID
            int billingAddressID = rs.getInt(1) + 1;

            // Insert customer using db object
            dbConnection.insertCustomer(
                    customerFName,
                    customerLName,
                    customerPhone,
                    customerEmail,
                    billingAddressID,
                    customerPaymentMethod
            );
        }

        // Get new customer ID
        int newCustomerID = getCustomerID(customerFName, customerLName, customerPhone, customerEmail);

        // If the customer ID is greater than 0 where was an ID pulled from the db
        if (newCustomerID > 0) {
            // Assign Customer to Room
            dbConnection.updateCustomerID(roomID, customerID);
        }

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Could not check in customer!");
        alert.show();
        // If Check in fails, call cancel method to make room available again
        cancel(roomID);
    }

    // Method to cancel checking a customer in
    public void cancel(int roomID) throws SQLException {
        room.checkOut(roomID);
    }
}
