package com.example.cita450_finalproject;

import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerInformationController implements Initializable {

    public TextField textFName;
    public TextField textLName;
    public TextField textPhoneNumber;
    public TextField textEmail;
    public ChoiceBox choicePaymentMethod;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Hello beautiful");
    }
}
