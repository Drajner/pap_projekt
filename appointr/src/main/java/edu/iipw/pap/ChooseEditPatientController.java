package edu.iipw.pap;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChooseEditPatientController implements Initializable {

    @FXML
    private ChoiceBox<String> patientList;
    @FXML
    private Button chooseButton;
    @FXML
    private Button cancelButton;
    private Doctor loggedDoctor;
    private Connection conn;
    private ArrayList<Patient> patients;

    public ChooseEditPatientController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void transferData(ObservableList<AppointmentTableRow> data, Doctor doctor, Connection usedConn) {
        conn = usedConn;
        loggedDoctor = doctor;

        DBContext dbContext = new DBContext();
        try {
            patients = dbContext.getPatients(conn);

            for (Patient p : patients) {
                patientList.getItems().add(p.toString());
            }
            patientList.setValue(patients.get(0).toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void choosePatient(ActionEvent event) throws IOException {
        choosePatientFromData();
    }

    public void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private void choosePatientFromData() throws IOException {
        Parent root;
        String s_patient = patientList.getValue();
        Patient editedPatient = null;

        for (Patient p : patients) {
            if (p.toString().equals(s_patient)) {
                editedPatient = p;
                break;
            }
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editPatientScreen.fxml"));
            root = loader.load();
            EditPatientController epc = loader.getController();

            Stage parentStage = (Stage) cancelButton.getScene().getWindow();

            epc.transferPatient(editedPatient, loggedDoctor, conn, parentStage);
            int sceneX = 300;
            int sceneY = 320;
            Stage stage = new Stage();
            stage.getIcons().add(new Image(App.class.getResource("appointr_logo.png").toString()));
            stage.setTitle("Edit patient");
            stage.setScene(new Scene(root, sceneX, sceneY));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
