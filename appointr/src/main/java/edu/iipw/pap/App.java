package edu.iipw.pap;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * JavaFX App.
 */
public class App extends Application {
    private static Stage stg;

    public static void showAppointments(ArrayList<Appointment> appointments) {
        if (appointments.isEmpty()) {
            System.out.println("Brak wizyt.");
        } else {
            ArrayList<Integer> listingColor = new ArrayList<>();
            listingColor.add(250);
            listingColor.add(75);
            listingColor.add(75);
            Appointment a;
            for (int i = 0; i < appointments.size(); i++) {
                a = appointments.get(i);
                System.out.println(CLI.ANSI_RGB(listingColor, i + ". ") + CLI.ANSI_RESET + a);
            }
        }
    }

    public static void showDoctors(ArrayList<Doctor> doctors) {
        if (doctors.isEmpty()) {
            System.out.println("Brak lekarzy.");
        } else {
            ArrayList<Integer> listingColor = new ArrayList<>();
            listingColor.add(250);
            listingColor.add(75);
            listingColor.add(75);
            Doctor d;
            for (int i = 0; i < doctors.size(); i++) {
                d = doctors.get(i);
                System.out.println(CLI.ANSI_RGB(listingColor, i + ". ") + CLI.ANSI_RESET + d);
            }
        }
    }

    public static void showPatients(ArrayList<Patient> patients) {
        if (patients.isEmpty()) {
            System.out.println("Brak pacjentów.");
        } else {
            ArrayList<Integer> listingColor = new ArrayList<>();
            listingColor.add(250);
            listingColor.add(75);
            listingColor.add(75);
            Patient p;
            for (int i = 0; i < patients.size(); i++) {
                p = patients.get(i);
                System.out.println(CLI.ANSI_RGB(listingColor, i + ". ") + CLI.ANSI_RESET + p);
            }
        }
    }

    public static void addAppointment(Doctor doctor, ArrayList<Patient> patients, ArrayList<Appointment> appointments) {
        Scanner sc = new Scanner(System.in);
        int officeId;
        String datetime = "";
        LocalDateTime appointmentDate;
        Patient patient;

        showPatients(patients);
        System.out.print("Wybierz pacjenta po indeksie: ");
        patient = patients.get(sc.nextInt());
        sc.nextLine();
        System.out.print("Data wizyty (YYYY-MM-DD): ");
        datetime += sc.nextLine();
        System.out.print("Godzina wizyty (HH:MM:SS): ");
        datetime += "T" + sc.nextLine();
        appointmentDate = LocalDateTime.parse(datetime);
        System.out.print("Gabinet: ");
        officeId = sc.nextInt();
        int id = 500;
        Appointment a = new Appointment(id, doctor, patient, appointmentDate, officeId);
        appointments.add(a);
    }

    public static void addDoctor(ArrayList<Doctor> doctors) {
        Scanner sc = new Scanner(System.in);
        String pesel, name, surname, specialization, login, password;
        LocalDate birthDate;
        Character gender;

        System.out.print("PESEL: ");
        pesel = sc.nextLine();
        System.out.print("Imię: ");
        name = sc.nextLine();
        System.out.print("Nazwisko: ");
        surname = sc.nextLine();
        System.out.print("Data urodzenia: ");
        birthDate = LocalDate.parse(sc.nextLine());
        System.out.print("Specjalizacja: ");
        specialization = sc.nextLine();
        System.out.print("Login: ");
        login = sc.nextLine();
        System.out.print("Hasło: ");
        password = sc.nextLine();
        System.out.println("Płeć: ");
        gender = sc.nextLine().charAt(0);
        Doctor d = new Doctor(pesel, name, surname, birthDate, specialization, login, password, new ArrayList<Appointment>(), gender);
        doctors.add(d);
    }

    public static void addPatient(ArrayList<Patient> patients) {
        Scanner sc = new Scanner(System.in);
        String pesel, name, surname, description;
        LocalDate birthDate;
        Character gender;

        System.out.print("Pesel: ");
        pesel = sc.nextLine();
        System.out.print("Imię: ");
        name = sc.nextLine();
        System.out.print("Nazwisko: ");
        surname = sc.nextLine();
        System.out.print("Data urodzenia: ");
        birthDate = LocalDate.parse(sc.nextLine());
        System.out.print("Opis dolegliwości: ");
        description = sc.nextLine();
        System.out.println("Płeć: ");
        gender = sc.nextLine().charAt(0);
        Patient p = new Patient(pesel, name, surname, birthDate, description, gender);
        patients.add(p);
    }

    public static void changePatient(ArrayList<Patient> patients) {
        Scanner sc = new Scanner(System.in);
        Patient patient;
        String attribute;

        showPatients(patients);
        System.out.print("Wybierz pacjenta po indeksie: ");
        patient = patients.get(sc.nextInt());
        sc.nextLine();

        boolean fin = false;
        while (!fin) {
            System.out.println(patient);
            System.out.print("Wybierz atrybut do zmiany lub opuść edytowanie: {name, surname, dateOfBirth, description, quit}: ");
            attribute = sc.nextLine();
            switch (attribute) {
                case "name":
                    System.out.print("Wprowadź nowe imię: ");
                    patient.setName(sc.nextLine());
                    break;
                case "surname":
                    System.out.print("Wprowadź nowe nazwisko: ");
                    patient.setSurname(sc.nextLine());
                    break;
                case "dateOfBirth":
                    System.out.print("Wprowadź nową datę urodzenia: ");
                    patient.setDateOfBirth(LocalDate.parse(sc.nextLine()));
                    break;
                case "description":
                    System.out.print("Wprowadź nowy opis: ");
                    patient.setDescription(sc.nextLine());
                    break;
                case "quit":
                    fin = true;
                    break;
                default:
                    System.out.println("Niepoprawny atrybut.");
                    break;
            }
        }
    }

    public static void changeAppointment(ArrayList<Appointment> appointments, ArrayList<Doctor> doctors, ArrayList<Patient> patients) {
        Scanner sc = new Scanner(System.in);
        Appointment appointment;
        String attribute;

        showAppointments(appointments);
        System.out.print("Wybierz wizyte po indeksie: ");
        appointment = appointments.get(sc.nextInt());
        sc.nextLine();

        boolean fin = false;
        while (!fin) {
            System.out.println(appointment);
            System.out.print("Wybierz atrybut do zmiany lub opuść edytowanie: {doctor, patient, datetime, address, quit}: ");
            attribute = sc.nextLine();
            switch (attribute) {
                case "doctor":
                    showDoctors(doctors);
                    System.out.print("Wybierz lekarza po indeksie: ");
                    appointment.setDoctor(doctors.get(sc.nextInt()));
                    sc.nextLine();
                    break;
                case "patient":
                    showPatients(patients);
                    System.out.print("Wybierz nowego pacjenta po indeksie: ");
                    appointment.setPatient(patients.get(sc.nextInt()));
                    sc.nextLine();
                    break;
                case "datetime":
                    System.out.print("Wprowadź nową datę i czas spotkania: ");
                    appointment.setTimeOfAppointment(LocalDateTime.parse(sc.nextLine()));
                    break;
                case "address":
                    System.out.print("Wprowadź nowy gabinet: ");
                    appointment.setOfficeId(sc.nextInt());
                    break;
                case "quit":
                    fin = true;
                    break;
                default:
                    System.out.println("Niepoprawny atrybut.");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        /*
        ArrayList<Patient> patients = new ArrayList<>();
        ArrayList<Appointment> appointments = new ArrayList<>();
        ArrayList<Doctor> doctors = new ArrayList<>();
        Patient patient1 = new Patient("Kamil", "Nowak", LocalDate.parse("2001-05-03"), "Cysts on the liver");
        Patient patient2 = new Patient("Adam", "Durham", LocalDate.parse("1997-04-26"), "Severe toothache");
        patients.add(patient1);
        patients.add(patient2);
        Doctor doctor1 = new Doctor("Jacek", "Kowalski", LocalDate.parse("1970-01-01"), "Optometrist", "jacek", "kEcAj", new ArrayList<Appointment>());
        Doctor doctor2 = new Doctor("John", "Moore", LocalDate.parse("1980-07-17"), "Dentist", "qwerty", "password", new ArrayList<Appointment>());
        doctors.add(doctor1);
        doctors.add(doctor2);
        Appointment appointment1 = new Appointment(doctor2, patient2, LocalDateTime.parse("2021-12-02T10:15:00"), "Baker Street 221B");
        Appointment appointment2 = new Appointment(doctor2, patient1, LocalDateTime.parse("2021-12-02T10:15:00"), "Baker Street 222A");
        doctor2.addAppointment(appointment1);
        doctor2.addAppointment(appointment2);  // should say that doctor2 cannot have appointment2 assigned to him
        for(int i = 0; i < doctor2.getAppointments().size(); i++){
            appointments.add(doctor2.getAppointments().get(i));
        }
        */
        Populate populate = new Populate();
        // ObservableList<Patient> patients = populate.patients;
        ArrayList<Patient> patients = populate.patients;
        ArrayList<Doctor> doctors = populate.doctors;
        ArrayList<Appointment> appointments = populate.appointments;

        Scanner sc = new Scanner(System.in);
        String input;
        int inputValue;
        System.out.println(CLI.interlineGradient(18, 100, 150, 200, 200, 25, 25));
        System.out.println(CLI.ANSI_RGB_Gradient(150, 200, 250, 250, 75, 75, "Witamy w Appointr!"));
        System.out.println(CLI.interlineGradient(18, 100, 150, 200, 200, 25, 25));
        ArrayList<Integer> listingColor = new ArrayList<>();
        listingColor.add(100);
        listingColor.add(150);
        listingColor.add(200);
        while (true) {
            /*
            System.out.println(CLI.ANSI_RGB(listingColor, "1.") + CLI.ANSI_RESET + " Pokaż wizyty");
            System.out.println(CLI.ANSI_RGB(listingColor, "2.") + CLI.ANSI_RESET + " Pokaż lekarzy");
            System.out.println(CLI.ANSI_RGB(listingColor, "3.") + CLI.ANSI_RESET + " Pokaż pacjentów");
            System.out.println(CLI.ANSI_RGB(listingColor, "4.") + CLI.ANSI_RESET + " Dodaj wizytę (dla pierwszego lekarza)");
            System.out.println(CLI.ANSI_RGB(listingColor, "5.") + CLI.ANSI_RESET + " Dodaj lekarza");
            System.out.println(CLI.ANSI_RGB(listingColor, "6.") + CLI.ANSI_RESET + " Dodaj pacjenta");
            System.out.println(CLI.ANSI_RGB(listingColor, "7.") + CLI.ANSI_RESET + " Edytuj wizytę");
            System.out.println(CLI.ANSI_RGB(listingColor, "8.") + CLI.ANSI_RESET + " Edytuj pacjenta");
            System.out.println(CLI.ANSI_RGB(listingColor, "9.") + CLI.ANSI_RESET + " Usuń wizytę");
            System.out.println(CLI.ANSI_RGB(listingColor, "10.") + CLI.ANSI_RESET + " Usuń lekarza");
            System.out.println(CLI.ANSI_RGB(listingColor, "11.") + CLI.ANSI_RESET + " Usuń pacjenta");
            System.out.println(CLI.ANSI_RGB(listingColor, "12.") + CLI.ANSI_RESET + " Test GUI");
            System.out.println(CLI.ANSI_RGB(listingColor, "13.") + CLI.ANSI_RESET + " Zakończ");
            System.out.println(CLI.interlineGradient(18, 100, 150, 200, 200, 25, 25));
            System.out.print("Wpisz opcję: ");
            input = sc.nextLine();
            System.out.println(CLI.interlineGradient(18, 100, 150, 200, 200, 25, 25));
            */
            input = "12";
            switch (input) {
                case "1":
                    showAppointments(appointments);
                    System.out.println(CLI.interlineGradient(18, 100, 150, 200, 200, 25, 25));
                    break;
                case "2":
                    showDoctors(doctors);
                    System.out.println(CLI.interlineGradient(18, 100, 150, 200, 200, 25, 25));
                    break;
                case "3":
                    showPatients(patients);
                    System.out.println(CLI.interlineGradient(18, 100, 150, 200, 200, 25, 25));
                    break;
                case "4":
                    /*
                    addAppointment(doctor1, patients, appointments);
                    */
                    addAppointment(doctors.get(0), patients, appointments);

                    System.out.println(CLI.interlineGradient(18, 100, 150, 200, 200, 25, 25));
                    break;
                case "5":
                    addDoctor(doctors);
                    System.out.println(CLI.interlineGradient(18, 100, 150, 200, 200, 25, 25));
                    break;
                case "6":
                    addPatient(patients);
                    System.out.println(CLI.interlineGradient(18, 100, 150, 200, 200, 25, 25));
                    break;
                case "7":
                    changeAppointment(appointments, doctors, patients);
                    System.out.println(CLI.interlineGradient(18, 100, 150, 200, 200, 25, 25));
                    break;
                case "8":
                    changePatient(patients);
                    System.out.println(CLI.interlineGradient(18, 100, 150, 200, 200, 25, 25));
                    break;
                case "9":
                    if (appointments.isEmpty()) {
                        System.out.println("Brak wizyt.");
                    } else {
                        showAppointments(appointments);
                        System.out.println("Którą wizytę chcesz usunąć?");
                        System.out.print("Podaj indeks: ");
                        input = sc.nextLine();
                        inputValue = Integer.parseInt(input);
                        appointments.remove(inputValue);
                    }
                    System.out.println(CLI.interlineGradient(18, 100, 150, 200, 200, 25, 25));
                    break;
                case "10":
                    if (doctors.isEmpty()) {
                        System.out.println("Brak lekarzy.");
                    } else {
                        showDoctors(doctors);
                        System.out.println("Którego lekarza chcesz usunąć?");
                        System.out.print("Podaj indeks: ");
                        input = sc.nextLine();
                        inputValue = Integer.parseInt(input);
                        doctors.remove(inputValue);
                    }
                    System.out.println(CLI.interlineGradient(18, 100, 150, 200, 200, 25, 25));
                    break;
                case "11":
                    if (patients.isEmpty()) {
                        System.out.println("Brak pacjentów.");
                    } else {
                        showPatients(patients);
                        System.out.println("Którego pacjenta chcesz usunąć?");
                        System.out.print("Podaj indeks: ");
                        input = sc.nextLine();
                        inputValue = Integer.parseInt(input);
                        patients.remove(inputValue);
                    }
                    System.out.println(CLI.interlineGradient(18, 100, 150, 200, 200, 25, 25));
                    break;
                case "12":
                    launch();
                    // no break because we want to close the program after launch()
                case "13":
                    System.out.println("Kończę pracę.");
                    System.out.println(CLI.interlineGradient(18, 100, 150, 200, 200, 25, 25));
                    System.exit(0);
                    break;
                default:
                    System.out.println("Nie ma takiej opcji - spróbuj ponownie.");
                    System.out.println(CLI.interlineGradient(18, 100, 150, 200, 200, 25, 25));
                    break;
            }

        }

    }

    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("loadingScreen.fxml"));
        int sceneX = 350;
        int sceneY = 300;
        Scene scene = new Scene(fxmlLoader.load(), sceneX, sceneY);
        stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResource("appointr_logo.png")).toString()));
        stage.setTitle("Appointr");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                try {
                    Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoggingScreen.fxml")));
                    stg.getScene().setRoot(pane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        new Thread(sleeper).start();
    }

}
