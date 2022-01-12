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
import java.util.ResourceBundle;

public class ChooseEditPatientController implements Initializable {

    public ChooseEditPatientController() {}

    @FXML
    private ChoiceBox<String> patientList;
    @FXML
    private Button chooseButton;
    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void transferData(ObservableList<TableRow> data) {
        for (TableRow tr: data) {
            patientList.getItems().add(tr.getAppointment().getPatient().toString());
        }
        //patientList.setValue(populate.patients.get(0).toString());
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
        try {
            root = FXMLLoader.load(getClass().getResource("editPatientScreen.fxml"));
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
