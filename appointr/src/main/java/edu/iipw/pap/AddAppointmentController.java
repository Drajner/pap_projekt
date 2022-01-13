package edu.iipw.pap;

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

public class AddAppointmentController implements Initializable {

    public AddAppointmentController() {
    // doctor = moze LoggerController bedzie posiadal funckje ktora zwroci aktualnie zalogowanego lekarza
    }

    @FXML
    private ChoiceBox<String> patientList;
    @FXML
    private DatePicker dateList;
    @FXML
    private Spinner<String> hourSelection;
    @FXML
    private TextField address;
    @FXML
    private Button cancelButton;
    @FXML
    private Button addButton;

    private ArrayList<Patient> patients;

    private Doctor loggedDoctor;

    private Connection conn;

    public void transferDoctorAndConn(Doctor doctor, Connection usedConn){
        loggedDoctor = doctor;
        conn = usedConn;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

        valueFactory.setValue("12:00");

        hourSelection.setValueFactory(valueFactory);

    }



    public void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public Patient getPatient() {
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

    public String getAddress() {
        return address.getText();
    }

    public Appointment createAppointment() {
        int id = 404;
        Patient patient = getPatient();
        LocalDate date = getDate();
        LocalTime time = getTime();
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        String address = getAddress();

        return new Appointment(id, loggedDoctor, patient, dateTime, address);
    }

    public void addAppointment() {
        Appointment appointment = createAppointment();

        try {
            DBContext.addAppointment(conn, appointment);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
