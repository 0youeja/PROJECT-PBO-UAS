package com.mycompany.projectpakkhadafi;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author M Fawwaz
 */
public class ProjectPakKhadafi{

    private static Connection conn;

    public static void main(String[] args) {
//        Connection koneksi = DatabaseUtiliies.get;

        if (conn == null) {
            String url = "jdbc:mysql://localhost:3306/userdb";
            String user = "root";
            String password = "";
            ResultSet result = null;

            try {
                Driver driver = new Driver();
                DriverManager.registerDriver(driver);
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Koneksi berhasil");
                

            } catch (SQLException ex) {
                System.out.println("Koneksi gagal");
            }
        }
    }
}
