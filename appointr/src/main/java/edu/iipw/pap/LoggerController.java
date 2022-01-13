package edu.iipw.pap;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class LoggerController implements Initializable {

    public LoggerController() { }

    @FXML
    private Button logInButton;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;

    private ArrayList<Doctor> doctors;
    private Connection conn = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (conn == null) {

            DBContext dbContext = new DBContext();
            conn = dbContext.getConnection();

            try {
                doctors = dbContext.getDoctors(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void userLogIn(ActionEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {
        String login = loginField.getText();
        String password = passwordField.getText();

        boolean isLogin = false;
        for (Doctor d: doctors) {
            if (login.equals(d.getLogin()) && password.equals(d.getPassword())) {
                isLogin = true;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("doctorView.fxml"));
                Parent root = loader.load();
                doctorViewController dvc = loader.getController();
                dvc.usedDoctorAndConn(d, conn);
                Stage stage2 = new Stage();
                int sceneX = 300;
                int sceneY = 320;
                stage2.getIcons().add(new Image(App.class.getResource("appointr_logo.png").toString()));
                stage2.setTitle("Appointr");
                stage2.setScene(new Scene(root, sceneX, sceneY));
                stage2.setResizable(true);
                stage2.setHeight(480);
                stage2.setWidth(640);
                stage2.setMinHeight(360);
                stage2.setMinWidth(380);
                stage2.show();
                //a.changeScene("doctorView.fxml");
                Stage stage = (Stage) logInButton.getScene().getWindow();
                stage.close();
            }
        }
        if (!isLogin) {
            System.out.println("Wrong login or password");
        }
    }
}
