/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.projectpakkhadafi;


import javax.swing.JOptionPane;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;


public class Data_Buku extends javax.swing.JFrame {

  
    public Data_Buku() {
        initComponents();
        setTitle("FORM DATA BUKU");
        this.setLocation(200, 100);
        ProjectPakKhadafi.getConnection();
        refreshBuku();
        BtnEdit.setEnabled(false);
        /*
initComponents(): Menginisialisasi komponen GUI.
setTitle("FORM DATA BUKU"): Mengatur judul jendela menjadi "FORM DATA BUKU".
this.setLocation(200, 100): Mengatur posisi jendela pada layar.
ProjectPakKhadafi.getConnection(): Menghubungkan ke database.
refreshBuku(): Memperbarui data buku yang ditampilkan pada tabel.
BtnEdit.setEnabled(false): Menonaktifkan tombol edit saat pertama kali aplikasi dijalankan.*/
    }
    
    private void SimpanBuku(){
        String sql = "Insert into data_buku values(?,?,?,?,?)";
        try {
            int kodeBuku = Integer.parseInt(TxtKode.getText()); // Ensure Kode_Buku is an integer
            PreparedStatement st = ProjectPakKhadafi.conn.prepareStatement(sql);

            st.setInt(1, kodeBuku); // Use setInt for integer values
            st.setString(2, TxtJudul.getText());
            st.setString(3, TxtPengarang.getText());
            st.setString(4, TxtPenerbit.getText());
            String tanggal = new SimpleDateFormat("yyyy-MM-dd").format(TahunTerbit.getDate());
            st.setString(5, tanggal);
            st.execute();
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Kode Buku harus berupa angka.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
        /*
String sql: Query SQL untuk menyimpan data buku.
int kodeBuku = Integer.parseInt(TxtKode.getText()): Mengonversi teks dari field Kode_Buku menjadi integer.
PreparedStatement st: Objek untuk menjalankan query SQL dengan parameter.
st.setInt(1, kodeBuku): Mengisi parameter query dengan kode buku yang berupa integer.
st.setString: Mengisi parameter query dengan data dari form.
String tanggal: Mengambil tanggal dari field TahunTerbit dan mengonversinya menjadi format "yyyy-MM-dd".
st.execute(): Menjalankan query untuk menyimpan data.
catch (NumberFormatException e): Menangkap kesalahan jika Kode Buku bukan angka.
catch (SQLException e): Menangkap kesalahan SQL dan menampilkan pesan kesalahan.
        */
    }
    
    private void editBuku(){
         String sql = "UPDATE data_buku SET Judul_Buku=?, Pengarang=?, Penerbit=?, Tahun_Terbit=? WHERE Kode_Buku=?";
        try {
            PreparedStatement st = ProjectPakKhadafi.conn.prepareStatement(sql);
            st.setString(1, TxtJudul.getText());
            st.setString(2, TxtPengarang.getText());
            st.setString(3, TxtPenerbit.getText());
            String tanggal = new SimpleDateFormat("yyyy-MM-dd").format(TahunTerbit.getDate());
            st.setString(4, tanggal);
            st.setString(5, TxtKode.getText()); // Use TxtKode for WHERE clause
            st.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil diupdate");
            refreshBuku(); // Refresh the table after update
            clearData(); // Clear input fields after update
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
        /*
String sql: Query SQL untuk memperbarui data buku berdasarkan Kode_Buku.
PreparedStatement st: Objek untuk menjalankan query SQL dengan parameter.
st.setString: Mengisi parameter query dengan data dari form.
String tanggal: Mengambil tanggal dari field TahunTerbit dan mengonversinya menjadi format "yyyy-MM-dd".
st.executeUpdate(): Menjalankan query untuk memperbarui data.
refreshBuku(): Memperbarui data buku yang ditampilkan pada tabel.
clearData(): Mengosongkan field input setelah update.
catch (SQLException e): Menangkap kesalahan SQL dan menampilkan pesan kesalahan.
        */
    }
    
    private void hapusBuku(String KODE){
             String sql = "delete from data_buku where Kode_Buku=?";
        try {
            PreparedStatement st = ProjectPakKhadafi.conn.prepareStatement(sql);
            st.setString(1, KODE);
            st.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
        /*
String sql: Query SQL untuk menghapus data buku berdasarkan Kode_Buku.
PreparedStatement st: Objek untuk menjalankan query SQL dengan parameter.
st.setString(1, KODE): Mengisi parameter query dengan Kode_Buku dari buku yang akan dihapus.
st.execute(): Menjalankan query untuk menghapus data.
catch (SQLException e): Menangkap kesalahan SQL dan menampilkan pesan kesalahan.
        */
    }
    
    private void tampilBuku(String KODE){
          Statement st;
    ResultSet rs;
    try {
        st = ProjectPakKhadafi.conn.createStatement();
        String sql = "select * from data_buku where Kode_Buku='" + KODE + "'";
        rs = st.executeQuery(sql);
        if (rs.next()) {
            TxtKode.setText(rs.getString("Kode_Buku"));
            TxtJudul.setText(rs.getString("Judul_Buku"));
            TxtPengarang.setText(rs.getString("Pengarang"));
            TxtPenerbit.setText(rs.getString("Penerbit"));
            TahunTerbit.setDate(rs.getDate("Tahun_Terbit"));
            BtnEdit.setEnabled(true); // Enable the Edit button
        } else {
            JOptionPane.showMessageDialog(this, "Data tidak ditemukan");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
    /*
Statement st: Objek untuk mengeksekusi query SQL.
ResultSet rs: Objek untuk menyimpan hasil dari query.
st = ProjectPakKhadafi.conn.createStatement(): Membuat statement untuk menjalankan query SQL.
String sql = "select * from data_buku where Kode_Buku='" + KODE + "'": Query SQL untuk mengambil data buku berdasarkan Kode_Buku.
rs = st.executeQuery(sql): Menjalankan query dan menyimpan hasilnya dalam ResultSet.
if (rs.next()): Mengecek apakah ada data yang ditemukan.
TxtKode.setText(rs.getString("Kode_Buku")): Mengisi field Kode_Buku dengan data dari hasil query.
TxtJudul.setText(rs.getString("Judul_Buku")): Mengisi field Judul_Buku dengan data dari hasil query.
TxtPengarang.setText(rs.getString("Pengarang")): Mengisi field Pengarang dengan data dari hasil query.
TxtPenerbit.setText(rs.getString("Penerbit")): Mengisi field Penerbit dengan data dari hasil query.
TahunTerbit.setDate(rs.getDate("Tahun_Terbit")): Mengisi field Tahun_Terbit dengan data dari hasil query.
BtnEdit.setEnabled(true): Mengaktifkan tombol Edit.
JOptionPane.showMessageDialog: Menampilkan pesan dialog jika data tidak ditemukan atau jika terjadi kesalahan.
    */
    }
    
    public void refreshBuku(){
        Statement st;
        ResultSet rs;
        try {
            st = ProjectPakKhadafi.conn.createStatement();
            String sql = "SELECT * FROM `data_buku` order by Kode_Buku ASC";
            rs = st.executeQuery(sql);
            String[] Header = {"Kode_Buku", "Judul_Buku", "Pengarang", "Penerbit", "Tahun_Terbit"};
            DefaultTableModel model = new DefaultTableModel(Header, 0);
            while (rs.next()) {
                Object[] row = {rs.getString("Kode_Buku"), rs.getString("Judul_Buku"), rs.getString("Pengarang"), rs.getString("Penerbit"), rs.getString("Tahun_Terbit")};
                model.addRow(row);
            }
            TableBuku.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
        /*
Statement st: Objek untuk mengeksekusi query SQL.
ResultSet rs: Objek untuk menyimpan hasil dari query.
st = ProjectPakKhadafi.conn.createStatement(): Membuat statement untuk menjalankan query SQL.
String sql = "SELECT * FROM data_buku order by Kode_Buku ASC": Query SQL untuk mengambil semua data dari tabel data_buku dan mengurutkannya berdasarkan Kode_Buku secara ascending.
rs = st.executeQuery(sql): Menjalankan query dan menyimpan hasilnya dalam ResultSet.
String[] Header = {"Kode_Buku", "Judul_Buku", "Pengarang", "Penerbit", "Tahun_Terbit"}: Membuat array string yang berisi header kolom untuk tabel.
DefaultTableModel model = new DefaultTableModel(Header, 0): Membuat model tabel baru dengan header yang telah ditentukan.
while (rs.next()): Looping melalui setiap baris data dalam ResultSet.
Object[] row = {rs.getString("Kode_Buku"), rs.getString("Judul_Buku"), rs.getString("Pengarang"), rs.getString("Penerbit"), rs.getString("Tahun_Terbit")}: Membuat array objek untuk setiap baris data.
model.addRow(row): Menambahkan baris data ke model tabel.
TableBuku.setModel(model): Mengatur model tabel baru ke TableBuku.
catch (SQLException e): Menangkap SQLException jika terjadi kesalahan saat menjalankan query.
JOptionPane.showMessageDialog(this, "Error: " + e.getMessage()): Menampilkan pesan dialog jika terjadi kesalahan.
        */
    }
    
    private void clearData(){
           TxtKode.setText("");
           TxtJudul.setText("");
           TxtPengarang.setText("");
           TxtPenerbit.setText("");
           TxtKode.requestFocus();
           BtnEdit.setEnabled(false); // Disable the Edit button
           TxtKode.setEnabled(true);
           /*
TxtKode.setText(""): Mengosongkan field Kode_Buku.
TxtJudul.setText(""): Mengosongkan field Judul_Buku.
TxtPengarang.setText(""): Mengosongkan field Pengarang.
TxtPenerbit.setText(""): Mengosongkan field Penerbit.
TxtKode.requestFocus(): Mengatur fokus ke field Kode_Buku.
BtnEdit.setEnabled(false): Menonaktifkan tombol Edit.
TxtKode.setEnabled(true): Mengaktifkan kembali field Kode_Buku.
           */
    }
    
    void Keluar() {
        int jawab = javax.swing.JOptionPane.showConfirmDialog(null, "Kamu yakin ingin keluar?", "Konfirmasi", javax.swing.JOptionPane.YES_NO_OPTION);
        if (jawab == 0) this.dispose();
          /*
int jawab: Menyimpan pilihan pengguna dari dialog konfirmasi.
javax.swing.JOptionPane.showConfirmDialog: Menampilkan dialog konfirmasi dengan pilihan Yes dan No.
if (jawab == 0) this.dispose(): Jika pengguna memilih Yes (nilai 0), maka jendela aplikasi akan ditutup (dispose).*/
    }
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TxtKode = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TxtJudul = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TxtPengarang = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TxtPenerbit = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TahunTerbit = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableBuku = new javax.swing.JTable();
        BtnTambah = new javax.swing.JButton();
        BtnEdit = new javax.swing.JButton();
        BtnCari = new javax.swing.JButton();
        BtnHapus = new javax.swing.JButton();
        BtnKeluar = new javax.swing.JButton();
        BtnKeluar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(20, 19, 79));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DATA BUKU PERPUSTAKAAN");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(242, 242, 242));
        jLabel2.setText("Kode Buku :");

        TxtKode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtKodeActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(242, 242, 242));
        jLabel3.setText("Judul Buku :");

        TxtJudul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtJudulActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(242, 242, 242));
        jLabel4.setText("Pengarang :");

        TxtPengarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtPengarangActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(242, 242, 242));
        jLabel5.setText("Penerbit :");

        TxtPenerbit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtPenerbitActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(242, 242, 242));
        jLabel6.setText("Tahun Terbit :");

        TableBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(TableBuku);

        BtnTambah.setIcon(new javax.swing.ImageIcon("C:\\Users\\rizqi\\Documents\\ProjectPakKhadafi\\src\\image\\tambahBUTTON.png")); // NOI18N
        BtnTambah.setBorder(null);
        BtnTambah.setContentAreaFilled(false);
        BtnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahActionPerformed(evt);
            }
        });

        BtnEdit.setIcon(new javax.swing.ImageIcon("C:\\Users\\rizqi\\Documents\\ProjectPakKhadafi\\src\\image\\editBUTTON.png")); // NOI18N
        BtnEdit.setBorder(null);
        BtnEdit.setContentAreaFilled(false);
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });

        BtnCari.setIcon(new javax.swing.ImageIcon("C:\\Users\\rizqi\\Documents\\ProjectPakKhadafi\\src\\image\\searchBUTTON.png")); // NOI18N
        BtnCari.setBorder(null);
        BtnCari.setContentAreaFilled(false);
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });

        BtnHapus.setIcon(new javax.swing.ImageIcon("C:\\Users\\rizqi\\Documents\\ProjectPakKhadafi\\src\\image\\deleteBUTTON.png")); // NOI18N
        BtnHapus.setBorder(null);
        BtnHapus.setContentAreaFilled(false);
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });

        BtnKeluar.setIcon(new javax.swing.ImageIcon("C:\\Users\\rizqi\\Documents\\ProjectPakKhadafi\\src\\image\\buttonOUT.png")); // NOI18N
        BtnKeluar.setBorder(null);
        BtnKeluar.setContentAreaFilled(false);
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });

        BtnKeluar1.setIcon(new javax.swing.ImageIcon("C:\\Users\\rizqi\\Documents\\ProjectPakKhadafi\\src\\image\\buttonBack.png")); // NOI18N
        BtnKeluar1.setBorder(null);
        BtnKeluar1.setContentAreaFilled(false);
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(BtnTambah)
                                .addGap(18, 18, 18)
                                .addComponent(BtnCari)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(BtnHapus)
                                .addGap(18, 18, 18)
                                .addComponent(BtnEdit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TxtPengarang, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(51, 51, 51)
                                    .addComponent(TxtKode, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(51, 51, 51)
                                    .addComponent(TxtJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(39, 39, 39)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TahunTerbit, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(BtnKeluar1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BtnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(54, 54, 54))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(401, 401, 401))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(TxtKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(TxtJudul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtPengarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TahunTerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnTambah)
                            .addComponent(BtnCari))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnHapus)
                            .addComponent(BtnEdit)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1)
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnKeluar1)
                            .addComponent(BtnKeluar))))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtKodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtKodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtKodeActionPerformed

    private void TxtJudulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtJudulActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtJudulActionPerformed

    private void TxtPengarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtPengarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtPengarangActionPerformed

    private void TxtPenerbitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtPenerbitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtPenerbitActionPerformed

    private void BtnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahActionPerformed
        SimpanBuku();
        this.requestFocus();
        refreshBuku();
        clearData();
    }//GEN-LAST:event_BtnTambahActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        editBuku();
        refreshBuku();
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
       String strInput = JOptionPane.showInputDialog("Silahkan Masukan KODE BUKU :");
       TxtKode.setEnabled(false);
       tampilBuku(strInput);
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
         String strInput = JOptionPane.showInputDialog("Silahkan Masukan KODE BUKU :");
          hapusBuku(strInput);
            if(strInput.isEmpty() == false) {
                 int jawab = javax.swing.JOptionPane.showConfirmDialog(null, "Kamu yakin ingin Dihapus?", "Konfirmasi", javax.swing.JOptionPane.YES_NO_OPTION);
                 if (jawab == 0) {
                     hapusBuku(strInput);
                 }
                  JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                  refreshBuku();
                  clearData();
            }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        Keluar();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        Utama utama = new Utama();
        utama.setVisible(true);
        utama.setLocationRelativeTo(null);
        dispose();
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

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
            java.util.logging.Logger.getLogger(Data_Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Data_Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Data_Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Data_Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Data_Buku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCari;
    private javax.swing.JButton BtnEdit;
    private javax.swing.JButton BtnHapus;
    private javax.swing.JButton BtnKeluar;
    private javax.swing.JButton BtnKeluar1;
    private javax.swing.JButton BtnTambah;
    private javax.swing.JTable TableBuku;
    private com.toedter.calendar.JDateChooser TahunTerbit;
    private javax.swing.JTextField TxtJudul;
    private javax.swing.JTextField TxtKode;
    private javax.swing.JTextField TxtPenerbit;
    private javax.swing.JTextField TxtPengarang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
