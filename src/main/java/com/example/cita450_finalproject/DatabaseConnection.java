package com.example.cita450_finalproject;
import java.io.*;
import  java.sql.*;
import java.util.Scanner;

public class DatabaseConnection {
    Connection con;
    public DatabaseConnection() {
        connect();
    }

    private void connect() {
        try {
            // Get mysql password from creds.txt
            String mysqlPassword = readPassword();
            // Create connection to database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", mysqlPassword);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    // Probably not the best way, but it works
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
            System.out.println("Uh Oh.");
            e.printStackTrace();
        }
        return "";
    }

    public ResultSet selectQuery(String query) throws SQLException {
        // Create query statement
        Statement stmt = con.createStatement();

        // Execute and return query results
        return stmt.executeQuery(query);
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

    public void updateCustomerID(int roomID, int customerID)
    {
        try {
            // Prepare update statement
            PreparedStatement updateStatement = con.prepareStatement("UPDATE rooms SET customer_id = ? WHERE room_id = ?");

            // Set update values (Replaces the '?' with values bellow)
            updateStatement.setInt(1, customerID);
            updateStatement.setInt(2, roomID);

            // Execute update statement
            updateStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void unassignCustomerFromRoom(int roomID) {
        try {
            // Prepare update statement
            PreparedStatement updateStatement = con.prepareStatement("UPDATE rooms SET customer_id = ? WHERE room_id = ?");

            // Set update values (Replaces the '?' with values bellow)
            updateStatement.setNull(1, 0); // NULL
            updateStatement.setInt(2, roomID);

            // Execute update statement
            updateStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /* TODO: Move to Customer class */

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

    public void updateRoomClean(int int_roomID, boolean bol_clean) {
        try {
            // Prepare update statement
            PreparedStatement updateStatement = con.prepareStatement("UPDATE rooms SET is_clean = ? WHERE room_id = ?");

            // Set update values (Replaces the '?' with values bellow)
            updateStatement.setBoolean(1, bol_clean);  //Hey pal look Chino and Caleb is disappointed that you didn't know
            updateStatement.setInt(2, int_roomID); //how to do this is part and all the parts SMH, SMWD.
                                                                //P.S. JUST PRESS 1 PAL PLZ FOR ME CHINO CALEB LOVE BRO!
            // Execute update statement
            updateStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
