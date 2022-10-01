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

    public void insertCustomer(String customerFName, String customerLName, String customerPhone, String customerEmail, int customerBillingAddressID, String customerPaymentMethod) {
        

    }
}
