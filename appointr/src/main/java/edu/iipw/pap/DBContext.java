package edu.iipw.pap;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        String login = rs.getString(6);
        String password = rs.getString(7);

        return new Doctor(pesel, name, surname, dateOfBirth, specialization, login, password, new ArrayList<Appointment>());
    }

    private Patient createPatient(ResultSet rs) throws Exception {
        String pesel = rs.getString(1);
        String name = rs.getString(2);
        String surname = rs.getString(3);
        LocalDate dateOfBirth = LocalDate.parse(rs.getString(4).substring(0, 10));
        String description = rs.getString(5);

        return new Patient(pesel, name, surname, dateOfBirth, description);
    }

    public ArrayList<Doctor> getDoctors(Connection conn) throws Exception{
        ArrayList<Doctor> doctors = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM doctors");
            while (rs.next()) {
                doctors.add(createDoctor(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctors;
    }

    public ArrayList<Patient> getPatients(Connection conn) throws Exception{
        ArrayList<Patient> patients = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM patients");
            while (rs.next()) {
                patients.add(createPatient(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }

    public ArrayList<Appointment> getAppointments(Connection conn) {
        Patient patient;
        Doctor doctor;

        return null;
    }

    public void deleteDoctor(Connection conn, String pesel) throws Exception{
        Statement stmt = conn.createStatement();
        stmt.executeQuery("DELETE FROM doctors WHERE pesel = " + pesel);
    }

    public void deletePatient(Connection conn, String pesel) throws Exception{
        Statement stmt = conn.createStatement();
        stmt.executeQuery("DELETE FROM patients WHERE pesel = " + pesel);
    }

    public void deleteAppointment() {}

    public void addDoctor(Connection conn, Doctor doctor) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeQuery(String.format("INSERT INTO doctors VALUES (%s, %s, %s, %s, %s, %s, %s)",
                doctor.getPesel(), doctor.getName(), doctor.getSurname(), doctor.getDateOfBirth(),
                doctor.getSpecialization(), doctor.getLogin(), doctor.getPassword()));
    }

    public void addPatient(Connection conn, Patient patient) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeQuery(String.format("INSERT INTO patients VALUES (%s, %s, %s, %s, %s)",
                patient.getPesel(), patient.getName(), patient.getSurname(), patient.getDateOfBirth(),
                patient.getDescription()));
    }

    public void addAppointment() {}

    public static void main(String[] args) throws Exception {
        DBContext context = new DBContext();
        Connection conn = context.getConnection();
        System.out.println("Connection opened: " + conn);

        context.getPatients(conn);
//        try {
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT name FROM patients");
//            while (rs.next()) {
//                String patientName = new String();
//                InputStreamReader in = new InputStreamReader(rs.getAsciiStream("name"));
//                while(in.ready()){
//                    patientName = patientName + (char)in.read();
//                }
//                System.out.println(patientName);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        context.close();
        System.out.println("Connection closed");
    }

}
