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
import javafx.scene.image.Image;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
// import javafx.event.ActionEvent;
// import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;

// Links for further reading:
// https://www.lwjgl.org/guide
// https://javastart.pl/baza-wiedzy/java-zaawansowane/enum
// https://www.freecodecamp.org/news/java-string-to-int-how-to-convert-a-string-to-an-integer/

/** JavaFX App. */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        /*
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

        Button addPatientButton = new Button();
        addPatientButton.setText("Dodaj");
        Label label = new Label(labelString);
        label.setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-font-size: 12px;");
        StackPane root = new StackPane(label);
        root.getChildren().add(addPatientButton);
        Scene scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
        */

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("LoggingScreen.fxml"));
        int sceneX = 640;
        int sceneY = 480;
        Scene scene = new Scene(fxmlLoader.load(), sceneX, sceneY);
        stage.getIcons().add(new Image(Icon.icon()));
        stage.setTitle("Appointr");
        stage.setScene(scene);
        stage.show();

    }

    public static void showAppointments(ArrayList<Appointment> appointments) {
        if (appointments.isEmpty()) {
            System.out.println("Brak wizyt");
        } else {
            Appointment a;
            for (int i = 0; i < appointments.size(); i++) {
                a = appointments.get(i);
                System.out.println(i + ". " + a);
            }
        }
    }

    public static void printPatients(ArrayList<Patient> patients){
        if (patients.isEmpty()) {
            System.out.println("Brak pacjentów");
        } else {
            Patient p;
            for (int i = 0; i < patients.size(); i++) {
                p = patients.get(i);
                System.out.println(i + ". " + p);
            }
        }
    }

    public static void addPatient(ArrayList<Patient> patients) {
        Scanner sc = new Scanner(System.in);
        String name, surname, description;
        LocalDate birthDate;

        System.out.print("Imię: "); name = sc.nextLine();
        System.out.print("Nazwisko: "); surname = sc.nextLine();
        System.out.print("Data urodzenia: "); birthDate = LocalDate.parse(sc.nextLine());
        System.out.print("Opis: "); description = sc.nextLine();
        sc.close();
        Patient p = new Patient(name, surname, birthDate, description);
        patients.add(p);
    }

    public static void addAppointment(Doctor doctor, ArrayList<Patient> patients, ArrayList<Appointment> appointments){
        Scanner sc = new Scanner(System.in);
        String address;
        String datetime = "";
        LocalDateTime appointmentDate;
        Patient patient;

        printPatients(patients);
        System.out.print("Wbierz pacjenta po indeksie: "); patient = patients.get(sc.nextInt());
        sc.nextLine();
        System.out.print("Data wizyty (YYYY-MM-DD): "); datetime += sc.nextLine();
        System.out.print("Godzina wizyty (HH:MM:SS): "); datetime += "T" + sc.nextLine();
        appointmentDate = LocalDateTime.parse(datetime);
        System.out.print("Adres: "); address = sc.nextLine();
        sc.close();
        Appointment a = new Appointment(doctor, patient, appointmentDate, address);
        appointments.add(a);
    }

    public static void changeAppointment(ArrayList<Appointment> appointments, ArrayList<Patient> patients){
        Scanner sc = new Scanner(System.in);
        Appointment appointment;
        String attribute;

        showAppointments(appointments);
        System.out.print("Wybierz wizyte po indeksie: "); appointment = appointments.get(sc.nextInt());
        sc.nextLine();

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
                    sc.nextLine();
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
                    sc.close();
                    break;
                default:
                    System.out.println("Niepoprawny atrybut.");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        // launch();
        ArrayList<Patient> patients = new ArrayList<>();
        Patient p = new Patient("Adam", "Nowak", LocalDate.parse("2001-05-03"), "chore wątroba");
        patients.add(p);
        ArrayList<Appointment> appointments = new ArrayList<>();
        LocalDate data = LocalDate.parse("1970-01-01");
        Doctor doctor1 = new Doctor("Jacek", "Kowalski", data, "Dentysta", "jacek", "kEcAj");
        Scanner inputScan = new Scanner(System.in);
        String input;
        int inputValue;
        System.out.println("Witamy w Appointr!");
        while(true) {
            System.out.println("\n1. Pokaż wizyty");
            System.out.println("2. Pokaż pacjentów");
            System.out.println("3. Dodaj pacjenta");
            System.out.println("4. Dodaj wizytę");
            System.out.println("5. Edytuj wizytę");
            System.out.println("6. Usuń pacjenta. Do użycia tylko wtedy gdy są pacjenci.");
            System.out.println("7. Usuń wizytę. Do użycia tylko wtedy gdy są wizyty.");
            System.out.println("8. Zakończ\n");
            System.out.print("Wpisz numer: ");
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
                    addAppointment(doctor1, patients, appointments);
                    break;
                case "5":
                    changeAppointment(appointments, patients);
                    break;
                case "6":
                    printPatients(patients);
                    System.out.println("\nKtórego pacjenta chcesz usunąć?");
                    System.out.print("Podaj indeks: ");
                    input =  inputScan.nextLine();
                    inputValue = Integer.parseInt(input);
                    patients.remove(inputValue);
                    break;
                case "7":
                    showAppointments(appointments);
                    System.out.println("Którą wizytę chcesz usunąć?\n");
                    System.out.print("Podaj indeks: ");
                    input =  inputScan.nextLine();
                    inputValue = Integer.parseInt(input);
                    appointments.remove(inputValue);
                    break;
                case "8":
                    System.out.println("Kończę pracę.");
                    inputScan.close();
                    System.exit(0);
                case "9":
                    System.out.println("Interline test: \n");
                    CLI cli = new CLI();
                    cli.interline(5);
                    break;
                default:
                    System.out.println("Nie ma takiej opcji. Spróbuj ponownie:");
            }

        }
    }

}
