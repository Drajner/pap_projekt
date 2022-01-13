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

    public ChooseEditPatientController() {}

    @FXML
    private ChoiceBox<String> patientList;
    @FXML
    private Button chooseButton;
    @FXML
    private Button cancelButton;

    private ObservableList<AppointmentTableRow> tempData;

    private Doctor loggedDoctor;

    private Connection conn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void transferData(ObservableList<AppointmentTableRow> data, Doctor doctor, Connection usedConn) {
        conn = usedConn;
        loggedDoctor = doctor;
        tempData = data;
        ArrayList<Patient> patients;

        DBContext dbContext = new DBContext();
        try {
            patients = dbContext.getPatients(conn);

            for (Patient p: patients) {
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

    private void choosePatientFromData() throws IOException{
        Parent root;
        String[] splitPatient = patientList.getValue().split(" ");
        String editedPatientPesel = splitPatient[0];
        Patient editedPatient = tempData.get(0).getAppointment().getPatient();
        for (AppointmentTableRow tr: tempData) {
            if(tr.getAppointment().getPatient().getPesel() == editedPatientPesel)
            {
                editedPatient = tr.getAppointment().getPatient();
                break;
            }
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editPatientScreen.fxml"));
            root = loader.load();
            EditPatientController epc = loader.getController();
            epc.transferPatient(editedPatient, loggedDoctor, conn);
            int sceneX = 300;
            int sceneY = 320;
            Stage stage = new Stage();
            stage.getIcons().add(new Image(App.class.getResource("appointr_logo.png").toString()));
            stage.setTitle("Edit patient");
            stage.setScene(new Scene(root, sceneX, sceneY));
            stage.setResizable(false);
            stage.show();
            Stage stage2 = (Stage) cancelButton.getScene().getWindow();
            stage2.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
