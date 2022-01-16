package edu.iipw.pap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
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

public class DoctorViewController implements Initializable {

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
    private ObservableList<AppointmentTableRow> data;
    private Doctor usedDoctor;
    private Connection conn;

    public DoctorViewController() {
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
        genderColumn.setCellValueFactory(new PropertyValueFactory<PatientTableRow, String>("gender"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<PatientTableRow, LocalDate>("birthDate"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<PatientTableRow, String>("description"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<PatientTableRow, String>("gender"));
    }

    public void usedDoctorAndConn(Doctor loggedDoctor, Connection usedConn) {
        usedDoctor = loggedDoctor;
        conn = usedConn;

        updateAppointmentTable();
        updatePatientsTable();

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
        Text text1a = new Text("Witaj ");
        text1a.setFont(Font.font("Helvetica", 24));

        String doctorName = usedDoctor.getName() + " " + usedDoctor.getSurname();
        Text text1b = new Text(doctorName);
        text1b.setFill(Color.rgb(155, 177, 214));
        text1b.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.ITALIC, 24));

        Text text1c = new Text("!\n\n");
        text1c.setFont(Font.font("Helvetica", 24));

        /* Number of upcoming appointments */
        Text text2a = new Text("◉ Liczba nadchodzących wizyt: ");
        text2a.setFont(Font.font("Helvetica", 18));

        Text text2b = new Text(String.valueOf(data.size() - numOfExpiredAppointments) + '\n');
        text2b.setFont(Font.font("Helvetica", FontWeight.BOLD, 18));

        /* Next visit */
        Text text3a = new Text("◉ Najbliższa wizyta: ");
        text3a.setFont(Font.font("Helvetica", 18));

        Text text3b = new Text(closestAppointment.getDate() + '\n');
        text3b.setFont(Font.font("Helvetica", FontWeight.BOLD, 18));

        /* Number of expired appointments */
        Text text4a = new Text("◉ Liczba wizyt wygasłych: ");
        text4a.setFont(Font.font("Helvetica", 18));

        Text text4b = new Text(String.valueOf(numOfExpiredAppointments) + '\n');
        text4b.setFont(Font.font("Helvetica", FontWeight.BOLD, 18));

        Text text4c = new Text("◉ Zalecane jest usunięcie wizyt wygasłych");
        text4c.setOpacity(0.39215686274);
        text4c.setFont(Font.font("Helvetica", FontPosture.ITALIC, 18));

        /* Set the dimensions of the TextFlow window */
        sideText.setMinWidth(200);
        sideText.setPrefWidth(275);
        sideText.setMaxWidth(350);

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
            if (a.getDoctor().getPesel().equals(usedDoctor.getPesel()))
                rows.add(new AppointmentTableRow(a, a.getId()));
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
                             "-fx-effect: innershadow(three-pass-box, rgba(0,0,0,0.5), 10, 0, 0, 0);");
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

    @FXML
    public void addPatient(ActionEvent event) throws IOException {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addPatientScreen.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("chooseEditPatientScreen.fxml"));
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
            // this:
            FXMLLoader loader = new FXMLLoader(getClass().getResource("deletePatientScreen.fxml"));
            root = loader.load();
            DeletePatientController dpc = loader.getController();
            dpc.transferData(data, usedDoctor, conn);
            // instead of this:
            // root = FXMLLoader.load(getClass().getResource("deletePatientScreen.fxml"));
            int sceneX = 200;
            int sceneY = 150;
            Stage stage = new Stage();
            stage.getIcons().add(new Image(App.class.getResource("appointr_logo.png").toString()));
            stage.setTitle("Delete patient");
            stage.setScene(new Scene(root, sceneX, sceneY));
            stage.setResizable(false);

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addAppointmentScreen.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("chooseEditAppointmentScreen.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("deleteAppointmentScreen.fxml"));
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

}
