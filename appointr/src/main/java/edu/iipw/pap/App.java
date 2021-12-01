package edu.iipw.pap;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
// import java.time.LocalDate;
// import java.time.LocalDateTime;

import javafx.application.Application;
import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
// import javafx.event.ActionEvent;
// import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;


/**
 * JavaFX App.
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        /*String javaVersion = SystemInfo.javaVersion();
        String javafxVersion = SystemInfo.javafxVersion();

        Doctor doctor1 = new Doctor("John", "Moore", LocalDate.parse("1980-07-17"), "Dentist", "qwerty", "password");
        Patient patient1 = new Patient("Adam", "Durham", LocalDate.parse("1997-04-26"), "Severe toothache");
        Appointment appointment1 = new Appointment(doctor1, patient1, LocalDateTime.parse("2021-12-02T10:15:00"), "Baker Street 221B");

        String labelString = new String("Java version: " + javaVersion + "\n" +
                "JavaFX version: " + javafxVersion + "\n" +
                "Doctor: " + doctor1.toString() + "\n" +
                "Patient: " + patient1.toString() + "\n" +
                "Appointment: " + appointment1.toString());

        Button addPatientButton = new Button();
        addPatientButton.setText("Dodaj");
        Label label = new Label(labelString);
        label.setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-font-size: 12px;");
        StackPane root = new StackPane(label);
        root.getChildren().add(addPatientButton);
        Scene scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();  */
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("Gabinet");
        stage.setScene(scene);
        stage.show();

    }

    public void addPatient(ArrayList<Patient> patients) {
        Scanner sc = new Scanner(System.in);
        String name, surname, description;
        LocalDate birth_date;
        System.out.print("Imię: "); name = sc.nextLine();
        System.out.print("Nazwisko: "); surname = sc.nextLine();
        System.out.print("Data urodzenia: "); birth_date = LocalDate.parse(sc.nextLine());
        System.out.print("Opis: "); description = sc.nextLine();
        Patient p = new Patient(name, surname, birth_date, description);
        patients.add(p);
    }

    public static void main(String[] args) {
        launch();
    }

}
