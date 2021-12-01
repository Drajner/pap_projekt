package edu.iipw.pap;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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


        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("LoggingScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("Gabinet");
        stage.setScene(scene);
        stage.show();

    }

    public static void showAppointments(ArrayList<Appointment> appointments){
        int c = 0;
        for (Appointment a : appointments) {
            System.out.println(c + ". " + a);
            c += 1;
        }
    }

    public static void addPatient(ArrayList<Patient> patients) {
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

    public static void addAppointment(Doctor doctor, ArrayList<Patient> patients, ArrayList<Appointment> appointments){
        Scanner sc = new Scanner(System.in);
        String address;
        LocalDateTime appointment_date;
        Patient patient;

        printPatients(patients);
        System.out.print("Wbierz pacjenta po indeksie: ");  patient = patients.get(sc.nextInt());
        System.out.print("Data urodzenia: "); appointment_date = LocalDateTime.parse(sc.nextLine());
        System.out.print("Adres: "); address = sc.nextLine();
        Appointment a = new Appointment(doctor, patient, appointment_date, address);
        appointments.add(a);
    }

    public static void printPatients(ArrayList<Patient> patients){
        int c = 0;
        for (Patient p: patients) {
            System.out.println(c + '.' + p.getFullName());
            c += 1;
        }
    }

    public static void changeAppointment(ArrayList<Appointment> appointments, ArrayList<Patient> patients){
        Scanner sc = new Scanner(System.in);
        Appointment appointment;
        String attribute;

        showAppointments(appointments);
        System.out.print("Wybierz wizyte po indeksie: "); appointment = appointments.get(sc.nextInt());

        boolean fin = false;
        while(!fin){
            System.out.println(appointment);
            System.out.print("Wpisz atrybut do zmiany lub opuść edytowanie: {patient, datetime, address, quit}: ");
            attribute = sc.nextLine();
            switch (attribute) {
                case "patient":
                    printPatients(patients);
                    System.out.print("Wybierz nowego pacjenta po indeksie: ");
                    appointment.setPatient(patients.get(sc.nextInt()));
                    break;
                case "datetime":
                    System.out.print("Wprowadź nową datę i czas spotkania: ");
                    appointment.setTimeOfAppointment(LocalDateTime.parse(sc.nextLine()));
                    break;
                case "address":
                    System.out.print("Wprowadź nowy adres: ");
                    appointment.setAddress(sc.nextLine());
                    break;
                case "quit":
                    fin = true;
                    break;
            }
        }
    }




    public static void main(String[] args) {
        //launch();
        ArrayList<Patient> patients = new ArrayList<Patient>();
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        LocalDate  data = LocalDate.parse("1970-01-01");
        Doctor Jacek = new Doctor("Jacek", "Kowalski", data , "Dentysta", "jacek", "jacek");
        boolean notEnd = true;
        Scanner inputScan = new Scanner(System.in);
        String input;
        int inputValue = 0;
        System.out.println("Witamy w Appointr!");
        while(notEnd){
            System.out.println("1. Pokaż wizyty");
            System.out.println("2. Pokaż pacjentów");
            System.out.println("3. Dodaj pacjenta");
            System.out.println("4. Dodaj wizytę");
            System.out.println("5. Edytuj wizytę");
            System.out.println("6. Usuń pacjenta. Do użycia tylko wtedy gdy są pacjenci.");
            System.out.println("7. Usuń wizytę. Do użycia tylko wtedy gdy są wizyty.");
            System.out.println("8. Zakończ");
            input =  inputScan.nextLine();
            switch(input){
                case "1":
                    showAppointments(appointments);
                    break;
                case "2":
                    printPatients(patients);
                    break;
                case "3":
                    addPatient(patients);
                    break;
                case "4":
                    addAppointment(Jacek, patients, appointments);
                    break;
                case "5":
                    changeAppointment(appointments, patients);
                    break;
                case "6":
                    System.out.println("Którego pacjenta chcesz usunąć (podaj cyfrę)?");
                    input =  inputScan.nextLine();
                    inputValue = Integer.valueOf(input) - 1;
                    patients.remove(inputValue);
                    break;
                case "7":
                    System.out.println("Którą wizytę chcesz usunąć (podaj cyfrę)?");
                    input =  inputScan.nextLine();
                    inputValue = Integer.valueOf(input) - 1;
                    appointments.remove(inputValue);
                    break;
                case "8":
                    notEnd = false;
                    break;
            }

        }
    }

}
