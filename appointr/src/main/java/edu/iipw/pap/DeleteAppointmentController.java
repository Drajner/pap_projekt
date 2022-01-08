package edu.iipw.pap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;

public class DeleteAppointmentController {

    public DeleteAppointmentController() { }

    @FXML
    private ChoiceBox<Appointment> appointmentList;
    @FXML
    private Button deleteButton;
    @FXML
    private Button cancelButton;

    public Appointment getAppointment() {
        return appointmentList.getValue();
    }

    public void deleteAppointment(ActionEvent event) throws IOException {
        deleteAppointmentFromData();
    }

    public void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private void deleteAppointmentFromData() throws IOException{

        Appointment appointment = getAppointment();
        // usuniecie z bazy danych wizyty
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}