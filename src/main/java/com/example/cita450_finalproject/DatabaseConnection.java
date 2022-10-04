package com.example.cita450_finalproject;
import  java.sql.*;

public class DatabaseConnection {
    Connection con;
    public DatabaseConnection() {
        connect();
    }

    private void connect() {
        try {
            // Create connection to database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "password");
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    public ResultSet selectQuery(String query) {
        try {
            // Create query statement
            Statement stmt = con.createStatement();
            // Execute and return query results
            return stmt.executeQuery(query);
        } catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void updateAvailability(int roomID, boolean isAvailable) {
        try {
            // Prepare update statement
            PreparedStatement updateStatement = con.prepareStatement("UPDATE rooms SET is_available = ? WHERE room_id = ?");

            // Set update values (Replaces the '?' with values bellow)
            updateStatement.setBoolean(1, isAvailable);
            updateStatement.setInt(2, roomID);

            // Execute update statement
            updateStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertCustomer(String customerFName, String customerLName, String customerPhone, String customerEmail, int customerBillingAddressID, String customerPaymentMethod) throws SQLException {
        // The mysql insert statement
        String query = " INSERT INTO customers (customer_fname, customer_lname, customer_phone, customer_email, customer_billing_address_id, customer_payment_method)"
                + " values (?, ?, ?, ?, ?, ?)";

        // Create the mysql insert prepared statement
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString(1, customerFName);
        preparedStmt.setString(2, customerLName);
        preparedStmt.setString(3, customerPhone);
        preparedStmt.setString(4, customerEmail);
        preparedStmt.setInt(5, customerBillingAddressID);
        preparedStmt.setString(6, customerPaymentMethod);

        // Execute the prepared statement
        preparedStmt.execute();
    }
}
