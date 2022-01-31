package edu.iipw.pap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoggingScreenController implements Initializable {

    @FXML
    private Button logInButton;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    private ArrayList<Doctor> doctors;
    private ArrayList<String> admins;
    private Connection conn = null;
    public LoggingScreenController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (conn == null) {

            DBContext dbContext = new DBContext();
            conn = dbContext.getConnection();

            try {
                doctors = dbContext.getDoctors(conn);
                admins = dbContext.getAdmins(conn);
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
        for (Doctor d : doctors) {
            if (login.equals(d.getLogin()) && password.equals(d.getPassword())) {
                isLogin = true;

                Parent root;
                if (admins.contains(d.getPesel())) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("adminView.fxml"));
                    root = loader.load();
                    AdminViewController avc = loader.getController();
                    avc.usedDoctorAndConn(d, conn);
                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("doctorView.fxml"));
                    root = loader.load();
                    DoctorViewController dvc = loader.getController();
                    dvc.usedDoctorAndConn(d, conn);
                }

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

                stage2.getScene().getStylesheets().add(App.class.getResource("doctorViewCSS.css").toExternalForm());

                stage2.show();
                Stage stage = (Stage) logInButton.getScene().getWindow();
                stage.close();
            }
        }
        if (!isLogin) {
            System.out.println("Wrong login or password");
        }
    }
}
