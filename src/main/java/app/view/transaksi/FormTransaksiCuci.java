/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.app.view.transaksi;

import main.java.app.database.Koneksi;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;

/**
 *
 * @author aliframadhan
 */
public class FormTransaksiCuci extends javax.swing.JFrame {
    String tanggal;
    
    private Connection koneksi = new Koneksi().connect();
    private DefaultTableModel model;

    /**
     * Creates new form FormMembership
     */
    public FormTransaksiCuci() {
        initComponents();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        kosong();
        aktif();
        datatable();
        loadComboBoxData();
        
        Date date = new Date();
        SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
        
        txttanggal.setText(tgl.format(date));
        txtcari.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent ect){
                datatable();
            }
        });
        
        cbkaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt){
                loadNamaKaryawan();
            }
        }); 
        
        cbpelanggan.addActionListener(new java.awt.event.ActionListener(){
          public void actionPerformed(java.awt.event.ActionEvent evt){
              loadNamaPelanggan();
          }
        });
    }
    
    protected void aktif(){
        txtharga.requestFocus();
    }
    
    protected void kosong(){
        txtid.setText(generateIdTrx());
        cbkaryawan.setSelectedItem(null);
        cbpelanggan.setSelectedItem(null);
        cblayanan.setSelectedItem(null);
        txtharga.setText("");
        txtdiskon.setText("");
        txtnamapelanggan.setText("");
        txtnamakaryawan.setText("");
        txtjenislayanan.setText("");
    }
    
    private String generateIdTrx(){
        String prefix = "TRX";
        String sql = "SELECT id_transaksi FROM transaksi_cuci ORDER BY id_transaksi DESC LIMIT 1";
        try {
            Statement stat = koneksi.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                String lastId = rs.getString("id_transaksi");
                int number = Integer.parseInt(lastId.substring(3));
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
        Object[] Baris = {"ID Transaksi", "Tanggal Transaksi", "ID Karyawan", "ID Pelanggan", "ID Layanan", "Total Harga", "Diskon (%)", "Metode Pembayaran"};
            model = new DefaultTableModel(null, Baris);
            String cariitem = txtcari.getText();
            
            try {
                String sql = "SELECT *FROM transaksi_cuci WHERE id_transaksi LIKE '%" + cariitem + "%' " 
                        + "OR tanggal_transaksi LIKE '%" + cariitem + "%' " 
                        + "OR id_karyawan LIKE '%" + cariitem + "%' " 
                        + "OR id_pelanggan LIKE '%" + cariitem + "%' " 
                        + "OR id_layanan LIKE '%" + cariitem + "%' " 
                        + "OR harga LIKE '%" + cariitem + "%' "
                        + "OR diskon LIKE '%" + cariitem + "%' "
                        + "OR metode_pembayaran LIKE '%" + cariitem + "%' "
                        + "ORDER BY id_transaksi ASC";
                Statement stat = koneksi.createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                
                while (hasil.next()){
                    model.addRow(new Object[]{
                        hasil.getString(1),
                        hasil.getString(2),
                        hasil.getString(3),
                        hasil.getString(4),
                        hasil.getString(5),
                        hasil.getString(6),
                        hasil.getString(7),
                        hasil.getString(8),
                    });
                }
                tabletrx.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal dipanggil"+e);
        }
    }
    
    public void loadComboBoxData(){
        try {
            Connection koneksi = Koneksi.connect();
            Statement stat = koneksi.createStatement();
            
            ResultSet rsKaryawan = stat.executeQuery("SELECT id_karyawan FROM data_karyawan");
            while (rsKaryawan.next()){
                cbkaryawan.addItem(rsKaryawan.getString("id_karyawan"));
            }
            rsKaryawan.close();
            
            ResultSet rsPelanggan = stat.executeQuery("SELECT id_pelanggan FROM data_pelanggan");
            while (rsPelanggan.next()){
                cbpelanggan.addItem(rsPelanggan.getString("id_pelanggan"));
            }
            rsPelanggan.close();
            
            ResultSet rsLayanan = stat.executeQuery("SELECT id_layanan FROM layanan_cuci");
            while (rsLayanan.next()){
                cblayanan.addItem(rsLayanan.getString("id_layanan"));
            }
            rsLayanan.close();
            
            stat.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal load data combobox: "+ e.getMessage());
        }
    }
    
    private void loadNamaKaryawan(){
        String id = (String) cbkaryawan.getSelectedItem();
        if (id != null) {
            try {
                String sql = "SELECT nama_karyawan FROM data_karyawan WHERE id_karyawan = ?";
                PreparedStatement ps = koneksi.prepareStatement(sql);
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    txtnamakaryawan.setText(rs.getString("nama_karyawan"));
                } else {
                    txtnamakaryawan.setText("");
                }
                rs.close();
                ps.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal mengambil nama karyawan: " + e.getMessage());
            }
        }
    }
    
    private void loadNamaPelanggan(){
        String id = (String) cbpelanggan.getSelectedItem();
        if (id != null) {
            try {
                String sql = "SELECT nama_pelanggan, model_kendaraan, jenis_layanan FROM data_pelanggan WHERE id_pelanggan = ?";
                PreparedStatement ps = koneksi.prepareStatement(sql);
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String namaPelanggan = rs.getString("nama_pelanggan");
                    String modelKendaraan = rs.getString("model_kendaraan");
                    String namaLayanan = rs.getString("jenis_layanan");
                    
                    txtnamapelanggan.setText(namaPelanggan);
                    txtmodelkendaraan.setText(modelKendaraan);
                    txtjenislayanan.setText(namaLayanan);
                    
                    String sql2 = "SELECT id_layanan, harga FROM layanan_cuci WHERE jenis_layanan = ?";
                    PreparedStatement ps2 = koneksi.prepareStatement(sql2);
                    ps2.setString(1, namaLayanan);
                    ResultSet rs2 = ps2.executeQuery();
                    if(rs2.next()){
                        String harga = rs2.getString("harga");
                        String idLayanan = rs2.getString("id_layanan");
                        
                        txtharga.setText(harga);
                        cblayanan.setSelectedItem(idLayanan);
                    } else{
                        txtharga.setText("");
                    }
                    rs2.close();
                    ps2.close();
                } else {
                    txtnamapelanggan.setText("");
                    txtmodelkendaraan.setText("");
                    txtjenislayanan.setText("");
                    txtharga.setText("");
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txttanggal = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbkaryawan = new javax.swing.JComboBox<>();
        cbpelanggan = new javax.swing.JComboBox<>();
        txtharga = new javax.swing.JTextField();
        txtdiskon = new javax.swing.JTextField();
        cbpembayaran = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtnamapelanggan = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtjenislayanan = new javax.swing.JTextField();
        btnbatal = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        btnsimpan = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        txtid = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtmodelkendaraan = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cblayanan = new javax.swing.JComboBox<>();
        txtnamakaryawan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtcari = new javax.swing.JTextField();
        btncari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabletrx = new javax.swing.JTable();
        btnkembali = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Transaksi Cuci");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("ID Transaksi");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Tanggal Transaksi");

        txttanggal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txttanggal.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("ID Karyawan");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("ID Pelanggan");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Total Harga");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Diskon (%)");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Metode Pembayaran");

        cbkaryawan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cbpelanggan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtharga.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtharga.setEnabled(false);

        txtdiskon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cbpembayaran.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbpembayaran.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tunai", "Transfer", "E-Wallet" }));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Nama Pelanggan");

        txtnamapelanggan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtnamapelanggan.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Model Kendaraan");

        txtjenislayanan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtjenislayanan.setEnabled(false);

        btnbatal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/assets/icons/batal.png"))); // NOI18N
        btnbatal.setText("Batal");
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
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

        txtid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtid.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Jenis Layanan");

        txtmodelkendaraan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtmodelkendaraan.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("ID Layanan");

        cblayanan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cblayanan.setEnabled(false);

        txtnamakaryawan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtnamakaryawan.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Nama Karyawan");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel2)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel8))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txttanggal)
                    .addComponent(cbkaryawan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbpelanggan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtharga)
                    .addComponent(txtdiskon)
                    .addComponent(cbpembayaran, 0, 246, Short.MAX_VALUE)
                    .addComponent(txtid)
                    .addComponent(cblayanan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnsimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnedit, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtnamakaryawan, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtjenislayanan)
                            .addComponent(txtnamapelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                            .addComponent(txtmodelkendaraan))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnamakaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(cbkaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(cbpelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnsimpan)
                                    .addComponent(btnedit))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnhapus)
                                    .addComponent(btnbatal)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(cblayanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(txtdiskon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(cbpembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtnamapelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtmodelkendaraan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtjenislayanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Data Transaksi");

        txtcari.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btncari.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btncari.setText("Cari");
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });

        tabletrx.setModel(new javax.swing.table.DefaultTableModel(
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
        tabletrx.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabletrxMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabletrx);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btncari))
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncari))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        btnkembali.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnkembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/assets/icons/kembali.png"))); // NOI18N
        btnkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkembaliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnkembali)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(69, 69, 69)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnkembali))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 469, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        String id = txtid.getText().trim();
        String harga = txtharga.getText().trim();
        String cekSql = "SELECT id_transaksi FROM transaksi_cuci WHERE id_transaksi = ?";
        try {
            PreparedStatement cekStat = koneksi.prepareStatement(cekSql);
            cekStat.setString(1, id);
            ResultSet rs = cekStat.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "ID transaksi sudah digunakan.");
                txtid.requestFocus();
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengecek ID: " + e);
            return;
        }

        String sql = "INSERT INTO transaksi_cuci (id_transaksi, tanggal_transaksi, id_karyawan, id_pelanggan, id_layanan, harga, diskon, metode_pembayaran) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stat = koneksi.prepareStatement(sql);
            stat.setString(1, txtid.getText());
            stat.setString(2, txttanggal.getText());
            stat.setString(3, cbkaryawan.getSelectedItem().toString());
            stat.setString(4, cbpelanggan.getSelectedItem().toString());
            stat.setString(5, cblayanan.getSelectedItem().toString());
            stat.setDouble(6, Double.parseDouble(harga));
            stat.setString(7, txtdiskon.getText());
            stat.setString(8, cbpembayaran.getSelectedItem().toString());

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data transaksi berhasil disimpan.");
            kosong();
            txtid.requestFocus();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data transaksi gagal disimpan. " + e);
        }
        datatable();
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // TODO add your handling code here:
        try{
            String sql = "UPDATE transaksi_cuci set tanggal_transaksi=?, id_karyawan=?, id_pelanggan=?, id_layanan=?, harga=?, diskon=?, metode_pembayaran=? WHERE id_transaksi='"+txtid.getText()+"'";
            PreparedStatement stat = koneksi.prepareStatement(sql);
            stat.setString(1, txttanggal.getText());
            stat.setString(2, cbkaryawan.getSelectedItem().toString());
            stat.setString(3, cbpelanggan.getSelectedItem().toString());
            stat.setString(4, cblayanan.getSelectedItem().toString());
            stat.setString(5, txtharga.getText());
            stat.setString(6, txtdiskon.getText());
            stat.setString(7, cbpembayaran.getSelectedItem().toString());

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            kosong();
            txtid.requestFocus();
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
                JOptionPane.showMessageDialog(null, "ID Transaksi tidak boleh kosong.");
                txtid.requestFocus();
                return;
            }
            String sql = "DELETE FROM transaksi_cuci WHERE id_transaksi = ?";
            try {
                PreparedStatement stat = koneksi.prepareStatement(sql);
                stat.setString(1, id);

                int rowsAffected = stat.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus.");
                    kosong();
                    txtid.requestFocus();
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
        Date date = new Date();
        SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
        txttanggal.setText(tgl.format(date));
    }//GEN-LAST:event_btnbatalActionPerformed

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        // TODO add your handling code here:
        datatable();
    }//GEN-LAST:event_btncariActionPerformed

    private void btnkembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkembaliActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnkembaliActionPerformed

    private void tabletrxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabletrxMouseClicked
        // TODO add your handling code here:
        int bar = tabletrx.getSelectedRow();
        String a = model.getValueAt(bar, 0).toString();
        String b = model.getValueAt(bar, 1).toString();
        String c = model.getValueAt(bar, 2).toString();
        String d = model.getValueAt(bar, 3).toString();
        String e = model.getValueAt(bar, 4).toString();
        String f = model.getValueAt(bar, 5).toString();
        String g = model.getValueAt(bar, 6).toString();
        String h = model.getValueAt(bar, 7).toString();

        txtid.setText(a);
        txttanggal.setText(b);
        cbkaryawan.setSelectedItem(c);
        cbpelanggan.setSelectedItem(d);
        cblayanan.setSelectedItem(e);
        txtharga.setText(f);
        txtdiskon.setText(g);
        cbpembayaran.setSelectedItem(h);
    }//GEN-LAST:event_tabletrxMouseClicked

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
            java.util.logging.Logger.getLogger(FormTransaksiCuci.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormTransaksiCuci.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormTransaksiCuci.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTransaksiCuci.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormTransaksiCuci().setVisible(true);
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
    private javax.swing.JComboBox<String> cbkaryawan;
    private javax.swing.JComboBox<String> cblayanan;
    private javax.swing.JComboBox<String> cbpelanggan;
    private javax.swing.JComboBox<String> cbpembayaran;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabletrx;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtdiskon;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtjenislayanan;
    private javax.swing.JTextField txtmodelkendaraan;
    private javax.swing.JTextField txtnamakaryawan;
    private javax.swing.JTextField txtnamapelanggan;
    private javax.swing.JTextField txttanggal;
    // End of variables declaration//GEN-END:variables
}
