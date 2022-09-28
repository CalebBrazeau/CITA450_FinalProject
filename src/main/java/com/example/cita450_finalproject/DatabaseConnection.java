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
            Statement stmt = con.createStatement();
            return stmt.executeQuery(query); // Return query results
        } catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void updateAvailability(int roomID, boolean isAvailable) {
        try {
            PreparedStatement pstmt = con.prepareStatement("UPDATE rooms SET is_available = ? WHERE room_id = ?");

            pstmt.setBoolean(1, isAvailable);
            pstmt.setInt(2, roomID);

            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
