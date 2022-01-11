package edu.iipw.pap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeleteAppointmentController implements Initializable {

    public DeleteAppointmentController() { }

    @FXML
    private ChoiceBox<String> appointmentList;
    @FXML
    private Button deleteButton;
    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Populate populate = new Populate();
        for (Appointment a: populate.appointments) {
            appointmentList.getItems().add(a.toString());
        }
    }
    public String getAppointment() {
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

        String appointment = getAppointment();
        // usuniecie z bazy danych wizyty
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}