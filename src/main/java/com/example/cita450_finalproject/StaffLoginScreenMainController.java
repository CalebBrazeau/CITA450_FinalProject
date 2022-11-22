package com.example.cita450_finalproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.IOException;

public class StaffLoginScreenMainController
{
    public TextField UserNameTextBox;
    public TextField PassWordTextBox;

    public Button loginButton;

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
            //set login to sucessful
            bol_SucessfulLogin = true;
            return  bol_SucessfulLogin;
        }
        //otherwise the username and password don't match or aren't in the system
        return bol_default;
    }

    private int getEmplyeeID(String userName) throws SQLException {
        int int_employeeId;
        DatabaseConnection dbConnection = new DatabaseConnection();
        ResultSet selectJobID;

        //get the employee id connected to the username
        selectJobID = dbConnection.selectQuery("SELECT employee_Id WHERE username = '" + userName+ "';");
        int_employeeId = selectJobID.getInt(1);

        //return the employeeID
        return int_employeeId;
    }

    private int getJobID(String userName) throws SQLException
    {
        int int_jobId;
        DatabaseConnection dbConnection = new DatabaseConnection();
        ResultSet selectJobID;

        //get the job_id connected with the employee id
        selectJobID = dbConnection.selectQuery("SELECT job_Id WHERE username = '" + userName+ "';");
        System.out.println(selectJobID + "Got here" );
        int_jobId = selectJobID.getInt(1);

        //return the job id
        return int_jobId;
    }
    @FXML
    private void Login() throws SQLException
    {
        //if login was sucessful
        if(CheckLogin())
        {
            //get the username employeeId and jobid
            String username = getUserName();
            //int employee_Id = getEmplyeeID(username);
            int job_Id = getJobID(username);
            //go to next screen
            closeWindow();
            LoadNextScreen(job_Id);
        }//end if
        //else
        else
        {
            //display an error message

        }//end else
    }//end method

    @FXML
    private void LoadNextScreen(int job_Id)
    {
        int frontDeskID = 1;
        int cleaninfID = 2;
        int maintenanceID = 3;

        System.out.print("Got to LoadNextScreen Method");
        try
        {   //load the correct employee screen
            FXMLLoader loader;
            if(job_Id == frontDeskID)
            {
                loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            }
            else if (job_Id == cleaninfID)
            {
                loader = new FXMLLoader(getClass().getResource("janitor-dashboard.fxml"));
            }
            else if(job_Id == maintenanceID)
            {
                loader = new FXMLLoader(getClass().getResource("MaintenanceOrJanitorSelect.fxml"));
            }
            else
            {
                loader = new FXMLLoader(getClass().getResource("EmployeeSelect.fxml"));
            }

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

    @FXML
    private void LoadPrevoiusScreen()
    {
        System.out.print("Got to LoadPrevoiusScreen Method");
        closeWindow();
        try
        {   //load the employee select screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserSelect1.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("User Select");
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }//end catch
    }
    @FXML
    private void closeWindow() {
        // get a handle to the stage
        Stage stage = (Stage) loginButton.getScene().getWindow();
        // DESTROY THE CHILD, CORRUPT THEM ALL
        stage.close();
    }

}
