package edu.iipw.pap;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
// import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ChooseEditDoctorController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Button chooseButton;
    @FXML
    private ChoiceBox<String> doctorList;
    private Doctor loggedDoctor;
    private Connection conn;
    private ArrayList<Doctor> doctors;

    public ChooseEditDoctorController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void transferData(ObservableList<AppointmentTableRow> data, Doctor doctor, Connection usedConn) {
        conn = usedConn;
        loggedDoctor = doctor;

        DBContext dbContext = new DBContext();
        try {
            doctors = dbContext.getDoctors(conn);

            for (Doctor d : doctors) {
                doctorList.getItems().add(d.toString());
            }
            doctorList.setValue(doctors.get(0).toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void choosePatient(ActionEvent event) throws IOException {
        choosePatientFromData();
    }

    @FXML
    public void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private void choosePatientFromData() throws IOException {
    //     Parent root;
    //     String s_patient = doctorList.getValue();
    //     Doctor editedPatient = null;

    //     for (Doctor d : doctors) {
    //         if (d.toString().equals(s_patient)) {
    //             editedPatient = d;
    //             break;
    //         }
    //     }

    //     try {
    //         FXMLLoader loader = new FXMLLoader(getClass().getResource("editPatientScreen.fxml"));
    //         root = loader.load();
    //         EditPatientController epc = loader.getController();

    //         Stage parentStage = (Stage) cancelButton.getScene().getWindow();

    //         epc.transferPatient(editedPatient, loggedDoctor, conn, parentStage);
    //         int sceneX = 300;
    //         int sceneY = 320;
    //         Stage stage = new Stage();
    //         stage.getIcons().add(new Image(App.class.getResource("appointr_logo.png").toString()));
    //         stage.setTitle("Edit patient");
    //         stage.setScene(new Scene(root, sceneX, sceneY));
    //         stage.setResizable(false);
    //         stage.show();

    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    }

}
