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
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author frdn1120
 */
public class FormKendaraan extends javax.swing.JFrame {
    private Connection koneksi = new Koneksi().connect();
    private DefaultTableModel model;

    /**
     * Creates new form FormKendaraan
     */
    public FormKendaraan() {
        initComponents();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        kosong();
        aktif();
        datatable();
        loadComboBoxData();        
        
        txtcari.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent ect){
                datatable();
            }
        });
        
        cbpelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt){
                loadNamaPelanggan();
            }
        }); 
    }
    
    protected void aktif(){
        txtplat.requestFocus();
    }

    protected void kosong(){
        txtid.setText(generateIdTrx());
        cbpelanggan.setSelectedItem(null);
        buttonGroup1.clearSelection();
        cbmodel.setSelectedItem(null);
        txtplat.setText("");
    }
    
    private String generateIdTrx(){
        String prefix = "KD";
        String sql = "SELECT id_kendaraan FROM data_kendaraan ORDER BY id_kendaraan DESC LIMIT 1";
        try {
            Statement stat = koneksi.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                String lastId = rs.getString("id_kendaraan");
                int number = Integer.parseInt(lastId.substring(2));
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
    
    protected void datatable(){
        Object[] Baris = {"ID Kendaraan", "ID Pelanggan", "Jenis Kendaraan", "Model Kendaraan", "Plat Nomor"};
            model = new DefaultTableModel(null, Baris);
            String cariitem = txtcari.getText();
            
            try {
                String sql = "SELECT *FROM data_kendaraan WHERE id_kendaraan LIKE '%" + cariitem + "%' " 
                        + "OR id_pelanggan LIKE '%" + cariitem + "%' " 
                        + "OR jenis_kendaraan LIKE '%" + cariitem + "%' " 
                        + "OR model_kendaraan LIKE '%" + cariitem + "%' "
                        + "OR plat_nomor LIKE '%" + cariitem + "%' "
                        + "ORDER BY id_kendaraan ASC";
                Statement stat = koneksi.createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                
                while (hasil.next()){
                    model.addRow(new Object[]{
                        hasil.getString(1),
                        hasil.getString(2),
                        hasil.getString(3),
                        hasil.getString(4),
                        hasil.getString(5),
                    });
                }
                tablekendaraan.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal dipanggil"+e);
        }
    }
    
    public void loadComboBoxData(){
        try {
            Connection koneksi = Koneksi.connect();
            Statement stat = koneksi.createStatement();
            
            ResultSet rsPelanggan = stat.executeQuery("SELECT id_pelanggan FROM data_pelanggan");
            while (rsPelanggan.next()){
                cbpelanggan.addItem(rsPelanggan.getString("id_pelanggan"));
            }
            rsPelanggan.close();
        } catch (Exception e) {
             e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal load data combobox: "+ e.getMessage());
        }
    }
    
    private void loadNamaPelanggan() {
        String id = (String) cbpelanggan.getSelectedItem();
        if (id != null) {
            try {
                String sql = "SELECT nama_pelanggan FROM data_pelanggan WHERE id_pelanggan = ?";
                PreparedStatement ps = koneksi.prepareStatement(sql);
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String namaPelanggan = rs.getString("nama_pelanggan");
                    txtnamapelanggan.setText(namaPelanggan);
                } else {
                    txtnamapelanggan.setText("");
                }
                rs.close();
                ps.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal mengambil nama pelanggan: " + e.getMessage());
            }
        }
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnkembali = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtplat = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        kmotor = new javax.swing.JRadioButton();
        kmobil = new javax.swing.JRadioButton();
        cbmodel = new javax.swing.JComboBox<>();
        txtnamapelanggan = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablekendaraan = new javax.swing.JTable();
        txtcari = new javax.swing.JTextField();
        btncari = new javax.swing.JButton();
        cbpelanggan = new javax.swing.JComboBox<>();
        btnsimpan = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(51, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Data Kendaraan");

        btnkembali.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnkembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/assets/icons/kembali.png"))); // NOI18N
        btnkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkembaliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnkembali)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(79, 79, 79))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnkembali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("ID Kendaraan");

        txtid.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtid.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("ID Pelanggan");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Jenis Kendaraan");

        txtplat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Model Kendaraan");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Plat Nomor");

        buttonGroup1.add(kmotor);
        kmotor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        kmotor.setText("Motor");
        kmotor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kmotorActionPerformed(evt);
            }
        });

        buttonGroup1.add(kmobil);
        kmobil.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        kmobil.setText("Mobil");
        kmobil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kmobilActionPerformed(evt);
            }
        });

        cbmodel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbmodel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Motor Besar", "Motor Kecil", "Sedan", "SUV", "MPV", "Hatchback", "Truck", "Pick-Up" }));

        txtnamapelanggan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtnamapelanggan.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Nama Pelanggan");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Kendaraan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        tablekendaraan.setModel(new javax.swing.table.DefaultTableModel(
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
        tablekendaraan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablekendaraanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablekendaraan);

        txtcari.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btncari.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btncari.setText("Cari");
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btncari)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncari))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addContainerGap())
        );

        cbpelanggan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(26, 26, 26)
                            .addComponent(cbmodel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel6)
                                .addComponent(jLabel4)
                                .addComponent(btnedit, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(33, 33, 33)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(kmotor)
                                            .addGap(18, 18, 18)
                                            .addComponent(kmobil))
                                        .addComponent(txtnamapelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtplat, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnhapus)
                                        .addComponent(btnbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(36, 36, 36)
                                    .addComponent(cbpelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(149, 149, 149)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnsimpan))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbpelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(kmotor)
                    .addComponent(kmobil))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbmodel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtplat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtnamapelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsimpan)
                    .addComponent(btnbatal))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnedit)
                    .addComponent(btnhapus))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnkembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkembaliActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnkembaliActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        String plat = txtplat.getText().trim();
        String jenis = null;
        if(kmotor.isSelected()){
            jenis = "Motor";
        }else if (kmobil.isSelected()){
            jenis = "Mobil";
        }
        if (plat.isEmpty()){
            JOptionPane.showMessageDialog(null, "Data kendaraan harus terisi semua.");
            txtplat.requestFocus();
            return;
        }
        String cekSql = "SELECT id_kendaraan FROM data_kendaraan WHERE id_kendaraan = ?";
        try {
            PreparedStatement cekStat = koneksi.prepareStatement(cekSql);
            cekStat.setString(1, jenis);
            ResultSet rs = cekStat.executeQuery();
            if (rs.next()){
                JOptionPane.showMessageDialog(null, "ID sudah digunakan.");
                txtplat.requestFocus();
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengecek ID: " + e);
            return;
        }
        String sql = "INSERT into data_kendaraan values (?,?,?,?,?)";
        try{
            PreparedStatement stat = koneksi.prepareStatement(sql);
            stat.setString(1, txtid.getText());
            stat.setString(2, cbpelanggan.getSelectedItem().toString());
            stat.setString(3, jenis);
            stat.setString(4, cbmodel.getSelectedItem().toString());
            stat.setString(5, txtplat.getText());

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan.");
            kosong();
            txtplat.requestFocus();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data gagal disimpan."+e);
        }
        datatable();
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // TODO add your handling code here:
        String jenis = null;
        String nama = txtplat.getText().trim();
        if (kmotor.isSelected()){
            jenis = "Motor";
        } else if (kmobil.isSelected()){
            jenis = "Mobil";
        }
        if (nama.isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Pelanggan harus terisi semua.");
            txtplat.requestFocus();
            return;
        }
        try{
            String sql = "UPDATE data_kendaraan set id_pelanggan=?, jenis_kendaraan=?, model_kendaraan=?, plat_nomor=? WHERE id_kendaraan='"+txtid.getText()+"'";
            PreparedStatement stat = koneksi.prepareStatement(sql);
            stat.setString(1, cbpelanggan.getSelectedItem().toString());
            stat.setString(2, jenis);
            stat.setString(3, cbmodel.getSelectedItem().toString());
            stat.setString(4, txtplat.getText());

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            kosong();
            txtplat.requestFocus();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data gagal diubah" +e);
        }
        datatable();
    }//GEN-LAST:event_btneditActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(null, "Hapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0){
            String id = txtid.getText().trim();
            if (id.isEmpty()){
                JOptionPane.showMessageDialog(null, "ID Kendaraan tidak boleh kosong.");
                txtplat.requestFocus();
                return;
            }
            String sql = "DELETE FROM data_kendaraan WHERE id_kendaraan = ?";
            try {
                PreparedStatement stat = koneksi.prepareStatement(sql);
                stat.setString(1, id);

                int rowsAffected = stat.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus.");
                    kosong();
                    txtplat.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan atau sudah dihapus.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data gagal dihapus: " + e.getMessage());
            }
            datatable();
        }
    }//GEN-LAST:event_btnhapusActionPerformed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        // TODO add your handling code here:
        kosong();
    }//GEN-LAST:event_btnbatalActionPerformed

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        // TODO add your handling code here:
        datatable();
    }//GEN-LAST:event_btncariActionPerformed

    private void kmotorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kmotorActionPerformed
        // TODO add your handling code here:
        String[] modelMotor = {"Motor Besar", "Motor Kecil"};
        cbmodel.setModel(new javax.swing.DefaultComboBoxModel<>(modelMotor));
    }//GEN-LAST:event_kmotorActionPerformed

    private void kmobilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kmobilActionPerformed
        // TODO add your handling code here:
        String[] modelMobil = {"Mobil"};
        cbmodel.setModel(new javax.swing.DefaultComboBoxModel<>(modelMobil));
    }//GEN-LAST:event_kmobilActionPerformed

    private void tablekendaraanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablekendaraanMouseClicked
        // TODO add your handling code here:
        int bar = tablekendaraan.getSelectedRow();
        String a = model.getValueAt(bar, 0).toString();
        String b = model.getValueAt(bar, 1).toString();
        String c = model.getValueAt(bar, 2).toString();
        String d = model.getValueAt(bar, 3).toString();
        String e = model.getValueAt(bar, 4).toString();
        
        txtid.setText(a);
        cbpelanggan.setSelectedItem(b);
        
        if (c != null){
            if(c.equalsIgnoreCase("Motor")){
                kmotor.setSelected(true);
                kmobil.setSelected(false);
                String[] modelMotor = {"Motor Besar", "Motor Kecil"};
                cbmodel.setModel(new javax.swing.DefaultComboBoxModel<>(modelMotor));
            } else if(c.equalsIgnoreCase("Mobil")){
                kmobil.setSelected(true);
                kmotor.setSelected(false);
                String[] modelMobil = {"Mobil Sedan", "Mobil SUV", "Mobil MPV", "Mobil Hatchback", "Mobil Pick-Up"};
                cbmodel.setModel(new javax.swing.DefaultComboBoxModel<>(modelMobil));
            }
        }
        
        cbmodel.setSelectedItem(d);
        txtplat.setText(e);
    }//GEN-LAST:event_tablekendaraanMouseClicked

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
            java.util.logging.Logger.getLogger(FormKendaraan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormKendaraan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormKendaraan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormKendaraan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormKendaraan().setVisible(true);
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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbmodel;
    private javax.swing.JComboBox<String> cbpelanggan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton kmobil;
    private javax.swing.JRadioButton kmotor;
    private javax.swing.JTable tablekendaraan;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnamapelanggan;
    private javax.swing.JTextField txtplat;
    // End of variables declaration//GEN-END:variables
}
