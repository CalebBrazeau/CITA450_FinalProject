package com.example.cita450_finalproject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    //Method Instantitor
    private void Instantior( /*int int_RoomID*/)
    {
        //establish connection to database
        dbConnection = new DatabaseConnection();

        //set variabels


    }

    //METHOD handle what is being shown when something is searched
    public ResultSet PullInformation(String str_SearchCondidtion)
    {
        ResultSet resultSet;
        //if the search condition is room number
            //pull up the rooms information that matches that room number

        //if the search condition is customer name
            //pull up any rooms that that customer is currently assigned to

        //if the search condition is handicap accessesible
            //pull up any rooms that are hanicap accessible

        //if the search condition is floor number
            //pull up all rooms on that floor

        //if the search condition is number of beds
            //pull up all rooms that have that number of beds

        return resultSet;
    }

    //METHOD get room ID
   // public int GetRoomID()
    {
        //return int_RoomID;
    }

    //METHOD Check in
    public void Checkin()
    {
        //if the room is avaiable
        if(!CheckAvailable())
        {
            //error room not avaibale
            return;
        }
        else
        {
            //mark room as available
            UpdateAvailable();

            //set customerid
        }
    }

    //METHOD Check out
    public void Checkout(int int_RoomID)
    {
        //if the room is avaiable
        if (CheckAvailable(int_RoomID))
        {
            //error room not avaibale
            System.out.println( "ERROR:: ROOM: "+ int_RoomID + " Status: Room not available.");
            return;
        }
        else
        {
            //eventually this will be set to needs cleaning then from there cleaning would set this to true, but for now keeping it simple
            //change room to dirty
            System.out.println( " ROOM: "+ int_RoomID + " Status: needs cleaning");
            //mark room as available
            UpdateAvailable(int_RoomID);


        }
    }
    //METHOD Update Room Avaliability
    private void UpdateAvailable(int int_RoomID)
    {
        boolean bol_isAvailable = CheckAvailable(int_RoomID);

        //if checking out
        if( bol_isAvailable = false)
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
        boolean bol_defualt = false;

        try {
            //variables


            String SQL_Query = "SELECT is_available FROM rooms WHERE room_id = " + int_RoomID; //sql statemenet
            ResultSet refinedSearch = dbConnection.selectQuery(SQL_Query);                     //pull avaiablity from table

            //set availabilty based off of the refined search
            bol_isAvailable = refinedSearch.getBoolean(1);

            return bol_isAvailable;
        } catch (Exception e) {
            System.out.println(e);
        }

        return  bol_defualt;
    }
    //METHOD Check Floor Number for Room
   // private int CheckFloorNum()
    {
        //pull floor number from table
       // return int_floorNumber;
    }
    //METHOD Check Number Beds in Room
   //private int CheckNumBeds()
    {
        //pull number of beds from table
        //return int_numBeds;
    }
    //METHOD Check Handicap Accessible
   // private boolean CheckHandicap()
    {
        //pull hanicap accessible from table
       // return bol_isHandicapAccessible;
    }


}//end of Class "Room"
