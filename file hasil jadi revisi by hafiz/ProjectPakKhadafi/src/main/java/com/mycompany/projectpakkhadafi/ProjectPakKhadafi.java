package com.mycompany.projectpakkhadafi;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProjectPakKhadafi {
    
   

    public static Connection conn;
    /*
public static Connection conn;: Variabel conn digunakan untuk menyimpan objek koneksi ke database MySQL. 
Deklarasi static memungkinkan variabel ini dapat diakses dan dibagi nilainya di seluruh kelas.
    */

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
        /*
public static Connection getConnection(): Metode ini bertanggung jawab untuk mendapatkan koneksi ke database. 
Jika conn belum terinisialisasi (nilai null), maka metode ini akan mencoba membuat koneksi baru.

String url: URL JDBC untuk koneksi ke database MySQL userdb yang berjalan di localhost pada port 3306.

String user dan password: Username dan password untuk mengakses database MySQL.

Driver driver = new Driver();: Membuat objek Driver dari driver MySQL.

DriverManager.registerDriver(driver);: Mendaftarkan driver MySQL ke DriverManager
agar dapat digunakan untuk mendapatkan koneksi.

conn = DriverManager.getConnection(url, user, password);: Membuka koneksi ke database menggunakan URL, 
username, dan password yang telah ditentukan.

System.out.println("Koneksi berhasil");: Mencetak pesan bahwa koneksi berhasil 
ke konsol jika tidak terjadi masalah.

catch (SQLException ex) { ... }: Menangkap dan menangani pengecualian jika terjadi
kesalahan dalam membuat atau menggunakan koneksi database.

return conn;: Mengembalikan objek koneksi (conn) yang telah berhasil dibuat atau yang sudah ada.
        */
    }

    public static void main(String[] args) {
        
        getConnection();
        home Home = new home();
        Home.pack();
        Home.setVisible(true);
        Home.setLocationRelativeTo(null);
    }
    /*
getConnection();: Memanggil metode getConnection() untuk mendapatkan koneksi ke database sebelum memulai aplikasi.
Ini memastikan bahwa koneksi ke database tersedia sebelum halaman utama (home) ditampilkan.

Membuat dan Menampilkan Halaman Utama (home):

home Home = new home();: Membuat objek baru dari kelas home.

Home.pack();: Mengatur ukuran frame sesuai dengan preferensi defaultnya.

Home.setVisible(true);: Menampilkan frame Home di layar.

Home.setLocationRelativeTo(null);: Mengatur posisi frame Home agar muncul di tengah layar.
    */
}
