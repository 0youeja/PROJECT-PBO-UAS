/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.projectpakkhadafi;


public class Utama extends javax.swing.JFrame {

   
    public Utama() {
        initComponents();
    }
     void Keluar() {
        int jawab = javax.swing.JOptionPane.showConfirmDialog(null, "Kamu yakin ingin keluar?", "Konfirmasi", javax.swing.JOptionPane.YES_NO_OPTION);
        if (jawab == 0) this.dispose();
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        LogOut = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        DataAnggota = new javax.swing.JMenuItem();
        DataBuku = new javax.swing.JMenuItem();
        DataPeminjam = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\rizqi\\Documents\\ProjectPakKhadafi\\src\\image\\webutama.png")); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1160, -1));

        jMenu1.setText("File");

        LogOut.setText("Logout");
        LogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOutActionPerformed(evt);
            }
        });
        jMenu1.add(LogOut);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        DataAnggota.setText("Data Anggota");
        DataAnggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DataAnggotaActionPerformed(evt);
            }
        });
        jMenu2.add(DataAnggota);

        DataBuku.setText("Data Buku");
        DataBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DataBukuActionPerformed(evt);
            }
        });
        jMenu2.add(DataBuku);

        DataPeminjam.setText("Data Pinjaman");
        DataPeminjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DataPeminjamActionPerformed(evt);
            }
        });
        jMenu2.add(DataPeminjam);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DataBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DataBukuActionPerformed
        Data_Buku data_Buku = new Data_Buku();
        data_Buku.setVisible(true);
        data_Buku.setLocationRelativeTo(null);
        dispose();
    }//GEN-LAST:event_DataBukuActionPerformed

    private void LogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOutActionPerformed
        Keluar();
    }//GEN-LAST:event_LogOutActionPerformed

    private void DataAnggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DataAnggotaActionPerformed
        Data_Anggota data_anggota = new Data_Anggota();
        data_anggota.setVisible(true);
        data_anggota.setLocationRelativeTo(null);
        dispose();
        
    }//GEN-LAST:event_DataAnggotaActionPerformed

    private void DataPeminjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DataPeminjamActionPerformed
        Data_Peminjaman data_peminjaman  = new Data_Peminjaman();
        data_peminjaman.setVisible(true);
        data_peminjaman.setLocationRelativeTo(null);
        dispose();
    }//GEN-LAST:event_DataPeminjamActionPerformed

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
            java.util.logging.Logger.getLogger(Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Utama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem DataAnggota;
    private javax.swing.JMenuItem DataBuku;
    private javax.swing.JMenuItem DataPeminjam;
    private javax.swing.JMenuItem LogOut;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}
