package edu.iipw.pap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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

    private Stage parentStage;

    private Appointment edditedAppointment;

    private ArrayList<Patient> patients;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void transferAppointment(Appointment appointment, Doctor doctor, Connection usedConn, Stage stage) {
        conn = usedConn;
        loggedDoctor = doctor;
        edditedAppointment = appointment;
        parentStage = stage;

        DBContext dbContext = new DBContext();

        try {
            patients = dbContext.getPatients(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Patient p : patients) {
            patientList.getItems().add(p.toString());
        }

        patientList.setValue(appointment.getPatient().toString());
        dateList.setValue(appointment.getTimeOfAppointment().toLocalDate());
        officeId.setText(String.valueOf(appointment.getOfficeId()));
        ArrayList<String> hours_array = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 60; j += 5) {
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

    private Patient getPatient() {
        String s_patient = patientList.getValue();
        Patient patient = null;

        for (Patient p : patients) {
            if (s_patient.equals(p.toString())) {
                patient = p;
                break;
            }
        }
        return patient;
    }

    private LocalDate getDate() {
        return dateList.getValue();
    }

    private LocalTime getTime() {
        String hour = hourSelection.getValue();
        return LocalTime.parse(hour);
    }

    private int getOfficeId() {
        return Integer.parseInt(officeId.getText());
    }

    private Appointment editAppointmentFromData() {
        return new Appointment(edditedAppointment.getId(), loggedDoctor, getPatient(),
                LocalDateTime.of(getDate(), getTime()), getOfficeId());
    }

    public void editAppointment(ActionEvent event) throws IOException {
        Appointment appointment = editAppointmentFromData();

        try {
            DBContext.editAppointment(conn, appointment);
        } catch (Exception e) {
            e.printStackTrace();
        }

        parentStage.fireEvent(
                new WindowEvent(parentStage, WindowEvent.WINDOW_CLOSE_REQUEST)
        );

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
