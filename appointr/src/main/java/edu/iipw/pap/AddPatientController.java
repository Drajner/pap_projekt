package edu.iipw.pap;

import java.io.IOException;
import java.sql.Connection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;


public class AddPatientController {

    public AddPatientController() { }

    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField peselField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private DatePicker dataField;
    @FXML
    private TextArea descriptionField;

    private Doctor loggedDoctor;

    private Connection conn;

    public void transferConn(Connection usedConn){
        conn = usedConn;
    }

    public void addPatient(ActionEvent event) throws IOException {
        Patient patient = createPatientFromData();

        try {
            DBContext.addPatient(conn, patient);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private Patient createPatientFromData() throws IOException{

        return new Patient(peselField.getText(), nameField.getText(),
                surnameField.getText(), dataField.getValue(), descriptionField.getText());
    }
}
