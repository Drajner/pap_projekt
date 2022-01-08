package edu.iipw.pap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class DeletePatientController {

    public DeletePatientController() { }

    @FXML
    private Button deleteButton;
    @FXML
    private Button cancelButton;

    public void deletePatient(ActionEvent event) throws IOException {
        deletePatientFromData();
    }

    public void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private void deletePatientFromData() throws IOException{

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
