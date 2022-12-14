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

public class ChooseEditAppointmentController implements Initializable {

    @FXML
    private ChoiceBox<String> appointmentList;
    @FXML
    private Button chooseButton;
    @FXML
    private Button cancelButton;
    private Doctor loggedDoctor;
    private ArrayList<Appointment> appointments;
    private Connection conn;

    public ChooseEditAppointmentController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void transferData(ObservableList<AppointmentTableRow> data, Doctor doctor, Connection usedConn) {
        loggedDoctor = doctor;
        conn = usedConn;
        Appointment appointment = null;

        DBContext dbContext = new DBContext();
        try {
            appointments = dbContext.getAppointments(conn);

            for (Appointment a : appointments) {
                if (a.getDoctor().getPesel().equals(loggedDoctor.getPesel())) {
                    if (appointment == null)
                        appointment = a;
                    appointmentList.getItems().add(a.toString());
                }
            }

            if (appointment != null)
                appointmentList.setValue(appointment.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chooseAppointment(ActionEvent event) throws IOException {
        chooseAppointmentFromData();
    }

    public void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private void chooseAppointmentFromData() throws IOException {
        Parent root;
        String s_appointment = appointmentList.getValue();
        Appointment editedAppointment = null;

        for (Appointment p : appointments) {
            if (p.toString().equals(s_appointment)) {
                editedAppointment = p;
                break;
            }
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editAppointmentScreen.fxml"));
            root = loader.load();
            EditAppointmentController eac = loader.getController();

            Stage parentStage = (Stage) cancelButton.getScene().getWindow();

            eac.transferAppointment(editedAppointment, loggedDoctor, conn, parentStage);
            int sceneX = 240;
            int sceneY = 240;
            Stage stage = new Stage();
            stage.getIcons().add(new Image(App.class.getResource("appointr_logo.png").toString()));
            stage.setTitle("Edit appointment");
            stage.setScene(new Scene(root, sceneX, sceneY));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
