package edu.iipw.pap;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class doctorViewController {

    public doctorViewController() { }

    @FXML
    private Button addPatientButton;
    @FXML
    private Button editPatientButton;
    @FXML
    private Button deletePatientButton;
    @FXML
    private Button addAppointmentButton;
    @FXML
    private Button editAppointmentButton;
    @FXML
    private Button deleteAppointmentButton;

    public void addPatient(ActionEvent event) throws IOException {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("addPatientScreen.fxml"));
            int sceneX = 300;
            int sceneY = 320;
            Stage stage = new Stage();
            stage.getIcons().add(new Image(App.class.getResource("appointr_logo.png").toString()));
            stage.setTitle("Add patient");
            stage.setScene(new Scene(root, sceneX, sceneY));
            stage.setResizable(false);
            stage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void editPatient(ActionEvent event) throws IOException {
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
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void deletePatient(ActionEvent event) throws IOException {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("deletePatientScreen.fxml"));
            int sceneX = 200;
            int sceneY = 150;
            Stage stage = new Stage();
            stage.getIcons().add(new Image(App.class.getResource("appointr_logo.png").toString()));
            stage.setTitle("Delete patient");
            stage.setScene(new Scene(root, sceneX, sceneY));
            stage.setResizable(false);
            stage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void addAppointment(ActionEvent event) throws IOException {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("addAppointmentScreen.fxml"));
            int sceneX = 240;
            int sceneY = 240;
            Stage stage = new Stage();
            stage.getIcons().add(new Image(App.class.getResource("appointr_logo.png").toString()));
            stage.setTitle("Add appointment");
            stage.setScene(new Scene(root, sceneX, sceneY));
            stage.setResizable(false);
            stage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void editAppointment(ActionEvent event) throws IOException {
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
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteAppointment(ActionEvent event) throws IOException {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("deleteAppointmentScreen.fxml"));
            int sceneX = 200;
            int sceneY = 150;
            Stage stage = new Stage();
            stage.getIcons().add(new Image(App.class.getResource("appointr_logo.png").toString()));
            stage.setTitle("Delete appointment");
            stage.setScene(new Scene(root, sceneX, sceneY));
            stage.setResizable(false);
            stage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
