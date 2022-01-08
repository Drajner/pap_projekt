package edu.iipw.pap;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EditAppointmentController {
    @FXML
    private ChoiceBox<Appointment> appointmentList;
    @FXML
    private ChoiceBox<Patient> patientList;
    @FXML
    private DatePicker dateList;
    @FXML
    private Spinner<LocalTime> hourSelection;
    @FXML
    private TextField address;

    public void close() {
    }

    public Appointment getAppointment() {
        return appointmentList.getValue();
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

    public void editAppointment() {
        Appointment appointment = getAppointment();

        // zmiana okna na takie ktore wyswietla dane wizyty do zmiany

        Patient patient = getPatient();
        LocalDate date = getDate();
        LocalTime time = getTime();
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        String address = getAddress();

        // update wpis w bazie danych dotyczacy danej wizyty
    }
}
