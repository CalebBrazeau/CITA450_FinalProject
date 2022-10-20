package com.example.cita450_finalproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerInformationController implements Initializable {

    public TextField textFName;
    public TextField textLName;
    public TextField textPhoneNumber;
    public TextField textEmail;
    public ChoiceBox choicePaymentMethod;

    Customer customer;
    String roomID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            customer = new Customer();
            setupPaymentMethods();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    private void setupPaymentMethods() {
        choicePaymentMethod.getItems().add("VISA");
        choicePaymentMethod.getItems().add("CASH");
    }

    @FXML
    private void collectCustomerInfo() throws SQLException {
        customer.insertNewCustomer(
                textFName.getText(),
                textLName.getText(),
                textPhoneNumber.getText(),
                textEmail.getText(),
                choicePaymentMethod.getSelectionModel().getSelectedItem().toString(),
                Integer.parseInt(roomID)
        );
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }
}
