package edu.iipw.pap;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class DBContext implements AutoCloseable {
    private Connection conn = null;
    private final String dbUrl = "jdbc:oracle:thin:@//ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";
    private final String dbUser = "z31";
    private final String dbPassword = "khffcc";

    public void close() {
        if (conn != null) {
            try {
                System.out.println("Closing database connection to z31DB");
                conn.close();
            } catch (SQLException e) {
                    System.out.println("Unable to close connection: " + e);
            }
            conn = null;
        }
    }

    public Connection getConnection() {
        if (conn == null) {
            try {
                System.out.println("Opening connection to z31DB");
                conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            } catch(SQLException e) {
                System.out.println("Unable to open connection: " + e);
            }
        }
        return conn;
    }

    private Doctor createDoctor(ResultSet rs) throws Exception {
        String pesel = rs.getString(1);
        String name = rs.getString(2);
        String surname = rs.getString(3);
        LocalDate dateOfBirth = LocalDate.parse(rs.getString(4).substring(0, 10));
        String specialization = rs.getString(5);

        // trzeba dodac login i password do bazy danych

        String login = null;
        String password = null;
//        String login = rs.getString(6);
//        String password = rs.getString(7);

        return new Doctor(pesel, name, surname, dateOfBirth, specialization, login, password, new ArrayList<>());
    }

    private Patient createPatient(ResultSet rs) throws Exception {
        String pesel = rs.getString(1);
        String name = rs.getString(2);
        String surname = rs.getString(3);
        LocalDate dateOfBirth = LocalDate.parse(rs.getString(4).substring(0, 10));
        String description = rs.getString(5);

        return new Patient(pesel, name, surname, dateOfBirth, description);
    }

    private Appointment createAppointment(ResultSet rs, Connection conn) throws Exception {
        Statement stmt = conn.createStatement();
        Doctor doctor = null;
        Patient patient = null;

        int appointment_id = rs.getInt(1);

        String doctorPesel = rs.getString(2);
        ResultSet doctor_rs = stmt.executeQuery("SELECT * FROM doctors WHERE pesel = " + doctorPesel);
        if (doctor_rs.next())
            doctor = createDoctor(doctor_rs);

        String patientPesel = rs.getString(3);
        ResultSet patient_rs = stmt.executeQuery("SELECT * FROM patients WHERE pesel = " + doctorPesel);
        if (patient_rs.next())
            patient = createPatient(patient_rs);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime time = LocalDateTime.parse(rs.getString(4).substring(0, 16), formatter);

        String officeId = rs.getString(5);

        return new Appointment(appointment_id, doctor, patient, time, officeId);
    }

    public ArrayList<Doctor> getDoctors(Connection conn) throws Exception{
        ArrayList<Doctor> doctors = new ArrayList<>();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM doctors");
        while (rs.next()) {
            doctors.add(createDoctor(rs));
        }

        return doctors;
    }

    public ArrayList<Patient> getPatients(Connection conn) throws Exception{
        ArrayList<Patient> patients = new ArrayList<>();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM patients");
        while (rs.next()) {
            patients.add(createPatient(rs));
        }

        return patients;
    }

    public ArrayList<Appointment> getAppointments(Connection conn) throws Exception {
        ArrayList<Appointment> appointments = new ArrayList<>();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM appointments");
        while (rs.next()) {
            appointments.add(createAppointment(rs, conn));
        }
        return appointments;
    }

    public void deleteDoctor(Connection conn, String pesel) throws Exception{
        Statement stmt = conn.createStatement();
        stmt.executeQuery("DELETE FROM doctors WHERE pesel = " + pesel);
    }

    public static void deletePatient(Connection conn, String pesel) throws Exception{
        Statement stmt = conn.createStatement();
        stmt.executeQuery("DELETE FROM patients WHERE pesel = " + pesel);
    }

    public static void deleteAppointment(Connection conn, int appointmentId) throws Exception{
        Statement stmt = conn.createStatement();
        stmt.executeQuery("DELETE FROM appointments WHERE appointment_id = " + appointmentId);
    }

    public void addDoctor(Connection conn, Doctor doctor) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeQuery(String.format("INSERT INTO doctors VALUES (%s, %s, %s, %s, %s, %s, %s)",
                doctor.getPesel(), doctor.getName(), doctor.getSurname(), doctor.getDateOfBirth(),
                doctor.getSpecialization(), doctor.getLogin(), doctor.getPassword()));
    }

    public static void addPatient(Connection conn, Patient patient) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeQuery(String.format("INSERT INTO patients VALUES (%s, %s, %s, %s, %s)",
                patient.getPesel(), patient.getName(), patient.getSurname(), patient.getDateOfBirth(),
                patient.getDescription()));
    }

    public static void addAppointment(Connection conn, Appointment appointment) throws Exception{
        Statement stmt = conn.createStatement();
        stmt.executeQuery(String.format("INSERT INTO appointments VALUES (NULL, %s, %s, %s, %s)",
                appointment.getDoctor().getPesel(), appointment.getPatient().getPesel(),
                appointment.getTimeOfAppointment(), appointment.getOfficeId()));
    }

    public static void editDoctor(Connection conn, Doctor doctor) throws Exception {
        Statement stmt = conn.createStatement();
        stmt.executeQuery(String.format(
                "UPDATE doctors " +
                "SET pesel = %s, name = %s, surname = %s, birth_date = %s, spec_id = %d;",
                doctor.getPesel(), doctor.getName(), doctor.getSurname(), doctor.getDateOfBirth(), doctor.getSpecialization()));
    }

    public static void editPatient(Connection conn, Patient patient, String oldPesel) throws Exception {
        Statement stmt = conn.createStatement();
        stmt.executeQuery(String.format(
                "UPDATE patients " +
                        "SET pesel = %s, name = '%s', surname = '%s', birth_date = '%s', description = '%s' " +
                        "WHERE pesel = %s",
                patient.getPesel(), patient.getName(), patient.getSurname(), patient.getDateOfBirth(), patient.getDescription(),
                oldPesel));
    }

    public static void editAppointment(Connection conn, Appointment appointment) throws Exception {
        Statement stmt = conn.createStatement();
        stmt.executeQuery(String.format(
                "UPDATE appointments " +
                        "SET appointment_id = %d, doctor = %s, patient = %s, time = %s, office_id = %d;",
                appointment.getId(), appointment.getDoctor().getPesel(), appointment.getPatient().getPesel(),
                appointment.getTimeOfAppointment(), appointment.getOfficeId()));
    }

    public static void main(String[] args) {
        DBContext context = new DBContext();
        try {
            Connection conn = context.getConnection();
            System.out.println("Connection opened: " + conn);

            ArrayList<Patient> patients = context.getPatients(conn);

            context.close();
            System.out.println("Connection closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
