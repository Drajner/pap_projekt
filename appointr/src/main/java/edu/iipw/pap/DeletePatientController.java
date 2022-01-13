package edu.iipw.pap;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class DeletePatientController implements Initializable {

    public DeletePatientController() { }

    @FXML
    private ChoiceBox<String> patientList;
    @FXML
    private Button deleteButton;
    @FXML
    private Button cancelButton;

    private ArrayList<Patient> patients;

    private Doctor loggedDoctor;

    private Connection conn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBContext dbContext = new DBContext();
        try {
            patients = dbContext.getPatients(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Patient p: patients) {
            patientList.getItems().add(p.toString());
        }
    }

    public void transferData(ObservableList<AppointmentTableRow> data, Doctor doctor, Connection usedConn) {
        // fajnie jakby bylo przekazane connection i w sumie nic wiecej
        loggedDoctor = doctor;
        conn = usedConn;
        for (AppointmentTableRow tr: data) {
            patientList.getItems().add(tr.getAppointment().getPatient().toString());
        }

    }

    public String getPatient() {
        return patientList.getValue();
    }

    public void deletePatient(ActionEvent event) throws IOException {
        Patient patient = deletePatientFromData();

        try {
            DBContext.deletePatient(conn, patient.getPesel());
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

    private Patient deletePatientFromData() throws IOException{
        String s_patient = getPatient();
        Patient patient = null;

        for (Patient p: patients) {
            if (s_patient.equals(p.toString())) {
                patient = p;
                break;
            }
        }
        return patient;
    }
}
