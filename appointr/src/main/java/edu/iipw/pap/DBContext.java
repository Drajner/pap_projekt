package edu.iipw.pap;

import java.io.InputStreamReader;
import java.sql.*;
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

    private Doctor createDoctor(ResultSet rs, Connection conn) throws Exception {
        String login = null;
        String password = null;

        String pesel = rs.getString(1);
        String name = rs.getString(2);
        String surname = rs.getString(3);
        LocalDate dateOfBirth = LocalDate.parse(rs.getString(4).substring(0, 10));
        String specialization = rs.getString(5);

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM accounts WHERE doctor_id = ?");
        stmt.setString(1, pesel);
        ResultSet account_rs = stmt.executeQuery();

        if (account_rs.next()) {
            login = account_rs.getString(2);
            password = account_rs.getString(3);
        }

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
        Doctor doctor = null;
        Patient patient = null;

        int appointment_id = rs.getInt(1);

        String doctorPesel = rs.getString(2);
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM doctors WHERE pesel = ?");
        stmt.setString(1, doctorPesel);
        ResultSet doctor_rs = stmt.executeQuery();
        if (doctor_rs.next())
            doctor = createDoctor(doctor_rs, conn);

        String patientPesel = rs.getString(3);
        stmt = conn.prepareStatement("SELECT * FROM patients WHERE pesel = ?");
        stmt.setString(1, patientPesel);
        ResultSet patient_rs = stmt.executeQuery();
        if (patient_rs.next())
            patient = createPatient(patient_rs);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime time = LocalDateTime.parse(rs.getString(4).substring(0, 16), formatter);

        int officeId = rs.getInt(5);

        return new Appointment(appointment_id, doctor, patient, time, officeId);
    }

    public ArrayList<Doctor> getDoctors(Connection conn) throws Exception{
        ArrayList<Doctor> doctors = new ArrayList<>();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM doctors");
        while (rs.next()) {
            doctors.add(createDoctor(rs, conn));
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
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM doctors WHERE pesel = ?");
        stmt.setString(1, pesel);
        stmt.executeQuery();
    }

    public static void deletePatient(Connection conn, String pesel) throws Exception{
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM patients WHERE pesel = ?");
        stmt.setString(1, pesel);
        stmt.executeQuery();
    }

    public static void deleteAppointment(Connection conn, int appointmentId) throws Exception{
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM appointments WHERE appointment_id = ?");
        stmt.setInt(1, appointmentId);
        stmt.executeQuery();
    }

    public void addDoctor(Connection conn, Doctor doctor) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO doctors VALUES (?, ?, ?, ?, ?, ?, ?)");
        stmt.setString(1, doctor.getPesel());
        stmt.setString(2, doctor.getName());
        stmt.setString(3, doctor.getSurname());
        stmt.setDate(4, Date.valueOf(doctor.getDateOfBirth()));
        stmt.setString(5, doctor.getSpecialization());
        stmt.setString(6, doctor.getLogin());
        stmt.setString(7, doctor.getPassword());
        stmt.executeQuery();
    }

    public static void addPatient(Connection conn, Patient patient) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO patients VALUES (?, ?, ?, ?, ?)");
        stmt.setString(1, patient.getPesel());
        stmt.setString(2, patient.getName());
        stmt.setString(3, patient.getSurname());
        stmt.setDate(4, Date.valueOf(patient.getDateOfBirth()));
        stmt.setString(5, patient.getDescription());
        stmt.executeQuery();
    }

    public static void addAppointment(Connection conn, Appointment appointment) throws Exception {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO appointments VALUES (NULL, ?, ?, ?, ?)");
        stmt.setString(1, appointment.getDoctor().getPesel());
        stmt.setString(2, appointment.getPatient().getName());
        stmt.setTimestamp(3, Timestamp.valueOf(appointment.getTimeOfAppointment()));
        stmt.setInt(4, appointment.getOfficeId());
        stmt.executeQuery();
    }

    public static void editDoctor(Connection conn, Doctor doctor) throws Exception {
        PreparedStatement stmt = conn.prepareStatement("UPDATE doctors " +
                "SET pesel = ?, name = ?, surname = ?, birth_date = ?, spec_id = ?");
        stmt.setString(1, doctor.getPesel());
        stmt.setString(2, doctor.getName());
        stmt.setString(3, doctor.getSurname());
        stmt.setDate(4, Date.valueOf(doctor.getDateOfBirth()));

        PreparedStatement stmt2 = conn.prepareStatement("SELECT spec_id FROM specializations WHERE name = ?");
        stmt2.setString(1, doctor.getSpecialization());
        ResultSet spec_rs = stmt2.executeQuery();
        spec_rs.next();
        int specId = spec_rs.getInt(1);
        stmt.setInt(5, specId);

        stmt.executeQuery();
    }

    public static void editPatient(Connection conn, Patient patient, String patientPesel) throws Exception {
        PreparedStatement stmt = conn.prepareStatement("UPDATE patients " +
                "SET pesel = ?, name = ?, surname = ?, birth_date = ?, description = ? " +
                "WHERE pesel = ?");
        stmt.setString(1, patient.getPesel());
        stmt.setString(2, patient.getName());
        stmt.setString(3, patient.getSurname());
        stmt.setDate(4, Date.valueOf(patient.getDateOfBirth()));
        stmt.setString(5, patient.getDescription());
        stmt.setString(6, patientPesel);
        stmt.executeQuery();
    }

    public static void editAppointment(Connection conn, Appointment appointment) throws Exception {
        PreparedStatement stmt = conn.prepareStatement("UPDATE appointments " +
                "SET appointment_id = ?, doctor = ?, patient = ?, time = ?, office_id = ?");
        stmt.setInt(1, appointment.getId());
        stmt.setString(2, appointment.getDoctor().getPesel());
        stmt.setString(3, appointment.getPatient().getPesel());
        stmt.setTimestamp(4, Timestamp.valueOf(appointment.getTimeOfAppointment()));
        stmt.setInt(5, appointment.getOfficeId());
        stmt.executeQuery();
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
