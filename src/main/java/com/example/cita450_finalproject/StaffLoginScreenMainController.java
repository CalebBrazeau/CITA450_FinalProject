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
    public TextField UserNameTextBox;   //where a user inserts their username
    public TextField PassWordTextBox;   //where a user inserts their password
    public Button loginButton;          //button selected when information is entered

    //METHOD to retrieve and return the username entered
    @FXML
    private String getUserName()
    {
        //get the username from the textbox;
        String str_UserName= UserNameTextBox.getText();

        //return it
        return str_UserName;
    }//end method

    //METHOD to retrieve and return the password that was entered
    @FXML
    private String getPassword()
    {
        //get the Password from the textbox;
        String str_Password = PassWordTextBox.getText();

        //return it
        return str_Password;
    }//end method

    //METHOD that validates login credintals and checs to see if it was sucessful
    @FXML
    private boolean CheckLogin() throws SQLException
    {
        // establish connection to database
        DatabaseConnection dbConnection = new DatabaseConnection();
        ResultSet resultSet;

        //variables
        boolean bol_SucessfulLogin;     //was the login successful
        boolean bol_default = false;    //default is that login was not successful.

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
            //return that the login was a success
            return  bol_SucessfulLogin;
        }//end if
        //otherwise the username and password don't match or aren't in the system, failed login
        return bol_default;
    }//end method

    //METHOD to get the job id (what kind of employee the user is. Called after a successful login.
    private int getJobID(String userName) throws SQLException
    {
        //establish a connection to the database
        DatabaseConnection dbConnection = new DatabaseConnection();
        ResultSet selectJobID;

        //get the job_id connected with the employee id
        selectJobID = dbConnection.selectQuery("SELECT * From employee WHERE username = '" + userName+ "' ;");
        selectJobID.next();

        //set the job id.
        int int_jobId = (int) selectJobID.getFloat("job_Id");
        //return the job id
        return int_jobId;
    }//end method

    //METHOD login, this is where it calls the login check, and if it was successful,
    //it will bring employees to their correct screen.
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
            //go to next screen and close the current one
            closeWindow();
            LoadNextScreen(job_Id);
        }//end if

        //right now it doesnt do anything if not successful, but idealy it would produce
        //an error message to the user.
    }//end method

    //METHOD to load the next screen based off of the job ID
    @FXML
    private void LoadNextScreen(int job_Id)
    {
        int frontDeskID = 1;    //front desk employees
        int cleaninfID = 2;     //cleaning/janitorial employees
        int maintenanceID = 3;  //hanymen/mainenance employees

        String str_Title;       //the name of the screen being loaded

        try
        {   //load the correct employee screen
            FXMLLoader loader;
            if(job_Id == frontDeskID)   //if its a front desk employye
            {
                //go to the main dashboard
                loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
                //name the screen
                str_Title = "Front Desk";
            }
            else if (job_Id == cleaninfID) //else if its a cleaning employee
            {
                //go to the janitor dashboard
                loader = new FXMLLoader(getClass().getResource("janitor-dashboard.fxml"));
                //name the screen
                str_Title = "Janitor";
            }
            else if(job_Id == maintenanceID)    //else if its a maintenace person
            {
                //THIS SHOULD GO TO THE MAINTENANCE SCREEN (because it is currently on another branch of the
                //project it doesnt yet.
                loader = new FXMLLoader(getClass().getResource("MaintenanceOrJanitorSelect.fxml"));
                //name the screen
                str_Title = "Maintenance";
            }
            else //there was some sort of error, but you still signed in as an employee, select which you are. perhaps manager.
            {
                //load the employee select screen
                loader = new FXMLLoader(getClass().getResource("EmployeeSelect.fxml"));
                //name the screen
                str_Title = "Error, Select Employee Type";
            }

            //create new screen with style sheets
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(str_Title);
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }//end catch
    }//end method

    //METHOD to load the previous screen

    @FXML
    private void LoadPrevoiusScreen()
    {
       //close the window
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
        }//end try
        catch (IOException e)
        {
            e.printStackTrace();
        }//end catch
    }//end method

    //method that closes the window
    @FXML
    private void closeWindow() {
        // get a handle to the stage
        Stage stage = (Stage) loginButton.getScene().getWindow();
        // DESTROY THE CHILD, CORRUPT THEM ALL
        stage.close();
    }//end method
}//end class
