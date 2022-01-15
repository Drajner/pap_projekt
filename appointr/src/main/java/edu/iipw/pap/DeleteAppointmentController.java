package edu.iipw.pap;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DeleteAppointmentController implements Initializable {

    private final ArrayList<Appointment> appointments = new ArrayList<>();
    @FXML
    private ChoiceBox<String> appointmentList;
    @FXML
    private Button deleteButton;
    @FXML
    private Button cancelButton;
    private Connection conn;
    private Doctor loggedDoctor;

    public DeleteAppointmentController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void transferData(ObservableList<AppointmentTableRow> data, Doctor doctor, Connection usedConn) {
        loggedDoctor = doctor;
        conn = usedConn;
        for (AppointmentTableRow tr : data) {
            appointmentList.getItems().add(tr.getAppointment().toString());
            appointments.add(tr.getAppointment());
        }
    }

    public String getAppointment() {
        return appointmentList.getValue();
    }

    public void deleteAppointment(ActionEvent event) throws IOException {
        Appointment appointment = deleteAppointmentFromData();

        try {
            DBContext.deleteAppointment(conn, appointment.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.fireEvent(
                new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST)
        );
    }

    public void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private Appointment deleteAppointmentFromData() throws IOException {

        String s_appointment = getAppointment();
        Appointment appointment = null;

        for (Appointment a : appointments) {
            if (a.toString().equals(s_appointment)) {
                appointment = a;
                break;
            }
        }

        return appointment;
    }
}