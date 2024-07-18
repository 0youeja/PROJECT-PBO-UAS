/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.projectpakkhadafi;


import javax.swing.JOptionPane;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;



public class Data_Anggota extends javax.swing.JFrame {

 
    public Data_Anggota() {
        initComponents();
        setTitle("FORM DATA ANGGOTA");
        this.setLocation(200, 100);
        ProjectPakKhadafi.getConnection();
        refreshSiswa();
        BtnEdit.setEnabled(false);
        
        /*
initComponents(): Memanggil metode yang menginisialisasi komponen GUI.
setTitle("FORM DATA ANGGOTA"): Mengatur judul dari jendela aplikasi menjadi "FORM DATA ANGGOTA".
this.setLocation(200, 100): Mengatur posisi jendela pada layar.
ProjectPakKhadafi.getConnection(): Memanggil metode untuk mendapatkan koneksi ke database dari kelas ProjectPakKhadafi.
refreshSiswa(): Metode untuk memperbarui data siswa (belum diberikan di kode, tetapi diasumsikan ada untuk menyegarkan data di tabel atau list).
BtnEdit.setEnabled(false): Menonaktifkan tombol edit saat pertama kali aplikasi dijalankan.*/
    }
    
   private void SimpanSiswa() {
        String sql = "Insert into data_anggota values(?,?,?,?,?)";
        String jkel = "";

        try {
            PreparedStatement st = ProjectPakKhadafi.conn.prepareStatement(sql);

            st.setString(1, TxtNim.getText());
            st.setString(2, TxtNama.getText());
            if (RbP.isSelected() == true) jkel = "pria";
            else if (RbW.isSelected() == true) jkel = "Wanita";
            st.setString(3, jkel);
            st.setString(4, TxtTelepon.getText());
            st.setString(5, cmbKelas.getSelectedItem().toString());
            st.execute();
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
        /*
String sql: Query SQL untuk menyimpan data anggota.
PreparedStatement st: Objek yang digunakan untuk mengeksekusi query SQL dengan parameter.
st.setString: Mengisi parameter query dengan data dari form.
JOptionPane.showMessageDialog: Menampilkan pesan dialog untuk memberitahu pengguna tentang status penyimpanan data.*/
    }
    //edit siswa
    private void editsiswa() {
        String sql = "UPDATE data_anggota SET Nama=?, Jenis_Kelamin=?, Telepon=?, Kelas=? WHERE Nim=?";

        try {
            PreparedStatement st = ProjectPakKhadafi.conn.prepareStatement(sql);
            String jkel = "";

            st.setString(5, TxtNim.getText());
            st.setString(1, TxtNama.getText());
            if (RbP.isSelected()) jkel = "pria";
            else if (RbW.isSelected()) jkel = "Wanita";
            st.setString(2, jkel);
            st.setString(3, TxtTelepon.getText());
            st.setString(4, cmbKelas.getSelectedItem().toString());
            st.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil diupdate");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
        /*
String sql: Query SQL untuk memperbarui data anggota berdasarkan NIM.
st.setString: Mengisi parameter query dengan data dari form.
st.executeUpdate(): Menjalankan query untuk memperbarui data.
JOptionPane.showMessageDialog: Menampilkan pesan dialog untuk memberitahu pengguna tentang status pembaruan data.*/
    }
    
    //hapus siswa
    private void hapusSiswa(String NIM) {
        String sql = "delete from data_anggota where Nim=?";
        try {
            PreparedStatement st = ProjectPakKhadafi.conn.prepareStatement(sql);
            st.setString(1, NIM);
            st.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
        /*
String sql: Query SQL untuk menghapus data anggota berdasarkan NIM.
st.setString(1, NIM): Mengisi parameter query dengan NIM dari anggota yang akan dihapus.
st.execute(): Menjalankan query untuk menghapus data.
JOptionPane.showMessageDialog: Menampilkan pesan dialog jika terjadi kesalahan.*/
    }
    
    //tampil data
   private void tampilData(String NIM) {
        Statement st;
        ResultSet rs;
        try {
            st = ProjectPakKhadafi.conn.createStatement();
            String sql = "select * from data_anggota where Nim='" + NIM + "'";
            rs = st.executeQuery(sql);
            if (rs.next()) {
                TxtNim.setText(rs.getString("Nim"));
                TxtNama.setText(rs.getString("Nama"));
                if (rs.getString("Jenis_Kelamin").equals("Pria")) {
                    RbP.setSelected(true);
                } else {
                    RbW.setSelected(true);
                }
                TxtTelepon.setText(rs.getString("Telepon"));
                cmbKelas.setSelectedItem(rs.getString("Kelas"));
                BtnEdit.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
        /*
Statement st: Objek untuk mengeksekusi query SQL.
ResultSet rs: Objek untuk menyimpan hasil dari query.
st.executeQuery(sql): Menjalankan query untuk mengambil data anggota berdasarkan NIM.
rs.next(): Mengecek apakah ada data yang ditemukan.
TxtNim.setText(rs.getString("Nim")): Mengisi form dengan data dari hasil query.
JOptionPane.showMessageDialog: Menampilkan pesan dialog jika data tidak ditemukan atau jika terjadi kesalahan.*/
    }
    
    //refresh siswa
    public void refreshSiswa() {
        Statement st;
        ResultSet rs;
        try {
            st = ProjectPakKhadafi.conn.createStatement();
            String sql = "SELECT * FROM `data_anggota` order by Nim ASC";
            rs = st.executeQuery(sql);
            String[] Header = {"Nim", "Nama", "Jenis_Kelamin", "Telepon", "Kelas"};
            DefaultTableModel model = new DefaultTableModel(Header, 0);
            while (rs.next()) {
                Object[] row = {rs.getString("Nim"), rs.getString("Nama"), rs.getString("Jenis_Kelamin"), rs.getString("Telepon"), rs.getString("Kelas")};
                model.addRow(row);
            }
            TableAnggota.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
        /*
Statement st: Objek untuk mengeksekusi perintah SQL.
ResultSet rs: Objek untuk menyimpan hasil dari query SQL.
st = ProjectPakKhadafi.conn.createStatement(): Membuat statement untuk menjalankan query SQL.
String sql = "SELECT * FROM data_anggota order by Nim ASC": Query SQL untuk mengambil semua data dari tabel data_anggota dan mengurutkannya berdasarkan kolom Nim.
rs = st.executeQuery(sql): Menjalankan query dan menyimpan hasilnya dalam ResultSet.
String[] Header = {"Nim", "Nama", "Jenis_Kelamin", "Telepon", "Kelas"}: Mendefinisikan header untuk tabel.
DefaultTableModel model = new DefaultTableModel(Header, 0): Membuat model tabel dengan header yang telah didefinisikan.
while (rs.next()): Iterasi melalui hasil query.
Object[] row = {rs.getString("Nim"), rs.getString("Nama"), rs.getString("Jenis_Kelamin"), rs.getString("Telepon"), rs.getString("Kelas")}: Mengambil data dari hasil query dan menyimpannya dalam array.
model.addRow(row): Menambahkan baris ke model tabel.
TableAnggota.setModel(model): Mengatur model tabel pada TableAnggota.
catch (SQLException e): Menangkap kesalahan SQL dan menampilkan pesan kesalahan.*/
    }
    
    private void clearData() {
        TxtNim.setText("");
        TxtNama.setText("");
        Bgp.clearSelection();
        TxtTelepon.setText("");
        cmbKelas.setSelectedIndex(-1);
        TxtNim.requestFocus();
        BtnEdit.setEnabled(false);
        TxtNim.setEnabled(true);
    }
    /*
TxtNim.setText(""): Mengosongkan teks pada field NIM.
TxtNama.setText(""): Mengosongkan teks pada field Nama.
Bgp.clearSelection(): Mengosongkan pilihan pada grup tombol radio (jenis kelamin).
TxtTelepon.setText(""): Mengosongkan teks pada field Telepon.
cmbKelas.setSelectedIndex(-1): Mengatur combo box kelas ke posisi tidak terpilih.
TxtNim.requestFocus(): Mengatur fokus kembali ke field NIM.
BtnEdit.setEnabled(false): Menonaktifkan tombol Edit.
TxtNim.setEnabled(true): Mengaktifkan kembali field NIM.*/
   
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

        Bgp = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TxtNim = new javax.swing.JTextField();
        TxtNama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        RbP = new javax.swing.JRadioButton();
        RbW = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        TxtTelepon = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmbKelas = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableAnggota = new javax.swing.JTable();
        BtnTambah = new javax.swing.JButton();
        BtnEdit = new javax.swing.JButton();
        BtnCari = new javax.swing.JButton();
        BtnKeluar = new javax.swing.JButton();
        BtnHapus = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(20, 19, 79));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DATA ANGGOTA");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nim :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Jenis Kelamin :");

        RbP.setBackground(new java.awt.Color(20, 19, 79));
        Bgp.add(RbP);
        RbP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        RbP.setForeground(new java.awt.Color(255, 255, 255));
        RbP.setText("Pria");
        RbP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbPActionPerformed(evt);
            }
        });

        RbW.setBackground(new java.awt.Color(20, 19, 79));
        Bgp.add(RbW);
        RbW.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        RbW.setForeground(new java.awt.Color(255, 255, 255));
        RbW.setText("Wanita");
        RbW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbWActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Telepon : ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Kelas : ");

        cmbKelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TI MSU ", "TMD MSU ", "AKUTANSI MSU ", "ABT MSU ", "TMJ MSU ", "TEKNIK ELEKTRO", "TEKNIK MESIN", "TEKNIK SIPIL", "AKUTANSI", "BISPRO", "ABT", "TEKNIK PERTAMBANGAN", "TEKNIK INDUSTRI", "TEKNIK KELAUTAN", "TEKNIK PENERBANGAN", "TEKNOLOGI BIOPROSES", "TEKNIK REFRIGERASI DAN TATA UDARA", "TEKNIK GRAFIKA" }));
        cmbKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKelasActionPerformed(evt);
            }
        });

        TableAnggota.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TableAnggota);

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

        BtnKeluar.setIcon(new javax.swing.ImageIcon("C:\\Users\\rizqi\\Documents\\ProjectPakKhadafi\\src\\image\\buttonOUT.png")); // NOI18N
        BtnKeluar.setBorder(null);
        BtnKeluar.setContentAreaFilled(false);
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
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

        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\rizqi\\Documents\\ProjectPakKhadafi\\src\\image\\buttonBack.png")); // NOI18N
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TxtNim)
                                    .addComponent(TxtNama)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(RbP)
                                        .addGap(18, 18, 18)
                                        .addComponent(RbW))
                                    .addComponent(TxtTelepon)
                                    .addComponent(cmbKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(BtnHapus)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(BtnEdit))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(BtnTambah)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(BtnCari)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BtnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 756, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(516, 516, 516)
                        .addComponent(jLabel1)))
                .addGap(0, 26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TxtNim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TxtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(RbP)
                    .addComponent(RbW))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BtnTambah)
                        .addGap(12, 12, 12)
                        .addComponent(BtnHapus))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BtnCari)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnEdit)))
                .addGap(52, 52, 52))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnKeluar)
                    .addComponent(jButton1))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RbWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbWActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RbWActionPerformed

    private void BtnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahActionPerformed
        SimpanSiswa();
        this.requestFocus();
        refreshSiswa();
        clearData();
        
    }//GEN-LAST:event_BtnTambahActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        editsiswa();
        refreshSiswa();
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        String strInput = JOptionPane.showInputDialog("Silahkan Masukan NIM :");
        TxtNim.setEnabled(false);
        tampilData(strInput);
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
          String strInput = JOptionPane.showInputDialog("Silahkan Masukan NIM :");
          hapusSiswa(strInput);
            if(strInput.isEmpty() == false) {
                 int jawab = javax.swing.JOptionPane.showConfirmDialog(null, "Kamu yakin ingin Dihapus?", "Konfirmasi", javax.swing.JOptionPane.YES_NO_OPTION);
                 if (jawab == 0) {
                     hapusSiswa(strInput);
                 }
                  JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                  refreshSiswa();
                  clearData();
            }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        Keluar();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Utama utama = new Utama();
        utama.setVisible(true);
        utama.setLocationRelativeTo(null);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void RbPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RbPActionPerformed

    private void cmbKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKelasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbKelasActionPerformed

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
            java.util.logging.Logger.getLogger(Data_Anggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Data_Anggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Data_Anggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Data_Anggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Data_Anggota().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Bgp;
    private javax.swing.JButton BtnCari;
    private javax.swing.JButton BtnEdit;
    private javax.swing.JButton BtnHapus;
    private javax.swing.JButton BtnKeluar;
    private javax.swing.JButton BtnTambah;
    private javax.swing.JRadioButton RbP;
    private javax.swing.JRadioButton RbW;
    private javax.swing.JTable TableAnggota;
    private javax.swing.JTextField TxtNama;
    private javax.swing.JTextField TxtNim;
    private javax.swing.JTextField TxtTelepon;
    private javax.swing.JComboBox<String> cmbKelas;
    private javax.swing.JButton jButton1;
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
