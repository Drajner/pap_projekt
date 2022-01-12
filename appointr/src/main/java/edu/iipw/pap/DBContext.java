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
    private String dbUrl = "jdbc:oracle:thin:@//ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";
    private String dbUser = "z31";
    private String dbPassword = "khffcc";

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

    public String getPatients(Connection conn) throws Exception{
        Patient patient;
        ArrayList<Patient> patients = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM patients");
            while (rs.next()) {
                String pesel = rs.getString(1);
                String name = rs.getString(2);
                String surname = rs.getString(3);
                LocalDate dateOfBirth = LocalDate.parse(rs.getString(4));
                String description = rs.getString(5);

                patient = new Patient(pesel, name, surname, dateOfBirth, description);

                patients.add(patient);
                System.out.println(patients.get(0).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

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
