package edu.iipw.pap;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AddDoctorController {

    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;
    @FXML
    private DatePicker dataField;
    @FXML
    private TextField genderField;
    @FXML
    private TextField loginField;
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField peselField;
    @FXML
    private TextField specializationField;
    @FXML
    private TextField surnameField;
    private Connection conn;

    public AddDoctorController() {
    }

    public void transferConn(Connection usedConn) {
        conn = usedConn;
    }

    @FXML
    public void addDoctor(ActionEvent event) throws IOException {
        Doctor doctor = createDoctorFromData();

        try {
            DBContext.addDoctor(conn, doctor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.fireEvent(
                new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST)
        );
    }

    @FXML
    public void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private Doctor createDoctorFromData() throws IOException {
        return new Doctor(peselField.getText(),
                          nameField.getText(),
                          surnameField.getText(),
                          dataField.getValue(),
                          specializationField.getText(),
                          loginField.getText(),
                          passwordField.getText(),
                          new ArrayList<Appointment>(),
                          genderField.getText().charAt(0),
                          1);
    }
}
