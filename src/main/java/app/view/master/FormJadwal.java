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

/**
 *
 * @author aliframadhan
 */
public class FormJadwal extends javax.swing.JFrame {
    private Connection koneksi = new Koneksi().connect(); 
    private DefaultTableModel model;

    /**
     * Creates new form FormJadwal
     */
    public FormJadwal() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        loadComboBoxData();
        kosong();
        aktif();
        datatable();
        
        txtcari.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent ect){
                datatable();
            }
        });
        
        cbKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt){
                loadKaryawan();
            }
        }); 
    }
    
    protected void aktif(){
        txtid.requestFocus();
    }
    
    protected void kosong(){
        txtid.setText(generateIdJadwal());
        if (cbKaryawan.getItemCount() > 0) {
            cbKaryawan.setSelectedIndex(0);   
        } else {
            cbKaryawan.setSelectedIndex(-1); 
        }
        
        txtnama.setText("");
        if (cbGrup.getItemCount() > 0) {
            cbGrup.setSelectedIndex(0);
        } else {
            cbGrup.setSelectedIndex(-1);
        }
    }
    
    private String generateIdJadwal(){
        String prefix = "G";
        String sql = "SELECT id_grup FROM grup_kerja ORDER BY id_grup DESC LIMIT 1";
        try{
            Statement stat = koneksi.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()){
                String lastId = rs.getString("id_grup");
                int number = Integer.parseInt(lastId.substring(1));
                number++;
                return String.format(prefix+"%03d",number);
            }else{
                return prefix + "001";
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal generate ID: " + e);
            return null;
        }
    }

    protected void datatable(){
        Object[] Baris = {"ID Jadwal", "ID Karyawan", "Nama Karyawan", "Grup Jadwal"};
            model = new DefaultTableModel(null, Baris);
            String cariitem = txtcari.getText();
            
            try {
                String sql = "SELECT *FROM grup_kerja WHERE grup_jadwal LIKE '%" + cariitem + "%' " 
                        + "OR id_karyawan LIKE '%" + cariitem + "%' " 
                        + "OR nama_karyawan LIKE '%" + cariitem + "%' " 
                        + "OR grup_jadwal LIKE '%" + cariitem + "%' "
                        + "ORDER BY id_grup ASC";
                Statement stat = koneksi.createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                
                while (hasil.next()){
                    model.addRow(new Object[]{
                        hasil.getString(1),
                        hasil.getString(2),
                        hasil.getString(3),
                        hasil.getString(4),
                    });
                }
                tbljdwl.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal dipanggil"+e);
        }
    }
    
    public void loadComboBoxData() {
        cbKaryawan.removeAllItems();
        cbKaryawan.addItem("-- Pilih --");

        String sql = "SELECT id_karyawan FROM data_karyawan";
        try (Statement stat = koneksi.createStatement();
             ResultSet rsKaryawan = stat.executeQuery(sql)) {

            while (rsKaryawan.next()) {
                cbKaryawan.addItem(rsKaryawan.getString("id_karyawan"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Gagal load data combobox: " + e.getMessage());
        }
    }
    
    public void loadKaryawan() {
        Object selected = cbKaryawan.getSelectedItem();
        if (selected == null) {
            txtnama.setText("");
            return;
        }

        String idKaryawan = selected.toString();
        if (idKaryawan.equals("-- Pilih --")) { 
            txtnama.setText("");
            return;
        }

        String sql = "SELECT nama_karyawan FROM data_karyawan WHERE id_karyawan = ?";

        try (PreparedStatement ps = koneksi.prepareStatement(sql)) {
            ps.setString(1, idKaryawan);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    txtnama.setText(rs.getString("nama_karyawan"));
                } else {
                    txtnama.setText("");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Gagal load nama karyawan: " + e.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnkembali = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cbGrup = new javax.swing.JComboBox<>();
        btnsimpan = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        cbKaryawan = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtcari = new javax.swing.JTextField();
        btncari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbljdwl = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 255, 255));

        jLabel1.setBackground(new java.awt.Color(51, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Data Jadwal Libur");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 949, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnkembali)
        );

        cbGrup.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbGrup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --", "Grup A", "Grup B", "Grup C" }));

        btnsimpan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/assets/icons/save.png"))); // NOI18N
        btnsimpan.setText("Simpan");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
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

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("ID Jadwal");

        txtid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtid.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Nama Karyawan");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("ID Karyawan");

        txtnama.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtnama.setEnabled(false);

        cbKaryawan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbKaryawan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --" }));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Jadwal Grup");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Jadwal Kerja", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        txtcari.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btncari.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btncari.setText("Cari");
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });

        tbljdwl.setModel(new javax.swing.table.DefaultTableModel(
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
        tbljdwl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbljdwlMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbljdwl);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btncari)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncari))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Keterangan Grup", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Grup A libur Selasa");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Grup B libur Rabu");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Grup C libur Kamis");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Jum'at Sabtu Minggu Senin Masuk Semua");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel6)
                .addGap(24, 24, 24)
                .addComponent(jLabel7)
                .addGap(24, 24, 24)
                .addComponent(jLabel8)
                .addGap(24, 24, 24)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(71, 71, 71)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbGrup, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnbatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnsimpan))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnedit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cbGrup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnsimpan)
                            .addComponent(btnedit))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnbatal)
                            .addComponent(btnhapus))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnkembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkembaliActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnkembaliActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        String idGrup     = txtid.getText().trim();
        String idKaryawan = "";
        String nama       = txtnama.getText().trim();
        String grupCombo  = "";

        // Ambil nilai combobox secara aman
        if (cbKaryawan.getSelectedItem() != null) {
            idKaryawan = cbKaryawan.getSelectedItem().toString().trim();
        }
        if (cbGrup.getSelectedItem() != null) {
            grupCombo = cbGrup.getSelectedItem().toString().trim();
        }

        // ===== VALIDASI =====
        if (idGrup.isEmpty()
                || idKaryawan.isEmpty() || idKaryawan.equals("-- Pilih --")
                || nama.isEmpty()
                || grupCombo.isEmpty() || grupCombo.equals("-- Pilih --")) {

            JOptionPane.showMessageDialog(this,
                    "ID Jadwal, ID Karyawan, Nama Karyawan, dan Grup Jadwal harus diisi.",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            cbKaryawan.requestFocus();
            return;
        }

        // ===== CEK DUplikasi =====
        String cekSql = "SELECT id_grup FROM grup_kerja WHERE id_grup = ?";
        try {
            PreparedStatement cekStat = koneksi.prepareStatement(cekSql);
            cekStat.setString(1, idGrup);
            ResultSet rs = cekStat.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this,
                        "ID Jadwal sudah digunakan.",
                        "Peringatan",
                        JOptionPane.WARNING_MESSAGE);
                txtid.requestFocus();
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Terjadi kesalahan saat mengecek ID: " + e,
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ===== INSERT TANPA MAPPING =====
        String sql = "INSERT INTO grup_kerja "
                   + "(id_grup, id_karyawan, nama_karyawan, grup_jadwal) "
                   + "VALUES (?,?,?,?)";

        try {
            PreparedStatement stat = koneksi.prepareStatement(sql);
            stat.setString(1, idGrup);
            stat.setString(2, idKaryawan);
            stat.setString(3, nama);
            stat.setString(4, grupCombo); 

            stat.executeUpdate();
            JOptionPane.showMessageDialog(this,
                    "Data berhasil disimpan.",
                    "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);

            kosong();
            txtid.requestFocus();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Data gagal disimpan: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        datatable();
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        // TODO add your handling code here:
        kosong();
        aktif();
    }//GEN-LAST:event_btnbatalActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // TODO add your handling code here:
        String idGrup     = txtid.getText().trim();
        String idKaryawan = "";
        String nama       = txtnama.getText().trim();
        String grupCombo  = "";

        // Ambil nilai combobox secara aman
        if (cbKaryawan.getSelectedItem() != null) {
            idKaryawan = cbKaryawan.getSelectedItem().toString().trim();
        }
        if (cbGrup.getSelectedItem() != null) {
            grupCombo = cbGrup.getSelectedItem().toString().trim();
        }

        // ===== VALIDASI =====
        if (idGrup.isEmpty()
                || idKaryawan.isEmpty() || idKaryawan.equals("-- Pilih --")
                || nama.isEmpty()
                || grupCombo.isEmpty() || grupCombo.equals("-- Pilih --")) {

            JOptionPane.showMessageDialog(this,
                    "ID Jadwal, ID Karyawan, Nama Karyawan, dan Grup Jadwal harus diisi.",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            cbKaryawan.requestFocus();
            return;
        }

        // ===== UPDATE DATA (ID_GRUP TIDAK DIUBAH) =====
        String sql = "UPDATE grup_kerja SET "
                   + "id_karyawan = ?, "
                   + "nama_karyawan = ?, "
                   + "grup_jadwal = ? "
                   + "WHERE id_grup = ?";

        try {
            PreparedStatement stat = koneksi.prepareStatement(sql);
            stat.setString(1, idKaryawan);
            stat.setString(2, nama);
            stat.setString(3, grupCombo);
            stat.setString(4, idGrup);      // kondisi WHERE

            int hasil = stat.executeUpdate();
            if (hasil > 0) {
                JOptionPane.showMessageDialog(this,
                        "Data berhasil diubah.",
                        "Informasi",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Data dengan ID tersebut tidak ditemukan.",
                        "Informasi",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            datatable();   // refresh tabel
            kosong();      // reset form
            txtid.requestFocus();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Data gagal diubah: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btneditActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
        String idGrup = txtid.getText().trim();

        // ===== VALIDASI: ID HARUS ADA =====
        if (idGrup.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Pilih data yang akan dihapus terlebih dahulu.",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // ===== KONFIRMASI HAPUS =====
        int konfirmasi = JOptionPane.showConfirmDialog(this,
                "Yakin ingin menghapus data dengan ID: " + idGrup + " ?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (konfirmasi != JOptionPane.YES_OPTION) {
            return; 
        }

        // ===== PROSES HAPUS =====
        String sql = "DELETE FROM grup_kerja WHERE id_grup = ?";

        try {
            PreparedStatement stat = koneksi.prepareStatement(sql);
            stat.setString(1, idGrup);

            int hasil = stat.executeUpdate();
            if (hasil > 0) {
                JOptionPane.showMessageDialog(this,
                        "Data berhasil dihapus.",
                        "Informasi",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Data dengan ID tersebut tidak ditemukan.",
                        "Informasi",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            kosong(); 
            datatable();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Data gagal dihapus: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnhapusActionPerformed

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        // TODO add your handling code here:
        datatable();
    }//GEN-LAST:event_btncariActionPerformed

    private void tbljdwlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbljdwlMouseClicked
        // TODO add your handling code here:
        int bar = tbljdwl.getSelectedRow();
        String a = model.getValueAt(bar, 0).toString();
        String b = model.getValueAt(bar, 1).toString();
        String c = model.getValueAt(bar, 2).toString();
        String d = model.getValueAt(bar, 3).toString();
        
        txtid.setText(a);
        cbKaryawan.setSelectedItem(b);
        txtnama.setText(c);
        cbGrup.setSelectedItem(d);
    }//GEN-LAST:event_tbljdwlMouseClicked

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
            java.util.logging.Logger.getLogger(FormJadwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormJadwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormJadwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormJadwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormJadwal().setVisible(true);
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
    private javax.swing.JComboBox<String> cbGrup;
    private javax.swing.JComboBox<String> cbKaryawan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbljdwl;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnama;
    // End of variables declaration//GEN-END:variables
}
