package edu.iipw.pap;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AppController {

    public AppController() { }

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
        if (loginField.getText().toString().equals("admin") && passwordField.getText().toString().equals("admin")) {
            a.changeScene("doctorView.fxml");
        } else {
            System.out.println("Wrong login or password");
        }
    }

}
