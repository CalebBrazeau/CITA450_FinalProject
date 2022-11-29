package com.example.cita450_finalproject;

import javafx.scene.control.Alert;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Room
{
    //DATABASE
    DatabaseConnection dbConnection;    //connection to database

    //Method Instantiator
    public Room() throws SQLException {
        // establish connection to database
        dbConnection = new DatabaseConnection();
    }//end method

    //METHOD handle what is being shown when something is searched
    public ResultSet pullInformation(String str_SearchCondition, String str_Searched) throws SQLException {
        // If there is no search condition, return
        if (str_SearchCondition == null) { return null; }

        // Variable to store query results
        ResultSet resultSet;

        // Switch expression, new to me but IntelliJ suggested it ¯\_(ツ)_/¯
        String query = switch (str_SearchCondition) {
            //if the search condition is room number
            //pull up the rooms information that matches that room number
            case "Room Number" -> "SELECT * FROM rooms WHERE room_id = " + str_Searched+ ";";

            //if the search condition is customer ID
            //pull up any rooms that that customer is currently assigned to
            case "Customer ID" -> "SELECT * FROM rooms WHERE customer_id = " + str_Searched+ ";";

            //if the search condition is handicap accessible
            //pull up any rooms that are handicap accessible
            case "Handicap Accessible" -> "SELECT * FROM rooms WHERE is_handicap_accessible = true ";

           //if the search condition is floor number
            //pull up all rooms on that floor
            case "Floor Number" -> "SELECT * FROM rooms WHERE room_id LIKE '" + str_Searched + "%'";

            //if the search condition is available
            //pull up all rooms that have that are available
            case "Available" -> "SELECT * FROM rooms WHERE is_available IS true";

            //if the search condition is number of beds
            //pull up all rooms that have that number of beds
            case "Number of Beds" -> "SELECT * FROM rooms WHERE number_of_beds =" + str_Searched + ";";

            //specifically for the janitor screen
            case "Clean" -> "SELECT * FROM rooms WHERE is_clean IS false;";


            //nothing is selected
            default -> "SELECT * FROM rooms";
        };//end swtich
        // Run the query on the database object
        resultSet = dbConnection.selectQuery(query);

        // Return query results
        return resultSet;
    }//end method

    //METHOD Check in
    public void checkIn(int int_RoomID) throws SQLException {
        // Show error message and return if the room is not available
        if(!CheckAvailable(int_RoomID)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Uh Oh");
            alert.setHeaderText("Room is not available!");
            alert.show();
            return;
        }
        // Update room availability
        UpdateAvailable(int_RoomID);
    }//end method

    //METHOD Check out
    public void checkOut(int int_RoomID) throws SQLException {
        //if the room is available
        if (CheckAvailable(int_RoomID))
        {
            // Create new alert of type warning
            Alert alert = new Alert(Alert.AlertType.WARNING);
            // Set content text to explain error
            alert.setContentText("Room was not checked in!");
            // Display alert
            alert.show();

            return;
        }//end if

        //if the room is not aviable
        else
        {
        //unassign the customer from the room
        dbConnection.unassignCustomerFromRoom(int_RoomID);
        //mark the room as dirty
        UpdateRoomClean(int_RoomID);
        }
    }//end method

    //METHOD Update Room Availability
    private void UpdateAvailable(int int_RoomID) throws SQLException {
        boolean bol_isAvailable = CheckAvailable(int_RoomID); //room availability
        boolean bol_isClean = RoomClean(int_RoomID);       //room clean status

        //if room has been checked out and room is clean
        if(!bol_isAvailable & bol_isClean)
        {
            //change the variable to available
            bol_isAvailable = true;
            //make the room available by sending the variable
            dbConnection.updateAvailability(int_RoomID, bol_isAvailable);
        }//end if
        //if checking in
        else
        {
            //change the variable to unavailable
            bol_isAvailable = false;
            //make the room unavailable by sending the variable
            dbConnection.updateAvailability(int_RoomID, bol_isAvailable);
        }//end else
    }//end method

    //METHOD Check Room Availability
    private boolean CheckAvailable(int int_RoomID)
    {
        boolean bol_isAvailable; //true = room is available false = room is not
        boolean bol_default = false;

        try {
            String SQL_Query = "SELECT is_available FROM rooms WHERE room_id = " + int_RoomID; //sql statement
            ResultSet refinedSearch = dbConnection.selectQuery(SQL_Query);                     //pull availability from table

            // If there is a value returned from the query
            if (refinedSearch.next()) {
                //set availability based off of the refined search
                bol_isAvailable = refinedSearch.getBoolean(1);
                // Return availability
                return bol_isAvailable;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }//end catch

        //return default
        return  bol_default;
    }//end method


    //METHOD Update Room Clean
    public void UpdateRoomClean(int int_RoomID) throws SQLException {
        //variables
        boolean bol_clean = RoomClean(int_RoomID); // is the room clean
        boolean bol_isAvaibale = CheckAvailable((int_RoomID)); // is the room available
        //if janitor marks clean
        if(!bol_clean && !bol_isAvaibale) //if room not clean then it is dirty
        {
            //change the variable to room clean
            bol_clean = true; // janitor marks clean
            dbConnection.updateRoomClean(int_RoomID, bol_clean); //update room clean
            //make the room available
            UpdateAvailable(int_RoomID);
        }

        //if the room is clean then the system will mark as dirty
        // Customer leaves so the room is dirty and needs to be clean
        else
        {
            //change the variable to room clean
            bol_clean = false;      //Marks the room dirty which Janitors will come
            //make the room unavailable by sending the variable
            dbConnection.updateRoomClean(int_RoomID, bol_clean);
        }//end else
    }//end method

    //METHOD Check Room Is Clean
    private boolean RoomClean(int int_RoomID)
    {
        boolean bol_clean; //true = room is available
        boolean bol_default = false;    //false = room is not

        try {
            //variables
            String SQL_Query = "SELECT is_clean FROM rooms WHERE room_id = " + int_RoomID; //sql statement
            ResultSet refinedSearch = dbConnection.selectQuery(SQL_Query);                //pull room from table

            // If there is a value returned from the query
            if (refinedSearch.next()) {
                //set room clean based off of the refined search
                bol_clean = refinedSearch.getBoolean(1);

                // Return clean status
                return bol_clean;
            }//end if
        } //end try
        catch (Exception e)
        {
            e.printStackTrace();
        }//end catch
        //return the default
        return  bol_default;
    }//end method
}//end of Class "Room"
