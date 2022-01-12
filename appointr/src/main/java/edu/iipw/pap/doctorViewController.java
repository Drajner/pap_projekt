package edu.iipw.pap;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class doctorViewController implements Initializable{

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
    @FXML
    private TableView<TableRow> appointmentTable;
    @FXML
    private TableColumn<TableRow, Integer> numberColumn;
    @FXML
    private TableColumn<TableRow, LocalDate> dateColumn;
    @FXML
    private TableColumn<TableRow, String> nameColumn;
    @FXML
    private TableColumn<TableRow, String> addressColumn;

    private ObservableList<TableRow> data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numberColumn.setCellValueFactory(new PropertyValueFactory<TableRow, Integer>("index"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<TableRow, LocalDate>("date"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<TableRow, String>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<TableRow, String>("address"));

        Populate populate = new Populate();
        ArrayList<Appointment> appointments = populate.appointments;
        ArrayList<TableRow> rows = new ArrayList<TableRow>();
        for (int i = 0; i < appointments.size(); i++) {
            rows.add(new TableRow(appointments.get(i), i + 1));
        }

        data = FXCollections.observableArrayList(rows);

        appointmentTable.setItems(data);
    }

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("chooseEditPatientScreen.fxml"));
            root = loader.load();
            ChooseEditPatientController cepc = loader.getController();
            cepc.transferData(data);
            int sceneX = 200;
            int sceneY = 150;
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
            // this:
            FXMLLoader loader = new FXMLLoader(getClass().getResource("deletePatientScreen.fxml"));
            root = loader.load();
            DeletePatientController dpc = loader.getController();
            dpc.transferData(data);
            // instead of this:
            // root = FXMLLoader.load(getClass().getResource("deletePatientScreen.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("chooseEditAppointmentScreen.fxml"));
            root = loader.load();
            ChooseEditAppointmentController ceac = loader.getController();
            ceac.transferData(data);
            int sceneX = 200;
            int sceneY = 150;
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("deletePatientScreen.fxml"));
            root = loader.load();
            DeleteAppointmentController dac = loader.getController();
            dac.transferData(data);
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
