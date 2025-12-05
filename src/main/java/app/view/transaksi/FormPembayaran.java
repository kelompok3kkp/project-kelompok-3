/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.app.view.transaksi;

import main.java.app.database.Koneksi;
import java.sql.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import java.io.InputStream;

/**
 *
 * @author aliframadhan
 */
public class FormPembayaran extends javax.swing.JFrame {
    String tanggal;
    
    private Connection koneksi = new Koneksi().connect();
    private DefaultTableModel model;

    /**
     * Creates new form FormMembership
     */
    public FormPembayaran() {
        initComponents();
        
        jtanggal.setModel(new javax.swing.SpinnerDateModel());
        JSpinner.DateEditor tgl = new JSpinner.DateEditor(jtanggal, "yyyy-MM-dd");
        jtanggal.setEditor(tgl);
        jtanggal.setValue(new Date());
        
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
        
        cbtransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt){
                loadTransaksi();
            }
        }); 
    }
    
    protected void aktif(){
        txtharga.requestFocus();
    }
    
    protected void kosong(){
        txtid.setText(generateIdTrx());
        cbtransaksi.setSelectedItem(null);
        txtharga.setText("");
        txtdiskon.setText("");
        txttotal.setText("");
        jtanggal.setValue(new Date());
    }
    
    private String generateIdTrx(){
        String prefix = "TRX";
        String sql = "SELECT id_pembayaran FROM pembayaran ORDER BY id_pembayaran DESC LIMIT 1";
        try {
            Statement stat = koneksi.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                String lastId = rs.getString("id_pembayaran");
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
        Object[] Baris = {"ID Pembayaran", "Tanggal Transaksi", "ID Transaksi", "Total Harga", "Diskon (%)", "Metode Pembayaran", "Total Bayar"};
            model = new DefaultTableModel(null, Baris);
            String cariitem = txtcari.getText();
            
            try {
                String sql = "SELECT *FROM pembayaran WHERE id_pembayaran LIKE '%" + cariitem + "%' " 
                        + "OR tanggal_transaksi LIKE '%" + cariitem + "%' " 
                        + "OR id_transaksi LIKE '%" + cariitem + "%' " 
                        + "OR harga LIKE '%" + cariitem + "%' "
                        + "OR diskon LIKE '%" + cariitem + "%' "
                        + "OR metode_pembayaran LIKE '%" + cariitem + "%' "
                        +  "OR total_bayar LIKE '%" + cariitem + "%' "
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
            
            ResultSet rsTransaksi = stat.executeQuery("SELECT id_transaksi FROM isi");
            while (rsTransaksi.next()){
                cbtransaksi.addItem(rsTransaksi.getString("id_transaksi"));
            }
            rsTransaksi.close();
            
            stat.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal load data combobox: "+ e.getMessage());
        }
    }
    
    public void loadTransaksi() {
    try {
        Connection koneksi = Koneksi.connect();
        String idTransaksi = cbtransaksi.getSelectedItem().toString();

        String sql = "SELECT SUM(harga) AS total_harga FROM isi WHERE id_transaksi = ?";
        PreparedStatement ps = koneksi.prepareStatement(sql);
        ps.setString(1, idTransaksi);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            double totalHarga = rs.getDouble("total_harga");
            txtharga.setText(String.valueOf(totalHarga));
        } else {
            txtharga.setText("0");
        }

        rs.close();
        ps.close();
        koneksi.close();
    } catch (Exception e) {
    }
}
    
    private void hitung(){
        try {
            double hargaAwal = Double.parseDouble(txtharga.getText());
            int diskon = Integer.parseInt(txtdiskon.getText());
            double totalAkhir = hargaAwal - (hargaAwal * diskon / 100);
            
            txttotal.setText(String.valueOf(totalAkhir));
        } catch (Exception e) {
            if (!txtharga.getText().isEmpty()) {
            txttotal.setText(txtharga.getText());
        } else {
            txttotal.setText("0");
        }
        }
    }
    
    public void cetakStruk(String idBayar) {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("id_pembayaran", idBayar);
            System.out.println("Cetak id_pembayaran = " + idBayar);

            Connection conn = new Koneksi().connect();

            InputStream jrxml = getClass().getResourceAsStream("/main/java/app/view/report/Nota.jrxml");
            if (jrxml == null) {
                throw new RuntimeException("Nota.jrxml tidak ditemukan di classpath: /main/java/app/view/report/Nota.jrxml");
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(jrxml);
            JasperPrint jp = JasperFillManager.fillReport(jasperReport, params, conn);
            JasperViewer.viewReport(jp, false);

        } catch (Exception e) {
            Throwable root = e;
            while (root.getCause() != null) root = root.getCause();
            JOptionPane.showMessageDialog(
                this,
                "Gagal mencetak struk:\n" + e.getMessage() +
                (root != e ? ("\nRoot cause: " + root.getMessage()) : ""),
                "Error Cetak",
                JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbtransaksi = new javax.swing.JComboBox<>();
        txtharga = new javax.swing.JTextField();
        txtdiskon = new javax.swing.JTextField();
        cbpembayaran = new javax.swing.JComboBox<>();
        btnbatal = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        btnsimpan = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        txtid = new javax.swing.JTextField();
        jtanggal = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        txtcari = new javax.swing.JTextField();
        btncari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabletrx = new javax.swing.JTable();
        txttotal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnkembali = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("ID Pembayaran");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Tanggal Transaksi");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("ID Transaksi");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Total Harga");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Diskon (%)");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Metode Pembayaran");

        cbtransaksi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtharga.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtharga.setEnabled(false);

        txtdiskon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtdiskon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdiskonActionPerformed(evt);
            }
        });
        txtdiskon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdiskonKeyReleased(evt);
            }
        });

        cbpembayaran.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbpembayaran.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tunai", "Transfer" }));

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

        jtanggal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jtanggal.setModel(new javax.swing.SpinnerDateModel());
        jtanggal.setEnabled(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Transaksi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

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
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btncari)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncari))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                .addContainerGap())
        );

        txttotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Total Bayar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel2)
                            .addComponent(jLabel10))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbtransaksi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtid)
                            .addComponent(jtanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel3))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtdiskon)
                            .addComponent(txtharga)
                            .addComponent(cbpembayaran, 0, 246, Short.MAX_VALUE)
                            .addComponent(txttotal))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnsimpan)
                .addGap(18, 18, 18)
                .addComponent(btnedit, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jtanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(cbtransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
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
                            .addComponent(cbpembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsimpan)
                    .addComponent(btnedit)
                    .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbatal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(164, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(51, 255, 255));

        btnkembali.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnkembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/assets/icons/kembali.png"))); // NOI18N
        btnkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkembaliActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pembayaran Cuci");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnkembali)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(85, 85, 85))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnkembali)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        String id = txtid.getText().trim();
        String idStruk = txtid.getText();
        String harga = txtharga.getText().trim();
        String cekSql = "SELECT id_pembayaran FROM pembayaran WHERE id_pembayaran = ?";
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

        String sql = "INSERT INTO pembayaran (id_pembayaran, tanggal_transaksi, id_transaksi, harga, diskon, metode_pembayaran, total_bayar) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement stat = koneksi.prepareStatement(sql);
            stat.setString(1, txtid.getText());

            Date tanggal = (Date) jtanggal.getValue();
            SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
            stat.setString(2, tgl.format(tanggal));

            stat.setString(3, cbtransaksi.getSelectedItem().toString());
            stat.setDouble(4, Double.parseDouble(harga));
            stat.setString(5, txtdiskon.getText());
            stat.setString(6, cbpembayaran.getSelectedItem().toString());
            stat.setString(7, txttotal.getText());

            stat.executeUpdate();
            
            int response = JOptionPane.showConfirmDialog(this,
                    "Data transaksi berhasil disimpan.\nApakah Anda ingin mencetak struk?",
                    "Konfirmasi Penyimpanan",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            // Jika pengguna menekan tombol "Yes"
            if (response == JOptionPane.YES_OPTION) {
                cetakStruk(idStruk); // Panggil method cetak
            }
            
            JOptionPane.showMessageDialog(null, "Data transaksi berhasil disimpan.");
            kosong();
            txtid.requestFocus();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data transaksi gagal disimpan. " + e);
        }
        datatable();
        hitung();
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // TODO add your handling code here:
        try{
            Date tanggal = (Date) jtanggal.getValue();
            SimpleDateFormat tgl = new SimpleDateFormat("yyy-MM-dd");
            String tanggalFormatted = tgl.format(tanggal);
            String sql = "UPDATE pembayaran set id_transaksi=?, harga=?, diskon=?, metode_pembayaran=? WHERE id_pembayaran='"+txtid.getText()+"'";
            PreparedStatement stat = koneksi.prepareStatement(sql);
            stat.setString(1, cbtransaksi.getSelectedItem().toString());
            stat.setString(2, txtharga.getText());
            stat.setString(3, txtdiskon.getText());
            stat.setString(4, cbpembayaran.getSelectedItem().toString());

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            kosong();
            txtid.requestFocus();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data gagal diubah" +e);
        }
        datatable();
        hitung();
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
            String sql = "DELETE FROM pembayaran WHERE id_pembayaran = ?";
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
        jtanggal.setValue(new Date());
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

        txtid.setText(a);
        
        try {
            SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
            Date tanggal = tgl.parse(b);
            jtanggal.setValue(tanggal);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Format tanggal tidak valid: " + b);
        }
        
        cbtransaksi.setSelectedItem(c);
        txtharga.setText(d);
        txtdiskon.setText(e);
        cbpembayaran.setSelectedItem(f);
        txttotal.setText(g);
    }//GEN-LAST:event_tabletrxMouseClicked

    private void txtdiskonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdiskonActionPerformed
        // TODO add your handling code here:
        double xharga = Double.parseDouble(txtharga.getText());
        int diskon = Integer.parseInt(txtdiskon.getText());
        double xjumlah = xharga - (xharga * diskon / 100);
        txttotal.setText(String.valueOf(xjumlah));
    }//GEN-LAST:event_txtdiskonActionPerformed

    private void txtdiskonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdiskonKeyReleased
        // TODO add your handling code here:
        try {
            double xharga = Double.parseDouble(txtharga.getText());
            int diskon = Integer.parseInt(txtdiskon.getText());
            double xjumlah = xharga - (xharga * diskon / 100);
            txttotal.setText(String.valueOf(xjumlah));
        } catch (Exception e) {
            txttotal.setText("0");
        }
    }//GEN-LAST:event_txtdiskonKeyReleased

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
            java.util.logging.Logger.getLogger(FormPembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPembayaran().setVisible(true);
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
    private javax.swing.JComboBox<String> cbpembayaran;
    private javax.swing.JComboBox<String> cbtransaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jtanggal;
    private javax.swing.JTable tabletrx;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtdiskon;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
