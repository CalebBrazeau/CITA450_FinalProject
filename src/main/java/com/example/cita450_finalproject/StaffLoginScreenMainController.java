package com.example.cita450_finalproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

import java.io.IOException;

public class StaffLoginScreenMainController
{
    public TextField UserNameTextBox;
    public TextField PassWordTextBox;

    //Passwords
    String str_passFrontDesk ="Rooms";
    String str_passCleaning = "Mop";
    String str_passMaintencance = "Hammer";

    @FXML
    private String getUserName()
    {
        //get the username from the textbox;
        String str_UserName= UserNameTextBox.getText();

        return str_UserName;
    }
    @FXML
    private String getPassword()
    {
        //get the Password from the textbox;
        String str_Password = PassWordTextBox.getText();

        return str_Password;
    }

    private boolean CheckLogin()
    {
        boolean bol_SucessfulLogin;
        boolean bol_defaul = false;
        //get the username
        String username = getUserName();
        //get the password
        String password = getPassword();

        switch (username) {
            //front desk employee
            case "FrontDesk":
                //check password if it is correct
                if(password == str_passFrontDesk)
                {
                    //mark password as correct
                    bol_SucessfulLogin = true;

                    //return
                    return bol_SucessfulLogin;
                }
                else {return bol_defaul;}
            //cleaning employees
            case "Janitor":
                //check password if it is correct
                if(password == str_passCleaning)
                {
                    //mark password as correct
                    bol_SucessfulLogin = true;

                    //return
                    return bol_SucessfulLogin;
                }
                else {return bol_defaul;}
            //Maintenance employees
            case "Repairs":
                //check password if it is correct
                if(password == str_passMaintencance)
                {
                    //mark password as correct
                    bol_SucessfulLogin = true;

                    //return
                    return bol_SucessfulLogin;
                }
                else {return bol_defaul;}

            //invalid username
            default:
                return bol_defaul;

        }
    }
    private void Login()
    {

        //if login was sucessful
        if(CheckLogin())
        {
            //go to next screen
            LoadNextScreen();
        }
        //else
        else
        {
            //display an error message
        }


    }

    @FXML
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
