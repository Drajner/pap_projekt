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


public class EditPatientController {

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
    private Stage parentStage;
    private String patientPesel;

    public EditPatientController() {
    }

    public void transferPatient(Patient patient, Doctor doctor, Connection usedConn, Stage stage) {
        conn = usedConn;
        parentStage = stage;

        peselField.setText(patient.getPesel());
        patientPesel = patient.getPesel();
        nameField.setText(patient.getName());
        surnameField.setText(patient.getSurname());
        descriptionField.setText((patient.getDescription()));
        dataField.setValue(patient.getDateOfBirth());
        genderField.setText(patient.getGender().toString());
    }

    public void editPatient(ActionEvent event) throws IOException {
        Patient patient = editPatientFromData();

        try {
            DBContext.editPatient(conn, patient, patientPesel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        parentStage.fireEvent(
                new WindowEvent(parentStage, WindowEvent.WINDOW_CLOSE_REQUEST)
        );

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private Patient editPatientFromData() throws IOException {
        return new Patient(peselField.getText(), nameField.getText(), surnameField.getText(),
                dataField.getValue(), descriptionField.getText(), genderField.getText().charAt(0));

    }
}
