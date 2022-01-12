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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditAppointmentController implements Initializable {
    @FXML
    private ChoiceBox<Appointment> appointmentList;
    @FXML
    private ChoiceBox<Patient> patientList;
    @FXML
    private DatePicker dateList;
    @FXML
    private Spinner<String> hourSelection;
    @FXML
    private TextField address;
    @FXML
    private Button cancelButton;
    @FXML
    private Button editButton;

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

    public void transferAppointment(Appointment appointment) {

    }

    public void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
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
        String hour = hourSelection.getValue();
        return LocalTime.parse(hour);
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
