package edu.iipw.pap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class DeleteAppointmentController {

    public DeleteAppointmentController() { }

    @FXML
    private Button deleteButton;
    @FXML
    private Button cancelButton;

    public void deleteAppointment(ActionEvent event) throws IOException {
        deleteAppointmentFromData();
    }

    public void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private void deleteAppointmentFromData() throws IOException{

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
