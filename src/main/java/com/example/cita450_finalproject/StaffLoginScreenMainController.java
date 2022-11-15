package com.example.cita450_finalproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class StaffLoginScreenMainController
{
    //Usernames and Passwords
    String str_userFrontDesk = "FrontDesk";
    String str_userCleaning = "Janitor";
    String str_userMaintenance = "Repairs";

    String str_passFrontDesk ="Rooms";
    String str_passCleaning = "Mop";
    String str_passMaintencance = "Hammer";

    @FXML
    private String getUserName()
    {
        //get the username from the textbox;
        String str_UserName;
    
        return str_UserName;
    }
    private String getPassword()
    {
        //get the Password from the textbox;
        String str_Password;

        return str_Password;
    }

    private boolean CheckLogin()
    {
        boolean bol_SucessfulLogin;
        boolean bol_defaul = false;
        //get the username
        //get the password

        //switch
        //case front desk
            //check password
            //return
            //break
        //case cleaning
            //check password
             //return
            //break
        //case maintenance
            //check password
        //return
            //break
        //no matching case
            //error invalid username
            return bol_defaul;
    }
    private void Login()
    {
        //if login sucessful
        //go to next screen
        //else
        //display an error message

    }

    private void LoadNextScreen()
    {
        try
        {   //load the employee select screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeSelect.fxml"));
            Parent root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }//end catch
    }

}
