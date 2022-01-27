package edu.iipw.pap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class DoctorViewControllerWeb implements Initializable {

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
    private Button editProfileButton;
    @FXML
    private Tab appointmentsTab;
    @FXML
    private Tab patientsTab;
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
    private ToggleButton darkTheme;
    @FXML
    private ToggleButton engLang;
    private ObservableList<AppointmentTableRow> data;
    private Doctor usedDoctor;
    private Connection conn;

    public DoctorViewControllerWeb() {
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
        Text text3a;
        if (engLang.isSelected()) {
            text3a = new Text("◉ Next appointment: ");
        } else {
            text3a = new Text("◉ Najbliższa wizyta: ");
        }
        text3a.setFont(Font.font("Helvetica", 18));

        Text text3b = new Text(closestAppointment.getDate() + '\n');
        text3b.setFont(Font.font("Helvetica", FontWeight.BOLD, 18));

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
            engLang.setText("English");
            darkTheme.setText("Dark theme");
            appointmentsTab.setText("Appointments");
            patientsTab.setText("Patients");
            numberColumn.setText("No.");
            dateColumn.setText("Date");
            appointmentNameColumn.setText("Name");
            officeColumn.setText("Office");
            patientNameColumn.setText("First name and surname");
            birthDateColumn.setText("Birth date");
            descriptionColumn.setText("Description");
            genderColumn.setText("Sex");
        } else {
            engLang.setText("Polski");
            darkTheme.setText("Tryb ciemny");
            appointmentsTab.setText("Wizyty");
            patientsTab.setText("Pacjenci");
            numberColumn.setText("Nr");
            dateColumn.setText("Data i godzina");
            appointmentNameColumn.setText("Imię i Nazwisko Pacjenta");
            officeColumn.setText("Gabinet");
            patientNameColumn.setText("Imię i Nazwisko");
            birthDateColumn.setText("Data urodzenia");
            descriptionColumn.setText("Opis");
            genderColumn.setText("Płeć");
        }
        stage.setWidth(stage.getWidth() + refresh);
    }

}
