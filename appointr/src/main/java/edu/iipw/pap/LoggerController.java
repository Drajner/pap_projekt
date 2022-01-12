package edu.iipw.pap;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoggerController {

    public LoggerController() { }

    @FXML
    private Button logInButton;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;

    public void userLogIn(ActionEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {
        App a = new App();

        Populate populate = new Populate();
        ArrayList<Doctor> doctors = populate.doctors;

        String login = loginField.getText();
        String password = passwordField.getText();

        for (Doctor d: doctors) {
            if (login.equals(d.getLogin()) && password.equals(d.getPassword()))
                a.changeScene("doctorView.fxml");
        }
        System.out.println("Wrong login or password");
    }

}
