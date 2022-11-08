package com.example.cita450_finalproject;
import javafx.scene.control.Alert;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Room
{
    /*
     * CLASS: Room
     * Author: Chino Beach
     *
     * Purpose:
     * This class serves to hold all the information about the room in the hotel.
     * -Handles checking customers in and out of rooms
     * -Checks room's availability
     * -Updates room's availability
     * -Checks the floor number for a room
     * -Checks the number of beds in a room
     * -Check size of beds in a room
     * -Checks handicap accessiblity for rooms
     * -Checks which customer is currently in a room
     * -Assign a customer to a room
     * -Pulls the search information for a room
     * Done by Chino ^
     *
     * Done by Ricardo Gibson:
     * -Checks if a room is clean
     * -Updates if a room is clean
     */


    //DATABASE
    DatabaseConnection dbConnection;    //connection to database

    //Method Instantiator
    public Room()
    {
        // establish connection to database
        dbConnection = new DatabaseConnection();
    }

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
        };
            resultSet = dbConnection.selectQuery(query);

            return resultSet;
    }

    //METHOD Check in
    public void checkIn(int int_RoomID)
    {
        // Return if the room is not available
        if(!CheckAvailable(int_RoomID)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Uh Oh");
            alert.setHeaderText("Room is not available!");
            alert.show();
            return;
        }

        // Update room availability
        UpdateAvailable(int_RoomID);
    }

    //METHOD Check out
    public void checkOut(int int_RoomID)
    {
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
        }
        //otherwise the room is occupied
        //unassigned the customer from the room
        //customer = null
        //unassign the customer from the room
        dbConnection.unassignCustomerFromRoom(int_RoomID);

        //mark the room as dirty
        UpdateRoomClean(int_RoomID);

        //I am commenting this out because the room should not be marked available
        //until the room is marked clean by the janitor.
        //UpdateAvailable(int_RoomID);
    }
    //METHOD Update Room Availability
    private void UpdateAvailable(int int_RoomID)
    {
        boolean bol_isAvailable = CheckAvailable(int_RoomID); //room availabilty
        boolean bol_isClean = RoomClean(int_RoomID);       //room clean status

        //if room has been checked out and room is clean
        if(!bol_isAvailable & bol_isClean)
        {
            //change the variable to available
            bol_isAvailable = true;
            //make the room available by sending the variable
            dbConnection.updateAvailability(int_RoomID, bol_isAvailable);
        }

        //if checking in
        else
        {
            //change the variable to unavailable
            bol_isAvailable = false;
            //make the room unavailable by sending the variable
            dbConnection.updateAvailability(int_RoomID, bol_isAvailable);
        }
    }
    //METHOD Check Room Availability
    private boolean CheckAvailable(int int_RoomID)
    {
        boolean bol_isAvailable; //true = room is available false = room is not
        boolean bol_default = false;

        try {
            //variables


            String SQL_Query = "SELECT is_available FROM rooms WHERE room_id = " + int_RoomID; //sql statement
            ResultSet refinedSearch = dbConnection.selectQuery(SQL_Query);                     //pull availability from table

            // If there is a value returned from the query
            if (refinedSearch.next()) {
                //set availability based off of the refined search
                bol_isAvailable = refinedSearch.getBoolean(1);
                return bol_isAvailable;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        //return default

        return  bol_default;

    }
    //METHOD Update Room Availability
    private void UpdateCustomerID(int int_RoomID, int int_CustID)
    {
       int int_CustomerID = CheckCustomerID(int_RoomID); //the customers id
       int int_NullCustomerID = 0;

        //if checking out, (there is a customer assigned to the room)
        if( int_CustomerID != int_NullCustomerID)
        {
            //unassigned the customer from the room
            int_CustomerID = int_NullCustomerID;
            //make the room available by sending the variable
            dbConnection.updateCustomerID(int_RoomID, int_CustomerID);
        }

        //if checking in
        else
        {
            //get the customers id
            int_CustomerID = int_CustID ; //this will need to be done differntly
            //assign the customer to a room
            dbConnection.updateCustomerID(int_RoomID, int_CustomerID);

        }

    }
    //METHOD Check if the customers ID
    private int CheckCustomerID(int int_RoomID)
    {
        int int_CustomerID; //the customers ID
        int int_default = 0; //no customer can have ID of 0
        try {

            String SQL_Query = "SELECT customer_id FROM rooms WHERE room_id = " + int_RoomID; //sql statemenet
            ResultSet refinedSearch = dbConnection.selectQuery(SQL_Query);                     //pull avaiablity from table

            //set availability based off of the refined search
            int_CustomerID = refinedSearch.getInt(1);

            return int_CustomerID;
        } catch (Exception e) {
            System.out.println(e);
        }
        //return default
        return  int_default;
    }
    //METHOD Update Room Clean

    public void UpdateRoomClean(int int_RoomID)
    {
        //variables
        boolean bol_clean = RoomClean(int_RoomID); // is the room clean

        //if janitor marks clean

        if(!bol_clean)          //if room not clean then it is dirty
        {
            //change the variable to room clean
            bol_clean = true; // janitor marks clean
            //make the room available by sending the variable
            dbConnection.updateRoomClean(int_RoomID, true); //update room clean <<<---some type of conflict with dbConnection
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

        }
        //debug
        System.out.println(bol_clean);

    }


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
                return bol_clean;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return  bol_default;
    }
}//end of Class "Room"
