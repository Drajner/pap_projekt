package edu.iipw.pap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable {

    @FXML
    private TextFlow sideText;
    @FXML
    private Button addPatientButton;
    @FXML
    private Button editPatientButton;
    @FXML
    private Button deletePatientButton;
    @FXML
    private Button addAppointmentButton;
    @FXML
    private Button editAppointmentButton;
    @FXML
    private Button deleteAppointmentButton;
    @FXML
    private Button addDoctorButton;
    @FXML
    private Button editDoctorButton;
    @FXML
    private Button deleteDoctorButton;
    @FXML
    private Button editProfileButton;
    @FXML
    private Tab appointmentsTab;
    @FXML
    private Tab patientsTab;
    @FXML
    private Tab doctorsTab;
    @FXML
    private TableView<AppointmentTableRow> appointmentTable;
    @FXML
    private TableColumn<AppointmentTableRow, Integer> numberColumn;
    @FXML
    private TableColumn<AppointmentTableRow, String> dateColumn;
    @FXML
    private TableColumn<AppointmentTableRow, String> appointmentNameColumn;
    @FXML
    private TableColumn<AppointmentTableRow, String> officeColumn;
    @FXML
    private TableView<PatientTableRow> patientTable;
    @FXML
    private TableColumn<PatientTableRow, String> peselColumn;
    @FXML
    private TableColumn<PatientTableRow, String> patientNameColumn;
    @FXML
    private TableColumn<PatientTableRow, String> genderColumn;
    @FXML
    private TableColumn<PatientTableRow, LocalDate> birthDateColumn;
    @FXML
    private TableColumn<PatientTableRow, String> descriptionColumn;
    @FXML
    private TableView<DoctorTableRow> doctorTable;
    @FXML
    private TableColumn<DoctorTableRow, String> peselColumnDoc;
    @FXML
    private TableColumn<DoctorTableRow, String> doctorNameColumn;
    @FXML
    private TableColumn<DoctorTableRow, String> genderColumnDoc;
    @FXML
    private TableColumn<DoctorTableRow, LocalDate> birthDateColumnDoc;
    @FXML
    private TableColumn<DoctorTableRow, String> specializationColumn;
    @FXML
    private ToggleButton darkTheme;
    @FXML
    private ToggleButton engLang;
    @FXML
    private ImageView flagImage;
    private ObservableList<AppointmentTableRow> data;
    private Doctor usedDoctor;
    private Connection conn;

    public AdminViewController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /* Appointments table */
        numberColumn.setCellValueFactory(new PropertyValueFactory<AppointmentTableRow, Integer>("index"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<AppointmentTableRow, String>("date"));
        appointmentNameColumn.setCellValueFactory(new PropertyValueFactory<AppointmentTableRow, String>("name"));
        officeColumn.setCellValueFactory(new PropertyValueFactory<AppointmentTableRow, String>("office"));

        /* Patients table */
        peselColumn.setCellValueFactory(new PropertyValueFactory<PatientTableRow, String>("pesel"));
        patientNameColumn.setCellValueFactory(new PropertyValueFactory<PatientTableRow, String>("name"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<PatientTableRow, LocalDate>("birthDate"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<PatientTableRow, String>("description"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<PatientTableRow, String>("gender"));

        /* Doctors table */
        peselColumnDoc.setCellValueFactory(new PropertyValueFactory<DoctorTableRow, String>("pesel"));
        doctorNameColumn.setCellValueFactory(new PropertyValueFactory<DoctorTableRow, String>("name"));
        birthDateColumnDoc.setCellValueFactory(new PropertyValueFactory<DoctorTableRow, LocalDate>("birthDate"));
        specializationColumn.setCellValueFactory(new PropertyValueFactory<DoctorTableRow, String>("specialization"));
        genderColumnDoc.setCellValueFactory(new PropertyValueFactory<DoctorTableRow, String>("gender"));
    }

    public void usedDoctorAndConn(Doctor loggedDoctor, Connection usedConn) {
        usedDoctor = loggedDoctor;
        conn = usedConn;

        updateAppointmentTable();
        updatePatientsTable();
        updateDoctorsTable();

        updateSideText();
    }

    public void updateSideText() {
        /* Clear all text in case of updating */
        sideText.getChildren().clear();

        /* Check for expired appointments */
        AppointmentTableRow closestAppointment = null;
        int numOfExpiredAppointments = 0;

        Comparator<AppointmentTableRow> dateComparator = new Comparator<AppointmentTableRow>() {
            @Override
            public int compare(AppointmentTableRow atr1, AppointmentTableRow atr2) {
                return atr1.getDate().compareTo(atr2.getDate());
            }
        };

        for (AppointmentTableRow appointment : data.sorted(dateComparator)) {
            if (appointment.getAppointment().getTimeOfAppointment().isAfter(LocalDateTime.now())) {
                if (closestAppointment == null) {
                    closestAppointment = appointment;
                } else if (appointment.getAppointment().getTimeOfAppointment().isBefore(closestAppointment.getAppointment().getTimeOfAppointment())) {
                    closestAppointment = appointment;
                }
            } else {
                numOfExpiredAppointments++;
            }
        }

        /* Welcome message */
        Text text1a;
        if (engLang.isSelected()) {
            text1a = new Text("Welcome ");
        } else {
            text1a = new Text("Witaj ");
        }
        text1a.setFont(Font.font("Helvetica", 24));

        String doctorName = usedDoctor.getName() + " " + usedDoctor.getSurname();
        Text text1b = new Text(doctorName);
        text1b.setFill(Color.rgb(155, 177, 214));
        text1b.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.ITALIC, 24));

        Text text1c = new Text("!\n\n");
        text1c.setFont(Font.font("Helvetica", 24));

        /* Number of upcoming appointments */
        Text text2a;
        if (engLang.isSelected()) {
            text2a = new Text("◉ Number of upcoming appointments: ");
        } else {
            text2a = new Text("◉ Liczba nadchodzących wizyt: ");
        }
        text2a.setFont(Font.font("Helvetica", 18));

        Text text2b = new Text(String.valueOf(data.size() - numOfExpiredAppointments) + '\n');
        text2b.setFont(Font.font("Helvetica", FontWeight.BOLD, 18));

        /* Next appointment */
        Text text3a = new Text("");
        Text text3b = new Text("");
        if (closestAppointment != null) {
            if (engLang.isSelected()) {
                text3a = new Text("◉ Next appointment: ");
            } else {
                text3a = new Text("◉ Najbliższa wizyta: ");
            }
            text3a.setFont(Font.font("Helvetica", 18));

            text3b = new Text(closestAppointment.getDate() + '\n');
            text3b.setFont(Font.font("Helvetica", FontWeight.BOLD, 18));
        }

        /* Number of expired appointments */
        Text text4a;
        if (engLang.isSelected()) {
            text4a = new Text("◉ Number of expired appointments: ");
        } else {
            text4a = new Text("◉ Liczba wizyt wygasłych: ");
        }
        text4a.setFont(Font.font("Helvetica", 18));

        Text text4b = new Text(String.valueOf(numOfExpiredAppointments) + '\n');
        text4b.setFont(Font.font("Helvetica", FontWeight.BOLD, 18));

        Text text4c;
        if (engLang.isSelected()) {
            text4c = new Text("◉ It is recommended to delete expired appointments");
        } else {
            text4c = new Text("◉ Zalecane jest usunięcie wizyt wygasłych");
        }
        text4c.setFont(Font.font("Helvetica", FontPosture.ITALIC, 18));
        text4c.setOpacity(0.39215686274);

        /* Set the dimensions of the TextFlow window */
        sideText.setMinWidth(200);
        sideText.setPrefWidth(275);
        sideText.setMaxWidth(350);

        if (darkTheme.isSelected()) {
            text1a.setFill(Color.WHITE);
            text1c.setFill(Color.WHITE);
            text2a.setFill(Color.WHITE);
            text2b.setFill(Color.WHITE);
            text3a.setFill(Color.WHITE);
            text3b.setFill(Color.WHITE);
            text4a.setFill(Color.WHITE);
            text4b.setFill(Color.WHITE);
            text4c.setFill(Color.WHITE);
        }

        /* Add text to the TextFlow window */
        sideText.getChildren().addAll(text1a, text1b, text1c,
                                      text2a, text2b,
                                      text3a, text3b);

        if (numOfExpiredAppointments > 0) {
            sideText.getChildren().addAll(text4a, text4b, text4c);
        }

        /* Set text alignment */
        sideText.setTextAlignment(TextAlignment.LEFT);
    }

    public void updateAppointmentTable() {
        DBContext dbContext = new DBContext();

        ArrayList<Appointment> appointments = new ArrayList<>();

        try {
            appointments = dbContext.getAppointments(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<AppointmentTableRow> rows = new ArrayList<AppointmentTableRow>();
        for (Appointment a : appointments) {
            if (a.getDoctor().getHospitalId() == usedDoctor.getHospitalId()) {
                rows.add(new AppointmentTableRow(a, a.getId()));
            }
        }

        /* if an appointment is expired, render it differently */
        /* might have to experiment with this one a bit more */
        appointmentTable.setRowFactory(tv -> new TableRow<AppointmentTableRow>() {
            @Override
            public void updateItem(AppointmentTableRow item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) {
                    setStyle("");
                } else if (item.getAppointment().getTimeOfAppointment().isBefore(LocalDateTime.now())) {
                    setStyle("-fx-opacity: 0.5;" +
                             "-fx-effect: innershadow(three-pass-box, rgba(0, 0, 0, 0.5), 10, 0, 0, 0);");
                } else {
                    setStyle("");
                }
            }
        });

        data = FXCollections.observableArrayList(rows);
        appointmentTable.setItems(data);
    }

    public void updatePatientsTable() {
        DBContext dbContext = new DBContext();
        ArrayList<Patient> patients = new ArrayList<>();

        try {
            patients = dbContext.getPatients(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<PatientTableRow> patientRows = new ArrayList<PatientTableRow>();
        for (Patient patient : patients) {
            patientRows.add(new PatientTableRow(patient));
        }

        ObservableList<PatientTableRow> patientsData = FXCollections.observableArrayList(patientRows);
        patientTable.setItems(patientsData);
    }

    public void updateDoctorsTable() {
        DBContext dbContext = new DBContext();
        ArrayList<Doctor> doctors = new ArrayList<>();

        try {
            doctors = dbContext.getDoctors(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<DoctorTableRow> doctorRows = new ArrayList<DoctorTableRow>();
        for (Doctor doctor : doctors) {
            doctorRows.add(new DoctorTableRow(doctor));
        }

        ObservableList<DoctorTableRow> doctorsData = FXCollections.observableArrayList(doctorRows);
        doctorTable.setItems(doctorsData);
    }

    @FXML
    public void addPatient(ActionEvent event) throws IOException {
        Parent root;
        try {
            String lang;
            if (engLang.isSelected()) {
                lang = "addPatientScreenEng.fxml";
            } else {
                lang = "addPatientScreen.fxml";
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource(lang));

            root = loader.load();
            AddPatientController apc = loader.getController();
            apc.transferConn(conn);
            int sceneX = 300;
            int sceneY = 320;
            Stage stage = new Stage();
            stage.getIcons().add(new Image(App.class.getResource("appointr_logo.png").toString()));
            stage.setTitle("Add patient");
            stage.setScene(new Scene(root, sceneX, sceneY));
            stage.setResizable(false);

            if (darkTheme.isSelected()) {
                String css = getClass().getResource("darkTheme2.css").toExternalForm();
                stage.getScene().getStylesheets().add(css);
            }

            stage.setOnCloseRequest(
                    windowEvent -> {
                        updatePatientsTable();
                        updateSideText();
                    }
            );

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editPatient(ActionEvent event) throws IOException {
        Parent root;
        try {
            String lang;
            if (engLang.isSelected()) {
                lang = "chooseEditPatientScreenEng.fxml";
            } else {
                lang = "chooseEditPatientScreen.fxml";
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource(lang));

            root = loader.load();
            ChooseEditPatientController cepc = loader.getController();
            cepc.transferData(data, usedDoctor, conn);
            int sceneX = 200;
            int sceneY = 150;
            Stage stage = new Stage();
            stage.getIcons().add(new Image(App.class.getResource("appointr_logo.png").toString()));
            stage.setTitle("Edit patient");
            stage.setScene(new Scene(root, sceneX, sceneY));
            stage.setResizable(false);

            if (darkTheme.isSelected()) {
                String css = getClass().getResource("darkTheme2.css").toExternalForm();
                stage.getScene().getStylesheets().add(css);
            }

            stage.setOnCloseRequest(
                    windowEvent -> {
                        updatePatientsTable();
                        updateAppointmentTable();
                    }
            );

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deletePatient(ActionEvent event) throws IOException {
        Parent root;
        try {
            String lang;
            if (engLang.isSelected()) {
                lang = "deletePatientScreenEng.fxml";
            } else {
                lang = "deletePatientScreen.fxml";
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource(lang));

            root = loader.load();
            DeletePatientController dpc = loader.getController();
            dpc.transferData(data, usedDoctor, conn);
            int sceneX = 200;
            int sceneY = 150;
            Stage stage = new Stage();
            stage.getIcons().add(new Image(App.class.getResource("appointr_logo.png").toString()));
            stage.setTitle("Delete patient");
            stage.setScene(new Scene(root, sceneX, sceneY));
            stage.setResizable(false);

            if (darkTheme.isSelected()) {
                String css = getClass().getResource("darkTheme2.css").toExternalForm();
                stage.getScene().getStylesheets().add(css);
            }

            stage.setOnCloseRequest(
                    windowEvent -> {
                        updatePatientsTable();
                        updateAppointmentTable();
                        updateSideText();
                    }
            );

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addAppointment(ActionEvent event) throws IOException {
        Parent root;
        try {
            String lang;
            if (engLang.isSelected()) {
                lang = "addAppointmentScreenEng.fxml";
            } else {
                lang = "addAppointmentScreen.fxml";
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource(lang));

            root = loader.load();
            AddAppointmentController aac = loader.getController();
            aac.transferDoctorAndConn(usedDoctor, conn);
            int sceneX = 240;
            int sceneY = 240;
            Stage stage = new Stage();
            stage.getIcons().add(new Image(App.class.getResource("appointr_logo.png").toString()));
            stage.setTitle("Add appointment");
            stage.setScene(new Scene(root, sceneX, sceneY));
            stage.setResizable(false);

            if (darkTheme.isSelected()) {
                String css = getClass().getResource("darkTheme2.css").toExternalForm();
                stage.getScene().getStylesheets().add(css);
            }

            stage.setOnCloseRequest(
                    windowEvent -> {
                        updateAppointmentTable();
                        updateSideText();
                    }
            );

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editAppointment(ActionEvent event) throws IOException {
        Parent root;
        try {
            String lang;
            if (engLang.isSelected()) {
                lang = "chooseEditAppointmentScreenEng.fxml";
            } else {
                lang = "chooseEditAppointmentScreen.fxml";
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource(lang));

            root = loader.load();
            ChooseEditAppointmentController ceac = loader.getController();
            ceac.transferData(data, usedDoctor, conn);
            int sceneX = 200;
            int sceneY = 150;
            Stage stage = new Stage();
            stage.getIcons().add(new Image(App.class.getResource("appointr_logo.png").toString()));
            stage.setTitle("Edit appointment");
            stage.setScene(new Scene(root, sceneX, sceneY));
            stage.setResizable(false);

            if (darkTheme.isSelected()) {
                String css = getClass().getResource("darkTheme2.css").toExternalForm();
                stage.getScene().getStylesheets().add(css);
            }

            stage.setOnCloseRequest(
                    windowEvent -> {
                        updateAppointmentTable();
                        updateSideText();
                    }
            );

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteAppointment(ActionEvent event) throws IOException {
        Parent root;
        try {
            String lang;
            if (engLang.isSelected()) {
                lang = "deleteAppointmentScreenEng.fxml";
            } else {
                lang = "deleteAppointmentScreen.fxml";
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource(lang));

            root = loader.load();
            DeleteAppointmentController dac = loader.getController();
            dac.transferData(data, usedDoctor, conn);
            int sceneX = 200;
            int sceneY = 150;
            Stage stage = new Stage();
            stage.getIcons().add(new Image(App.class.getResource("appointr_logo.png").toString()));
            stage.setTitle("Delete appointment");
            stage.setScene(new Scene(root, sceneX, sceneY));
            stage.setResizable(false);

            if (darkTheme.isSelected()) {
                String css = getClass().getResource("darkTheme2.css").toExternalForm();
                stage.getScene().getStylesheets().add(css);
            }

            stage.setOnCloseRequest(
                    windowEvent -> {
                        updateAppointmentTable();
                        updateSideText();
                    }
            );

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addDoctor(ActionEvent event) throws IOException {
        System.out.println("lol, not yet implemented");
    }

    @FXML
    public void editDoctor(ActionEvent event) throws IOException {
        System.out.println("lol, not yet implemented");
    }

    @FXML
    public void deleteDoctor(ActionEvent event) throws IOException {
        System.out.println("lol, not yet implemented");
    }

    @FXML
    public void editProfile(ActionEvent event) throws IOException {
        System.out.println("lol, not yet implemented");
    }

    @FXML
    void changeTheme(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        if (darkTheme.isSelected()) {
            String css = getClass().getResource("darkTheme2.css").toExternalForm();
            stage.getScene().getStylesheets().clear();
            stage.getScene().getStylesheets().add(css);
        } else {
            String css = getClass().getResource("doctorViewCSS.css").toExternalForm();
            stage.getScene().getStylesheets().clear();
            stage.getScene().getStylesheets().add(css);
        }
        updateSideText();
    }

    @FXML
    void changeLang(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        updateSideText();
        byte refresh = 1;  // we do this because otherwise sideText doesn't fully update, for some reason
        if (engLang.isSelected()) {
            refresh *= -1;
            flagImage.setImage(new Image(App.class.getResource("gb.png").toString()));
            darkTheme.setText("Dark theme");
            appointmentsTab.setText("Appointments");
            patientsTab.setText("Patients");
            doctorsTab.setText("Doctors");
            addAppointmentButton.setText("Add appointment");
            editAppointmentButton.setText("Edit appointment");
            deleteAppointmentButton.setText("Delete appointment");
            addPatientButton.setText("Add patient");
            editPatientButton.setText("Edit patient");
            deletePatientButton.setText("Delete patient");
            addDoctorButton.setText("Add doctor");
            editDoctorButton.setText("Edit doctor");
            deleteDoctorButton.setText("Delete doctor");
            editProfileButton.setText("Edit profile");
            numberColumn.setText("No.");
            dateColumn.setText("Date");
            appointmentNameColumn.setText("Name");
            officeColumn.setText("Office");
            patientNameColumn.setText("First name and surname");
            birthDateColumn.setText("Birth date");
            descriptionColumn.setText("Description");
            genderColumn.setText("Sex");
            doctorNameColumn.setText("First name and surname");
            genderColumnDoc.setText("Sex");
            birthDateColumnDoc.setText("Birth date");
            specializationColumn.setText("Specialization");
        } else {
            flagImage.setImage(new Image(App.class.getResource("pl.png").toString()));
            darkTheme.setText("Tryb ciemny");
            appointmentsTab.setText("Wizyty");
            patientsTab.setText("Pacjenci");
            doctorsTab.setText("Lekarze");
            addAppointmentButton.setText("Dodaj wizytę");
            editAppointmentButton.setText("Edytuj wizytę");
            deleteAppointmentButton.setText("Usuń wizytę");
            addPatientButton.setText("Dodaj pacjenta");
            editPatientButton.setText("Edytuj pacjenta");
            deletePatientButton.setText("Usuń pacjenta");
            addDoctorButton.setText("Dodaj lekarza");
            editDoctorButton.setText("Edytuj lekarza");
            deleteDoctorButton.setText("Usuń lekarza");
            editProfileButton.setText("Edytuj profil");
            numberColumn.setText("Nr");
            dateColumn.setText("Data i godzina");
            appointmentNameColumn.setText("Imię i Nazwisko Pacjenta");
            officeColumn.setText("Gabinet");
            patientNameColumn.setText("Imię i Nazwisko");
            birthDateColumn.setText("Data urodzenia");
            descriptionColumn.setText("Opis");
            genderColumn.setText("Płeć");
            doctorNameColumn.setText("Imię i Nazwisko");
            genderColumnDoc.setText("Płeć");
            birthDateColumnDoc.setText("Data urodzenia");
            specializationColumn.setText("Specjalizacja");
        }
        stage.setWidth(stage.getWidth() + refresh);
    }

}
