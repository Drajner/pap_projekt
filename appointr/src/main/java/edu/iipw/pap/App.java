package edu.iipw.pap;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        String javaVersion = SystemInfo.javaVersion();
        String javafxVersion = SystemInfo.javafxVersion();

        Doctor doctor1 = new Doctor("John", "Moore", LocalDate.parse("1980-07-17"), "Dentist", "qwerty", "password");
        Patient patient1 = new Patient("Adam", "Durham", LocalDate.parse("1997-04-26"), "Severe toothache");
        Appointment appointment1 = new Appointment(doctor1, patient1, LocalDateTime.parse("2021-12-02T10:15:00"), "Baker Street 221B");

        String labelString = new String("Java version: " + javaVersion + "\n" +
                "JavaFX version: " + javafxVersion + "\n" +
                "Doctor: " + doctor1.toString() + "\n" +
                "Patient: " + patient1.toString() + "\n" +
                "Appointment: " + appointment1.toString());

        Label label = new Label(labelString);
        label.setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-font-size: 12px;");
        Scene scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
