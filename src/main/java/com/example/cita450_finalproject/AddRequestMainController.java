package com.example.cita450_finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static javax.management.remote.JMXConnectorFactory.connect;

public class AddRequestMainController implements Initializable {
    Room room;


    @FXML
    public Button btnAddRequest;

    public RadioButton rbutton1;

    public RadioButton rbutton2;

    public RadioButton rbutton3;

    public TextArea Areacomments;

    public TextField searchRequestTextbox;


    @FXML
    public void AddRequest(ActionEvent actionEvent) throws SQLException {

        String comments = Areacomments.getText();   //comments section
        System.out.println(comments);

        Integer roomID = Integer.valueOf(searchRequestTextbox.getText());     ///Enter room ID
        System.out.println(roomID);

        String brokenItem = "";     //Scope
        if (rbutton1.isSelected()) {
            brokenItem = "broken toilet";
            System.out.println(brokenItem);

        } else if (rbutton2.isSelected()) {
            brokenItem = "broken light";
            System.out.println(brokenItem);

        } else if (rbutton3.isSelected()) {
            brokenItem = "broken AC";
            System.out.println(brokenItem);
        }


        room.AddRequest(roomID, comments, brokenItem);      //It sends it to the room object
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        room = new Room();

    }
}

