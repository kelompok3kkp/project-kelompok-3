/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.app.view.master;

import main.java.app.database.Koneksi;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

/**
 *
 * @author Artakii
 */
public class FormShift extends javax.swing.JFrame {
    private Connection koneksi = new Koneksi().connect(); 
    private DefaultTableModel model;

    /**
     * Creates new form FormShift
     */
    public FormShift() {
         initComponents();
         
        spmulai.setModel(new javax.swing.SpinnerDateModel());
        JSpinner.DateEditor mulai = new JSpinner.DateEditor(spmulai, "HH:mm:ss");
        spmulai.setEditor(mulai);
        spmulai.setValue(new Date());

        spselesai.setModel(new javax.swing.SpinnerDateModel());
        JSpinner.DateEditor selesai = new JSpinner.DateEditor(spselesai, "HH:mm:ss");
        spselesai.setEditor(selesai);
        spselesai.setValue(new Date());

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 15);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        spselesai.setValue(cal.getTime());

        cbshift.removeAllItems();
        cbshift.addItem("Pagi");
        cbshift.addItem("Malam");

        cbshift.addActionListener(evt -> aturJamByShift());

        kosong();
        aktif();
        datatable();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
      
    }
        
    private void aturJamByShift() {
    String shift = cbshift.getSelectedItem().toString();
    Calendar calMulai = Calendar.getInstance();
    Calendar calSelesai = Calendar.getInstance();

    if (shift.equalsIgnoreCase("Pagi")) {
        calMulai.set(Calendar.HOUR_OF_DAY, 9);
        calMulai.set(Calendar.MINUTE, 0);
        calMulai.set(Calendar.SECOND, 0);

        calSelesai.set(Calendar.HOUR_OF_DAY, 15);
        calSelesai.set(Calendar.MINUTE, 0);
        calSelesai.set(Calendar.SECOND, 0);
    } else if (shift.equalsIgnoreCase("Malam")) {
        calMulai.set(Calendar.HOUR_OF_DAY, 15);
        calMulai.set(Calendar.MINUTE, 0);
        calMulai.set(Calendar.SECOND, 0);

        calSelesai.set(Calendar.HOUR_OF_DAY, 21);
        calSelesai.set(Calendar.MINUTE, 0);
        calSelesai.set(Calendar.SECOND, 0);
    }

    spmulai.setValue(calMulai.getTime());
    spselesai.setValue(calSelesai.getTime());
}

    
      protected void kosong() {
        txtid.setText(generateIdShift());
        spmulai.setValue(new Date());
        spselesai.setValue(new Date());

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 15);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        spselesai.setValue(cal.getTime());
    }

    protected void aktif() {
        txtid.requestFocus();
    }

    protected void datatable() {
        Object[] Baris = {"ID Shift", "Shift", "Jam Mulai", "Jam Selesai"};
        model = new DefaultTableModel(null, Baris);
        tablejamkerja.setModel(model);
        String cariitem = txtcari.getText();

        try {
            String sql = "SELECT * FROM shift WHERE id_shift LIKE '%" + cariitem + "%'"
                    + "OR id_shift Like '%" + cariitem + "%'"
                    + "OR shift Like '%" + cariitem + "%'"
                    + "OR jam_mulai Like '%" + cariitem + "%'"
                    + "OR jam_selesai Like '%" + cariitem + "%'"
                    + "ORDER BY id_shift ASC";
            PreparedStatement stat = koneksi.prepareStatement(sql);
            ResultSet hasil = stat.executeQuery();

            while (hasil.next()) {
                model.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4)
                });
            }
            tablejamkerja.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data shift gagal ditampilkan\n" + e.getMessage());
        }
    }

    private String generateIdShift(){
        String prefix = "S";
        String sql = "SELECT id_shift FROM shift ORDER BY id_shift DESC LIMIT 1";
        try {
            Statement stat = koneksi.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                String lastId = rs.getString("id_shift");
                int number = Integer.parseInt(lastId.substring(1));
                number++;
                return String.format(prefix + "%03d", number);
            } else {
                return prefix + "001";
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal generate ID: " + e);
            return null;
        }
    }
         
    /**
     * 
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnkembali = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        spmulai = new javax.swing.JSpinner();
        spselesai = new javax.swing.JSpinner();
        btnsimpan = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablejamkerja = new javax.swing.JTable();
        btncari = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbshift = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 255, 255));

        jLabel1.setBackground(new java.awt.Color(51, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Data Jam Kerja");

        btnkembali.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnkembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/assets/icons/kembali.png"))); // NOI18N
        btnkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkembaliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnkembali)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1018, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnkembali)
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("ID Shift");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Jam Mulai");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Jam Selesai");

        txtid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtid.setEnabled(false);
        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });

        spmulai.setModel(new javax.swing.SpinnerDateModel());
        spmulai.setEnabled(false);

        spselesai.setModel(new javax.swing.SpinnerDateModel());
        spselesai.setEnabled(false);

        btnsimpan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/assets/icons/save.png"))); // NOI18N
        btnsimpan.setText("Simpan");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        btnedit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/assets/icons/edit.png"))); // NOI18N
        btnedit.setText("Edit");
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });

        btnhapus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/assets/icons/hapus.png"))); // NOI18N
        btnhapus.setText("Hapus");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });

        btnbatal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/assets/icons/batal.png"))); // NOI18N
        btnbatal.setText("Batal");
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Jam Kerja", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        tablejamkerja.setModel(new javax.swing.table.DefaultTableModel(
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
        tablejamkerja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablejamkerjaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablejamkerja);

        btncari.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btncari.setText("Cari");
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });

        txtcari.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btncari)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncari)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Shift");

        cbshift.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pagi", "Malam" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(spselesai, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spmulai, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(47, 47, 47)
                                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(68, 68, 68)
                                .addComponent(cbshift, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnsimpan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnbatal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnedit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(cbshift, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(spmulai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(spselesai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(158, 158, 158))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnsimpan)
                        .addGap(18, 18, 18)
                        .addComponent(btnbatal)
                        .addGap(18, 18, 18)
                        .addComponent(btnedit)
                        .addGap(15, 15, 15)
                        .addComponent(btnhapus)))
                .addContainerGap(489, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnkembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkembaliActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnkembaliActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        String id = txtid.getText().trim();

    // Cek apakah ID Shift sudah digunakan
    String cekSql = "SELECT id_shift FROM shift WHERE id_shift = ?";
    try {
        PreparedStatement cekStat = koneksi.prepareStatement(cekSql);
        cekStat.setString(1, id);
        ResultSet rs = cekStat.executeQuery();
        if (rs.next()) {
            JOptionPane.showMessageDialog(null, "ID Shift sudah digunakan.");
            txtid.requestFocus();
            return;
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengecek ID: " + e);
        return;
    }

    String sql = "INSERT INTO shift VALUES (?, ?, ?, ?)";
    try {
        PreparedStatement stat = koneksi.prepareStatement(sql);
        stat.setString(1, id);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String mulai = sdf.format(spmulai.getValue());
        String selesai = sdf.format(spselesai.getValue());

        stat.setString(2, mulai);
        stat.setString(3, selesai);

        stat.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data shift berhasil disimpan.");

        kosong(); // reset form
        txtid.requestFocus();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Data shift gagal disimpan: " + e.getMessage());
    }

    datatable(); // refresh tabel
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // TODO add your handling code here:
        String id = txtid.getText().trim();
String shift = cbshift.getSelectedItem().toString(); 

try {
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    String jamMulai = sdf.format(spmulai.getValue());
    String jamSelesai = sdf.format(spselesai.getValue());

    String sql = "UPDATE shift SET shift=?, jam_mulai=?, jam_selesai=? WHERE id_shift=?";
    PreparedStatement stat = koneksi.prepareStatement(sql);  
    stat.setString(1, shift);          
    stat.setString(2, jamMulai);       
    stat.setString(3, jamSelesai);     
    stat.setString(4, id);             

    int rowsAffected = stat.executeUpdate();
    if (rowsAffected > 0) {
        JOptionPane.showMessageDialog(null, "Data shift berhasil diubah.");
        kosong();
        txtid.requestFocus();
    } else {
        JOptionPane.showMessageDialog(null, "Data shift tidak ditemukan.");
    }

} catch (SQLException e) {
    JOptionPane.showMessageDialog(null, "Data shift gagal diubah: " + e.getMessage());
}

datatable(); // refresh tabel

    }//GEN-LAST:event_btneditActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(null, "Hapus data shift?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
    if (ok == 0) {
        String id = txtid.getText().trim();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID Shift tidak boleh kosong.");
            txtid.requestFocus();
            return;
        }

        String sql = "DELETE FROM shift WHERE id_shift = ?";
        try {
            PreparedStatement stat = koneksi.prepareStatement(sql);
            stat.setString(1, id);

            int rowsAffected = stat.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Data shift berhasil dihapus.");
                kosong();
                txtid.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Data shift tidak ditemukan atau sudah dihapus.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data shift gagal dihapus: " + e.getMessage());
        }

        datatable(); // refresh data setelah hapus
    }
    }//GEN-LAST:event_btnhapusActionPerformed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        // TODO add your handling code here:
         kosong(); // reset semua input shift
        spmulai.setValue(new Date()); 
        spselesai.setValue(new Date());
        txtid.requestFocus(); 
    }//GEN-LAST:event_btnbatalActionPerformed

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        // TODO add your handling code here:
        datatable();
    }//GEN-LAST:event_btncariActionPerformed

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void tablejamkerjaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablejamkerjaMouseClicked
        // TODO add your handling code here:\
        int baris = tablejamkerja.getSelectedRow();
        if (baris != -1) {
        txtid.setText(tablejamkerja.getValueAt(baris, 0).toString());

        cbshift.setSelectedItem(tablejamkerja.getValueAt(baris, 1).toString());

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date mulai = sdf.parse(tablejamkerja.getValueAt(baris, 2).toString());
            Date selesai = sdf.parse(tablejamkerja.getValueAt(baris, 3).toString());

            spmulai.setValue(mulai);
            spselesai.setValue(selesai);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat jam: " + e.getMessage());
        }
        }
    }//GEN-LAST:event_tablejamkerjaMouseClicked

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
            java.util.logging.Logger.getLogger(FormShift.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormShift.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormShift.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormShift.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormShift().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btncari;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnkembali;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JComboBox<String> cbshift;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner spmulai;
    private javax.swing.JSpinner spselesai;
    private javax.swing.JTable tablejamkerja;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtid;
    // End of variables declaration//GEN-END:variables
}
