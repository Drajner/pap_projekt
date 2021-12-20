package edu.iipw.pap;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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

    public static void main(String[] args) {
        DBContext context = new DBContext();
        Connection conn = context.getConnection();
        System.out.println("Connection opened: " + conn);
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM patients");
            while (rs.next()) {
                String patientName = new String();
                InputStreamReader in = new InputStreamReader(rs.getAsciiStream("name"));
                while(in.ready()){
                    patientName = patientName + (char)in.read();
                }
                System.out.println(patientName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        context.close();
        System.out.println("Connection closed");
    }

}
