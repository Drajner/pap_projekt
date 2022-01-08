package edu.iipw.pap;

import java.io.IOException;

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
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private DatePicker dataField;
    @FXML
    private TextArea descriptionField;

    public void addPatient(ActionEvent event) throws IOException {
        createPatientFromData();
    }

    public void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private void createPatientFromData() throws IOException{

        new Patient(nameField.getText(), surnameField.getText(), dataField.getValue(), descriptionField.getText());
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
