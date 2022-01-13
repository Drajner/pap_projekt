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

    public ChooseEditAppointmentController() {}

    @FXML
    private ChoiceBox<String> appointmentList;
    @FXML
    private Button chooseButton;
    @FXML
    private Button cancelButton;

    private ObservableList<AppointmentTableRow> tempData;

    private Doctor loggedDoctor;

    private ArrayList<Appointment> appointments;

    private Connection conn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void transferData(ObservableList<AppointmentTableRow> data, Doctor doctor, Connection usedConn) {
        loggedDoctor = doctor;
        conn = usedConn;
        tempData = data;

        DBContext dbContext = new DBContext();
        try {
            appointments = dbContext.getAppointments(conn);

            for (Appointment a: appointments) {
                appointmentList.getItems().add(a.toString());
            }
            appointmentList.setValue(appointments.get(0).toString());

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

    private void chooseAppointmentFromData() throws IOException{
        Parent root;
        /*String[] splitAppointment = appointmentList.getValue().split(" ");
        int editedAppointmentId = Integer.parseInt(splitAppointment[0]);
        Appointment editedAppointment = tempData.get(0).getAppointment();
        for (AppointmentTableRow tr: tempData) {
            if(tr.getAppointment().getId() == editedAppointmentId)
            {
                editedAppointment = tr.getAppointment();
                break;
            }
        }*/
        String s_appointment = appointmentList.getValue();
        Appointment editedAppointment = null;

        for (Appointment p: appointments) {
            if (p.toString().equals(s_appointment)){
                editedAppointment = p;
                break;
            }
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editAppointmentScreen.fxml"));
            root = loader.load();
            EditAppointmentController eac = loader.getController();
            eac.transferAppointment(editedAppointment, loggedDoctor, conn);
            int sceneX = 240;
            int sceneY = 240;
            Stage stage = new Stage();
            stage.getIcons().add(new Image(App.class.getResource("appointr_logo.png").toString()));
            stage.setTitle("Edit appointment");
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
