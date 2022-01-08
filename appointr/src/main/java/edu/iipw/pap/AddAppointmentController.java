package edu.iipw.pap;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AddAppointmentController {
    private Doctor doctor;
    @FXML
    private ChoiceBox<Patient> patientList;
    @FXML
    private DatePicker dateList;
    @FXML
    private Spinner<LocalTime> hourSelection;
    @FXML
    private TextField address;

    public AddAppointmentController() {
        // doctor = moze LoggerController bedzie posiadal funckje ktora zwroci aktualnie zalogowanego lekarza
    }

    public void cancel() {

    }

    public Patient getPatient() {
        return patientList.getValue();
    }

    public LocalDate getDate() {
        return dateList.getValue();
    }

    public LocalTime getTime() {
        return hourSelection.getValue();
    }

    public String getAddress() {
        return address.getText();
    }

    public Appointment createAppointment() {
        Patient patient = getPatient();
        LocalDate date = getDate();
        LocalTime time = getTime();
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        String address = getAddress();

        return new Appointment(doctor, patient, dateTime, address);
    }

    public void addAppointment() {
        Appointment appointment = createAppointment();
        // dodanie wizyty do bazy danych
    }
}
