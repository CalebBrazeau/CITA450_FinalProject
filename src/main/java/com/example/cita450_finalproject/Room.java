package com.example.cita450_finalproject;

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

    //Class Variables
    boolean bol_isAvailable;            //is the room available and ready
    boolean bol_isHandicapAccessible;   //is the room handicap accessible

    int int_customerID;                 //who is checking into or out of the room
    int int_floorNumber;                //what floor is the room on
    int int_numBeds;                    //how many beds are in the room
    int int_RoomID;                     //the room number and ID in the table
    int int_DefaultRoom = 1;            //default room number

    char char_bedSize;                  //what size is(are) the bed(s)

    /*Small note about bed sizes:
     * It is being assumed that if there is more than one bed in the same room, they are same size*/

    //more defined variables(bed sizes)
    char twinSize = 'T';
    char doubleSize = 'D';
    char queenSize = 'Q';
    char kingSize = 'K';


    //Connection to database
    //database = database

    //Method Instantitor
    private void Instantior( /*int int_RoomID*/)
    {
        //establish connection to database
        dbConnection = new DatabaseConnection();

        //set variabels
        int_RoomID = GetRoomID();
        bol_isAvailable = CheckAvailable();
        bol_isHandicapAccessible = CheckHandicap();
        int_floorNumber = CheckFloorNum();
        int_numBeds = CheckNumBeds();

    }

    //METHOD handle what is being shown when something is searched
    public void PullInformation(String str_SearchCondidtion)
    {
        //if the search condition is room number

        //if the search condition is customer name

        //if the search condition is handicap accessesible

        //if the search condition is floor number

        //if the search condition is number of beds

    }

    //METHOD get room ID
    public int GetRoomID()
    {
        return int_RoomID;
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
    public void Checkout()
    {
        //if the room is avaiable
        if (CheckAvailable())
        {
            //error room not avaibale
            return;
        }
        else
        {
            //eventuall this will be set to needs cleaning then from there cleaning would set this to true, but for now keeping it simple
            //mark room as available
            UpdateAvailable();

        }
    }
    //METHOD Update Room Avaliability
    private void UpdateAvailable()
    {

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
    private boolean CheckAvailable()
    {
        //variable ( sql statement)
        String SQL_Query = "SELECT is_available FROM rooms WHERE room_id = " + int_RoomID;

        //pull avaiablity from table and set its variable
        bol_isAvailable = dbConnection.selectQuery(SQL_Query);
        return  bol_isAvailable;
    }
    //METHOD Check Floor Number for Room
    private int CheckFloorNum()
    {
        //pull floor number from table
        return int_floorNumber;
    }
    //METHOD Check Number Beds in Room
    private int CheckNumBeds()
    {
        //pull number of beds from table
        return int_numBeds;
    }
    //METHOD Check Handicap Accessible
    private boolean CheckHandicap()
    {
        //pull hanicap accessible from table
        return bol_isHandicapAccessible;
    }
/*
    //METHOD turn a string into an int
    public int StringToInt( String strTranslate, int intDefault)
    {
        int intTranslated;      //the string as an int
        String strTranslating;  //the ints inside the string

        //to start there is nothing that has been translated
        strTranslating = "";

        //this line is so that the code stops yelling at me. it just sets the returned into to default as a catch.
        intTranslated = intDefault;

        //for every character in the string to be translated
        for (int index = 0; index < strTranslate.length(); index++)
        {
            //if the character is a digit
            // if (char.IsDigit(strTranslate[index]))
            {
                //add it to translating string
                // strTranslating += strTranslate[index];

            }

            //as the translating string recieved digits
            //  if (strTranslating.length > 0)
            {
                //turn the translating string into the translated int
                //  intTranslated= int.Parse(strTranslating);
            }

            //if no digits were found
            // else
            {
                intTranslated = intDefault;
            }

        }

        //return the translated line
        return intTranslated;
    }

    //METHOD Get the room number that the user entered
    public void InputRoomNumber(String strInputRoomNum)
    {
        //varriable
        int intRoomNum;         //the room number being looked up

        //check if there was input or not
        if (strInputRoomNum != null && strInputRoomNum != "")
        {
            //if there was data do this

            // get the int out of the input
           //intRoomNum = StringToInt(strInputRoomNum, int_DefaultRoom);

            ///set the RoomID to the users input
            //int_RoomID = intRoomNum;

            //debug
            System.out.println(int_RoomID);

        }
        else
        {
            //there was no data

            //tell the user to reinput
            System.out.println("Error, no input detected. Try again.");
            return;

        }

        //return intRoomNum;
    } */

}//end of Class "Room"
