package com.example.cita450_finalproject;

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
     * It should be able to:
     * -Check in and Check Out a Customer to a room
     * -Check room availability
     * -Update room availability
     * -Check floor number for room
     * -Check number of beds in room
     * -Check size of beds in room
     * -Check handicap accessible
     * -See who is currently in a room
     * -assign a customer to a room
     * -pull the search information for a room
     */


    //DATABASE
    DatabaseConnection dbConnection;    //connection to database

    //Method Instantiator
    public Room()
    {
        // establish connection to database
        dbConnection = new DatabaseConnection();

        //set variables

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
            case "Customer ID" -> "SELECT * FROM rooms WHERE customer_id ID IS " + str_Searched+ ";";

            //if the search condition is handicap accessible
            //pull up any rooms that are handicap accessible
            case "Handicap Accessible" -> "SELECT * FROM rooms WHERE is_handicap_accessible IS true ";

            //a little more complicated //if the search condition is floor number
            //pull up all rooms on that floor
            case "Floor Number" -> "SELECT * FROM rooms WHERE Left(room_id, " + str_Searched + ");";

            //if the search condition is available
            //pull up all rooms that have that are available
            case "Available" -> "SELECT * FROM rooms WHERE is_available IS true";

            //if the search condition is number of beds
            //pull up all rooms that have that number of beds
            case "Number of Beds" -> "SELECT * FROM rooms WHERE number_of_beds IS " + str_Searched + ";";

            //nothing is selected
            default -> "SELECT * FROM rooms";
        };
            resultSet = dbConnection.selectQuery(query);

            return resultSet;


    }

    //METHOD Check in
    public void Checkin(int int_RoomID, int CustomerID) {
        //if the room is avaiable
        if (!CheckAvailable(int_RoomID)) {
            //error room not avaibale
            return;
        } else {
            //mark room as available
            UpdateAvailable(int_RoomID);

            //set customerid
            UpdateCustomerID(int_RoomID, CustomerID);
        }
    }
    public void checkIn(int int_RoomID)
    {
        // TODO: Maybe show an error message so the user knows what happened
        // Return if the room is not available
        if(!CheckAvailable(int_RoomID)) { return; }

        // Update room availability
        UpdateAvailable(int_RoomID);
    }

    //METHOD Check out
    public void checkOut(int int_RoomID)
    {
        //if the room is available
        if (CheckAvailable(int_RoomID))
        {
            //error room not avaibale
            System.out.println( "ERROR:: ROOM: "+ int_RoomID + " Status: Room was not checked in.");
            return;
        }

        //unassign the customer from the room
        //customer = null

        //change room to dirty
        /*do that*/
        //room status debug
       // System.out.println( " ROOM: "+ int_RoomID + " Status: needs cleaning");
        //time will pass.... eventually it will be marked clean

        //if the room is clean

            //mark room as available
         //   UpdateAvailable(int_RoomID);
            //room status debug
         //   System.out.println( " ROOM: "+ int_RoomID + " Status: Room available");




            // error room not available
            System.out.println( "ERROR:: ROOM: "+ int_RoomID + " Status: Room not available.");
            return;

        //eventually this will be set to needs cleaning then from there cleaning would set this to true, but for now keeping it simple
        //change room to dirty
        //System.out.println( " ROOM: "+ int_RoomID + " Status: needs cleaning");
        //mark room as available
        //(int_RoomID);
    }
    //METHOD Update Room Avaliability
    private void UpdateAvailable(int int_RoomID)
    {
        boolean bol_isAvailable = CheckAvailable(int_RoomID);

        //if checking out
        if(!bol_isAvailable)
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
        //debug
        System.out.println(bol_isAvailable);
    }
    //METHOD Check Room Avaiability
    private boolean CheckAvailable(int int_RoomID)
    {
        boolean bol_isAvailable; //true = room is avaibale false = room is not
        boolean bol_default = false;

        try {
            //variables


            String SQL_Query = "SELECT is_available FROM rooms WHERE room_id = " + int_RoomID; //sql statemenet
            ResultSet refinedSearch = dbConnection.selectQuery(SQL_Query);                     //pull avaiablity from table

            // If there is a value returned from the query
            if (refinedSearch.next()) {
                //set availabilty based off of the refined search
                bol_isAvailable = refinedSearch.getBoolean(1);
                return bol_isAvailable;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        //return default

        return  bol_default;

    }
    //METHOD Update Room Avaliability
    private void UpdateCustomerID(int int_RoomID, int int_CustID)
    {
       int int_CustomerID = CheckCustomerID(int_RoomID); //the customers id
       int int_NullCustomerID = 0;

        //if checking out, (there is a customer assigned to the room)
        if( int_CustomerID != int_NullCustomerID)
        {
            //unassign the customer from the room
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

            //set availabilty based off of the refined search
            int_CustomerID = refinedSearch.getInt(1);

            return int_CustomerID;
        } catch (Exception e) {
            System.out.println(e);
        }
        //return default
        return  int_default;
    }
    //METHOD Check Floor Number for Room
   private int CheckFloorNum(int int_RoomID)
    {
        int int_FloorNumber; //which floor the room is on
        int int_defualt = 1;//default floor is one

        //if the room id number starts with a 1
        if (100 <= int_RoomID && int_RoomID < 200)
        {
            //the room is on the first floor
            int_FloorNumber = 1;
        }
        //if the room id number starts with a 2
        else if (200 <= int_RoomID && int_RoomID < 300)
        {
            //the room is on the second floor
            int_FloorNumber = 2;
        }
        //if the room id number starts with a 3
        else if (300 <= int_RoomID && int_RoomID < 400)
        {
            //the room is on the 3rd floor
            int_FloorNumber = 3;
        }
        //if the room id number starts with a 4
        else if (400 <= int_RoomID && int_RoomID < 500)
        {
            //the room is on the 4th floor
            int_FloorNumber = 4;
        }
        else
        {
            //return default
            return  int_defualt;
        }
       return int_FloorNumber;

        /*if floors are added on then i would need a variable to be number of floors
        and a while statement that says while the room is within the floors do assign it
        to that floor else there is an error
        this can also be done more effiently by reading the first char in the room number
        and checking if its the between the lowest floor number and the highest floor number
        and the saying whatever number it is is the number of the floor, if its not then it
        cant be a real room number*/

    }
    //METHOD Check Number Beds in Room
   private int CheckNumBeds(int int_RoomID)
    {
       int int_numbOfBeds; //how many beds are in the room
       int int_default = 1; //default number of beds is one

        try {

            String SQL_Query = "SELECT number_of_beds FROM rooms WHERE room_id = " + int_RoomID; //sql statemenet
            ResultSet refinedSearch = dbConnection.selectQuery(SQL_Query);                     //pull avaiablity from table

            //set number of beds based off of the refined search
            int_numbOfBeds = refinedSearch.getInt(1);

            return int_numbOfBeds;
        } catch (Exception e) {
            System.out.println(e);
        }

        //return default
        return  int_default;
    }
    //METHOD Check Handicap Accessible
   private boolean CheckHandicap(int int_RoomID)
    {
        boolean bol_isHandicap; //true means its is hanicapAccessible
        boolean bol_defualt = false;

        try {
            //variables

            String SQL_Query = "SELECT is_handicap_accessible FROM rooms WHERE room_id = " + int_RoomID; //sql statemenet
            ResultSet refinedSearch = dbConnection.selectQuery(SQL_Query);                     //pull avaiablity from table

            //set availabilty based off of the refined search
            bol_isHandicap = refinedSearch.getBoolean(1);

            return bol_isHandicap;
        } catch (Exception e) {
            System.out.println(e);
        }
        // return default
        return bol_defualt;

    }


}//end of Class "Room"
