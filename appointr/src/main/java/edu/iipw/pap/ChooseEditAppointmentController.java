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
import java.util.ResourceBundle;

public class ChooseEditAppointmentController implements Initializable {

    public ChooseEditAppointmentController() {}

    @FXML
    private ChoiceBox<String> appointmentList;
    @FXML
    private Button chooseButton;
    @FXML
    private Button cancelButton;

    private ObservableList<TableRow> tempData;

    private Doctor loggedDoctor;

    private Connection conn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void transferData(ObservableList<TableRow> data, Doctor doctor, Connection usedConn) {
        loggedDoctor = doctor;
        conn = usedConn;
        tempData = data;
        for (TableRow tr: data) {
            appointmentList.getItems().add(tr.getAppointment().toString());
        }
        appointmentList.setValue(data.get(0).getAppointment().toString());
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
        try {
            root = FXMLLoader.load(getClass().getResource("editAppointmentScreen.fxml"));
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
