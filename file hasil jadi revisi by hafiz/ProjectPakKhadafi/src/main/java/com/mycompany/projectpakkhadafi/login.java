/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.projectpakkhadafi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class login extends javax.swing.JFrame {
    private static Connection conn;
      /*
public static Connection conn;: Variabel conn digunakan untuk menyimpan objek koneksi ke database MySQL. 
Deklarasi static memungkinkan variabel ini dapat diakses dan dibagi nilainya di seluruh kelas.
    */

    public login() {
        initComponents();
        
    
        loginbutton.addActionListener(new ActionListener() {
            @Override
             public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
        /*
public login()
Ini adalah konstruktor dari kelas login. Konstruktor adalah metode yang dipanggil saat objek dari kelas ini dibuat.
Konstruktor ini menginisialisasi komponen GUI dan menambahkan tindakan pada tombol login.
        
initComponents();
Memanggil metode initComponents untuk menginisialisasi semua komponen GUI yang diperlukan.
Metode ini biasanya dihasilkan secara otomatis oleh GUI builder (seperti NetBeans) dan 
berisi kode untuk membuat dan mengatur semua elemen GUI seperti tombol, label, teks field, dll.
        
loginbutton.addActionListener(new ActionListener() { ... });
Menambahkan ActionListener ke tombol loginbutton.
ActionListener adalah antarmuka yang digunakan untuk menangani kejadian aksi (action events) seperti klik tombol.
Ketika tombol loginbutton diklik, metode actionPerformed akan dipanggil.
        
@Override
Menandakan bahwa metode actionPerformed sedang mengoverride metode dengan nama yang sama dari antarmuka ActionListener.
        
public void actionPerformed(ActionEvent e) { ... }
Metode actionPerformed adalah metode yang akan dieksekusi ketika tombol loginbutton diklik.
Dalam metode ini, performLogin() dipanggil untuk menangani proses login.
        
performLogin();
Memanggil metode performLogin, yang berisi logika untuk memverifikasi kredensial login pengguna terhadap database.
        */
    }

       private void performLogin() {
        String username = emailLogin.getText();
        String password = new String(loginPassword.getPassword());

        if (conn == null) {
            String url = "jdbc:mysql://localhost:3306/userdb";
            String user = "root";
            String dbPassword = "";

            try {
                conn = DriverManager.getConnection(url, user, dbPassword);
                System.out.println("Koneksi berhasil");
            } catch (SQLException ex) {
                System.out.println("Koneksi gagal: " + ex.getMessage());
                return;
            }
        }

        try {
            String sql = "SELECT * FROM login WHERE username = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                JOptionPane.showMessageDialog(null, "Login Berhasil");

                Utama utama = new Utama();
                utama.setVisible(true);
                utama.setLocationRelativeTo(null);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Login gagal, username dan password salah!");
            }

            statement.close();
        } catch (SQLException ex) {
            System.out.println("Koneksi gagal: " + ex.getMessage());
        }
        /*
String username = emailLogin.getText();
Mengambil teks dari field input emailLogin dan menyimpannya ke dalam variabel username.
        
String password = new String(loginPassword.getPassword());
Mengambil teks dari field input loginPassword dan menyimpannya ke dalam variabel password.
        
if (conn == null) { ... }
Mengecek apakah koneksi ke database conn bernilai null. Jika null, membuat koneksi baru ke database menggunakan URL, username, dan password database yang ditentukan.
        
String url = "jdbc:mysql://localhost:3306/userdb";
URL untuk koneksi ke database userdb di MySQL server yang berjalan di localhost.
        
String user = "root";
Username untuk koneksi ke database.
        
String dbPassword = "";
Password untuk koneksi ke database. Dalam hal ini, password kosong.
        
try { conn = DriverManager.getConnection(url, user, dbPassword); ... } catch (SQLException ex) { ... }
Mencoba untuk membuat koneksi ke database. Jika berhasil, mencetak "Koneksi berhasil". Jika gagal, mencetak pesan kesalahan dan keluar dari metode.
        
String sql = "SELECT * FROM login WHERE username = ? AND password = ?";
Mendefinisikan query SQL untuk memeriksa apakah ada user dengan username dan password yang sesuai.
        
PreparedStatement statement = conn.prepareStatement(sql);
Menyiapkan statement SQL dengan menggunakan PreparedStatement.
        
statement.setString(1, username);
Mengatur parameter pertama dari query dengan nilai username.
        
statement.setString(2, password);
Mengatur parameter kedua dari query dengan nilai password.
        
ResultSet result = statement.executeQuery();
Menjalankan query dan menyimpan hasilnya dalam ResultSet.
        
if (result.next()) { ... } else { ... }
Mengecek apakah ada hasil dari query.
Jika ada, menampilkan pesan "Login Berhasil", membuka jendela utama (Utama), dan menutup jendela login saat ini.
Jika tidak ada hasil dari query, menampilkan pesan "Login gagal, username dan password salah!".
        
statement.close();
Menutup statement setelah selesai digunakan.
        
catch (SQLException ex) { ... }
Menangkap pengecualian SQL dan mencetak pesan kesalahan jika terjadi kesalahan dalam menjalankan query atau koneksi database.
        */
    }
     private void refreshPage() {
        home Home = new home();
        Home.setVisible(true);
        Home.setLocationRelativeTo(null);
        this.dispose();
        /*
private void refreshPage()
Ini adalah sebuah metode yang mungkin digunakan untuk melakukan refresh atau perpindahan halaman di dalam aplikasi.
        
home Home = new home();
Membuat sebuah objek baru dari kelas home. Nama kelasnya diawali dengan huruf kecil, mungkin karena kebiasaan penamaan atau konvensi tertentu dalam kode yang ada.
        
Home.setVisible(true);
Mengatur visibilitas objek Home menjadi terlihat (true), sehingga jendela atau frame yang diwakili oleh objek Home akan muncul di layar.
        
Home.setLocationRelativeTo(null);
Mengatur posisi objek Home agar muncul di tengah layar. Nilai null menandakan bahwa posisi akan diatur relatif terhadap layar utama.
        
this.dispose();
Menutup jendela atau frame saat ini yang memanggil metode refreshPage(). Kata kunci this mengacu pada objek saat ini (dalam hal ini, objek dari kelas yang berisi metode refreshPage()).
        */
    }
     
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        emailLogin = new javax.swing.JTextField();
        loginPassword = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        loginbutton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\rizqi\\Documents\\ProjectPakKhadafi\\src\\image\\back login.png")); // NOI18N
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, -1, -1));

        emailLogin.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        emailLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        getContentPane().add(emailLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 300, 440, 40));

        loginPassword.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        loginPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        getContentPane().add(loginPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 418, 430, 40));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 560, 520, 90));

        loginbutton.setIcon(new javax.swing.ImageIcon("C:\\Users\\rizqi\\Documents\\ProjectPakKhadafi\\src\\image\\log in tombol.png")); // NOI18N
        loginbutton.setBorder(null);
        loginbutton.setContentAreaFilled(false);
        loginbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbuttonActionPerformed(evt);
            }
        });
        getContentPane().add(loginbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 510, -1, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\rizqi\\Documents\\ProjectPakKhadafi\\src\\image\\log in panel.png")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1160, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        refreshPage();
        /*
Memanggil Metode refreshpage Untuk menampilkan halaman yang baru
        */
    }//GEN-LAST:event_jButton1ActionPerformed

    private void loginbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbuttonActionPerformed
      
    }//GEN-LAST:event_loginbuttonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField emailLogin;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField loginPassword;
    private javax.swing.JButton loginbutton;
    // End of variables declaration//GEN-END:variables
}


