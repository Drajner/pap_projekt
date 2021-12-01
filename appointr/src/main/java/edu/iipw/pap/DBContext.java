package edu.iipw.pap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBContext implements AutoCloseable {
    private Connection conn = null;

    public void close() {
        if (conn != null) {
            try {
                System.out.println("Closing database connection to bookStoreDB");
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
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "z31", "khffcc");
        } catch(SQLException e) {
            System.out.println("Unable to open connection: " + e);
        }
        }
    return conn;
    }

    public static void main(String[] args) {
        DBContext context = new DBContext();
        Connection conn = context.getConnection();
    }
}