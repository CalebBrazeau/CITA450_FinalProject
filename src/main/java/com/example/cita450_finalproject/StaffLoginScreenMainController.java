package com.example.cita450_finalproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        ResultSet resultSet;
        ResultSet selectEmployeeID;

        boolean bol_SucessfulLogin;
        boolean bol_default = false;
        //get the username
        String username = getUserName();
        //get the password
        String password = getPassword();

        //check logins with the table

        //search the database where the username and the password match what was entered
        resultSet = dbConnection.selectQuery("Select * FROM employee WHERE username = '" + username + "' AND password = '" + password + "';");
        //if the Username can be found in the table
        if (resultSet.isBeforeFirst())
        {
            bol_SucessfulLogin = true;
            selectEmployeeID = dbConnection.selectQuery("SELECT employee_id WHERE username = '" + username + "' AND password = '" + password + "';");
            return  bol_SucessfulLogin;


        }
        //otherwise the username and password don't match or aren't in the system
        return bol_default;
    }

    private int getJobID( int employeeID) throws SQLException {
        int int_jobId;
        DatabaseConnection dbConnection = new DatabaseConnection();
        ResultSet selectJobID;

        selectJobID = dbConnection.selectQuery("SELECT job_Id WHERE employee_id = '" + employeeID+ "';");
        int_jobId =  selectJobID.getObject("job_Id", Integer.class);

        System.out.print(int_jobId);
        return int_jobId;
    }
    @FXML
    private void Login() throws SQLException
    {
        System.out.print("Got to Login");

        //if login was sucessful
        if(CheckLogin())
        {
            //go to next screen
            System.out.print("Got to CheckLogin and was sucessful");
            System.out.print("trying to load next screen");
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
        System.out.print("Got to LoadNextScreen Method");
        try
        {   //load the employee select screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeSelect.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Employee Select");
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }//end catch
    }

}
