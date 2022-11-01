package com.example.cita450_finalproject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {
    DatabaseConnection dbConnection;
    Room room;

    public Customer() {
        try {
            dbConnection = new DatabaseConnection();
            room = new Room();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet getCustomerInfo(int int_customerID) throws SQLException {
        String str_query = "SELECT * FROM customers WHERE customer_id = " + int_customerID;

        return dbConnection.selectQuery(str_query);
    }

    public ResultSet getCustomerInfo(String customerFName, String customerLName) throws SQLException {
        // Query to check if a customer exists (Can match more cases)
        String query = "SELECT * FROM customers WHERE customer_fname = '" + customerFName +
                "' AND customer_lname = '"  + customerLName + "'";

        // Return query results
        return dbConnection.selectQuery(query);
    }

    public int getCustomerID(String customerFName, String customerLName, String customerPhone, String customerEmail, String customerPaymentMethod) throws SQLException {
        ResultSet rs = dbConnection.selectQuery("SELECT customer_id FROM customers WHERE customer_fname = '" + customerFName +
                "' AND customer_lname = '" + customerLName +
                "' AND customer_phone = '" + customerPhone +
                "' AND customer_email = '" + customerEmail +
                "' AND customer_payment_method = '" + customerPaymentMethod + "'");
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public void insertNewCustomer(String customerFName, String customerLName, String customerPhone, String customerEmail, String customerPaymentMethod, int roomID) throws SQLException {
        // Check if customer exists by attempting to get the customer info
        ResultSet customerInfo = getCustomerInfo(customerFName, customerLName);
        // If customer info is not null a customer with that first and last name already exists
        if (customerInfo.isBeforeFirst()) {
            // Print all matching customer info
            while (customerInfo.next()) {
                System.out.println(customerInfo.getInt(1));
                System.out.println(customerInfo.getString(2));
                System.out.println(customerInfo.getString(3));
                System.out.println(customerInfo.getString(4));
                System.out.println(customerInfo.getString(5));
                System.out.println(customerInfo.getInt(6));
                System.out.println(customerInfo.getString(7));
            }
            return; // Exit function so another entry is not added to database
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

        // Assign Customer to Room
        dbConnection.updateCustomerID(roomID, getCustomerID(customerFName, customerLName, customerPhone, customerEmail, customerPaymentMethod));
    }
}