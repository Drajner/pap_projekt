package edu.iipw.pap;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Parent;

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
        Parent root;

        DBContext dbContext = new DBContext();
        Connection conn = dbContext.getConnection();

        Populate populate = new Populate();
        ArrayList<Doctor> doctors = populate.doctors;

        String login = loginField.getText();
        String password = passwordField.getText();

        boolean isLogin = false;
        for (Doctor d: doctors) {
            if (login.equals(d.getLogin()) && password.equals(d.getPassword())) {
                isLogin = true;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("doctorView.fxml"));
                root = loader.load();
                doctorViewController dvc = loader.getController();
                dvc.usedDoctorAndConn(d, conn);
                a.changeScene("doctorView.fxml");
            }
        }
        if (!isLogin) {
            System.out.println("Wrong login or password");
        }
    }

}
