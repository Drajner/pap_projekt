package edu.iipw.pap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class DeletePatientController implements Initializable {

    public DeletePatientController() {}

    @FXML
    private ChoiceBox<String> patientList;
    @FXML
    private Button deleteButton;
    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Populate populate = new Populate();
        for (Patient p: populate.patients) {
            patientList.getItems().add(p.toString());
        }
    }

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
