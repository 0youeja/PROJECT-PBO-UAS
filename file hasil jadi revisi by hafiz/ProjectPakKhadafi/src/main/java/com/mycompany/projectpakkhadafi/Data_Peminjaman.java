/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.projectpakkhadafi;

import com.mycompany.projectpakkhadafi.ProjectPakKhadafi;
import javax.swing.JOptionPane;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Font;

public class Data_Peminjaman extends javax.swing.JFrame {

    public Data_Peminjaman() {
    initComponents();
    setTitle("FORM DATA PEMINJAMAN");
    this.setLocation(200, 100);
    ProjectPakKhadafi.getConnection();
    refreshData();
    BtnEdit.setEnabled(false);

    TxtNimm.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            getNamaAnggotaByNim();
        }
    });

    TxtKode.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            getJudulBukuByKode();
        }
    });
}


    private void getNamaAnggotaByNim() {
        String nim = TxtNimm.getText();
        String sql = "SELECT Nama FROM data_anggota WHERE Nim = ?";
        try (PreparedStatement st = ProjectPakKhadafi.conn.prepareStatement(sql)) {
            st.setString(1, nim);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    TxtNama.setText(rs.getString("Nama"));
                } else {
                    JOptionPane.showMessageDialog(this, "Nama tidak ditemukan untuk NIM: " + nim);
                    TxtNama.setText("");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

 private void getJudulBukuByKode() {
    String kodeBuku = TxtKode.getText();
    String sql = "SELECT Judul_Buku FROM data_buku WHERE Kode_Buku = ?";
    try (PreparedStatement st = ProjectPakKhadafi.conn.prepareStatement(sql)) {
        st.setString(1, kodeBuku);
        try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                TxtNamBuk.setText(rs.getString("Judul_Buku"));
            } else {
                JOptionPane.showMessageDialog(this, "Judul Buku tidak ditemukan untuk Kode Buku: " + kodeBuku);
                TxtNamBuk.setText("");
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
}



   private void SimpanPeminjaman() {
    try {
        Connection conn = ProjectPakKhadafi.getConnection();
        String nim = TxtNimm.getText();
        String kodeBuku = TxtKode.getText();

        String sql = "INSERT INTO data_peminjaman (Nim, Nama, Kode_Buku, Judul_Buku, Tanggal_Pinjam, Tanggal_Kembali) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, nim);
            pst.setString(2, TxtNama.getText());
            pst.setString(3, kodeBuku);
            pst.setString(4, TxtNamBuk.getText());
            pst.setDate(5, new java.sql.Date(TanggalPinjam.getDate().getTime()));
            pst.setDate(6, new java.sql.Date(TanggalPengembalian.getDate().getTime()));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
            refreshData();
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}

   private void editPeminjam() {
    String sql = "UPDATE data_peminjaman SET Nama=?, Kode_Buku=?, Judul_Buku=?, Tanggal_Pinjam=?, Tanggal_Kembali=? WHERE Nim=?";
    try (PreparedStatement st = ProjectPakKhadafi.conn.prepareStatement(sql)) {
        st.setString(1, TxtNama.getText());
        st.setString(2, TxtKode.getText());
        st.setString(3, TxtNamBuk.getText());
        st.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(TanggalPinjam.getDate()));
        st.setString(5, new SimpleDateFormat("yyyy-MM-dd").format(TanggalPengembalian.getDate()));
        st.setString(6, TxtNimm.getText());
        st.executeUpdate();
        JOptionPane.showMessageDialog(this, "Data berhasil diupdate");
        refreshData();
        clearData();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
}


    private void hapusPeminjam(String NIM) {
        String sql = "DELETE FROM data_peminjaman WHERE Nim=?";
        try (PreparedStatement st = ProjectPakKhadafi.conn.prepareStatement(sql)) {
            st.setString(1, NIM);
            st.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
            refreshData();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void tampilData(String NIM) {
        String sql = "SELECT * FROM data_peminjaman WHERE Nim='" + NIM + "'";
        try (Statement st = ProjectPakKhadafi.conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                TxtNimm.setText(rs.getString("Nim"));
                TxtNama.setText(rs.getString("Nama"));
                TxtKode.setText(rs.getString("Kode_Buku"));
                TxtNamBuk.setText(rs.getString("Nama_Buku"));
                TanggalPinjam.setDate(rs.getDate("Tanggal_Pinjam"));
                TanggalPengembalian.setDate(rs.getDate("Tanggal_Kembali"));
                BtnEdit.setEnabled(true);
                TxtNimm.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

  private void refreshData() {
    String sql = "SELECT * FROM data_peminjaman ORDER BY Nim ASC";
    try (Statement st = ProjectPakKhadafi.conn.createStatement();
         ResultSet rs = st.executeQuery(sql)) {
        String[] header = {"Nim", "Nama", "Kode_Buku", "Judul_Buku", "Tanggal_Pinjam", "Tanggal_Kembali"};
        DefaultTableModel model = new DefaultTableModel(header, 0);
        while (rs.next()) {
            Object[] row = {
                rs.getString("Nim"),
                rs.getString("Nama"),
                rs.getString("Kode_Buku"),
                rs.getString("Judul_Buku"),
                rs.getString("Tanggal_Pinjam"),
                rs.getString("Tanggal_Kembali")
            };
            model.addRow(row);
        }
        TabelPeminjaman.setModel(model);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
}


    private void clearData() {
        TxtNimm.setText("");
        TxtNama.setText("");
        TxtKode.setText("");
        TxtNamBuk.setText("");
        TanggalPinjam.setDate(null);
        TanggalPengembalian.setDate(null);
        TxtNimm.setEnabled(true);
        BtnEdit.setEnabled(false);
    }

    void Keluar() {
        int jawab = javax.swing.JOptionPane.showConfirmDialog(null, "Kamu yakin ingin keluar?", "Konfirmasi", javax.swing.JOptionPane.YES_NO_OPTION);
        if (jawab == 0) this.dispose();
    }

 


 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TxtNimm = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TxtNama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TxtKode = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TxtNamBuk = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        TanggalPinjam = new com.toedter.calendar.JDateChooser();
        TanggalPengembalian = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelPeminjaman = new javax.swing.JTable();
        BtnTambah = new javax.swing.JButton();
        BtnCari = new javax.swing.JButton();
        BtnHapus = new javax.swing.JButton();
        BtnEdit = new javax.swing.JButton();
        BtnBack = new javax.swing.JButton();
        BtnKeluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(20, 19, 79));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DATA PEMINJAMAN");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("NIM:");

        TxtNimm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNimmActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("NAMA :");

        TxtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNamaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("KODE BUKU :");

        TxtKode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtKodeActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("NAMA BUKU :");

        TxtNamBuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNamBukActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("TANGGAL PINJAM :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("TANGGAL KEMBALI :");

        TabelPeminjaman.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TabelPeminjaman);

        BtnTambah.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BtnTambah.setIcon(new javax.swing.ImageIcon("C:\\Users\\rizqi\\Documents\\ProjectPakKhadafi\\src\\image\\tambahBUTTON.png")); // NOI18N
        BtnTambah.setBorder(null);
        BtnTambah.setContentAreaFilled(false);
        BtnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahActionPerformed(evt);
            }
        });

        BtnCari.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BtnCari.setIcon(new javax.swing.ImageIcon("C:\\Users\\rizqi\\Documents\\ProjectPakKhadafi\\src\\image\\searchBUTTON.png")); // NOI18N
        BtnCari.setBorder(null);
        BtnCari.setContentAreaFilled(false);
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });

        BtnHapus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BtnHapus.setIcon(new javax.swing.ImageIcon("C:\\Users\\rizqi\\Documents\\ProjectPakKhadafi\\src\\image\\deleteBUTTON.png")); // NOI18N
        BtnHapus.setBorder(null);
        BtnHapus.setContentAreaFilled(false);
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });

        BtnEdit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BtnEdit.setIcon(new javax.swing.ImageIcon("C:\\Users\\rizqi\\Documents\\ProjectPakKhadafi\\src\\image\\editBUTTON.png")); // NOI18N
        BtnEdit.setBorder(null);
        BtnEdit.setContentAreaFilled(false);
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });

        BtnBack.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BtnBack.setIcon(new javax.swing.ImageIcon("C:\\Users\\rizqi\\Documents\\ProjectPakKhadafi\\src\\image\\buttonBack.png")); // NOI18N
        BtnBack.setBorder(null);
        BtnBack.setContentAreaFilled(false);
        BtnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBackActionPerformed(evt);
            }
        });

        BtnKeluar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BtnKeluar.setIcon(new javax.swing.ImageIcon("C:\\Users\\rizqi\\Documents\\ProjectPakKhadafi\\src\\image\\buttonOUT.png")); // NOI18N
        BtnKeluar.setBorder(null);
        BtnKeluar.setContentAreaFilled(false);
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(TxtKode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(TxtNama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(68, 68, 68)
                                            .addComponent(TxtNimm, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(TxtNamBuk, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(TanggalPengembalian, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                                            .addComponent(TanggalPinjam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(BtnHapus)
                                        .addGap(18, 18, 18)
                                        .addComponent(BtnEdit))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(BtnTambah)
                                        .addGap(18, 18, 18)
                                        .addComponent(BtnCari)))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BtnBack)
                                .addGap(18, 18, 18)
                                .addComponent(BtnKeluar)
                                .addGap(13, 13, 13))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(491, 491, 491)
                        .addComponent(jLabel1)))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(TxtNimm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(TxtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(TxtKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(TxtNamBuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(TanggalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(TanggalPengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(69, 69, 69)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnCari, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BtnTambah)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(BtnHapus)
                        .addGap(56, 56, 56))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BtnBack)
                            .addComponent(BtnEdit)
                            .addComponent(BtnKeluar))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1160, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtNimmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNimmActionPerformed
        getNamaAnggotaByNim();
    }//GEN-LAST:event_TxtNimmActionPerformed

    private void TxtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNamaActionPerformed
        
    }//GEN-LAST:event_TxtNamaActionPerformed

    private void TxtKodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtKodeActionPerformed
        getJudulBukuByKode();
    }//GEN-LAST:event_TxtKodeActionPerformed

    private void TxtNamBukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNamBukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNamBukActionPerformed

    private void BtnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahActionPerformed
        SimpanPeminjaman();
       

    }//GEN-LAST:event_BtnTambahActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        editPeminjam();
        
  
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
      tampilData(TxtNimm.getText());
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
          hapusPeminjam(TxtNimm.getText());
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        Keluar();
        /*
Memamnggil Metode Keluar untuk keluar dari semua halaman.
        */
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBackActionPerformed
        Utama utama = new Utama();
        utama.setVisible(true);
        utama.setLocationRelativeTo(null);
        dispose();
    }//GEN-LAST:event_BtnBackActionPerformed

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
            java.util.logging.Logger.getLogger(Data_Peminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Data_Peminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Data_Peminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Data_Peminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Data_Peminjaman().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBack;
    private javax.swing.JButton BtnCari;
    private javax.swing.JButton BtnEdit;
    private javax.swing.JButton BtnHapus;
    private javax.swing.JButton BtnKeluar;
    private javax.swing.JButton BtnTambah;
    private javax.swing.JTable TabelPeminjaman;
    private com.toedter.calendar.JDateChooser TanggalPengembalian;
    private com.toedter.calendar.JDateChooser TanggalPinjam;
    private javax.swing.JTextField TxtKode;
    private javax.swing.JTextField TxtNamBuk;
    private javax.swing.JTextField TxtNama;
    private javax.swing.JTextField TxtNimm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
