package edu.iipw.pap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditAppointmentController implements Initializable {
    @FXML
    private ChoiceBox<String> patientList;
    @FXML
    private DatePicker dateList;
    @FXML
    private Spinner<String> hourSelection;
    @FXML
    private TextField officeId;
    @FXML
    private Button cancelButton;
    @FXML
    private Button editButton;

    private Connection conn;

    private Doctor loggedDoctor;

    private Appointment edditedAppointment;

    private ArrayList<Patient> patients;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void transferAppointment(Appointment appointment, Doctor doctor, Connection usedConn) {

        conn = usedConn;
        loggedDoctor = doctor;
        edditedAppointment = appointment;
        DBContext dbContext = new DBContext();
        try {
            patients = dbContext.getPatients(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Patient p: patients) {
            patientList.getItems().add(p.toString());
        }

        patientList.setValue(appointment.getPatient().toString());
        dateList.setValue(appointment.getTimeOfAppointment().toLocalDate());

        ArrayList<String> hours_array = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 60; j+=5) {
                if (j < 10) {
                    hours_array.add(i + ":0" + j);
                } else {
                    hours_array.add(i + ":" + j);
                }
            }
        }

        ObservableList<String> hours = FXCollections.observableArrayList(hours_array);

        SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(hours);

        valueFactory.setValue(edditedAppointment.getTimeOfAppointment().toLocalTime().toString());

        hourSelection.setValueFactory(valueFactory);
    }

    public void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public Patient getPatient()
    {
        String s_patient = patientList.getValue();
        Patient patient = null;

        for (Patient p: patients) {
            if (s_patient.equals(p.toString())) {
                patient = p;
                break;
            }
        }
        return patient;
    }

    public LocalDate getDate() {
        return dateList.getValue();
    }

    public LocalTime getTime() {
        String hour = hourSelection.getValue();
        return LocalTime.parse(hour);
    }

    public int getOfficeId() {
        return Integer.parseInt(officeId.getText());
    }

    public void editAppointment() {
        // zmiana okna na takie ktore wyswietla dane wizyty do zmiany
        int id = 404;
        Patient patient = getPatient();
        LocalDate date = getDate();
        LocalTime time = getTime();
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        int officeId = getOfficeId();

        Appointment appointment = new Appointment(id, loggedDoctor, patient, dateTime, officeId);
        // update wpis w bazie danych dotyczacy danej wizyty
    }
}
