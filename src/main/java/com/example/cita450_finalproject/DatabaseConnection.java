package com.example.cita450_finalproject;
import java.io.*;
import  java.sql.*;
import java.util.Scanner;

public class DatabaseConnection {
    Connection con;     //the connection to the database
    public DatabaseConnection() throws SQLException {
        //connect to the database
        connect();
    }//end method

    // Method to create a connection to the mysql database
    private void connect() throws SQLException {
        // Get mysql password from creds.txt
        String mysqlPassword = readPassword();
        // Create connection to database
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", mysqlPassword);
    }

    // Method to read and return the password from creds.txt
    private String readPassword() {
        try {
            // Open Credentials file
            File myObj = new File("src/creds.txt");
            Scanner myReader = new Scanner(myObj);

            // If there is a line to read
            if (myReader.hasNextLine()) {
                // Return credentials content
                return myReader.nextLine();
            }
            // Close reader
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }//end method

    // Method to execute a select query on the database
    public ResultSet selectQuery(String query) throws SQLException {
        // Create query statement
        Statement stmt = con.createStatement();

        // Execute and return query results
        return stmt.executeQuery(query);
    }//end method

    // Method to update the 'is_available' column of a specified room
    public void updateAvailability(int roomID, boolean isAvailable) throws SQLException {
        // Prepare update statement
        PreparedStatement updateStatement = con.prepareStatement("UPDATE rooms SET is_available = ? WHERE room_id = ?");

        // Set update values (Replaces the '?' with values bellow)
        updateStatement.setBoolean(1, isAvailable);
        updateStatement.setInt(2, roomID);

        // Execute update statement
        updateStatement.executeUpdate();
    }//end method

    // Method to set the customer_id field of a specified room
    public void updateCustomerID(int roomID, int customerID) throws SQLException {
        // Prepare update statement
        PreparedStatement updateStatement = con.prepareStatement("UPDATE rooms SET customer_id = ? WHERE room_id = ?");

        // Set update values (Replaces the '?' with values bellow)
        updateStatement.setInt(1, customerID);
        updateStatement.setInt(2, roomID);

        // Execute update statement
        updateStatement.executeUpdate();
    }//end method

    // Method to set the customer_id field of a specified room to null
    public void unassignCustomerFromRoom(int roomID) throws SQLException {
        // Prepare update statement
        PreparedStatement updateStatement = con.prepareStatement("UPDATE rooms SET customer_id = ? WHERE room_id = ?");

        // Set update values (Replaces the '?' with values bellow)
        updateStatement.setNull(1, 0); // NULL
        updateStatement.setInt(2, roomID);

        // Execute update statement
        updateStatement.executeUpdate();
    }//end method

    // Method to insert customer data into the customer table
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
    }//end method

    // Method to update the is_clean column of a specified room
    public void updateRoomClean(int int_roomID, boolean bol_clean) throws SQLException {
        // Prepare update statement
        PreparedStatement updateStatement = con.prepareStatement("UPDATE rooms SET is_clean = ? WHERE room_id = ?");

        // Set update values (Replaces the '?' with values bellow)
        updateStatement.setBoolean(1, bol_clean);
        updateStatement.setInt(2, int_roomID);

        // Execute update statement
        updateStatement.executeUpdate();
    }//end method
}//end class
