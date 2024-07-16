package com.mycompany.projectpakkhadafi;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProjectPakKhadafi {
    
   

    public static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            String url = "jdbc:mysql://localhost:3306/userdb";
            String user = "root";
            String password = "";

            try {
                Driver driver = new Driver();
                DriverManager.registerDriver(driver);
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Koneksi berhasil");

            } catch (SQLException ex) {
                System.out.println("Koneksi gagal: " + ex.getMessage());
            }
        }
        return conn;
    }

    public static void main(String[] args) {
        
        getConnection();
        home Home = new home();
        Home.pack();
        Home.setVisible(true);
        Home.setLocationRelativeTo(null);
    }
}
