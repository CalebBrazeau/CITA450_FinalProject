package com.example.cita450_finalproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class StaffLoginScreenMainController
{
    public TextField UserNameTextBox;
    public TextField PassWordTextBox;

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

    @FXML
    private boolean CheckLogin() throws SQLException {
        // establish connection to database
        DatabaseConnection dbConnection = new DatabaseConnection();

        boolean bol_SucessfulLogin;
        boolean bol_default = false;
        //get the username
        String username = getUserName();
        //get the password
        String password = getPassword();

        //check logins with the table

        //if the Username can be found in the table
            //check if the passwords match
            //if they do
                //set bol_sucessfulLogin to true
            //else (it didnt match)
                //set bol_sucessfullogin to false
            //return bol_sucessfulLogin
        //otherwise they couldnt find the username
        return bol_default;
    }
    @FXML
    private void Login() throws SQLException {

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
