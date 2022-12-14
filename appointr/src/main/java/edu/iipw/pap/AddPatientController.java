package edu.iipw.pap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.sql.Connection;


public class AddPatientController {

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
    @FXML
    private TextField genderField;
    private Connection conn;

    public AddPatientController() {
    }

    public void transferConn(Connection usedConn) {
        conn = usedConn;
    }

    @FXML
    public void addPatient(ActionEvent event) throws IOException {
        Patient patient = createPatientFromData();

        try {
            DBContext.addPatient(conn, patient);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.fireEvent(
                new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST)
        );
    }

    @FXML
    public void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private Patient createPatientFromData() throws IOException {
        return new Patient(peselField.getText(), nameField.getText(),
                surnameField.getText(), dataField.getValue(), descriptionField.getText(), genderField.getText().charAt(0));
    }
}
