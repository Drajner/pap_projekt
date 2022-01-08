package edu.iipw.pap;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class DeleteAppointmentController {
    @FXML
    private ChoiceBox<Appointment> appointmentList;


    public void close() {
    }

    public Appointment getAppointment() {
        return appointmentList.getValue();
    }


    public void deleteAppointment() {
        Appointment appointment = getAppointment();
        // usuniecie z bazy danych wizyty
    }
}
